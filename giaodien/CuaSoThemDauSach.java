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

public class CuaSoThemDauSach extends JFrame {
	DefaultTableModel dt;
	JTable bang;
	private Connection conn,conn2,conn3,conn4;
	private ResultSet rs,rs2,rs4;
	private PreparedStatement stmt,stmt3;
	private Statement stmt2,stmt4;
	
	private JTextField TID;
	private JTextField TName;
	private JTextField TTeam;
	private JTextField TNxb;
	private JTextField TLanTB;
	private JTextField TSlg;
	private JTextField TVt;
	private JButton btn1;
	private JButton btn2 ;
	private JButton btn3 ;
	private int soLuongSachBefore;
	private int ok,soLuongSachNew;
	private String iddausach;
	public CuaSoThemDauSach(String Title){
		super(Title);	
		addControls1();
		try{
			conn = ConnectionFunc.getConnection();
			conn2 = ConnectionFunc.getConnection();
			conn3 = ConnectionFunc.getConnection();
			conn4 = ConnectionFunc.getConnection();
		}catch (Exception ex){}
		AddThemEvent();
		//AddThemEvent();
	}
	
	public void AddThemEvent()
	{
		btn1.addActionListener(new ActionListener() {
		//	@Override
			public void actionPerformed(ActionEvent e) {
				iddausach = TID.getText() ;
				String sql4 = "SELECT COUNT(*)	AS count1 FROM DauSach WHERE ID_DauSach = " + iddausach ;
				
				try{
					stmt4 = conn4.createStatement();
					rs4 = stmt4.executeQuery(sql4);
					
					 ok = 0;
					 while(rs4.next()) ok = rs4.getInt("count1");
					 System.out.println(ok + " hihp");
					 //stmt4.close();
					 //rs4.close();
					 //conn4.close();
				}catch(Exception pp){}
				String tg1 = TSlg.getText();
				soLuongSachNew = Integer.parseInt(tg1);
				if (ok==0){
				String sql = "INSERT INTO DauSach (ID_DauSach,Ten_DauSach,NhomTgia,NhaXuatBan) VALUES(?,?,?,?)";
				try{

						stmt = conn.prepareStatement(sql);
						stmt.setString(1,TID.getText());
						
						stmt.setString(2,TName.getText());
						stmt.setString(3,TTeam.getText());
						stmt.setString(4,TNxb.getText());
						
						stmt.executeUpdate();
				//		stmt.close();
					//	conn.close();
				}catch(Exception er){}
				}
						String sql2 = "SELECT COUNT(*)	AS count FROM CuonSach WHERE ID_DauSach = " + iddausach ;
						try{
							stmt2 = conn3.createStatement();  
							rs2 = stmt2.executeQuery(sql2);
							System.out.println("hihi");
							 soLuongSachBefore = 0;
							 while(rs2.next()) 
								 soLuongSachBefore = rs2.getInt("count");
							System.out.println(soLuongSachBefore);
						//	stmt2.close();
							//rs2.close();
						//	conn3.close();
						}catch(Exception eeee){}
						int IDds = Integer.parseInt(iddausach + "0000");				
						System.out.println(soLuongSachNew);
						System.out.println(soLuongSachBefore);
						System.out.println(IDds);
						for (int i = (1 + soLuongSachBefore);i<=soLuongSachNew+ soLuongSachBefore;i++){
						
							String sql3 = "INSERT INTO CuonSach (ID_CuonSach,ID_DauSach,LanTaiBan,VitriCuon) VALUES(?,?,?,?)";
							try{
							  	stmt3 = conn2.prepareStatement(sql3);
							  	
							  	int j = i + IDds;
							  	System.out.println(j);
							  	stmt3.setInt(1, j);
							   	
							   	stmt3.setString(2, iddausach);
							   	System.out.println("ok2");
							   	stmt3.setString(3,TLanTB.getText());
							   	stmt3.setString(4, TVt.getText());
							   	stmt3.executeUpdate();
							//   	stmt3.close();
							 //  	conn3.close();
							}catch(Exception e1){}
						}
				
				  
				System.out.println("Thanh Cong :))");
				Vector<String> vec = new Vector<String>();
				vec.add(TID.getText());
				vec.add(TName.getText());
				vec.add(TTeam.getText());
				vec.add(TNxb.getText());
				dt.addRow(vec);
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
		JPanel p1Team = new JPanel();
		JPanel p1Nxb = new JPanel();
		JPanel p1LanTB = new JPanel();
		JPanel p1Slg = new JPanel();
		JPanel p1Vt = new JPanel();
		p1ID.setLayout(new BoxLayout(p1ID,BoxLayout.X_AXIS));
		p1Name.setLayout(new BoxLayout(p1Name,BoxLayout.X_AXIS));
		p1Team.setLayout(new BoxLayout(p1Team,BoxLayout.X_AXIS));
		p1Nxb.setLayout(new BoxLayout(p1Nxb,BoxLayout.X_AXIS));
		p1LanTB.setLayout(new BoxLayout(p1LanTB,BoxLayout.X_AXIS));
		p1Slg.setLayout(new BoxLayout(p1Slg,BoxLayout.X_AXIS));
		p1Vt.setLayout(new BoxLayout(p1Vt,BoxLayout.X_AXIS));
		
		JLabel lID = new JLabel("ID đầu sách: ");
		JLabel  lName = new JLabel("Tên đầu sách:  ");
		JLabel lTeam = new JLabel("Nhóm tác giả: " );
		JLabel lNxb = new JLabel("Nhà xuất bản: ");
		JLabel lLanTB = new JLabel("Lần tái bản: ");
		JLabel lSlg = new JLabel("Số lượng nhập thêm: ");
		JLabel lVt = new JLabel("Vị trí: ");
		
		
		TID = new JTextField(15);
		TName = new JTextField(15);
		TTeam = new JTextField(15);
		TNxb = new JTextField(15);
		TLanTB = new JTextField(15);
		TSlg = new JTextField(15);
		TVt = new JTextField(15);
		p1ID.add(lID);p1ID.add(TID);
		p1Name.add(lName);p1Name.add(TName);
		p1Team.add(lTeam);p1Team.add(TTeam);
		p1Nxb.add(lNxb);p1Nxb.add(TNxb);
		p1LanTB.add(lLanTB);p1LanTB.add(TLanTB);
		p1Slg.add(lSlg);p1Slg.add(TSlg);
		p1Vt.add(lVt);p1Vt.add(TVt);
		
		pn1.add(p1ID);
		pn1.add(p1Name);
		pn1.add(p1Team);
		pn1.add(p1Nxb);
		pn1.add(p1LanTB);
		pn1.add(p1Slg);
		pn1.add(p1Vt);
		
		
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
		
		dt.addColumn("ID đầu sách");
		dt.addColumn("Tên đầu sách");
		dt.addColumn("Nhóm tác giả");
		dt.addColumn("Nhà xuất bản");
		dt.addColumn("Lần tái bản");
		dt.addColumn("Số lượng hiện tại");
		dt.addColumn("Vị trí");
		bang = new JTable(dt);
		JScrollPane bangSc = new JScrollPane(
				bang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		p3.add(bangSc, BorderLayout.CENTER);
		pn.add(p3);
		
		con.add(pn);
	}
	public void RunCuaSoThemDauSach(){
		this.setSize(700,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	//	this.setResizable(false);
		this.setVisible(true);
	}

}
