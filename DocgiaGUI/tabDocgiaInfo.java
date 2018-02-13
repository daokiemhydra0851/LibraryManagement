package DocgiaGUI;


import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

import java.awt.*;
import FT.ConnectionFunc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import giaodien.*;

public class tabDocgiaInfo extends JFrame {
	
	static private Connection conn,conn2,conn3,conn4,conn5;
	static private ResultSet rs,rss,rss2,rss3,rss4,rss5;
	static private PreparedStatement stmt3;
	static private Statement stmt,stmt2,stmt4,stmt5;
	static private JPanel p0,p1,p11,p3,p4,p5,p6,p7,p8,p9,p41,p42,p43,p44,p45,p31,p32,p33,p333;
	static private JLabel lTit,lID,lName,lBirth,lKhoa,lNgayCap,
							lID2,lName2,lBirth2,lKhoa2,lNgayCap2,lTieude,
							lPasscu,lPassmoi1,lPassmoi2;
	static private JTextField tPasscu,tPassmoi1,tPassmoi2;
	static private JButton btnPass,btnOK;
	static private String passcu,passmoi1,passmoi2;
	static private JTable bang;
	static private DefaultTableModel dt;

	static void rst(String id)
	{
		try{
			stmt4 = conn4.createStatement();
			
			rss4=stmt4.
executeQuery(" select MuonSach.ID_Muon,MuonSach.ID_Docgia,MuonSach.ID_CuonSach,MuonSach.NgayMuon from MuonSach LEFT JOIN TraSach ON MuonSach.ID_Muon=TraSach.ID_Tra WHERE TraSach.ID_Tra IS NULL AND ID_Docgia = " + id);
			// JOIN LEFT bang MUON SACH va BANG TRA SACH, tim ra dc ID Muon ma chua Tra ---> Sach Dang muon hien tai
			//Tim trong bang moi do ID = ID_Docgia
	        if(rss4!=null){
	        	dt.setNumRows(0); 
	                while(rss4.next()){
	                	stmt5=conn5.createStatement();
	                	String idcuon = rss4.getString(3);
	                	String iddau = Integer.toString(Integer.parseInt(idcuon) / 10000);
	                	System.out.println(iddau);
	                	rss5 = stmt5.executeQuery("select *from DauSach where ID_DauSach = " + iddau);
	                	if (rss5!=null){  
	                		while(rss5.next()){
			                    Vector <String> vtRow=new Vector();
			                    vtRow.add(idcuon);
			                    vtRow.add(rss5.getString(2));
			                    vtRow.add(rss4.getString(4));
			                    vtRow.add(rss5.getString(3));    
			                    vtRow.add(rss5.getString(5));
			                    dt.addRow(vtRow);
	                		}
	                	}
	            }
	        }
	        }catch (Exception ex){}
	}
	static JPanel addControls(String id){
		try{
			conn = ConnectionFunc.getConnection();
			conn2 = ConnectionFunc.getConnection();
			conn3 = ConnectionFunc.getConnection();
			conn4 = ConnectionFunc.getConnection();
			conn5 = ConnectionFunc.getConnection();
			 
		}catch (Exception ex){}
		p1 = new JPanel();p1.setBackground(Color.CYAN);
		p11 = new JPanel();p11.setBackground(Color.CYAN);
		p4 = new JPanel();p4.setBackground(Color.CYAN);
		p41 = new JPanel();p41.setBackground(Color.CYAN);
		p42 = new JPanel();p42.setBackground(Color.CYAN);
		p43 = new JPanel();p43.setBackground(Color.CYAN);
		p44 = new JPanel();p44.setBackground(Color.CYAN);
		p45 = new JPanel();p45.setBackground(Color.CYAN);
		
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		p11.setLayout(new BorderLayout());
		Font fontTieuDe1=new Font("arial", Font.BOLD, 20);
		p0=new JPanel();
		lTieude=new JLabel("          Thông tin cá nhân đọc giả");
		lTieude.setFont(fontTieuDe1);
		lTieude.setForeground(Color.BLUE);
		p1.add(lTieude);
		
	//	p1.add(p0);
		
		p4.setLayout(new BoxLayout(p4,BoxLayout.Y_AXIS));
		p41.setLayout(new BoxLayout(p41,BoxLayout.X_AXIS));
		p42.setLayout(new BoxLayout(p42,BoxLayout.X_AXIS));
		p43.setLayout(new BoxLayout(p43,BoxLayout.X_AXIS));
		p44.setLayout(new BoxLayout(p44,BoxLayout.X_AXIS));
		p45.setLayout(new BoxLayout(p45,BoxLayout.X_AXIS));
		
		lID =      new JLabel("ID đọc giả   	      :");
		lName =    new JLabel("             Tên đọc giả      :");
		lBirth =   new JLabel("             Ngày sinh   	 		    :");
		lKhoa =    new JLabel("        Khóa         	       	 :");
		lNgayCap = new JLabel("             Ngày cấp         :");
		
		lID2=new JLabel(id);
	//	String sql = "SELECT * FROM Docgia WHERE ID_Docgia = " + "1" + ";";
		p41.add(lID);p41.add(lID2);
		p42.add(lName);
		p43.add(lBirth);
		p44.add(lKhoa);
		p45.add(lNgayCap);
		String sql = "select *from Docgia WHERE ID_Docgia = " + id ;
		try{
			stmt = conn.createStatement();
			rss = stmt.executeQuery(sql);
			 
			if(rss!=null){
				 while(rss.next()){
		                lName2 = new JLabel(rss.getString(2));
		            //    System.out.println("1" + rss.getString(1));
		                lBirth2 = new JLabel(rss.getString(3));
		                lKhoa2= new JLabel(rss.getString(4));
		                lNgayCap2 = new JLabel(rss.getString(5));
		                p42.add(lName2);
		        		p43.add(lBirth2);
		        		p44.add(lKhoa2);
		        		p45.add(lNgayCap2);
		        		
				}
			}
		}catch (Exception ex){}

		
		p4.add(p41);p4.add(p42);p4.add(p43);p4.add(p44);p4.add(p45);
		p1.add(p4);
		btnPass = new JButton("Sửa mật khẩu đăng nhập");
		p1.add(btnPass);
		
		p3 = new JPanel();p3.setBackground(Color.CYAN);
		p3.setLayout(new BorderLayout());
		
		p333 = new JPanel();p333.setBackground(Color.CYAN);
		p333.setLayout(new BoxLayout(p333,BoxLayout.Y_AXIS));
		
		lPasscu =      new JLabel("Nhập mật khẩu cũ  	      :");
		lPassmoi1 =    new JLabel("Nhập mật khẩu mới      :");
		lPassmoi2 =    new JLabel("Xác nhận mật khẩu   	      :");
		tPasscu = new JTextField(15);
		tPassmoi1 = new JTextField(15);
		tPassmoi2 = new JTextField(15);
		p31 = new JPanel();p31.setBackground(Color.CYAN);
		p32 = new JPanel();p32.setBackground(Color.CYAN);
		p33 = new JPanel();p33.setBackground(Color.CYAN);
		p31.setLayout(new BoxLayout(p31,BoxLayout.X_AXIS));
		p32.setLayout(new BoxLayout(p32,BoxLayout.X_AXIS));
		p33.setLayout(new BoxLayout(p33,BoxLayout.X_AXIS));
		p31.add(lPasscu);p31.add(tPasscu);
		p32.add(lPassmoi1);p32.add(tPassmoi1);
		p33.add(lPassmoi2);p33.add(tPassmoi2);
		
		
		p333.add(p31);
		p333.add(p32);
		p333.add(p33);
		btnOK = new JButton("Xong");
		p333.add(btnOK);
		p3.add(p333, BorderLayout.NORTH);
		p333.setVisible(false);
		p1.add(p3);
		btnPass.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	                p333.setVisible(true);
	        }
	    });
		dt=new DefaultTableModel();
		
		dt.addColumn("ID cuốn sách mượn");
		dt.addColumn("Tên sách mượn");
		dt.addColumn("Ngày mượn");
		dt.addColumn("Nhóm tác giả");
		dt.addColumn("Nhà xuất bản");
		bang = new JTable(dt);
		JScrollPane bangSc = new JScrollPane(
				bang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		p11.add(bangSc, BorderLayout.CENTER);
		Border tt1 = BorderFactory.createLineBorder(Color.RED);
		TitledBorder btt1 = new TitledBorder(tt1,"Danh sách cuốn sách đang mượn: ");
		p11.setBorder(btt1);
		p1.add(p11);
		rst(id);
		
		btnOK.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	           p333.setVisible(false);
	           
	           String sql3 = "select *from Docgia_Pass WHERE ID_Docgia = " + id ;
	           try{
	   				stmt2 = conn2.createStatement();
	   				rss2 = stmt2.executeQuery(sql3);
	   				  
	   				if(rss2!=null){
	   					while(rss2.next()){
	   						passcu = rss2.getString(2);
	   						passmoi1 = tPassmoi1.getText();
	   			           passmoi2 = tPassmoi2.getText();
	   			        System.out.println("ccmnr	12367");  
	   						if (passcu.equals(tPasscu.getText())==true){
	   			        	   if (passmoi1.equals(passmoi2)==true){
	   			        		String sql2 = "UPDATE Docgia_Pass SET Pass_Docgia = ? WHERE ID_Docgia = ?";
	   			   				try{
	   			   					stmt3 = conn3.prepareStatement(sql2);
	   			   					//stmt.setString(1,tID.getText());
	   			   					stmt3.setString(1,passmoi1);
	   			   					stmt3.setString(2,id);
	   			   					stmt3.executeUpdate();
	   			   				    tPassmoi1.setText("");
	   			   				    tPassmoi2.setText("");
	   			   				    tPasscu.setText("");
	   			   				}catch (Exception ex){}
	   			        	   }   
	   			           }
	   					}
	   				}
	   		   }catch (Exception ex){}
	        }
	    });
		
		//AddThemEvent();
		return p1;
	}
}
