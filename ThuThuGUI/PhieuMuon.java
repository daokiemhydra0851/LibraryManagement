package ThuThuGUI;	

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import FT.ConnectionFunc;

public class PhieuMuon extends JFrame {
	
	 static JButton btn1,btn2,btn4,btn5,btnTong;
	 static Statement stmt2,stmt222,stmt2221,stmt2222,stmt22222,stmtmax,stmt31,stmt32,stmtsai1;
	 static PreparedStatement stmt,stmt3,stmt5;
	 static ResultSet rss2,rss222,rss2222,rss22222,rss2221,rss31,rss32,rsssai1;
	 static Connection conn, conn2, conn3,conn31,conn32,conn5,conn222,conn2221,connmax,conn2222,conn22222,connsai1;
	 static JButton btn3;
	static  JPanel  total, p0, p1, p2, p3, p4, p5, p6, p7,p8,p9;
	static JLabel lTong,lName1,lName2,lId1,lId2,llId1,llId2,llName1,llName2,lTieude,llIDcuon,lNgay;
	static  DefaultTableModel dt;
	static JTextField tTong,tidcuon;
	static  JTable bang;
	static String tgian1,tgian2,tgian3;
	static int RowPast,RowNow;
	ImageIcon icon;
	JScrollPane scrollPane;

