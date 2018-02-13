package giaodien;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.JPanel;

public class CuaSo2 extends JFrame {
	
	DefaultTableModel dt;
	JTable Muon;
	JButton BThem,BXoa;
	public CuaSo2(String Title){
		super(Title);	
		addControls();
	}
	
	public void addControls()
	{
		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		pnMain.setBackground(Color.CYAN);
		//pnMain.setLayout(new FlowLayout());
		pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
		
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		JPanel p1ID = new JPanel();
		JPanel p1Name = new JPanel();
		JPanel p1Birth = new JPanel();
		p1ID.setLayout(new FlowLayout());
		p1Name.setLayout(new FlowLayout());
		p1Birth.setLayout(new FlowLayout());
		
		
		
		
		String t = "20140851";
		JLabel lID = new JLabel("ID độc giả: "  + t);
		
		String tt = "Dao Nguyen Duong";
		JLabel  lName = new JLabel("Tên độc giả:  " + tt);
		
		String ttt = "12/11/1996";
		JLabel lBirth = new JLabel("Ngày sinh: " + ttt);
		
		p1ID.add(lID);p1.add(p1ID);
		p1Name.add(lName);p1.add(p1Name);
		p1Birth.add(lBirth);p1.add(p1Birth);
		
		
		pnMain.add(p1);
		con.add(pnMain);
		
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		//JPanel pMuon = new JPanel();
		
		
		
		dt=new DefaultTableModel();
		Muon = new JTable(dt);
		dt.addColumn("ID đầu sách");
		dt.addColumn("ID cuốn sách");
		dt.addColumn("Nhà xuất bản");
		dt.addColumn("Lần tái bản");
		
		//Try to give some data :)
		String row1[] = {"1234","832813","NXBGD","8"};
		dt.addRow(row1);
		String row2[] = {"3282","8301928","NXBBK","5"};
		dt.addRow(row2);
		
		
		
		JScrollPane pMuon = new JScrollPane(
				Muon,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		p2.add(pMuon, BorderLayout.CENTER);
		pnMain.add(p2);
		
		JPanel p3= new JPanel();
		p3.setLayout(new FlowLayout());
		BThem = new JButton("Thêm");
		BXoa = new JButton("Xóa");
		p3.add(BThem);
		p3.add(BXoa);
		pnMain.add(p3);

	}
	public void RunCuaSo2(){
		this.setSize(500,800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//this.setResizable(false);
		this.setVisible(true);
	}

}
