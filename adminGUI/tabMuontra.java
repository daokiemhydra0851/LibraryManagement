package adminGUI;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import FT.ConnectionFunc;
import adminGUI.CSThemSLg;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;


import giaodien.*;

public class tabMuontra extends JFrame {
	static private JPanel total,p0,p1,p7,p6,p77,p66,p2;
	static private JLabel lTit,lS12,lS122;
	static private JTabbedPane tabtg;
	static private JButton btn4,btnn4;
	static private JComboBox cbo3,cboo3;
	static private JTextField tS,tSS;
	static private DefaultTableModel dt,dt2;
	static private JTable bang,bang2;
	static JPanel addPanel1(){	
		p1 = new JPanel();p1.setBackground(Color.CYAN);
		p7 = new JPanel();p7.setBackground(Color.CYAN);
		p6 = new JPanel();p6.setBackground(Color.CYAN);
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		p7.setLayout(new BoxLayout(p7,BoxLayout.X_AXIS));
		p1.add(p7);p1.add(p6);
		btn4 = new JButton("Tìm ");
		tS = new JTextField(6);
		String arr2[] = {"ID Mượn ","Ngày mượn","ID Đọc giả","ID Cuốn sách","ID thủ thư "};
		cbo3 = new JComboBox(arr2);
		lS12 	=  new JLabel("Tìm theo: ");
		p7.add(lS12);
		p7.add(cbo3);
		p7.add(tS);
		p7.add(btn4);
		
		dt=new DefaultTableModel();
		
		dt.addColumn("ID Mượn");
		dt.addColumn("Ngày mượn");
		dt.addColumn("ID Đọc giả");
		dt.addColumn("ID Cuốn sách");
		dt.addColumn("Thủ thư làm việc");
		
		bang = new JTable(dt);
		JScrollPane bangSc = new JScrollPane(
				bang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		p6.add(bangSc, BorderLayout.CENTER);
		Border tt1 = BorderFactory.createLineBorder(Color.RED);
		TitledBorder btt1 = new TitledBorder(tt1,"Danh sách các lần mượn: ");
		p6.setBorder(btt1);
		
		return p1;
	}
	static JPanel addPanel2(){	
		p2 = new JPanel();p2.setBackground(Color.CYAN);
		p77 = new JPanel();p77.setBackground(Color.CYAN);
		p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
		p77.setLayout(new BoxLayout(p77,BoxLayout.X_AXIS));
		p66 = new JPanel();p66.setBackground(Color.CYAN);
		p2.add(p77);p2.add(p66);
		btnn4 = new JButton("Tìm ");
		tSS = new JTextField(6); 
		String arr2[] = {"ID Trả ","Ngày trả","ID Đọc giả","ID Cuốn sách","ID thủ thư "};
		cboo3 = new JComboBox(arr2);
		lS122 	=  new JLabel("Tìm theo: ");
		p77.add(lS122);
		p77.add(cboo3);
		p77.add(tSS);
		p77.add(btnn4);
		
		dt2=new DefaultTableModel();
		
		dt2.addColumn("ID Trả");
		dt2.addColumn("Ngày trả");
		dt2.addColumn("ID Đọc giả");
		dt2.addColumn("ID Cuốn sách");
		dt2.addColumn("Thủ thư làm việc");
		
		bang2 = new JTable(dt2);
		JScrollPane bangSc = new JScrollPane(
				bang2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		p66.add(bangSc, BorderLayout.CENTER);
		Border tt1 = BorderFactory.createLineBorder(Color.RED);
		TitledBorder btt1 = new TitledBorder(tt1,"Danh sách các lần trả: ");
		p66.setBorder(btt1);
		
		return p2;
	}
	
	static public JPanel addControls1(){
		total = new JPanel();total.setBackground(Color.CYAN);
		p0 = new JPanel();p0.setBackground(Color.CYAN);
		total.setLayout(new BoxLayout(total,BoxLayout.Y_AXIS));
		total.add(p0);
		lTit = new JLabel("QUẢN LÝ MƯỢN TRẢ");
		lTit.setFont(new Font("Serif",Font.BOLD,30));
		p0.add(lTit);
		tabtg = new JTabbedPane();
		
		JPanel p1 = addPanel1();
		JPanel p2 = addPanel2();
		tabtg.add(p1,"Thông tin mượn");
		tabtg.add(p2, "Thông tin trả");
		total.add(tabtg);
		return total;
	}
}
