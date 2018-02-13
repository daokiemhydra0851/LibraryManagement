package DocgiaGUI;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.lang.*;
import FT.ConnectionFunc;
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

public class tabTracuusach extends JFrame {
	
	static private JTabbedPane tabtg;
	//static private CSThemSLg form1;
	static private Connection conn,conn2,conn3,conn4,conn5,conn6,conn7;
	static private ResultSet rss,rss2,rss3,rss4,rss5,rss6,rss7;

	static private Statement stmt,stmt2,stmt3,stmt4,stmt5,stmt7,stmt6;
	static private JPanel total,p0,p1,p4,p6,p7,p41,p42,p43;
	static private JLabel lTit ,lName,lTg,lCN;
	static private JTextField tName,tTg;
	static private JButton btntim,btnrst;
	static private JTable bang,bang2;
	
	static private JComboBox cbo ;
	static private DefaultTableModel dt,dtt;
	static private int cCN,cNXB,v,v2,solgbandau,solgdangmuon,solgdangmuon2,solgbandau2;
	static String arrCN[] ={
							"Mọi chuyên ngành",
							"Công ngh? thông tin",
							"Điện tử viễn thông",
							"C? khí ",
							"?i?n ",
							"Hóa học",
							"Vật lý kỹ thuật ",
							"Thời trang ",
							"Th?c ph?m ",
							"Ngoại ngữ ",
							" Khác"};
	
	static private int Ktra2xau(String stri1,String stri2){
		if (stri2.isEmpty()) return 1;
		int len1 =  stri1.length();
		 int len2 = stri2.length();
		 int ok = 0;
		 if (len1 >= len2){
			 for (int j = 0;j<=len1 - len2;j++){
				 if (stri2.equals(stri1.substring(j,j+len2))==true){
					 ok = 1;
					 break;
				 }
			 }
		 }
		 return ok;//1 la OK, 0 la khong OK
	}
	static private void AddRowTable(String sID,String sTendau, String sTg,String sCN,String sNXB)
	{
		Vector <String> vtRow=new Vector();
        vtRow.add(sTendau);
        vtRow.add(sTg);
        vtRow.add(sCN);
        vtRow.add(sNXB);
         solgbandau = 0; 
        
		try{
			stmt3 = conn3.createStatement();
			rss3 = stmt3.executeQuery("SELECT COUNT(*) AS count1 FROM CuonSach WHERE ID_DauSach = " + sID);
			if (rss3!=null){
				while(rss3.next()) solgbandau = rss3.getInt("count1");
			
			}
		}catch(Exception pp){}
		 solgdangmuon = 0;
		try{
			stmt4 = conn4.createStatement();
			String idcuonmin = sID + "0000";
			String idcuonmax = sID + "9999";
			
			rss4=stmt4.executeQuery("SELECT COUNT(*) AS count2 FROM MuonSach LEFT JOIN TraSach ON MuonSach.ID_Muon=TraSach.ID_Tra WHERE TraSach.ID_Tra IS NULL AND MuonSach.ID_CuonSach <= " + idcuonmax + " AND MuonSach.ID_CuonSach >= " + idcuonmin);
			if (rss4!=null){
				while(rss4.next()) solgdangmuon = rss4.getInt("count2");
			 
			}
		}catch(Exception pp){}
		vtRow.add(Integer.toString(solgbandau-solgdangmuon));//So luong sach Hien tai chua dc muon
		
		int lantbmax = 0;
		try{
			stmt5 = conn5.createStatement();
			rss5 = stmt5.executeQuery("SELECT max(LanTB) AS maxlantb FROM CuonSach WHERE ID_DauSach = " + sID);
			
			while(rss5.next()) lantbmax = rss5.getInt("maxlantb");
		}catch(Exception pp){}
		vtRow.add(Integer.toString(lantbmax));
		//Dem So luong ban dau tuong ung vs LanTB max
		  solgbandau2 = 0;
		 try{
				stmt6 = conn6.createStatement();
				rss6 = stmt6.executeQuery("SELECT COUNT(*)	AS count3 FROM CuonSach WHERE ID_DauSach = " + sID + " AND LanTB = "+ Integer.toString(lantbmax));
				 while(rss6.next()) solgbandau2 = rss6.getInt("count3");
				 
		 }catch(Exception pp){}
		 //Dem So luong da muon tuong ung vs LanTB max
		  solgdangmuon2 = 0;
			try{
				stmt7 = conn7.createStatement();
				String idcuonmin = sID + "0000"; //id cuon sach nho nhat
				String idcuonmax = sID + "9999";// id cuon sach lon nhat
				rss7=stmt7.
executeQuery("select COUNT(*) as count4 from MuonSach LEFT JOIN TraSach ON MuonSach.ID_Muon=TraSach.ID_Tra LEFT JOIN CuonSach ON MuonSach.ID_CuonSach=CuonSach.ID_CuonSach WHERE TraSach.ID_Tra IS NULL AND CuonSach.LanTB = " + lantbmax + " AND MuonSach.ID_CuonSach <= " + idcuonmax + " AND MuonSach.ID_CuonSach >=" + idcuonmin);
				 while(rss7.next()) solgdangmuon2 = rss7.getInt("count4");
				
			}catch(Exception pp){}
			//So luong sach Hien tai chua dc muon tuong ung vs LanTB max	
			vtRow.add(Integer.toString(solgbandau2-solgdangmuon2));
	        dt.addRow(vtRow);              

	}
	static private void rst()
	{
		try{
			stmt = conn.createStatement();
			rss=stmt.executeQuery("select *from DauSach ");
	        if(rss!=null){
	        	dt.setNumRows(0); 
	                while(rss.next()){
	               	 AddRowTable(rss.getString(1),rss.getString(2),rss.getString(3),rss.getString(4),rss.getString(5));                 
	            }
	        }
	        }catch (Exception ex){}
	}
	static private void AddThemEvent1()
	{
		rst();
		btnrst.addActionListener(new ActionListener () {
			 public void actionPerformed(ActionEvent e){
				 rst();
			 }
		});
		btntim.addActionListener(new ActionListener () {
			 public void actionPerformed(ActionEvent e){
				 try{
				 stmt2=conn2.createStatement();
				 rss2=stmt2.executeQuery("select *from DauSach ");
				 if (rss2!=null){
					 dt.setNumRows(0);
					 while (rss2.next()){
						 System.out.println(rss2.getString(2) +" lululululu");
						 System.out.println(tName.getText() +" lululululu");
						 	
						 int ok1 = Ktra2xau(rss2.getString(2),tName.getText());
						System.out.println(ok1 + "erererer");
						 if (ok1==0) continue;
						 //System.out.println(ok1 + "erererer");
						 int ok2 = Ktra2xau(rss2.getString(3),tTg.getText());
						 System.out.println(ok2 + "qwqwqwqw");
						 if (ok2==0) continue;
						 
						 v = cbo.getSelectedIndex();
						 System.out.println("hre" + v);
						 boolean ok3 = (v==0)||((v>0)&&(Ktra2xau(rss2.getString(4),arrCN[v])==1));
						 System.out.println(ok3);
						 if (ok3==true){
							 AddRowTable(rss2.getString(1),rss2.getString(2),rss2.getString(3),rss2.getString(4),rss2.getString(5));
						 }
						 
						 
					 }
				 }
				 }catch (Exception ex){}
			 }
		});	 
		
	}
	
	
	
