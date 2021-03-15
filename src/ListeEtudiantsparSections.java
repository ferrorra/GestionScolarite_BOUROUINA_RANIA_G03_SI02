
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class ListeEtudiantsparSections extends JFrame {
	

	private JPanel contentPane;
	private static JTable table;
	private JTextField textField;
	private static JTextField textField_1;
	private static JComboBox<String> comboBox;
	private ResultSet rs=null;
	private PreparedStatement ps=null;


	public static JComboBox<String> getComboBox() {
		return comboBox;
	}

	public static void setComboBox(JComboBox<String> comboBox) {
		ListeEtudiantsparSections.comboBox = comboBox;
	}

	public static JTextField getTextField_1() {
		return textField_1;
	}

	public static void setTextField_1(JTextField textField_1) {
		ListeEtudiantsparSections.textField_1 = textField_1;
	}

	public ListeEtudiantsparSections() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 126, 663, 535);
		contentPane.add(scrollPane);
		
		setTable(new JTable());
		scrollPane.setViewportView(getTable());
		
		textField = new JTextField();
		textField.setBounds(175, 46, 272, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		 comboBox = new JComboBox<String>();
		 comboBox.setMaximumRowCount(20);
		 comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
		 comboBox.setSelectedIndex(0);
		 comboBox.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
				try {
				
					String sql="select matricule, nom, prenom, groupe, codeS from etudiant where codeS = ? " ;
					ps=connection.getConn().prepareStatement(sql);
					ps.setString(1, comboBox.getSelectedItem().toString());
					rs=ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					if(comboBox.getSelectedItem().toString().equals("")) {
						try {
							String sqll="select matricule, nom, prenom, groupe, codeS from etudiant ";
							ps=connection.getConn().prepareStatement(sqll);
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
					}
					
				}catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
					
				}finally {
					try {
					
						rs.close();
						ps.close();

						textField_1.setText(String.valueOf(table.getRowCount()));
						
						
					}catch(Exception exx) {}
				}
		 	}
		 });
		comboBox.setBounds(628, 46, 146, 31);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Rechercher");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setBounds(25, 54, 140, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblFiltrer = new JLabel("Filtrer");
		lblFiltrer.setForeground(SystemColor.textHighlight);
		lblFiltrer.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFiltrer.setBounds(585, 11, 140, 23);
		contentPane.add(lblFiltrer);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(683, 619, 91, 31);
		contentPane.add(textField_1);
		
		JLabel lblTotal = new JLabel("Totale");
		lblTotal.setForeground(SystemColor.textHighlight);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotal.setBounds(683, 585, 91, 23);
		contentPane.add(lblTotal);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "matricule", "nom", "prenom"}));
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setMaximumRowCount(20);
		comboBox_1.setBounds(471, 46, 146, 31);
		contentPane.add(comboBox_1);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String sql;
				if(! comboBox_1.getSelectedItem().toString().equals("")) {
						 try {

							if(! comboBox.getSelectedItem().toString().equals("")) {
							 sql="select matricule, nom, prenom, groupe, codeS from etudiant where "+ comboBox_1.getSelectedItem().toString() + " LIKE ? AND codeS = ? " ;
							ps=connection.getConn().prepareStatement(sql);
							ps.setString(2, comboBox.getSelectedItem().toString());
							ps.setString(1, textField.getText()+'%');
							}else {
								 sql="select matricule, nom, prenom, groupe, codeS from etudiant where "+ comboBox_1.getSelectedItem().toString() + " LIKE ?" ;
									ps=connection.getConn().prepareStatement(sql);
									ps.setString(1, textField.getText()+'%');
							}
							rs=ps.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
							if(textField.getText().equals("")) {
				
								try {
									String sqll="select matricule, nom, prenom, groupe, codeS from etudiant ";
									ps=connection.getConn().prepareStatement(sqll);
									rs=ps.executeQuery();
									table.setModel(DbUtils.resultSetToTableModel(rs));
								}catch(Exception exx) {
									JOptionPane.showMessageDialog(null, exx.getMessage());
								}finally {
									try {
										rs.close();
										ps.close();
									}catch(Exception ex) {}
								}
							}
						
						
						}catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, ex.getMessage());
							
						}finally {
							try {
							
								rs.close();
								ps.close();
		
								textField_1.setText(String.valueOf(table.getRowCount()));
								
							}catch(Exception ex) {}
						}
				}
				
			}
		});
		setTitle("Liste des Etudiants");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Images/1595344305_44032.jpg")));
	}

	public static JTable getTable() {
		return table;
	}

	public static void setTable(JTable table) {
		ListeEtudiantsparSections.table = table;
	}
}
