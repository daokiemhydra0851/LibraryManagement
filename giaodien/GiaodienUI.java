package giaodien;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JEditorPane;
import javax.swing.JPasswordField;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;

public class GiaodienUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

	// Launch the application.
	
//	public static void main(String[] args) {
	//	EventQueue.invokeLater(new Runnable() {
	public void run() {
		try {
			GiaodienUI frame = new GiaodienUI();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		//});
	//}
	
	// Create the frame.
	 
	public GiaodienUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		
		//GiaodienUI gd1 = new GiaodienUI();
	      //gd1.run();
		JLabel lblNhapTenMember = new JLabel("Nhap Ten member");
		lblNhapTenMember.setBackground(Color.WHITE);
		lblNhapTenMember.setForeground(Color.RED);
		panel.add(lblNhapTenMember);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(100);
		panel.add(passwordField);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JToolBar toolBar = new JToolBar();
		panel_1.add(toolBar);
		
		JLabel lblDangNhap = new JLabel("Dang Nhap");
		lblDangNhap.setForeground(Color.BLUE);
		panel_1.add(lblDangNhap);
		
		JButton btnNewButton = new JButton("New button");
		panel_1.add(btnNewButton);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		
		JScrollBar scrollBar = new JScrollBar();
		panel_4.add(scrollBar);
		
		JList list = new JList();
		panel_3.add(list);
	}
	

}