	static public JPanel addControls(){
		total = new JPanel();total.setBackground(Color.CYAN);
		p0 = new JPanel();p0.setBackground(Color.CYAN);
		total.setLayout(new BoxLayout(total,BoxLayout.Y_AXIS));
		total.add(p0);
		lTit = new JLabel("TRA CỨU SÁCH");
		lTit.setFont(new Font("Serif",Font.BOLD,30));
		p0.add(lTit);
		
		p1 = new JPanel();p1.setBackground(Color.CYAN);
		
		p4 = new JPanel();p4.setBackground(Color.CYAN);
		p6 = new JPanel();p6.setBackground(Color.CYAN);
		p7 = new JPanel();p7.setBackground(Color.CYAN);
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		
		p4.setLayout(new BoxLayout(p4,BoxLayout.Y_AXIS));
		p6.setLayout(new BorderLayout());
		p41 = new JPanel();p41.setBackground(Color.CYAN);
		p42 = new JPanel();p42.setBackground(Color.CYAN);
		p43 = new JPanel();p43.setBackground(Color.CYAN);
		p41.setLayout(new BoxLayout(p41,BoxLayout.X_AXIS));
		p42.setLayout(new BoxLayout(p42,BoxLayout.X_AXIS));
		p43.setLayout(new BoxLayout(p43,BoxLayout.X_AXIS));
		p7.setLayout(new BoxLayout(p7,BoxLayout.X_AXIS));
		p1.add(p4);p1.add(p7);p1.add(p6);
		
		

		Border tt = BorderFactory.createLineBorder(Color.RED);
		TitledBorder btt = new TitledBorder(tt,"Thông tin đầu sách: ");
		p4.setBorder(btt);
		
		
		lName =    new JLabel("Nhập tên sách cần tìm: ");
		lTg =      new JLabel("Nhập tên tác giả cần tìm: ");
		lCN =      new JLabel("Chọn chuyên ngành cần tìm: ");
		lName.setPreferredSize(lCN.getPreferredSize());
		lTg.setPreferredSize(lCN.getPreferredSize());
		lCN.setPreferredSize(lCN.getPreferredSize());
		
		int sz = 30;
		tName = new JTextField(sz);
		tTg = new JTextField(sz);
		cbo = new JComboBox(arrCN);
		p41.add(lName);p41.add(tName);p4.add(p41);
		p42.add(lTg);p42.add(tTg);p4.add(p42);
		p43.add(lCN);p43.add(cbo);p4.add(p43);
		
		ImageIcon addIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/them2.png");
		ImageIcon delIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/xoa2.png");
		ImageIcon upIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/up2.png");
		ImageIcon fiIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/tim2.png");
		
		btntim = new JButton("Tìm kiếm",addIma);
		btnrst = new JButton("Refresh bảng",upIma);
		p7.add(btntim);
		p7.add(btnrst);
		dt=new DefaultTableModel();
		
		dt.addColumn("Tên đầu sách");
		dt.addColumn("Tên tác giả");
		dt.addColumn("Chuyên ngành");
		dt.addColumn("Nhà xuất bản");
		dt.addColumn("Số sách");
		dt.addColumn("Lần TB mới nhất");
		dt.addColumn("Số sách với lần TB mới nhất");
		bang = new JTable(dt);
		JScrollPane bangSc = new JScrollPane(
				bang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		p6.add(bangSc, BorderLayout.CENTER);
		Border tt1 = BorderFactory.createLineBorder(Color.RED);
		TitledBorder btt1 = new TitledBorder(tt1,"Danh sách đầu sách: ");
		p6.setBorder(btt1);
		total.add(p1);
		try{
			conn = ConnectionFunc.getConnection();
			conn2 = ConnectionFunc.getConnection();
			conn3 = ConnectionFunc.getConnection();
			conn4 = ConnectionFunc.getConnection();
			conn5 = ConnectionFunc.getConnection();
			conn6 = ConnectionFunc.getConnection();
			conn7 = ConnectionFunc.getConnection();
			
		}catch (Exception ex){}
		AddThemEvent1();
		return total;
	}
	
}
