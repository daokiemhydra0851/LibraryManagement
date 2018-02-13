package giaodien;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import FT.ConnectionFunc;
import java.sql.*;
//import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class CuaSoThemDG extends JFrame {
	DefaultTableModel dt;
	JTable bang;
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement stmt;
	private JTextField TID;
	private JTextField TName;
	private JTextField TBirth;
	private JTextField TKhoa;
	private JButton btn1;
	private JButton btn2 ;
	private JButton btn3 ;
	public CuaSoThemDG(String Title){
		super(Title);	
		addControls1();
		try{
			conn = ConnectionFunc.getConnection();
		}catch (Exception ex){}
		AddThemEvent();
	}
	
	public void AddThemEvent()
	{
		btn1.addActionListener(new ActionListener() {
			
		//	@Override
			public void actionPerformed(ActionEvent e) {
				
				String sql = "INSERT INTO Docgia (ID_Docgia,Ten_Docgia,Ngaysinh_Docgia,Khoa_Docgia) VALUES(?,?,?,?)";
				try{
					stmt = conn.prepareStatement(sql);
					stmt.setString(1,TID.getText());
					stmt.setString(2,TName.getText());
					stmt.setString(3,TBirth.getText());
					stmt.setString(4,TKhoa.getText());
					stmt.executeUpdate();
					System.out.println("Thanh Cong :))");
					Vector<String> vec = new Vector<String>();
					vec.add(TID.getText());
					vec.add(TName.getText());
					vec.add(TBirth.getText());
					vec.add(TKhoa.getText());
					dt.addRow(vec);
					
				}catch (Exception ex){}
				}
		});
	}
	public void addControls1()
	{
		Container con = getContentPane();
		JPanel pn = new JPanel();
		pn.setBackground(Color.CYAN);
		pn.setLayout(new BoxLayout(pn,BoxLayout.Y_AXIS));
		
		
		
		JPanel pn1 = new JPanel();
		pn1.setLayout(new BoxLayout(pn1,BoxLayout.Y_AXIS));
		
		JPanel p1ID = new JPanel();
		JPanel p1Name = new JPanel();
		JPanel p1Birth = new JPanel();
		JPanel p1Khoa = new JPanel();
		p1ID.setLayout(new BoxLayout(p1ID,BoxLayout.X_AXIS));
		p1Name.setLayout(new BoxLayout(p1Name,BoxLayout.X_AXIS));
		p1Birth.setLayout(new BoxLayout(p1Birth,BoxLayout.X_AXIS));
		p1Khoa.setLayout(new BoxLayout(p1Khoa,BoxLayout.X_AXIS));
		
		
		
		JLabel lID = new JLabel("ID độc giả: ");
		JLabel  lName = new JLabel("Tên độc giả:  ");
		JLabel lBirth = new JLabel("Ngày sinh: " );
		JLabel lKhoa = new JLabel("Khóa: ");
		TID = new JTextField(15);
		TName = new JTextField(15);
		TBirth = new JTextField(15);
		TKhoa = new JTextField(15);
		p1ID.add(lID);p1ID.add(TID);
		p1Name.add(lName);p1Name.add(TName);
		p1Birth.add(lBirth);p1Birth.add(TBirth);
		p1Khoa.add(lKhoa);p1Khoa.add(TKhoa);
		pn1.add(p1ID);
		pn1.add(p1Name);
		pn1.add(p1Birth);
		pn1.add(p1Khoa);
		
		
		
		pn.add(pn1);
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new BoxLayout(pn2,BoxLayout.X_AXIS));
		 btn1 = new JButton("Thêm mới");
		 btn2 = new JButton("Cập nhật");
		 btn3 = new JButton("Xóa");
		pn2.add(btn1);
		pn2.add(btn2);
		pn2.add(btn3);
	
		pn.add(pn2);
	
		JPanel p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		
		dt=new DefaultTableModel();
		
		dt.addColumn("ID độc giả");
		dt.addColumn("Tên độc giả");
		dt.addColumn("Ngày sinh");
		dt.addColumn("Khóa");
		//String row1[] = {"1234","832813","NXBGD","8"};
		//dt.addRow(row1);
		bang = new JTable(dt);
		JScrollPane bangSc = new JScrollPane(
				bang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		p3.add(bangSc, BorderLayout.CENTER);
		pn.add(p3);
		
		con.add(pn);
	}
	public void RunCuaSoThemDG(){
		this.setSize(700,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	//	this.setResizable(false);
		this.setVisible(true);
	}

}
