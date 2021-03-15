import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window.Type;
import java.awt.Cursor;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class Paperasse extends JFrame {

	private static JPanel contentPane;
	private static JPanel panel;
	public static JPanel getPanel() {
		return panel;
	}

	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNom;
	private JLabel lblPrnom;
	private JLabel lblSection;
	private JLabel lblDate;
	private static JLabel mat;
	private static JLabel nom;
	private static JLabel prenom;
	private static JLabel section;
	private static JLabel date;



	public static JLabel getMat() {
		return mat;
	}

	public static void setMat(JLabel mat) {
		Paperasse.mat = mat;
	}

	public static JLabel getNom() {
		return nom;
	}

	public static void setNom(JLabel nom) {
		Paperasse.nom = nom;
	}

	public static JLabel getPrenom() {
		return prenom;
	}

	public static void setPrenom(JLabel prenom) {
		Paperasse.prenom = prenom;
	}

	public static JLabel getSection() {
		return section;
	}

	public static void setSection(JLabel section) {
		Paperasse.section = section;
	}

	public static JLabel getDate() {
		return date;
	}

	public static void setDate(JLabel date) {
		Paperasse.date = date;
	}



	public Paperasse() {
		setBounds(new Rectangle(3, 3, 3, 3));
		setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		setType(Type.POPUP);
		setTitle("Apper\u00E7u ");
		setFont(new Font("Arial Black", Font.PLAIN, 12));
		setForeground(SystemColor.activeCaptionText);
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 696, 795);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Images/1595344305_44032.jpg")));
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 680, 756);
		contentPane.add(scrollPane);
		
		 panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBackground(SystemColor.window);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Certificat de scolarit\u00E9");
		lblNewLabel.setBounds(131, 152, 442, 88);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 38));
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Matricule :");
		lblNewLabel_1.setBounds(104, 265, 117, 33);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel_1);
		
		lblNom = new JLabel("Nom  :");
		lblNom.setBounds(104, 309, 117, 33);
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNom);
		
		lblPrnom = new JLabel("Pr\u00E9nom  :");
		lblPrnom.setBounds(104, 354, 117, 33);
		lblPrnom.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblPrnom);
		
		lblSection = new JLabel("Section  :");
		lblSection.setBounds(104, 402, 117, 33);
		lblSection.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblSection);
		
		lblDate = new JLabel("Date  :");
		lblDate.setBounds(104, 446, 117, 33);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblDate);
		
		mat = new JLabel("mat");
		mat.setBounds(220, 265, 173, 33);
		panel.add(mat);
		
		nom = new JLabel("nom");
		nom.setBounds(201, 309, 173, 33);
		panel.add(nom);
		
		prenom = new JLabel("prenom");
		prenom.setBounds(201, 354, 173, 33);
		panel.add(prenom);
		
		section = new JLabel("section");
		section.setBounds(201, 402, 173, 33);
		panel.add(section);
		
		date = new JLabel("date");
		date.setBounds(189, 446, 173, 33);
		panel.add(date);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Paperasse.class.getResource("/Pictures/USTHB.png")));
		

		lblNewLabel_2.setBounds(10, 11, 188, 152);
		panel.add(lblNewLabel_2);
	}
}
