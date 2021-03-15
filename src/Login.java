import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ResultSet rs=null;
	private PreparedStatement ps=null;
	private JPasswordField p;
	private int i = 0;
	

	/**
	 * Launch the application.
	 */
	public void authentification() {
		//int i = 0;
		try {
		    
			String sql="select * from utilisateur where nomUtilisateur  = ? and mdp = ?";
	
			ps= connection.getConn().prepareStatement(sql);
			ps.setString(1, textField.getText());
			ps.setString(2, p.getText());
			rs=ps.executeQuery();					
			if(rs.next())
			{
				JOptionPane.showMessageDialog(null,"Nom d'utilisateur et mot de passe correctes!");
				dispose();
				
				new Menu().setVisible(true);
				//////open menu window//////////////////////////////////////////////////////////////////////////////////////////
			    
			}
			else { 
			
				i = i+1;
				JOptionPane.showMessageDialog(null,"Allez vous inscrire d'abord ! , vous etes à la tentative numéro " +i);
				
			}
			rs.close();
			ps.close();

		}catch (Exception e)
		{
			
		JOptionPane.showMessageDialog(null,e.getMessage());
		
		}finally {
			if (i==3) 
			this.setVisible(false);
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
				    connection.cnx(); 
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());

				}
			}
		});
	}


	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Images/1595344305_44032.jpg"))); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 346);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
	
		textField.setBounds(257, 58, 283, 39);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(257, 97, 283, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(257, 181, 283, 2);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel = new JLabel("Nom d'utilisateur");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(10, 70, 165, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblMotDePasse = new JLabel("Mot De Passe");
		lblMotDePasse.setForeground(SystemColor.textHighlight);
		lblMotDePasse.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD | Font.ITALIC, 18));
		lblMotDePasse.setBounds(10, 142, 165, 27);
		contentPane.add(lblMotDePasse);
		
		JButton btnNewButton = new JButton("Connexion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
				authentification();
				
			}
		});
		btnNewButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		btnNewButton.setForeground(SystemColor.textHighlight);
		btnNewButton.setBounds(54, 240, 145, 39);
		contentPane.add(btnNewButton);
		
		p = new JPasswordField();
	
		p.setEchoChar('*');
		p.setBounds(257, 144, 283, 39);
		contentPane.add(p);
		
		JCheckBox pw = new JCheckBox("Montrer mot de passe");
		pw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pw.isSelected())
				{
					p.setEchoChar((char)0);					
				}else {
					p.setEchoChar('*');
				}
			}
		});
		pw.setBackground(SystemColor.window);
		pw.setFont(new Font("Tw Cen MT", Font.BOLD, 17));
		pw.setForeground(SystemColor.textHighlight);
		pw.setBounds(301, 190, 182, 23);
		contentPane.add(pw);
		
		JLabel lblNomInexistant = new JLabel("Nom inexistant");
		lblNomInexistant.setVisible(false);
		lblNomInexistant.setForeground(Color.RED);
		lblNomInexistant.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD | Font.ITALIC, 18));
		lblNomInexistant.setBounds(550, 58, 126, 27);
		contentPane.add(lblNomInexistant);
		textField.addKeyListener(new KeyAdapter() {   
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
				
					String sql="select * from utilisateur where nomUtilisateur = ?";
					ps=connection.getConn().prepareStatement(sql);
					ps.setString(1, textField.getText());
					rs= ps.executeQuery();
					if(! rs.next()) 
						lblNomInexistant.setVisible(true);
					else
						lblNomInexistant.setVisible(false);
							
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Une erreur s'est produite");
				}
			}
		});
		p.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				if(event.getKeyCode()==KeyEvent.VK_ENTER) {
					btnNewButton.doClick();
				}
			}
		});
	}
}
