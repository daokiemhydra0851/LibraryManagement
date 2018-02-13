package giaodien;
//This is Fix
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import FT.ConnectionFunc;
import java.sql.*;
//import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class CuaSoThemDauSachFix extends JFrame {
	DefaultTableModel dt;
	JTable bang;
	private Connection conn,conn2,conn3,conn4;
	private ResultSet rs,rs2,rs4;
	private PreparedStatement stmt,stmt3;
	private Statement stmt2,stmt4;
	
	private JTextField TLanTB;
	private JTextField TSlg;
	private JTextField TVt;
	private JButton btn1;
	private JButton btn2 ;
	private JButton btn3 ;
	private int soLuongSachBefore;
	private int ok,soLuongSachNew;
	private String iddausach;
	public CuaSoThemDauSachFix(String Title,String tt1,String tt2,String tt3,String tt4){
		super(Title);	
		addControls1(tt1,tt2,tt3,tt4);
		try{
			conn = ConnectionFunc.getConnection();
			conn2 = ConnectionFunc.getConnection();
			conn3 = ConnectionFunc.getConnection();
			conn4 = ConnectionFunc.getConnection();
		}catch (Exception ex){}
		AddThemEvent(tt1,tt2,tt3,tt4);
		//AddThemEvent();
	}
	
	public void AddThemEvent(String tt1,String tt2,String tt3,String tt4)
	{
		
		// XU ly nut them moi
		btn1.addActionListener(new ActionListener() {
		//	@Override
			public void actionPerformed(ActionEvent e) {
				iddausach = tt1 ;
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
						stmt.setString(1,tt1);
						
						stmt.setString(2,tt2);
						stmt.setString(3,tt3);
						stmt.setString(4,tt4);
						
						stmt.executeUpdate();
				//		stmt.close();
					//	conn.close();
				}catch(Exception er){}
				}
						String sql2 = "SELECT COUNT(*)	AS count FROM CuonSach WHERE ID_DauSach = " + tt1 ;
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
						int IDds = Integer.parseInt(tt1 + "0000");				
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
							   	
							   	stmt3.setString(2, tt1);
							   	System.out.println("ok2");
							   	stmt3.setString(3,TLanTB.getText());
							   	stmt3.setString(4, TVt.getText());
							   	stmt3.executeUpdate();
							//   	stmt3.close();
							 //  	conn3.close();
							    
								System.out.println("Thanh Cong :))");
								Vector<String> vec = new Vector<String>();
								vec.add(TLanTB.getText());
								vec.add(TSlg.getText());
								vec.add(TVt.getText());
								dt.addRow(vec);
							}catch(Exception e1){}
						}
				}
			
		});
		
	}
	public void addControls1(String tt1,String tt2,String tt3,String tt4)
	{
		Container con = getContentPane();
		
		JPanel pn1 = new JPanel();
		pn1.setBackground(Color.CYAN);
		pn1.setLayout(new BoxLayout(pn1,BoxLayout.Y_AXIS));
		
		
		
		
		JPanel pnt = new JPanel();
		pnt.setLayout(new BoxLayout(pnt,BoxLayout.X_AXIS));
		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
		JLabel lID = new JLabel("ID đầu sách: ");
		JLabel  lName = new JLabel("Tên đầu sách:  ");
		JLabel lTeam = new JLabel("Nhóm tác giả: " );
		JLabel lNxb = new JLabel("Nhà xuất bản: ");
		p3.add(lID);
		p3.add(lName);
		p3.add(lTeam);
		p3.add(lNxb);
		pnt.add(p3);
		JPanel p4 = new JPanel();
		p4.setLayout(new BoxLayout(p4,BoxLayout.Y_AXIS));
		JLabel lID1 = new JLabel(tt1);
		JLabel  lName1 = new JLabel(tt2);
		JLabel lTeam1 = new JLabel(tt3);
		JLabel lNxb1 = new JLabel(tt4);
		p4.add(lID1);
		p4.add(lName1);
		p4.add(lTeam1);
		p4.add(lNxb1);
		pnt.add(p4);
		pn1.add(pnt);
		
		JLabel lLanTB = new JLabel("Lần tái bản: ");
		JLabel lSlg = new JLabel("Số lượng nhập thêm: ");
		JLabel lVt = new JLabel("Vị trí: ");
		TLanTB = new JTextField(15);
		TSlg = new JTextField(15);
		TVt = new JTextField(15);
		JPanel tu1 = new JPanel();
		JPanel tu2 = new JPanel();
		JPanel tu3 = new JPanel();
		tu1.setLayout(new BoxLayout(tu1,BoxLayout.X_AXIS));
		tu2.setLayout(new BoxLayout(tu2,BoxLayout.X_AXIS));
		tu3.setLayout(new BoxLayout(tu3,BoxLayout.X_AXIS));
		tu1.add(lLanTB);tu1.add(TLanTB);pn1.add(tu1);
		tu2.add(lSlg);tu2.add(TSlg);pn1.add(tu2);
		tu3.add(lVt);tu3.add(TVt);pn1.add(tu3);
		
		
		JPanel pn44 = new JPanel();
		pn44.setLayout(new BoxLayout(pn44,BoxLayout.X_AXIS));
		 btn1 = new JButton("Thêm mới");
		 btn2 = new JButton("Cập nhật");
		 btn3 = new JButton("Xóa");
		pn44.add(btn1);pn44.add(btn2);pn44.add(btn3);
		pn1.add(pn44);
		
	
		JPanel p33 = new JPanel();
		p33.setLayout(new BorderLayout());
		
		dt=new DefaultTableModel();
		

		dt.addColumn("Lần tái bản");
		dt.addColumn("Số lượng ");
		dt.addColumn("Vị trí");
		bang = new JTable(dt);
		JScrollPane bangSc = new JScrollPane(
				bang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		p33.add(bangSc, BorderLayout.CENTER);
		pn1.add(p33);
		
		con.add(pn1);
	}
	public void RunCuaSoThemDauSachFix(){
		this.setSize(700,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	//	this.setResizable(false);
		this.setVisible(true);
	}

}
