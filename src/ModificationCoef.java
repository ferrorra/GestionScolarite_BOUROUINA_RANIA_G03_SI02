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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ModificationCoef extends JFrame {
	private JPanel contentPane;
	private ResultSet rs=null;
	private PreparedStatement ps=null;
	private Connection cn = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;



	public ModificationCoef() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Modification du module ");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblNewLabel.setBounds(10, 11, 764, 85);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(161, 106, 274, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Rechercher");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {				
					String sql="select * from module where codeM = ? " ;
					ps=connection.getConn().prepareStatement(sql);
					ps.setString(1, textField.getText());
					rs=ps.executeQuery();
					if(rs.next())
					{
						textField_1.setText(rs.getString("libelleM"));
						textField_2.setText(rs.getString("coeff"));
					
					 
					
					}else {
						JOptionPane.showMessageDialog(null,"Le module n'existe pas");
					}
				
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
					 
				}finally {
					try {
						rs.close();
						ps.close();
					
					}catch(Exception ex) {}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setForeground(SystemColor.textHighlight);
		btnNewButton.setBounds(499, 111, 130, 28);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Code");
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(50, 107, 122, 32);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(256, 230, 274, 33);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(256, 313, 274, 33);
		contentPane.add(textField_2);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String sql="update module set libelleM = ? , coeff = ? where codeM = ?";
					try {
						ps=connection.getConn().prepareStatement(sql);
						ps.setString(1, textField_1.getText().toString());
						ps.setInt(2, Integer.parseInt(textField_2.getText()));
						ps.setString(3, textField.getText().toString());
					int result = ps.executeUpdate();	
					if(result !=0) {
						JOptionPane.showMessageDialog(null, "Modfication avec succès !");	
							dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Il y a un problème...");	
					}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null,"Un problème est survenu..." +  ex.getMessage());
					
					}
					finally {					
						try {						
							ps.close();
						}catch(Exception ex) {
						}
					}
			}
		});
		btnModifier.setForeground(SystemColor.textHighlight);
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModifier.setBounds(603, 269, 130, 28);
		contentPane.add(btnModifier);
		
		JLabel lblNom = new JLabel("Libell\u00E9");
		lblNom.setForeground(SystemColor.textHighlight);
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNom.setBounds(138, 231, 122, 32);
		contentPane.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Coefficient");
		lblPrenom.setForeground(SystemColor.textHighlight);
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrenom.setBounds(138, 313, 122, 32);
		contentPane.add(lblPrenom);
		setTitle("Changement des groupes / sections");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Images/1595344305_44032.jpg")));
	}

}
