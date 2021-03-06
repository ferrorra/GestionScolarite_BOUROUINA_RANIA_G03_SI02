import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

public class ChangementGS extends JFrame {

	private JPanel contentPane;
	private ResultSet rs=null;
	private PreparedStatement ps=null;
	private Connection cn = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;



	public ChangementGS() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Modification des informations de l'etudiant");
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
					String sql="select * from etudiant where matricule = ? " ;
					ps=connection.getConn().prepareStatement(sql);
					ps.setString(1, textField.getText());
					rs=ps.executeQuery();
					if(rs.next())
					{
						textField_1.setText(rs.getString("nom"));
						textField_2.setText(rs.getString("prenom"));
						textField_3.setText(rs.getString("groupe"));
					    textField_4.setText(rs.getString("codeS"));
					
					}else {
						JOptionPane.showMessageDialog(null,"L'Etudiant n'existe pas");
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
		
		JLabel lblNewLabel_1 = new JLabel("Matricule");
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
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(256, 394, 274, 33);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(256, 474, 274, 33);
		contentPane.add(textField_4);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String sql="update etudiant set nom = ? , prenom = ? , groupe = ?, codeS = ? where matricule = ?";
					try {
						ps=connection.getConn().prepareStatement(sql);
						ps.setString(1, textField_1.getText().toString());
						ps.setString(2, textField_2.getText().toString());
						ps.setString(3, textField_3.getText().toString());
						ps.setString(4, textField_4.getText().toString());			
						ps.setString(5, textField.getText().toString());
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
		btnModifier.setBounds(615, 395, 130, 28);
		contentPane.add(btnModifier);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setForeground(SystemColor.textHighlight);
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNom.setBounds(138, 231, 122, 32);
		contentPane.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setForeground(SystemColor.textHighlight);
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrenom.setBounds(138, 313, 122, 32);
		contentPane.add(lblPrenom);
		
		JLabel lblGroupe = new JLabel("Groupe");
		lblGroupe.setForeground(SystemColor.textHighlight);
		lblGroupe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGroupe.setBounds(138, 395, 122, 32);
		contentPane.add(lblGroupe);
		
		JLabel lblSection = new JLabel("Section");
		lblSection.setForeground(SystemColor.textHighlight);
		lblSection.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSection.setBounds(138, 474, 122, 32);
		contentPane.add(lblSection);
		setTitle("Changement des groupes / sections");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Images/1595344305_44032.jpg")));
	}

}
