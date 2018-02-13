package ThuThuGUI;


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

public class tabThuThuInfo extends JFrame {
	
	static private Connection conn,conn2,conn3;
	static private ResultSet rs,rss,rss2,rss3;
	static private PreparedStatement stmt3;
	static private Statement stmt,stmt2;
	static private JPanel p0,p1,p3,p4,p5,p6,p7,p8,p9,p41,p42,p43,p44,p45,p31,p32,p33,p333;
	static private JLabel lTit,lID,lName,lBirth,lgender,lNgayCap,
							lID2,lName2,lBirth2,lgender2,lNgayCap2,lTieude,
							lPasscu,lPassmoi1,lPassmoi2;
	static private JTextField tPasscu,tPassmoi1,tPassmoi2;
	static private JButton btnPass,btnOK;
	static private String passcu,passmoi1,passmoi2;
	
	
	static private void AddThemEvent(String id)
	{
	}
	
	static JPanel addControls(String id){
		try{
			conn = ConnectionFunc.getConnection();
			conn2 = ConnectionFunc.getConnection();
			conn3 = ConnectionFunc.getConnection();
			 System.out.println("uip1234");
		}catch (Exception ex){}
		p1 = new JPanel();p1.setBackground(Color.CYAN);
		p4 = new JPanel();p4.setBackground(Color.CYAN);
		p41 = new JPanel();p41.setBackground(Color.CYAN);
		p42 = new JPanel();p42.setBackground(Color.CYAN);
		p43 = new JPanel();p43.setBackground(Color.CYAN);
		p44 = new JPanel();p44.setBackground(Color.CYAN);
		p45 = new JPanel();p45.setBackground(Color.CYAN);
		
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		Font fontTieuDe1=new Font("arial", Font.BOLD, 20);
		p0=new JPanel();
		lTieude=new JLabel("          Thông tin cá nhân thủ thư");
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
		
		lID =      new JLabel("ID thủ thư   	      :");
		lName =    new JLabel("             Tên thủ thư      :");
		lBirth =   new JLabel("             Ngày sinh   	 		    :");
		lgender =    new JLabel("        Giới tính         	       	 :");
		lNgayCap = new JLabel("             Ngày cấp         :");
		
		lID2=new JLabel(id);
	//	String sql = "SELECT * FROM Thuthu WHERE ID_Thuthu = " + "1" + ";";
		p41.add(lID);p41.add(lID2);
		p42.add(lName);
		p43.add(lBirth);
		p44.add(lgender);
		p45.add(lNgayCap);
		String sql = "select *from Thuthu WHERE ID_Thuthu = " + id ;
		try{
			stmt = conn.createStatement();
			rss = stmt.executeQuery(sql);
			 
			if(rss!=null){
				 while(rss.next()){
		                lName2 = new JLabel(rss.getString(2));
		            //    System.out.println("1" + rss.getString(1));
		                lBirth2 = new JLabel(rss.getString(3));
		                lgender2= new JLabel(rss.getString(4));
		                lNgayCap2 = new JLabel(rss.getString(5));
		                p42.add(lName2);
		        		p43.add(lBirth2);
		        		p44.add(lgender2);
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
		//p3.add(p31);p3.add(p32);p3.add(p33);
		btnOK = new JButton("Xong");
		p333.add(btnOK);
		p3.add(p333, BorderLayout.NORTH);
		p3.setVisible(false);
		p1.add(p3);
		btnPass.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	                p3.setVisible(true);
	        }
	    });
		btnOK.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	           p3.setVisible(false);
	           
	           String sql3 = "select *from Thuthu_Pass WHERE ID_Thuthu = " + id ;
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
	   							System.out.println("ccmnr123");  
	   			        	   if (passmoi1.equals(passmoi2)==true){
	   			        		 System.out.println("ccmnr");  
	   			        		String sql2 = "UPDATE Thuthu_Pass SET Pass_Thuthu = ? WHERE ID_Thuthu = ?";
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
