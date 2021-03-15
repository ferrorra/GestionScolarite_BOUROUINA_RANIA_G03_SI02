import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NouveauModule extends JFrame {
	private ResultSet rs=null;
	private PreparedStatement ps=null;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	
	public String attribuerCode() {  //attribuer un code constitué de: la date + l'heure exacte
		Calendar cal= new GregorianCalendar();
		int day= cal.get(Calendar.DAY_OF_MONTH);
		int month= (cal.get(Calendar.MONTH))+1;
		int year= cal.get(Calendar.YEAR);				
		int hour= cal.get(Calendar.HOUR_OF_DAY);
		int minute= cal.get(Calendar.MINUTE);
		int second= cal.get(Calendar.SECOND);
		
		return new String(String.valueOf(day) + String.valueOf(month) + String.valueOf(year) + String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second));
		
	}
	   public Boolean verifierChamps() {
		if(textField.getText().isEmpty() ||textField_2.getText().isEmpty() ||textField_3.getText().isEmpty() ) return false;
		return true;
	}
	public Boolean verifierExistence(String codeM) {
		 String sql="select * from module where codeM = ?";
			try {
				ps=connection.getConn().prepareStatement(sql);		
				ps.setString(1,codeM);
			     rs = ps.executeQuery();		
			     if(rs.next()) {
				//le module existe
				return true;
			}else {
				//le module n'existe pas 
				return false;
			}
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null,"Un problème est survenu..." +  ex.getMessage());
				return false;
			
			}
			finally {					
				try {						
					ps.close();
				}catch(Exception ex) {
				}
			}
		
	}

	public NouveauModule() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ajouter un nouveau Module");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(10, 11, 729, 75);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();

		textField.setBounds(226, 123, 317, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
	
		textField_2.setColumns(10);
		textField_2.setBounds(226, 231, 317, 37);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();

		textField_3.setColumns(10);
		textField_3.setBounds(226, 339, 317, 37);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("Ajouter");
		
	
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Etes vous sur de vouloir ajouter ce module ?","Warning", dialogButton);
					if(dialogResult == 0){
				 String codeM = textField.getText() +textField_2.getText()+ attribuerCode();
				 String sql="insert into module values(?,?,?,?)";
				try {
				
					ps=connection.getConn().prepareStatement(sql);
					
					
					ps.setString(2, textField.getText().toString());
					ps.setInt(3,  Integer.parseInt(textField_2.getText()));        
					ps.setString(4, textField_3.getText().toString());
					ps.setString(1,codeM);
				
				
				int result = ps.executeUpdate();	
				      if(result > 0) {
					JOptionPane.showMessageDialog(null, "Inscription avec succès !");	
						dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Il y a un problème...");	
				}
			
				}catch(Exception ex) {
					if(! verifierExistence(codeM) ) {
						JOptionPane.showMessageDialog(null,"Ce module existe déja");
					}
				}
				finally {					
					try {						
						ps.close();
					}catch(Exception ex) {
					}
				}
				} 
					
			}
		
			
		});
		btnNewButton.setForeground(SystemColor.textHighlight);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(284, 562, 195, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Libell\u00E9");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1.setBounds(65, 134, 151, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPrenom = new JLabel("Coefficient");
		lblPrenom.setForeground(SystemColor.textHighlight);
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrenom.setBounds(65, 242, 151, 26);
		contentPane.add(lblPrenom);
		
		JLabel lblGroupe = new JLabel("Enseignant");
		lblGroupe.setForeground(SystemColor.textHighlight);
		lblGroupe.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGroupe.setBounds(65, 350, 151, 26);
		contentPane.add(lblGroupe);
		setTitle("Inscription de l'Etudiant");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Images/1595344305_44032.jpg")));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(verifierChamps()) btnNewButton.setEnabled(true);
				else btnNewButton.setEnabled(false);
			}
		});
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(verifierChamps()) btnNewButton.setEnabled(true);
				else btnNewButton.setEnabled(false);
			}
		});
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(verifierChamps()) btnNewButton.setEnabled(true);
				else btnNewButton.setEnabled(false);
			}
		});
	}

}
