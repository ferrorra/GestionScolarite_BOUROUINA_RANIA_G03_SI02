import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Inscription extends JFrame {
	private ResultSet rs=null;
	private PreparedStatement ps=null;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public Boolean verifierExistence(String matricule) {
		 String sql="select * from etudiant where matricule = ?";
			try {
				ps=connection.getConn().prepareStatement(sql);		
				ps.setString(1, matricule);
			     rs = ps.executeQuery();	
			 
			if(rs.next()) {
				//l'etudiant existe
				return true;
			}else {
				//l'etudiant n'existe pas 
			
				return false;
			}
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null,"Un problème est survenu..." +  ex.getMessage());
				ex.printStackTrace();
				return false;
			
			}
			finally {					
				try {						
					ps.close();
				}catch(Exception ex) {
				}
			}
		
	}
	
	   public Boolean verifierChamps() {
		if(textField.getText().isEmpty() || textField_1.getText().isEmpty()  ||textField_2.getText().isEmpty() ||textField_3.getText().isEmpty()  ||textField_4.getText().isEmpty()) return false;
		else {
			if(textField.getText().toString().length() != 10 ) return false;
		}
		return true;
	}

	public Inscription() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inscription d'un \u00E9tudiant");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(10, 11, 496, 75);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
	
		textField.setBounds(226, 123, 317, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();

		textField_1.setColumns(10);
		textField_1.setBounds(226, 196, 317, 37);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();

		textField_2.setColumns(10);
		textField_2.setBounds(226, 273, 317, 37);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();

		textField_3.setColumns(10);
		textField_3.setBounds(226, 356, 317, 37);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();

		textField_4.setColumns(10);
		textField_4.setBounds(226, 436, 317, 37);
		contentPane.add(textField_4);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setEnabled(false);
	
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				 int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Etes vous sur de vouloir ajouter cet Etudiant ?","Warning", dialogButton);
					if(dialogResult == 0){
				
		
				 String sql="insert into etudiant values(?,?,?,?,?)";
				try {
					
					ps=connection.getConn().prepareStatement(sql);
					ps.setString(1, textField.getText().toString());
					ps.setString(2, textField_1.getText().toString());
					ps.setString(3, textField_2.getText().toString());
					ps.setString(4, textField_3.getText().toString());
					ps.setString(5, textField_4.getText().toString());	
					
					
				                result = ps.executeUpdate();
				if(result > 0) {
					JOptionPane.showMessageDialog(null, "Inscription avec succès !");	
						dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Il y a un problème...");	
				}
					
				}catch(Exception ex) {
					if( verifierExistence(textField.getText().toString())) {
						JOptionPane.showMessageDialog(null, "Cet Etudiant existe déja");	
					
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
		
		JLabel lblNewLabel_1 = new JLabel("Matricule");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1.setBounds(65, 134, 151, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setForeground(SystemColor.textHighlight);
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNom.setBounds(65, 207, 151, 26);
		contentPane.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setForeground(SystemColor.textHighlight);
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrenom.setBounds(65, 284, 151, 26);
		contentPane.add(lblPrenom);
		
		JLabel lblGroupe = new JLabel("Groupe");
		lblGroupe.setForeground(SystemColor.textHighlight);
		lblGroupe.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGroupe.setBounds(65, 367, 151, 26);
		contentPane.add(lblGroupe);
		
		JLabel lblSection = new JLabel("Section");
		lblSection.setForeground(SystemColor.textHighlight);
		lblSection.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSection.setBounds(65, 447, 151, 26);
		contentPane.add(lblSection);
		setTitle("Inscription de l'Etudiant");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Images/1595344305_44032.jpg")));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(verifierChamps()) btnNewButton.setEnabled(true);
				else btnNewButton.setEnabled(false);
			}
		});
		textField_1.addKeyListener(new KeyAdapter() {
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
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(verifierChamps()) btnNewButton.setEnabled(true);
				else btnNewButton.setEnabled(false);
			}
		});
		
	}
}
