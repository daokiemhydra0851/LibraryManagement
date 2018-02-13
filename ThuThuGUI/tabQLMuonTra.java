package ThuThuGUI;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.util.Calendar;
import java.util.Date;
import java.sql.*;
import ThuThuGUI.PhieuMuon;
import java.awt.*;
import FT.ConnectionFunc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Vector;

import giaodien.*;
import quanlythuvien.io.PhieuMuon;

public class tabQLMuonTra extends JFrame {
	
	static private Connection conn,conn2,conn3,conn4,conn5,conn6,conntentt;
	static private ResultSet rs,rss,rss4,rss5,rsstentt;
	static private PreparedStatement stmt,stmt3,stmt6;
	static private Statement stmt2,stmt4,stmt5,stmttentt;
	static private JPanel total,p0,p1,p2,p3,p4,p5,p6,p7,p8,p9,p41,p42,p43,p44,p45;
	static private JLabel lTit,lID,lName,lBirth,lKhoa,lNgayCap,lS12,Spa1,Spa2;
	static private JTextField tID,tName,tBirth,tKhoa,tNgayCap,tS;
	static private JButton btn1,btn2,btn4,btn5;
	static private JTable bang;
	static private DefaultTableModel dt;
	static private JComboBox cbo;
	static private int v;
	static private String ten_thuthu;
	static void rst()
	{
		try{
			stmt2 = conn2.createStatement();
			rss=stmt2.executeQuery("select *from Docgia ");
	        if(rss!=null){
	        	dt.setNumRows(0); 
	                while(rss.next()){
	                    Vector <String> vtRow=new Vector();
	                    vtRow.add(rss.getString(1));
	                    vtRow.add(rss.getString(2));
	                    vtRow.add(rss.getString(3));
	                    vtRow.add(rss.getString(4));    
	                    vtRow.add(rss.getString(5));
	                    dt.addRow(vtRow);                
	            }
	        }
	        }catch (Exception ex){}
	}
	static private void AddThemEvent(String idthuthu)
	{
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rst();
				
			}	
		});
		rst();
		//int ii = bang.getSelectedRow();
		bang.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			
			public void mouseClicked(MouseEvent e){
				int row = bang.getSelectedRow();
				if (row == -1) return ;
				String tg1 = bang.getValueAt(row, 0)+ "";
				String tg2 = bang.getValueAt(row,1) + "";
				String tg3 = bang.getValueAt(row,2) + "";
				String tg4 = bang.getValueAt(row,3) + "";
				String tg5 = bang.getValueAt(row,4) + "";
				tID.setText(tg1);
				tName.setText(tg2);
				tBirth.setText(tg3);
				tKhoa.setText(tg4);
				tNgayCap.setText(tg5);
			}
		});
	
		String sql32 = "SELECT * FROM Thuthu WHERE ID_Thuthu = " + idthuthu;
		try{
			stmttentt = conntentt.createStatement();
			rsstentt = stmttentt.executeQuery(sql32);
			if(rsstentt!=null){
                while(rsstentt.next()){
                   ten_thuthu = rsstentt.getString(2);       
                   System.out.println(ten_thuthu);
                }
			}
		}catch (Exception ex){}
		btn1.addActionListener(new ActionListener() {// Thong tin muon
			public void actionPerformed(ActionEvent e) {
				
				PhieuMuon pm = new PhieuMuon("phiếu mượn ");


				Format formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = Calendar.getInstance().getTime();  
				String ngayhientai = formatter.format(date);


				pm.addcontrol( tName.getText(),tID.getText(),ten_thuthu,idthuthu,ngayhientai);
				pm.showWindow();
				
				
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhieuTra pt = new PhieuTra("phiếu trả ");


				Format formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = Calendar.getInstance().getTime();  
				String ngayhientai = formatter.format(date);


				pt.addcontrol( tName.getText(),tID.getText(),ten_thuthu,idthuthu,ngayhientai);
				pt.showWindow();

			}
		});
		
		
		cbo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				v = cbo.getSelectedIndex();
				
			}
		});
		System.out.println(v);
		btn4.addActionListener(new ActionListener () {
			 public void actionPerformed(ActionEvent e){
				 System.out.println(v);
				 if (v==1){
						String sql = "SELECT * FROM Docgia WHERE ID_Docgia = " + tS.getText();
						try{
							stmt4 = conn4.createStatement();
							
							rss4 = stmt4.executeQuery(sql);
							dt.setNumRows(0); 
							if(rss4!=null){
				                while(rss4.next()){
				                    Vector <String> vtRow=new Vector();
				                    vtRow.add(rss4.getString(1));
				                    vtRow.add(rss4.getString(2));
				                    vtRow.add(rss4.getString(3));
				                    vtRow.add(rss4.getString(4));    
				                    vtRow.add(rss4.getString(5));
				                    dt.addRow(vtRow);                
				                }
							}
						}catch (Exception ex){}
					}
				 else if (v==0) {
					
					 try{
					 stmt5 = conn5.createStatement();
					 rss5 = stmt5.executeQuery("select *from Docgia ");
					 if (rss5!=null){
						 dt.setNumRows(0);
						 while (rss5.next()){
							 String stri1 = rss5.getString(2);
							 String stri2 = tS.getText();
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
								 if (ok == 1){
									 Vector <String> vtRow=new Vector();
					                    vtRow.add(rss5.getString(1));
					                    vtRow.add(stri1);
					                    vtRow.add(rss5.getString(3));
					                    vtRow.add(rss5.getString(4));    
					                    vtRow.add(rss5.getString(5));
					                    dt.addRow(vtRow);              
								 }
							 }
							 
						 }
					 }
					 }catch (Exception ex){}
				 }
			 }
		});
	}
	
	static JPanel addControls(String idthuthu){
		total = new JPanel();total.setBackground(Color.CYAN);
		p1 = new JPanel();p1.setBackground(Color.CYAN);
		p0 = new JPanel();p0.setBackground(Color.CYAN);
		p2 = new JPanel();p2.setBackground(Color.CYAN);
		p3 = new JPanel();p3.setBackground(Color.CYAN);
		p4 = new JPanel();p4.setBackground(Color.CYAN);
		p5 = new JPanel();p5.setBackground(Color.CYAN);
		p6 = new JPanel();p6.setBackground(Color.CYAN);
		p7 = new JPanel();p7.setBackground(Color.CYAN);
		p8 = new JPanel();p8.setBackground(Color.CYAN);
		p9 = new JPanel();p9.setBackground(Color.CYAN);
		p41 = new JPanel();p41.setBackground(Color.CYAN);
		p42 = new JPanel();p42.setBackground(Color.CYAN);
		p43 = new JPanel();p43.setBackground(Color.CYAN);
		p44 = new JPanel();p44.setBackground(Color.CYAN);
		p45 = new JPanel();p45.setBackground(Color.CYAN);
		
		
		total.setLayout(new BoxLayout(total,BoxLayout.Y_AXIS));
		p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
		p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));
		p3.setLayout(new BorderLayout());
		p4.setLayout(new BoxLayout(p4,BoxLayout.Y_AXIS));
		p41.setLayout(new BoxLayout(p41,BoxLayout.X_AXIS));
		p42.setLayout(new BoxLayout(p42,BoxLayout.X_AXIS));
		p43.setLayout(new BoxLayout(p43,BoxLayout.X_AXIS));
		p44.setLayout(new BoxLayout(p44,BoxLayout.X_AXIS));
		p45.setLayout(new BoxLayout(p45,BoxLayout.X_AXIS));
		
		p5.setLayout(new BoxLayout(p5,BoxLayout.Y_AXIS));
		p6.setLayout(new BoxLayout(p6,BoxLayout.Y_AXIS));
		p7.setLayout(new BoxLayout(p7,BoxLayout.X_AXIS));
		p8.setLayout(new BoxLayout(p8,BoxLayout.X_AXIS));
		p9.setLayout(new BoxLayout(p9,BoxLayout.X_AXIS));
		total.add(p0);total.add(p1);total.add(p6);total.add(p3);
		p1.add(p5);p1.add(p4);//p1.add(p6);
		p2.add(p7);p2.add(p8);
		p6.add(p2);
		p6.add(p9);
		
		Border tt = BorderFactory.createLineBorder(Color.RED);
		TitledBorder btt = new TitledBorder(tt,"Thông tin cá nhân của đọc giả: ");
		p4.setBorder(btt);
		
		Border tt1 = BorderFactory.createLineBorder(Color.RED);
		TitledBorder btt1 = new TitledBorder(tt1,"Danh sách đọc giả: ");
		p3.setBorder(btt1);
		lTit = new JLabel("QUẢN LÝ MƯỢN TRẢ");
		lTit.setFont(new Font("Serif",Font.BOLD,30));
		p0.add(lTit);
		
		lID =      new JLabel("ID đọc giả   	      :");
		
		lName =    new JLabel("Tên đọc giả      :");
		lBirth =   new JLabel("Ngày sinh   	 		    :");
		lKhoa =    new JLabel("Khóa         	       	 :");
		lNgayCap = new JLabel("Ngày cấp         :");
		lS12 	=  new JLabel("Tìm theo:");
		 Spa1 = new JLabel("  ");
		 Spa2 = new JLabel("  ");
		 lID.setPreferredSize(lName.getPreferredSize());
			lKhoa.setPreferredSize(lName.getPreferredSize());
			lBirth.setPreferredSize(lName.getPreferredSize());
			lNgayCap.setPreferredSize(lName.getPreferredSize());
		int sz = 18;
		tID = new JTextField(sz);
		tName = new JTextField(sz);
		tBirth = new JTextField(sz);
		tKhoa = new JTextField(sz);
		tNgayCap = new JTextField(sz);
		tS = new JTextField(6);
		p41.add(lID);p41.add(tID);p4.add(p41);
		p42.add(lName);p42.add(tName);p4.add(p42);
		p43.add(lBirth);p43.add(tBirth);p4.add(p43);
		p44.add(lKhoa);p44.add(tKhoa);p4.add(p44);
		p45.add(lNgayCap);p45.add(tNgayCap);p4.add(p45);
		
		ImageIcon addIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/them2.png");
		ImageIcon delIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/xoa2.png");
		ImageIcon upIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/up2.png");
		ImageIcon fiIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/tim2.png");
		
		btn1 = new JButton("Thông tin mượn" );
		btn2 = new JButton("Thông tin trả" );
		btn5 = new JButton("   Refresh bảng ");
		btn4 = new JButton("Tìm ");
		
		p5.add(btn1);p5.add(btn2);p5.add(btn5);
		
		
		String arr[] ={"Tên đọc giả","ID đọc giả"};
		//String arr[] = {"tên","ID"};
		 cbo = new JComboBox(arr);
		 //cbo.setSelectedItem("Tên đọc ");
		//lS1 = new JLabel lS1("Tìm theo ");
		p2.add(Spa1);
		p2.add(lS12);
		p2.add(cbo);
		p2.add(tS);
		p2.add(btn4);
		p2.add(Spa2);
		
		
		dt=new DefaultTableModel();
		
		dt.addColumn("ID đọc giả");
		dt.addColumn("Tên đọc giả");
		dt.addColumn("Ngày sinh");
		dt.addColumn("Khóa");
		dt.addColumn("Ngày cấp");
		bang = new JTable(dt);
		JScrollPane bangSc = new JScrollPane(
				bang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		p3.add(bangSc, BorderLayout.CENTER);
		
		try{
			conn = ConnectionFunc.getConnection();
			conn2 = ConnectionFunc.getConnection();
			conn3 = ConnectionFunc.getConnection();
			conn4 = ConnectionFunc.getConnection();
			conn5 = ConnectionFunc.getConnection();
			conn6 = ConnectionFunc.getConnection();
			conntentt = ConnectionFunc.getConnection();
		}catch (Exception ex){}
		AddThemEvent(idthuthu);
		return total;
	}
}
/*
Pass ?

Muon tra button xoa

*/
