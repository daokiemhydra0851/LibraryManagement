package adminGUI;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.lang.*;
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

public class tabQLSach extends JFrame {
	
	static private JTabbedPane tabtg;
	//static private CSThemSLg form1;
	static private Connection coonnn,coonnn2,coonn2,conn,conn2,conn3,conn4,conn5,conn6,conn66,conn53,conn52,connn4,connn5,connn3;
	static private ResultSet rs,rss,rss4,rss5,rss53,rss52,rrss,rsss4,rsss3;
	static private PreparedStatement stmt,stmt3,stmt6,stmt66,stmttt,stmtt5,stmtt3;
	static private Statement stmt2,stmt4,stmt5,stmt52,stmt53,stmtt2,stmmt,stmtt4;
	static private JPanel total,p0,p1,p2,p3,p4,p5,p6,p7,p8,p9,p41,p42,p43,p44,p45,pp46,pp3,pp444,pp441,pp442,
	pp4,pp5,pp6,pp7,pp41,pp42,pp43,pp44,pp45;
	static private JLabel lTit,lID,lName,lTg,lNxb,lCN,lS12,lSS12 ,lSlg,
	                      llID1,llID2,llvitri,llNtb ,llG;
	static private JTextField tID,tName,tTg,tS,ttS
								,ttID1,ttID2,ttVitri,ttNtb,ttG,tSlg;
	static private JButton btn1,btn2,btn3,btn4,btnrst
							,btnn1,btnn2,btnn3,btnn4;
	static private JTable bang,bang2;
	static private JComboBox cbo,cbo1,cbo2,cbo3,cbo4,cbo5 ;
	static private DefaultTableModel dt,dtt;
	static private int cCN,cNXB,v,v2;
	static String arrCN[] ={"Công nghệ thông tin",
							"Điện tử viễn thông",
							"Cơ khí ",
							"Điện ",
							"Hóa học",
							"Vật lý kỹ thuật ",
							"Thời trang ",
							"Th?c ph?m ",
							"Ngoại ngữ ",
							" Khác"};
	static String arrNXB[] = {"NXB Bách Khoa HN",
							  "NXB Đại học quốc gia HN",
							  "NXB Kim Đồng",
							  "NXB Thống kê",
							  "NXB Trẻ",
							  "NXB Phụ nữ",
							  "NXB Giáo dục VN",
							  "NXB Thống kê"};
	static void rst()
	{
		try{
			stmt2 = conn2.createStatement();
			rss=stmt2.executeQuery("select *from DauSach ");
	        if(rss!=null){
	        	dt.setNumRows(0); 
	                while(rss.next()){
	                    Vector <String> vtRow=new Vector();
	                    vtRow.add(rss.getString(1));vtRow.add(rss.getString(2));
	                    vtRow.add(rss.getString(3));vtRow.add(rss.getString(4));    
	                    vtRow.add(rss.getString(5));
	                    dt.addRow(vtRow);                
	            }
	        }
	        }catch (Exception ex){}
	}
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
	static private void AddThemEvent1()
	{
		rst();
		// them nut reset bang
		btnrst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rst();
				
			}	
		});
		bang.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e){
				int row = bang.getSelectedRow();
				if (row == -1) return ;
				tID.setText(bang.getValueAt(row, 0)+ "");
				tName.setText(bang.getValueAt(row,1) + "");
				tTg.setText(bang.getValueAt(row,2) + "");
				cbo1.setSelectedItem(bang.getValueAt(row,3) + "");
				cbo2.setSelectedItem(bang.getValueAt(row,4) + "");
			}
		});
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql = "INSERT INTO DauSach (ID_DauSach,Ten_DauSach,NhomTgia,CNganh,NhaXuatBan) VALUES(?,?,?,?,?)";
				try{
					stmt = conn.prepareStatement(sql);
					stmt.setString(1,tID.getText());
					stmt.setString(2,tName.getText());
					stmt.setString(3,tTg.getText());
					stmt.setString(4, cbo1.getSelectedItem().toString());
					stmt.setString(5, cbo2.getSelectedItem().toString());
					stmt.executeUpdate();
					Vector<String> vec = new Vector<String>();
					vec.add(tID.getText());
					vec.add(tName.getText());
					vec.add(tTg.getText());
					vec.add(cbo1.getSelectedItem().toString());
					vec.add(cbo2.getSelectedItem().toString());
					dt.addRow(vec);
					tID.setText("");
					tName.setText("");
					tTg.setText("");
				}catch (Exception ex){}
				}
		});
		btn2.addActionListener(new ActionListener() { // xoa cuon ?
			public void actionPerformed(ActionEvent e) {
				
				String sql = "DELETE FROM DauSach WHERE ID_DauSach = ?";
				String sqll = "DELETE FROM CuonSach WHERE ID_DauSach = ?";
				try{
					//stmt66 = conn66.prepareStatement(sqll);
					//stmt66.setString(1,tID.getText());
				//	stmt66.executeUpdate();
					stmt6 = conn6.prepareStatement(sql);
					stmt6.setString(1,tID.getText());
					stmt6.executeUpdate();
					Vector<String> vec = new Vector<String>();
					dt.removeRow(bang.getSelectedRow());
					tID.setText("");
					tName.setText("");
					tTg.setText("");
					
				}catch (Exception ex){}
				}
		});
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//String sql = "INSERT INTO DauSach (ID_DauSach,Ten_DauSach,Nhomtg_DauSach,Khoa_DauSach,NCap_DauSach) VALUES(?,?,?,?,?)";
				String sql1 = "UPDATE DauSach SET Ten_DauSach = ?,Nhomtgia = ?,CNganh = ?,NhaXuatBan = ? WHERE ID_DauSach = ?";
				try{
					stmt3 = conn3.prepareStatement(sql1);
					//stmt.setString(1,tID.getText());
					stmt3.setString(1,tName.getText());
					stmt3.setString(2,tTg.getText());
					stmt3.setString(3, cbo1.getSelectedItem().toString());
					stmt3.setString(4, cbo2.getSelectedItem().toString());
					stmt3.setString(5,tID.getText());
					stmt3.executeUpdate();
					int ii = bang.getSelectedRow();
					dt.setValueAt(tName.getText(),ii,1);
					dt.setValueAt(tTg.getText(),ii,2);
					dt.setValueAt(cbo1.getSelectedItem().toString(),ii,3);
					dt.setValueAt(cbo2.getSelectedItem().toString(),ii,4);
				}catch (Exception ex){}
				}
		});
		
		cbo3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				v = cbo3.getSelectedIndex();
			}
		});
		System.out.println(v);
		btn4.addActionListener(new ActionListener () {
			 public void actionPerformed(ActionEvent e){
				 System.out.println(v);
				 if (v==0){
						String sql = "SELECT * FROM DauSach WHERE ID_DauSach = " + tS.getText();
						try{
							stmt4 = conn4.createStatement();
							rss4 = stmt4.executeQuery(sql);
							dt.setNumRows(0); 
							if(rss4!=null){
				                while(rss4.next()){
				                    Vector <String> vtRow=new Vector();
				                    vtRow.add(rss4.getString(1));vtRow.add(rss4.getString(2));
				                    vtRow.add(rss4.getString(3));vtRow.add(rss4.getString(4));    
				                    vtRow.add(rss4.getString(5));
				                    dt.addRow(vtRow);                
				                }
							}
						}catch (Exception ex){}
					}
				 else if (v==1) {// So sanh xau		 
						try{
							stmt5 = conn5.createStatement();
							rss5 = stmt5.executeQuery("SELECT * FROM DauSach ");
							 
							if(rss5!=null){
								dt.setNumRows(0);
				                while(rss5.next()){
				                	int ok = Ktra2xau(rss5.getString(2),tS.getText());
				                	if (ok ==0) continue;
				                    Vector <String> vtRow=new Vector();
				                    vtRow.add(rss5.getString(1));vtRow.add(rss5.getString(2));
				                    vtRow.add(rss5.getString(3));vtRow.add(rss5.getString(4));    
				                    vtRow.add(rss5.getString(5));
				                    dt.addRow(vtRow);                
				                }
							}
						}catch (Exception ex){}
				 }
				 else if (v==3) {
					 String sql = "SELECT * FROM DauSach WHERE CNganh = '" + tS.getText()+"'";
						try{
							stmt53 = conn53.createStatement();
							rss53 = stmt53.executeQuery(sql);
							 
							if(rss53!=null){
								dt.setNumRows(0);
				                while(rss53.next()){
				                    Vector <String> vtRow=new Vector();
				                    vtRow.add(rss53.getString(1));vtRow.add(rss53.getString(2));
				                    vtRow.add(rss53.getString(3));vtRow.add(rss53.getString(4));    
				                    vtRow.add(rss53.getString(5));
				                    dt.addRow(vtRow);                
				                }
							}
						}catch (Exception ex){}
				 }
				 else if (v==2) { // So sanh xau
						try{
							stmt52 = conn52.createStatement();
							rss52 = stmt52.executeQuery("SELECT * FROM DauSach ");
							if(rss52!=null){
								dt.setNumRows(0);
				                while(rss52.next()){
				                	int ok = Ktra2xau(rss52.getString(5),tS.getText());
				                	if (ok ==0) continue;
				                    Vector <String> vtRow=new Vector();
				                    vtRow.add(rss52.getString(1));vtRow.add(rss52.getString(2));
				                    vtRow.add(rss52.getString(3));vtRow.add(rss52.getString(4));    
				                    vtRow.add(rss52.getString(5));
				                    dt.addRow(vtRow);                
				                }
							}
						}catch (Exception ex){}
				 } 
			 }
		});
	}
	
	static JPanel addPanel1(){
		
		p1 = new JPanel();p1.setBackground(Color.CYAN);
		p3 = new JPanel();p3.setBackground(Color.CYAN);
		p4 = new JPanel();p4.setBackground(Color.CYAN);
		p5 = new JPanel();p5.setBackground(Color.CYAN);
		p6 = new JPanel();p6.setBackground(Color.CYAN);
		p7 = new JPanel();p7.setBackground(Color.CYAN);
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		p3.setLayout(new BoxLayout(p3,BoxLayout.X_AXIS));
		p4.setLayout(new BoxLayout(p4,BoxLayout.Y_AXIS));
		p5.setLayout(new BoxLayout(p5,BoxLayout.Y_AXIS));
		p6.setLayout(new BorderLayout());
		p41 = new JPanel();p41.setBackground(Color.CYAN);
		p42 = new JPanel();p42.setBackground(Color.CYAN);
		p43 = new JPanel();p43.setBackground(Color.CYAN);
		p44 = new JPanel();p44.setBackground(Color.CYAN);
		p45 = new JPanel();p45.setBackground(Color.CYAN);
		p41.setLayout(new BoxLayout(p41,BoxLayout.X_AXIS));
		p42.setLayout(new BoxLayout(p42,BoxLayout.X_AXIS));
		p43.setLayout(new BoxLayout(p43,BoxLayout.X_AXIS));
		p44.setLayout(new BoxLayout(p44,BoxLayout.X_AXIS));
		p45.setLayout(new BoxLayout(p45,BoxLayout.X_AXIS));
		p7.setLayout(new BoxLayout(p7,BoxLayout.X_AXIS));
		p1.add(p3);p1.add(p7);p1.add(p6);
		p3.add(p5);p3.add(p4);
		p4.add(p41);p4.add(p42);p4.add(p43);p4.add(p44);p4.add(p45);
		

		Border tt = BorderFactory.createLineBorder(Color.RED);
		TitledBorder btt = new TitledBorder(tt,"Thông tin đầu sách: ");
		p4.setBorder(btt);
		
		lID =      new JLabel("ID đầu sách: ");
		lName =    new JLabel("Tên sách: ");
		lTg =      new JLabel("Nhóm tác giả: ");
		lNxb =     new JLabel("Nhà xuất bản:   ");
		lCN =      new JLabel("Chuyên ngành: ");
		lID.setPreferredSize(lNxb.getPreferredSize());
		lName.setPreferredSize(lNxb.getPreferredSize());
		lTg.setPreferredSize(lNxb.getPreferredSize());
		lCN.setPreferredSize(lNxb.getPreferredSize());
		int sz = 30;
		tID = new JTextField(sz);
		tName = new JTextField(sz);
		tTg = new JTextField(sz);
		cbo1 = new JComboBox(arrCN);
		cbo2 = new JComboBox(arrNXB);
		p41.add(lID);p41.add(tID);p4.add(p41);
		p42.add(lName);p42.add(tName);p4.add(p42);
		p43.add(lTg);p43.add(tTg);p4.add(p43);
		p44.add(lCN);p44.add(cbo1);p4.add(p44);
		p45.add(lNxb);p45.add(cbo2);p4.add(p45);
		
		ImageIcon addIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/them2.png");
		ImageIcon delIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/xoa2.png");
		ImageIcon upIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/up2.png");
		ImageIcon fiIma = new ImageIcon("/home/duongnd/workspace/LibraryManagement/src/giaodien/tim2.png");
		
		btn1 = new JButton("Thêm đầu sách",addIma);
		btn2 = new JButton("  Xóa đầu sách 	",delIma);
		btn3 = new JButton("      Cập nhật    ",upIma);
		btnrst = new JButton("  Refresh bảng  "); 
		btn4 = new JButton("Tìm ");
		tS = new JTextField(6);
		p5.add(btn1);p5.add(btn2);
		p5.add(btn3);
		p5.add(btnrst);
		String arr2[] = {"ID Đầu sách","Tên đầu sách","NXB","Chuyên ngành"};
		cbo3 = new JComboBox(arr2);
		lS12 	=  new JLabel("Tìm theo: ");
		p7.add(lS12);
		p7.add(cbo3);
		p7.add(tS);
		p7.add(btn4);
		
		dt=new DefaultTableModel();
		
		dt.addColumn("ID đầu sách");
		dt.addColumn("Tên đầu sách");
		dt.addColumn("Nhóm tác giả");
		dt.addColumn("Chuyên ngành");
		dt.addColumn("Nhà xuất bản");
		
		bang = new JTable(dt);
		JScrollPane bangSc = new JScrollPane(
				bang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		p6.add(bangSc, BorderLayout.CENTER);
		Border tt1 = BorderFactory.createLineBorder(Color.RED);
		TitledBorder btt1 = new TitledBorder(tt1,"Danh sách đầu sách: ");
		p6.setBorder(btt1);
		
		try{
			conn = ConnectionFunc.getConnection();
			conn2 = ConnectionFunc.getConnection();
			conn3 = ConnectionFunc.getConnection();
			conn4 = ConnectionFunc.getConnection();
			conn5 = ConnectionFunc.getConnection();
			conn6 = ConnectionFunc.getConnection();
			conn52 = ConnectionFunc.getConnection();
			conn53 = ConnectionFunc.getConnection();
		}catch (Exception ex){}
		AddThemEvent1();
		
		return p1;
	}
	
	static private void AddThemEvent2(){
		try{
			stmtt2 = coonn2.createStatement();
			rrss=stmtt2.executeQuery("select *from CuonSach where ID_DauSach = " + ttID1.getText());
	        if(rrss!=null){
	                while(rss.next()){
	                    Vector <String> vtRow=new Vector();
	                    vtRow.add(rrss.getString(1));vtRow.add(rrss.getString(2));
	                    vtRow.add(rrss.getString(3));vtRow.add(rrss.getString(4));    
	                    vtRow.add(rrss.getString(5));
	                    dtt.addRow(vtRow);                
	            }
	        }
		}catch (Exception e) {}
		bang2.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e){
				int row = bang2.getSelectedRow();
				if (row == -1) return ;
				ttID1.setText(bang2.getValueAt(row, 0)+ "");
				ttID2.setText(bang2.getValueAt(row,1) + "");
				ttVitri.setText(bang2.getValueAt(row,2) + "");
				ttNtb.setText(bang2.getValueAt(row,3) + "");
				ttG.setText(bang2.getValueAt(row,4) + "");
			}
		});
		btnn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sqql = "select max(ID_CuonSach) as maxid from CuonSach where ID_DauSach = " + ttID1.getText();
				try{
					stmmt=coonnn.createStatement();
					ResultSet rsss11 = stmmt.executeQuery(sqql);
					int maxx =0;
					while (rsss11.next())
						maxx = rsss11.getInt("maxid");
					
					String solg =  tSlg.getText();
					if (maxx == 0) maxx = Integer.parseInt(ttID1.getText()) * 10000;
					for (int i = maxx + 1;i<=maxx + Integer.parseInt(solg);i++)
					{
						System.out.println(i);
						String sq3 = "INSERT INTO CuonSach (ID_CuonSach,ID_DauSach,Vitri,LanTB,Gia) VALUES(?,?,?,?,?)";
						try{
							stmttt = coonnn2.prepareStatement(sq3);
							stmttt.setInt(1,i);
							System.out.println(i);
							stmttt.setString(2,ttID1.getText());
							stmttt.setString(3,ttVitri.getText());
							stmttt.setString(4,ttNtb.getText());
							stmttt.setString(5,ttG.getText());
							stmttt.executeUpdate();
							// Can them nut refresh
							/*
							Vector<String> vec = new Vector<String>();
							vec.add(ttID1.getText());
							vec.add(Integer.toString(i));
							vec.add(ttVitri.getText());
							vec.add(ttNtb.getText());
							vec.add(ttG.getText());
							dtt.addRow(vec);
							ttID1.setText("");
							ttID2.setText("");
							ttVitri.setText("");
							ttNtb.setText("");
							ttG.setText(""); */
						}catch (Exception ex){}
						//}
					}
				}catch(Exception ex){}
			}
		});
		btnn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					stmtt3 = connn3.prepareStatement("UPDATE CuonSach SET Vitri = ?,LanTB = ?,Gia = ? WHERE ID_CuonSach = ?");
					//stmt.setString(1,tID.getText());
					stmtt3.setString(1,ttVitri.getText());
					
					stmtt3.setString(2,ttNtb.getText());
					stmtt3.setString(3,ttG.getText());
					stmtt3.setString(4,ttID2.getText());
					stmtt3.executeUpdate();
					int ii = bang2.getSelectedRow();
					dtt.setValueAt(ttID1.getText(),ii,0);
					dtt.setValueAt(ttID2.getText(),ii,1);
					dtt.setValueAt(ttVitri.getText(),ii,2);
					dtt.setValueAt(ttNtb.getText(),ii,3);
					dtt.setValueAt(ttG.getText(),ii,4);
				}catch (Exception ex){}
			}
		});
		btnn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					stmtt4 = connn4.createStatement();
					rsss4 = stmtt4.executeQuery("SELECT * FROM CuonSach WHERE ID_DauSach = " + ttS.getText());
					dtt.setNumRows(0); 
					if(rsss4!=null){
		                while(rsss4.next()){
		                    Vector <String> vtRow=new Vector();
		                    vtRow.add(ttS.getText());vtRow.add(rsss4.getString(1));
		                    vtRow.add(rsss4.getString(3));vtRow.add(rsss4.getString(4));    
		                    vtRow.add(rsss4.getString(5));
		                    dtt.addRow(vtRow);                
		                }
					}
				}catch (Exception ex){}
			}
		});	
		btnn2.addActionListener(new ActionListener() { // Xoa cuon
			public void actionPerformed(ActionEvent e) {
				try{
					stmtt5 = connn5.prepareStatement("DELETE FROM CuonSach WHERE ID_CuonSach = " + ttID2.getText());
					stmtt5.executeUpdate();
					Vector<String> vec = new Vector<String>();
					dtt.removeRow(bang2.getSelectedRow());
					ttID1.setText("");
					ttID2.setText("");
					ttVitri.setText("");
					ttNtb.setText("");
					ttG.setText("");
					tSlg.setText("");
					
				}catch (Exception ex){}
			}
		});	
	}
	static JPanel addPane2(){ // xet cai Cuonsach
		p2 = new JPanel();p2.setBackground(Color.CYAN);
		
		pp3 = new JPanel();pp3.setBackground(Color.CYAN);
		pp4 = new JPanel();pp4.setBackground(Color.CYAN);
		pp444 = new JPanel();pp444.setBackground(Color.CYAN);
		pp441 = new JPanel();pp441.setBackground(Color.CYAN);
		pp5 = new JPanel();pp5.setBackground(Color.CYAN);
		pp6 = new JPanel();pp6.setBackground(Color.CYAN);
		pp7 = new JPanel();pp7.setBackground(Color.CYAN);
		p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
		pp3.setLayout(new BoxLayout(pp3,BoxLayout.X_AXIS));
		
		
		pp4.setLayout(new BoxLayout(pp4,BoxLayout.Y_AXIS));
		pp444.setLayout(new BoxLayout(pp444,BoxLayout.Y_AXIS));
		pp441.setLayout(new BoxLayout(pp441,BoxLayout.X_AXIS));
		
		pp5.setLayout(new BoxLayout(pp5,BoxLayout.Y_AXIS));
		pp6.setLayout(new BorderLayout());
		
		pp41 = new JPanel();pp41.setBackground(Color.CYAN);
		pp42 = new JPanel();pp42.setBackground(Color.CYAN);
		pp43 = new JPanel();pp43.setBackground(Color.CYAN);
		pp44 = new JPanel();pp44.setBackground(Color.CYAN);
		pp45 = new JPanel();pp45.setBackground(Color.CYAN);
		pp46 = new JPanel();pp46.setBackground(Color.CYAN);
		pp41.setLayout(new BoxLayout(pp41,BoxLayout.X_AXIS));
		pp42.setLayout(new BoxLayout(pp42,BoxLayout.X_AXIS));
		pp43.setLayout(new BoxLayout(pp43,BoxLayout.X_AXIS));
		pp44.setLayout(new BoxLayout(pp44,BoxLayout.X_AXIS));
		pp45.setLayout(new BoxLayout(pp45,BoxLayout.X_AXIS));
		pp46.setLayout(new BoxLayout(pp46,BoxLayout.X_AXIS));
		
		pp7.setLayout(new BoxLayout(pp7,BoxLayout.X_AXIS));
		p2.add(pp3);p2.add(pp7);p2.add(pp6);
		pp3.add(pp5);pp3.add(pp444);
		pp4.add(pp41);pp4.add(pp42);pp4.add(pp43);pp4.add(pp44);pp4.add(pp45);
		pp444.add(pp4);pp444.add(pp441);

		Border tt = BorderFactory.createLineBorder(Color.RED);
		TitledBorder btt = new TitledBorder(tt,"Thông tin cuốn sách: ");
		pp4.setBorder(btt);
		
		llID1 =      new JLabel("ID đầu sách:");
		llID2 =    new JLabel("ID cuốn sách:");
		llvitri =      new JLabel("Vị trí:");
		llNtb =     new JLabel("Lần tái bản:");
		llG =      new JLabel("Giá:");
		lSlg = new JLabel("SLg thêm:"); 
		llID1.setPreferredSize(llID2.getPreferredSize());
		llvitri.setPreferredSize(llID2.getPreferredSize());
		llNtb.setPreferredSize(llID2.getPreferredSize());
		llG.setPreferredSize(llID2.getPreferredSize());
		lSlg.setPreferredSize(llID2.getPreferredSize());
		int sz1 = 30;
		ttID1 = new JTextField(sz1);
		ttID2 = new JTextField(sz1);
		ttVitri = new JTextField(sz1);
		ttNtb = new JTextField(sz1);
		ttG = new JTextField(sz1);
		//ttG.setEnabled(false);
		tSlg = new JTextField(sz1);
		//pp441.add(lSlg);//pp441.add(tSlg);
		/*cbo1 = new JComboBox(arrCN);
		cbo2 = new JComboBox(arrNXB);*/
		pp41.add(llID1);pp41.add(ttID1);pp4.add(pp41);
		pp42.add(llID2);pp42.add(ttID2);pp4.add(pp42);
		pp43.add(llvitri);pp43.add(ttVitri);pp4.add(pp43);
		pp44.add(llNtb);pp44.add(ttNtb);pp4.add(pp44);
		pp45.add(llG);pp45.add(ttG);pp4.add(pp45);
		pp46.add(lSlg);pp46.add(tSlg);pp4.add(pp46);
		ImageIcon addIma = new ImageIcon("/home/duongnd/workspace/THuNghiemCaiDa/src/giaodien/them2.png");
		ImageIcon delIma = new ImageIcon("/home/duongnd/workspace/THuNghiemCaiDa/src/giaodien/xoa2.png");
		ImageIcon upIma = new ImageIcon("/home/duongnd/workspace/THuNghiemCaiDa/src/giaodien/up2.png");
		ImageIcon fiIma = new ImageIcon("/home/duongnd/workspace/THuNghiemCaiDa/src/giaodien/tim2.png");
		
		btnn1 = new JButton("Thêm cuốn sách",addIma);
		btnn2 = new JButton("  Xóa cuốn sách 	",delIma);
		btnn3 = new JButton("      Cập nhật    ",upIma);
	//	btnnrst = new JButton("  Refresh bảng  ");
		btnn4 = new JButton("Tìm ");
		ttS = new JTextField(6);
		pp5.add(btnn1);
		pp5.add(btnn2);
		pp5.add(btnn3);
		//pp5.add(btnnrst);
		String arr22[] = {"ID Đầu sách","ID cuốn sách","Vị trí ","Lần tái bản","Giá"};
		
		lSS12 	=  new JLabel("Tìm theo ID đầu sách: ");
		pp7.add(lSS12);
		pp7.add(ttS);
		pp7.add(btnn4);
		
		dtt=new DefaultTableModel();
		
		dtt.addColumn("ID đầu sách");
		dtt.addColumn("ID cuốn sách");
		dtt.addColumn("Vị trí");
		dtt.addColumn("Lần tái bản");
		dtt.addColumn("Giá");
		
		bang2 = new JTable(dtt);
		JScrollPane bangSc2 = new JScrollPane(
				bang2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pp6.add(bangSc2, BorderLayout.CENTER);
		Border tt12 = BorderFactory.createLineBorder(Color.RED);
		TitledBorder btt12 = new TitledBorder(tt12,"Danh sách cuốn sách: ");
		pp6.setBorder(btt12);
		

		try{
			connn3 = ConnectionFunc.getConnection();
			connn4 = ConnectionFunc.getConnection();
			connn5 = ConnectionFunc.getConnection();
			coonn2 = ConnectionFunc.getConnection();
			coonnn = ConnectionFunc.getConnection();
			coonnn2 = ConnectionFunc.getConnection();
			
		}catch (Exception ex){} 
		AddThemEvent2();
		
		
		return p2;
	}
	
	
	static public JPanel addControls1(){
		total = new JPanel();total.setBackground(Color.CYAN);
		p0 = new JPanel();p0.setBackground(Color.CYAN);
		total.setLayout(new BoxLayout(total,BoxLayout.Y_AXIS));
		total.add(p0);
		lTit = new JLabel("QUẢN LÝ SÁCH");
		lTit.setFont(new Font("Serif",Font.BOLD,30));
		p0.add(lTit);
		tabtg = new JTabbedPane();
		
		JPanel p1 = addPanel1();
		JPanel p2 = addPane2();
		tabtg.add(p1,"Đầu sách");
		tabtg.add(p2, "Cuốn sách");
		total.add(tabtg);
		return total;
	}
}
/*
tab dausach:
	btn xoa: xoa all cuon ??
tab cuonsach:
*/
