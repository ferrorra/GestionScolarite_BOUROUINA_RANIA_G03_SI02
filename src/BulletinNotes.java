import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BulletinNotes extends JFrame {

	private JPanel contentPane;
	private ResultSet rs=null;
	private PreparedStatement ps=null;
	private Connection cn = null;


	public BulletinNotes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Bulletins des notes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Images/1595344305_44032.jpg")));
	}

}
