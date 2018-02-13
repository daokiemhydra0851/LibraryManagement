package adminGUI;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import giaodien.*;

public class tabQLTT extends JFrame {
	
	static private Connection conn,conn2,conn3,conn4,conn5,conn6,connpass,connxoapass;
	static private ResultSet rs,rss,rss4,rss5;
	static private PreparedStatement stmt,stmt3,stmt6,stmtpass,stmtxoapass;
	static private Statement stmt2,stmt4,stmt5;
	static private JPanel total,p0,p1,p2,p3,p4,p5,p6,p7,p8,p9,p41,p42,p43,p44,p45;
	static private JLabel lTit,lID,lName,lBirth,lgender,lNgayCap,lS12,Spa1,Spa2;
	static private JTextField tID,tName,tBirth,tgender,tNgayCap,tS;
	static private JButton btn1,btn2,btn3,btn4,btn5;
	static private JTable bang;
	static private DefaultTableModel dt;
	static private JComboBox cbo,cbogt;
	static private int v;
	static void rst()
	{
		try{
			stmt2 = conn2.createStatement();
			rss=stmt2.executeQuery("select *from Thuthu ");
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
	static private void AddThemEvent()
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
				//tgender.setText(tg4);
				cbogt.setSelectedItem(bang.getValueAt(row,3) + "");
				tNgayCap.setText(tg5);
			}
		});
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql = "INSERT INTO Thuthu (ID_Thuthu,Ten_Thuthu,Ngaysinh_Thuthu,gender_Thuthu,NCap_Thuthu) VALUES(?,?,?,?,?)";
				String sqlpass = "INSERT INTO Thuthu_Pass (ID_Thuthu,Pass_Thuthu) VALUES(?,?)";
				try{
					stmt = conn.prepareStatement(sql);
					stmt.setString(1,tID.getText());
					stmt.setString(2,tName.getText());
					stmt.setString(3,tBirth.getText());
					stmt.setString(4,cbogt.getSelectedItem().toString());
					stmt.setString(5, tNgayCap.getText());
					
					stmt.executeUpdate();
					stmtpass = connpass.prepareStatement(sqlpass);
					stmtpass.setString(1, tID.getText());
					stmtpass.setString(2, tID.getText());
					stmtpass.executeUpdate();
					
					System.out.println("Thanh Cong :))");
					Vector<String> vec = new Vector<String>();
					vec.add(tID.getText());
					vec.add(tName.getText());
					vec.add(tBirth.getText());
					vec.add(cbogt.getSelectedItem().toString());
					vec.add(tNgayCap.getText());
					dt.addRow(vec);
					tID.setText("");
					tName.setText("");
					tBirth.setText("");
					//tgender.setText("");
					tNgayCap.setText("");
					
				}catch (Exception ex){}
				}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql = "DELETE FROM Thuthu WHERE ID_Thuthu = ?";
				String sqlxoapass = "DELETE FROM Thuthu_Pass WHERE ID_Thuthu = ?";
				try{
					stmt6 = conn6.prepareStatement(sql);
					stmt6.setString(1,tID.getText());
					
					
					stmt6.executeUpdate();
					
					stmtxoapass = connxoapass.prepareStatement(sqlxoapass);
					stmtxoapass.setString(1,tID.getText());
					
					
					stmtxoapass.executeUpdate();
					
					//System.out.println("Thanh Cong :))");
					Vector<String> vec = new Vector<String>();
					dt.removeRow(bang.getSelectedRow());
					tID.setText("");
					tName.setText("");
					tBirth.setText("");
					//tgender.setText("");
					tNgayCap.setText("");
					
				}catch (Exception ex){}
				}
		});
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//String sql = "INSERT INTO Thuthu (ID_Thuthu,Ten_Thuthu,Ngaysinh_Thuthu,gender_Thuthu,NCap_Thuthu) VALUES(?,?,?,?,?)";
				String sql1 = "UPDATE Thuthu SET Ten_Thuthu = ?,Ngaysinh_Thuthu = ?,gender_Thuthu = ?,NCap_Thuthu = ? WHERE ID_Thuthu = ?";
				try{ // chua Update ID
					stmt3 = conn3.prepareStatement(sql1);
					//stmt.setString(1,tID.getText());
					stmt3.setString(1,tName.getText());
					stmt3.setString(2,tBirth.getText());
					stmt3.setString(3,cbogt.getSelectedItem().toString());
					stmt3.setString(4,tNgayCap.getText());
					stmt3.setString(5,tID.getText());
					
					stmt3.executeUpdate();
					
					int ii = bang.getSelectedRow();
					dt.setValueAt(tName.getText(),ii,1);
					dt.setValueAt(tBirth.getText(),ii,2);
					dt.setValueAt(cbogt.getSelectedItem().toString(),ii,3);
					dt.setValueAt(tNgayCap.getText(),ii,4);
					
				}catch (Exception ex){}
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
						String sql = "SELECT * FROM Thuthu WHERE ID_Thuthu = " + tS.getText();
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
						 rss5 = stmt5.executeQuery("select *from Thuthu ");
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
	
	static JPanel addControls(){
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
		TitledBorder btt = new TitledBorder(tt,"Thông tin cá nhân của thủ thư: ");
		p4.setBorder(btt);
		
		Border tt1 = BorderFactory.createLineBorder(Color.RED);
		TitledBorder btt1 = new TitledBorder(tt1,"Danh sách thủ thư: ");
		p3.setBorder(btt1);
		lTit = new JLabel("QUẢN LÝ THỦ THƯ");
		lTit.setFont(new Font("Serif",Font.BOLD,30));
		p0.add(lTit);
		
		lID =      new JLabel("ID thủ thư   	      :");
		
		lName =    new JLabel("Tên thủ thư      :");
		lBirth =   new JLabel("Ngày sinh   	 		    :");
		lgender =    new JLabel("Giới tính         	       	 :");
		lNgayCap = new JLabel("Ngày cấp         :");
		lS12 	=  new JLabel("Tìm theo:");
		 Spa1 = new JLabel("  ");
		 Spa2 = new JLabel("  ");
		 lID.setPreferredSize(lName.getPreferredSize());
			lgender.setPreferredSize(lName.getPreferredSize());
			lBirth.setPreferredSize(lName.getPreferredSize());
			lNgayCap.setPreferredSize(lName.getPreferredSize());
		int sz = 18;
		tID = new JTextField(sz);
		tName = new JTextField(sz);
		tBirth = new JTextField(sz);
		//tgender = new JTextField(sz);
		String arrgt[] ={"Nam","Nữ"};
		//String arr[] = {"tên","ID"};
		 cbogt = new JComboBox(arrgt);
		tNgayCap = new JTextField(sz);
		tS = new JTextField(6);
		p41.add(lID);p41.add(tID);p4.add(p41);
		p42.add(lName);p42.add(tName);p4.add(p42);
		p43.add(lBirth);p43.add(tBirth);p4.add(p43);
		p44.add(lgender);p44.add(cbogt);p4.add(p44);
		p45.add(lNgayCap);p45.add(tNgayCap);p4.add(p45);
		
		ImageIcon addIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/them2.png");
		ImageIcon delIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/xoa2.png");
		ImageIcon upIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/up2.png");
		ImageIcon fiIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/tim2.png");
		
		btn1 = new JButton("Thêm thủ thư",addIma);
		btn2 = new JButton("  Xóa thủ thư 	",delIma);
		btn3 = new JButton("   Cập nhật    ",upIma);
		btn5 = new JButton("   Refresh bảng ");
		btn4 = new JButton("Tìm ");
		
		p5.add(btn1);p5.add(btn2);
		p5.add(btn3);p5.add(btn5);
		
		
		String arr[] ={"Tên thủ thư","ID thủ thư"};
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
		
		dt.addColumn("ID thủ thư");
		dt.addColumn("Tên thủ thư");
		dt.addColumn("Ngày sinh");
		dt.addColumn("Giới tính");
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
			connpass = ConnectionFunc.getConnection();
			connxoapass = ConnectionFunc.getConnection();
		}catch (Exception ex){}
		AddThemEvent();
		return total;
	}
}
