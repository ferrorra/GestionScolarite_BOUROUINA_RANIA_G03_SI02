import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.UIManager;
import java.awt.Window.Type;
import javax.swing.ImageIcon;

public class Menu extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private Connection conn=null;
	private ResultSet rs=null;
	private PreparedStatement ps=null;
	private Connection cn = null;

	public void show(ArrayList<JButton> buttons) {
		show(buttons,true);
		}
	public void show(ArrayList<JButton> buttons,boolean S) {
		for(JButton i : buttons) {
			i.setVisible(S);
		}
		
	}
	
	public Menu() {
		ArrayList<JButton> mainButtons = new ArrayList<JButton>(),
				 MAJbuttons = new ArrayList<JButton>(), 
				 etatsButtons = new ArrayList<JButton>(), 
				 etudiants = new ArrayList<JButton>(), 
				 module = new ArrayList<JButton>(), 
				 examens = new ArrayList<JButton>();
		setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		setTitle("Menu Principal");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Images/1595344305_44032.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1025, 760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	    this.setUndecorated(true);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(372, 64, 283, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(372, 694, 283, 2);
		contentPane.add(separator_1);
		
		JButton btnNewButton = new JButton("Menu Principal");
	
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(455, 11, 120, 42);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("Quitter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login().setVisible(true);
			}
		});
		button.setBackground(new Color(255, 255, 255));
		button.setBounds(455, 707, 120, 42);
		contentPane.add(button);
		
		JButton btnEtudiant = new JButton("Etudiant");
		btnEtudiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show(etudiants);
			}
		});
		MAJbuttons.add(btnEtudiant);
		btnEtudiant.setForeground(Color.BLACK);
		btnEtudiant.setBackground(Color.WHITE);
		btnEtudiant.setBounds(119, 465, 120, 42);
		contentPane.add(btnEtudiant);
		
		JButton btnEtats = new JButton("Liste des \u00E9tudiants par section");
		btnEtats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ListeEtudiantsparSections().setVisible(true);
				cn=connection.cnx();
				try {
					String sql="select matricule, nom, prenom, groupe, codeS from etudiant ";
					ps=cn.prepareStatement(sql);
					rs=ps.executeQuery();
					ListeEtudiantsparSections.getTable().setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception exx) {
					JOptionPane.showMessageDialog(null, exx.getMessage());
				}finally {
					try {
						rs.close();
						ps.close();
						ListeEtudiantsparSections.getTextField_1().setText(String.valueOf(ListeEtudiantsparSections.getTable().getRowCount()));
					}catch(Exception ex) {}
				}
				
				try {

					String sql="select distinct codeS from etudiant";
					ps=cn.prepareStatement(sql);
					rs=ps.executeQuery();

					while(rs.next())
					{
						ListeEtudiantsparSections.getComboBox().addItem(rs.getString("codeS"));				
					}

				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
					ex.printStackTrace();
					 
				}finally {
					try {
						rs.close();
						ps.close();
					
					}catch(Exception ex) {}
				}
				
			}
		});
		etatsButtons.add(btnEtats);
		btnEtats.setForeground(Color.BLACK);
		btnEtats.setBackground(Color.WHITE);
		btnEtats.setBounds(731, 175, 204, 42);
		contentPane.add(btnEtats);
		
		JButton btnModule = new JButton("Module");
		btnModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show(module);
			}
		});
		MAJbuttons.add(btnModule);
		btnModule.setForeground(Color.BLACK);
		btnModule.setBackground(Color.WHITE);
		btnModule.setBounds(119, 181, 120, 42);
		contentPane.add(btnModule);
		
		JButton btnExamen = new JButton("Examen");
		btnExamen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show(examens);
			}
		});
		MAJbuttons.add(btnExamen);
		btnExamen.setForeground(Color.BLACK);
		btnExamen.setBackground(Color.WHITE);
		btnExamen.setBounds(119, 317, 120, 42);
		contentPane.add(btnExamen);
		
		JButton btnInscription = new JButton("Inscription");
		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Inscription().setVisible(true);
			}
		});
		etudiants.add(btnInscription);
		btnInscription.setForeground(Color.BLACK);
		btnInscription.setBackground(Color.WHITE);
		btnInscription.setBounds(321, 499, 166, 42);
		contentPane.add(btnInscription);
		
		JButton btnChangement = new JButton("Changement");
		btnChangement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangementGS().setVisible(true);
			}
		});
		etudiants.add(btnChangement);
		btnChangement.setForeground(Color.BLACK);
		btnChangement.setBackground(Color.WHITE);
		btnChangement.setBounds(321, 552, 166, 42);
		contentPane.add(btnChangement);
		
		JButton btnDpart = new JButton("D\u00E9part");
		btnDpart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Depart().setVisible(true);
			}
		});
		etudiants.add(btnDpart);
		btnDpart.setForeground(Color.BLACK);
		btnDpart.setBackground(Color.WHITE);
		btnDpart.setBounds(321, 605, 166, 42);
		contentPane.add(btnDpart);
		
		JButton btnNouveauModule = new JButton("Nouveau module");
		btnNouveauModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NouveauModule().setVisible(true);
			}
		});
		module.add(btnNouveauModule);
		btnNouveauModule.setForeground(Color.BLACK);
		btnNouveauModule.setBackground(Color.WHITE);
		btnNouveauModule.setBounds(321, 220, 166, 42);
		contentPane.add(btnNouveauModule);
		
		JButton btnModificationCoefficient = new JButton("Modification Coefficient");
		btnModificationCoefficient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModificationCoef().setVisible(true);
			}
		});
		module.add(btnModificationCoefficient);
		btnModificationCoefficient.setForeground(Color.BLACK);
		btnModificationCoefficient.setBackground(Color.WHITE);
		btnModificationCoefficient.setBounds(321, 273, 166, 42);
		contentPane.add(btnModificationCoefficient);
		
		JButton btnPparationExamen = new JButton("P\u00E9paration examen");
		btnPparationExamen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PreparationExamen().setVisible(true);
			}
		});
		examens.add(btnPparationExamen);
		btnPparationExamen.setForeground(Color.BLACK);
		btnPparationExamen.setBackground(Color.WHITE);
		btnPparationExamen.setBounds(321, 359, 166, 42);
		contentPane.add(btnPparationExamen);
		
		JButton btnSaisieNotes = new JButton("Saisie Notes");
		btnSaisieNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SaisieNotes().setVisible(true);
			}
		});
		examens.add(btnSaisieNotes);
		btnSaisieNotes.setForeground(Color.BLACK);
		btnSaisieNotes.setBackground(Color.WHITE);
		btnSaisieNotes.setBounds(321, 412, 166, 42);
		contentPane.add(btnSaisieNotes);
		
		JButton button_1 = new JButton("Etats");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show(etatsButtons);
			}
		});
		mainButtons.add(button_1);
		button_1.setForeground(Color.BLACK);
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(666, 77, 120, 42);
		contentPane.add(button_1);
		
		JButton btnMiseJour = new JButton("Mise \u00E0 jour");
		btnMiseJour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show(MAJbuttons);
			}
		});
		mainButtons.add(btnMiseJour);
		btnMiseJour.setForeground(Color.BLACK);
		btnMiseJour.setBackground(Color.WHITE);
		btnMiseJour.setBounds(243, 77, 120, 42);
		contentPane.add(btnMiseJour);
		
		JButton btnBulletin = new JButton("Bulletin des notes");
		btnBulletin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BulletinNotes().setVisible(true);
			}
		});
		etatsButtons.add(btnBulletin);
		btnBulletin.setForeground(Color.BLACK);
		btnBulletin.setBackground(Color.WHITE);
		btnBulletin.setBounds(731, 317, 204, 42);
		contentPane.add(btnBulletin);
		
		JButton btnCertificatDeScolarit = new JButton("Certificat de scolarit\u00E9");
		btnCertificatDeScolarit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CertificatDeScolarite().setVisible(true);
			}
		});
		etatsButtons.add(btnCertificatDeScolarit);
		btnCertificatDeScolarit.setForeground(Color.BLACK);
		btnCertificatDeScolarit.setBackground(Color.WHITE);
		btnCertificatDeScolarit.setBounds(731, 465, 204, 42);
		contentPane.add(btnCertificatDeScolarit);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show(mainButtons);
			}
		});
		show(mainButtons,false); 
				 show(MAJbuttons,false);  
				 show(etatsButtons,false);
				 show(etudiants,false);  
				 show(module,false);  
				 show(examens,false);
	}
}