	public  PhieuMuon (String tieude)
	{
		super (tieude);
	}
	private void Addevent( String ten, String id,String tentt,String idtt ,String ngay)
	 {
		 try{ // tao bang ban dau
				stmt2 = conn2.createStatement();
				rss2=stmt2.executeQuery("select *from MuonSach where ID_Docgia = " + id);
		        if(rss2!=null){
		        	dt.setNumRows(0); 
		                while(rss2.next()){
		                    Vector <String> vtRow=new Vector();
		                    vtRow.add(rss2.getString(1));
		                    String tg = rss2.getString(3);
		                    
		                    vtRow.add(tg);
		                    try{
		                    stmt222 = conn222.createStatement();
		                    rss222 = stmt222.executeQuery("select *from DauSach where ID_DauSach = " + Integer.toString((Integer.parseInt(tg)) / 10000));
		                    if (rss222!=null){
		                    	while (rss222.next()){
		                    		tgian3 = rss222.getString(2);
		                    	}
		                    }
		                    }catch (Exception ex1){}
		                    vtRow.add(tgian3);
		                    vtRow.add(rss2.getString(4));    
		                    vtRow.add(rss2.getString(5));
		                    try{
			                    stmt2221 = conn2221.createStatement();
			                    rss2221 = stmt2221.executeQuery("select *from CuonSach where ID_CuonSach = " + tg);
			                    if (rss2221!=null){
			                    	while (rss2221.next()){
			                    	vtRow.add(rss2221.getString(5)); // gia cuon sach
			                    	}
			                    }
			                }catch (Exception ex122){}
		                    vtRow.add("Xong");
		                    dt.addRow(vtRow);                
		            }
		        }
		}catch (Exception ex){}
		 RowPast = bang.getRowCount();
		// System.out.println("past1: " + RowPast * 10000);
		 bang.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				
				public void mouseClicked(MouseEvent e){
					int row = bang.getSelectedRow();
					if (row == -1) return ;
					String tg1 = bang.getValueAt(row, 1)+ "";
					tidcuon.setText(tg1);
				}
			});
		 btn1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String sql = "INSERT INTO MuonSach (ID_Muon,ID_Docgia,ID_CuonSach,NgayMuon,ID_Thuthu) VALUES(?,?,?,?,?)";
					try{
					int ok = 0;
					//Ktra
					stmtsai1 = connsai1.createStatement();
					int solgdangmuon = 0;
					rsssai1=stmtsai1.executeQuery("SELECT COUNT(*) AS count2 FROM MuonSach LEFT JOIN TraSach ON MuonSach.ID_Muon=TraSach.ID_Tra WHERE TraSach.ID_Tra IS NULL AND MuonSach.ID_CuonSach = " + tidcuon.getText());
					if (rsssai1!=null){
						while(rsssai1.next()) solgdangmuon = rsssai1.getInt("count2");
						if (solgdangmuon >0){ // Muon Sach dang dc muon roi
							 int output = JOptionPane.showConfirmDialog(null,"Sách đang được mượn, vui lòng nhập lại",""
						               ,JOptionPane.OK_CANCEL_OPTION); 
						     if(output == JOptionPane.OK_CANCEL_OPTION){
						            	JOptionPane.getRootFrame().dispose();
						     }
							ok =1;	
						}
					}
					
					
					if (ok ==0) //TMDK
					{
						String sqql = "select max(ID_Muon) as maxid from MuonSach where ID_Docgia = " + id;
						long maxx =0;
						try{
							stmtmax=connmax.createStatement();
							ResultSet rssmax = stmtmax.executeQuery(sqql);
							while (rssmax.next())
								maxx = rssmax.getLong("maxid");
							if (maxx == 0) maxx = Long.parseLong(id) * 1000;
							stmt = conn.prepareStatement(sql);
							stmt.setString(1,Long.toString(maxx + 1));
							//stmt.setString(1,Integer.toString(123));
							System.out.println(maxx + " "+ id + " " + Long.toString(maxx + 1));
							stmt.setString(2,id);
							stmt.setString(3,tidcuon.getText());
					
							stmt.setString(4,ngay);
					
							stmt.setString(5, idtt);
							stmt.executeUpdate();
						}catch(Exception ex){}
				
						Vector<String> vec = new Vector<String>();
						vec.add(Long.toString(maxx+1));
						vec.add(tidcuon.getText());
						
						try{
		                    stmt2222 = conn2222.createStatement();
		                    
		                    rss2222 = stmt2222.executeQuery("select *from DauSach where ID_DauSach = " + Integer.toString((Integer.parseInt(tidcuon.getText())) / 10000));
		                    
		                    if (rss2222!=null){
		                    	while (rss2222.next()){
		                    	tgian1 = rss2222.getString(2);
		                    	}
		                    }
		                }catch (Exception ex12){}
						vec.addElement(tgian1);
						vec.add(ngay);
						vec.add(idtt);
						try{
		                    stmt22222 = conn22222.createStatement();
		                    rss22222 = stmt22222.executeQuery("select *from CuonSach where ID_CuonSach = " + tidcuon.getText());
		                    if (rss22222!=null){
		                    	while (rss22222.next()){
		                    	vec.add(rss22222.getString(5)); // gia cuon sach
		                    	}
		                    }
		                }catch (Exception ex122){}
						dt.addRow(vec);
						tidcuon.setText("");
					}	
					}catch (Exception ex){}
					
				}
		});
		 /*
		 btn2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int ii = bang.getSelectedRow();
					//if ((ii!=-1)&&(bang.getValueAt(ii,3).equals(ngay) == true) && (bang.getValueAt(ii,4).equals(idtt) == true)){
					if ((ii >= RowPast)) {
						System.out.println(ii);
					String sql1 = "UPDATE MuonSach SET ID_CuonSach = ? WHERE ID_Muon = ?";
					try{ 
						stmt3 = conn3.prepareStatement(sql1);
						
						stmt3.setString(1,tidcuon.getText());
						stmt3.setString(2,bang.getValueAt(ii,0)+"");
						stmt3.executeUpdate();
						dt.setValueAt(tidcuon.getText(),ii,1);
						try{
		                    stmt32 = conn32.createStatement();
		                    rss32 = stmt32.executeQuery("select *from CuonSach where ID_CuonSach = " + Integer.toString((Integer.parseInt(tidcuon.getText())) / 10000));
		                    if (rss32!=null){
		                    	while (rss32.next()){
		                    	dt.setValueAt(rss32.getString(2),ii,2); // Update ten cuon sach
		                    	}
		                    }
		                }catch (Exception ex123){}
						try{
		                    stmt31 = conn31.createStatement();
		                    rss31 = stmt31.executeQuery("select *from CuonSach where ID_CuonSach = " + tidcuon.getText());
		                    if (rss31!=null){
		                    	while (rss31.next()){
		                    	dt.setValueAt(rss31.getString(5),ii,5); // Update gia cuon sach
		                    	}
		                    }
		                }catch (Exception ex122){}
						tidcuon.setText("");
					}catch (Exception ex){}
					}//end if
				}
		 });
		 */ 
		 btn5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int ii = bang.getSelectedRow();
					//if ((ii!=-1)&&(bang.getValueAt(ii,3).equals(ngay) == true) && (bang.getValueAt(ii,4).equals(idtt) == true)){
					if ((ii >= RowPast)) {
					String sql5 = "DELETE FROM MuonSach WHERE ID_Muon = ?";
					try{
						stmt5 = conn5.prepareStatement(sql5);
						stmt5.setString(1,bang.getValueAt(ii,0)+"");
						stmt5.executeUpdate();
						Vector<String> vec = new Vector<String>();
						dt.removeRow(ii);
						tidcuon.setText("");
					}catch (Exception ex){}
					}
				}
		});
		
		 btn3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent et) {
					dispose();
				}
		});
		 btnTong.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent et) {
					try{
						
					RowNow = bang.getRowCount();
					int tong = 0;
						for (int i = RowPast ;i<=RowNow - 1;i++){
							int giatien = Integer.parseInt(bang.getValueAt(i,5)+"");
							tong += giatien;
						}
						tTong.setText(Integer.toString(tong));
						tTong.setEditable(false);
						for (int i = RowPast ;i<=RowNow - 1;i++)dt.setValueAt("Xong",i,6);
						RowPast = RowNow;
					
					}catch(Exception eo){}
				}
		}); 
	 }
	 public void addcontrol( String ten, String id,String tentt,String idtt ,String ngay){
		
		 Container con = getContentPane();
		// total.setBackground(Color.CYAN);
			total = new JPanel(); total.setLayout(new BoxLayout(total, BoxLayout.Y_AXIS));
			Font fontTieuDe1=new Font("arial", Font.BOLD, 20);
			p0=new JPanel();
			lTieude=new JLabel("Thông tin phiếu mượn");
			p0.add(lTieude);
			lTieude.setFont(fontTieuDe1);
			lTieude.setForeground(Color.BLUE);
			total.add(p0);
			
			Font fontTieuDe2=new Font("arial", Font.ITALIC, 15);
			p1=new JPanel();
			p8 = new JPanel();
			// p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
			Font fontTieuDe=new Font("arial", Font.BOLD, 15);         // chỉnh sủa fone chữ
		lNgay = new JLabel(ngay);
		lNgay.setFont(fontTieuDe);
		p8.add(lNgay);
		total.add(p8);
		
		lName1=new JLabel("Tên độc giả :");
		lName1.setFont(fontTieuDe);     
		
		lName1.setForeground(Color.RED);
		lName1.setSize(100, 50);
		lName1.setLocation(27,40);
		//p1.setLayout(null);
		
		lName2=new JLabel(ten);
		lName2.setFont(fontTieuDe2);
		p1.add(lName1); p1.add(lName2);
		
		lName2.setLocation(40,40);
		total.add(p1);
		p2=new JPanel(); 
		
		//p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
		
		lId1=new JLabel("ID độc giả:");
		lId1.setForeground(Color.RED);
	
		lId1.setFont(fontTieuDe);               
		
		lId1.setLocation(40, 0);
		lId2=new JLabel(id);
		lId2.setFont(fontTieuDe);
		p2.add(lId1);p2.add(lId2);
		total.add(p2);
		p3=new JPanel();
		//p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS));
		llId1=new JLabel("ID thủ thư:");
			llId1.setFont(fontTieuDe);               
		llId1.setForeground(Color.RED);
		llId1.setLocation(40, 0);
		llId2=new JLabel(idtt);
		llId2.setFont(fontTieuDe2);
		p3.add(llId1);p3.add(llId2);
		total.add(p3);
		
		p4=new JPanel();
		//p4.setLayout(new BoxLayout(p4, BoxLayout.X_AXIS));
		llName1=new JLabel("Tên thủ thư:");
		llName1.setFont(fontTieuDe);
		llName1.setForeground(Color.RED);
		llName1.setLocation(40, 0);
		llName2=new JLabel(tentt);
		llName2.setFont(fontTieuDe2);
		p4.add(llName1);p4.add(llName2);
		total.add(p4);
		
//		llId1.setPreferredSize(llName1.getPreferredSize());
//		lId1.setPreferredSize(llName1.getPreferredSize());
//		lName1.setPreferredSize(llName1.getPreferredSize());
//		lId2.setPreferredSize(lName2.getPreferredSize());
//		llId2.setPreferredSize(lName2.getPreferredSize());
//		llName2.setPreferredSize(lName2.getPreferredSize());
		
		p5=new JPanel(); p5.setLayout(new BorderLayout());
		
		 dt=new DefaultTableModel();
			dt.addColumn("ID mượn");
			dt.addColumn("ID cuốn sách");
			dt.addColumn("Tên sách");
			dt.addColumn("Ngày mượn");
			dt.addColumn("ID thủ thư");
			dt.addColumn("Giá mượn");
			dt.addColumn("Xử lý");
			bang = new JTable(dt);
			JScrollPane bangSc = new JScrollPane(
					bang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			p5.add(bangSc, BorderLayout.CENTER);
			total.add(p5);
			
			ImageIcon ima1=new ImageIcon("src/images/tải xuống.png");
			ImageIcon ima2=new ImageIcon("src/images/last.gif");
			ImageIcon ima3=new ImageIcon("src/images/close-btn.png");
			ImageIcon ima4=new ImageIcon("src/images/refresh.jpg");
			
			btn1=new JButton("Thêm ");
			btn2=new JButton("Sửa ");
			btn5=new JButton("Xóa ");
			btnTong = new JButton("Tổng tiền ");
			btn3=new JButton("Thoát");
		//	btn4=new JButton("refesh",ima4);
			p6=new JPanel();
			p6.setLayout(new BoxLayout(p6, BoxLayout.X_AXIS));
			p6.add(btn1);
			//p6.add(btn2);
			p6.add(btnTong);
			p6.add(btn5);
			p6.add(btn3);
			
			p7=new JPanel();
			 p7.setLayout(new BoxLayout(p7, BoxLayout.X_AXIS));
			 llIDcuon = new JLabel("ID cuốn sách mượn: ");
			 tidcuon = new JTextField();
			 p7.add(llIDcuon);
			 p7.add(tidcuon);
			 total.add(p7);
			 p9=new JPanel();
			 p9.setLayout(new BoxLayout(p9, BoxLayout.X_AXIS));
			 lTong = new JLabel("Tổng tiền cần đặt cọc: ");
			 tTong = new JTextField();
			 p9.add(lTong);
			 p9.add(tTong);
			 total.add(p9);
			 total.add(p6);
			 con.add(total);
			 try{
					conn= ConnectionFunc.getConnection();
					conn2= ConnectionFunc.getConnection();
					conn3= ConnectionFunc.getConnection();
					conn31= ConnectionFunc.getConnection();
					conn32= ConnectionFunc.getConnection();
					conn5= ConnectionFunc.getConnection();
					conn222= ConnectionFunc.getConnection();
					conn2221= ConnectionFunc.getConnection();
					connmax= ConnectionFunc.getConnection();
					conn2222= ConnectionFunc.getConnection();
					conn22222= ConnectionFunc.getConnection();
					connsai1= ConnectionFunc.getConnection();
			 }catch (Exception ex){} 
			 Addevent(ten,id,tentt,idtt,ngay);		 
	}
	public void showWindow() 
	{
      this.setSize(600, 400);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
	}
}

