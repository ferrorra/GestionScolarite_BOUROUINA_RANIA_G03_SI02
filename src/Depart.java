import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;

public class Depart extends JFrame {
	private ResultSet rs=null;
	private PreparedStatement ps=null;
	private JPanel contentPane;
	private JTextField textField;

	
	public void printIt(JPanel panel, double i, double j) {    //Impression de tous les composants d'un JPanel avec des paramétres de la taille voulue 
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setJobName("Impression");
		
		printerJob.setPrintable(new Printable() {
		//	@Override
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
				if(pageIndex > 0) {
					return Printable.NO_SUCH_PAGE;    // si l'index de la page n'est pas valide
				}
				Graphics2D graphics2D = (Graphics2D) graphics;
				graphics2D.translate(pageFormat.getImageableX()*2, pageFormat.getImageableY()*2);
				graphics2D.scale(i, j);
				panel.paint(graphics2D); // transformation du contenu de la JPanel en graphismes 2D imprimables
				return Printable.PAGE_EXISTS;
			}
		});
		boolean ReturningResults = printerJob.printDialog();
		if(ReturningResults) {
			try {
				printerJob.print();  //Impression
				
			}catch(Exception e){
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
		}
	
	}
	
	
	public String getCurrentDate() {  //Méthode pour avoir la date du jour
		Calendar cal= new GregorianCalendar();
		int day= cal.get(Calendar.DAY_OF_MONTH);
		int month= (cal.get(Calendar.MONTH))+1;
		int year= cal.get(Calendar.YEAR);
		
		return year+"-"+month+"-"+ day;
	} 
	
	public Depart() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 564, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("D\u00E9part d'un Etudiant");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(10, 11, 260, 43);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(133, 65, 339, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Matricule");
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 73, 101, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnImprimer = new JButton("Supprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				 int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Etes vous sur de vouloir supprimer cet Etudiant ?","Warning", dialogButton);
				if(dialogResult == 0){		
				String sql="delete from etudiant where matricule = ? ";
				try {
					ps=connection.getConn().prepareStatement(sql);
					ps.setString(1, textField.getText());				
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"L'Etudiant a bien été supprimé !");
					dispose();
				} catch (SQLException ex) {
				}
			}
					 
			}	
		});
		btnImprimer.setForeground(SystemColor.textHighlight);
		btnImprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnImprimer.setBounds(211, 137, 186, 30);
		contentPane.add(btnImprimer);
		setTitle("Etablissement des Certificats de scolarité");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Images/1595344305_44032.jpg")));
	}

}
