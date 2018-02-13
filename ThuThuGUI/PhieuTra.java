package ThuThuGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class PhieuTra extends JFrame {
	
	 static JButton btn1,btn2,btn4,btn5,btnTong;
	 static Statement stmt2,stmt20,stmt222,stmt2221,stmt2222,stmt22222,stmtqql,stmt31,stmt32,stmtsai2;
	 static PreparedStatement stmt,stmt3,stmt5;
	 static ResultSet rss2,rss20,rss222,rss2222,rss22222,rss2221,rss31,rssqql,rss32,rsssai2;
	 static Connection conn, conn2,conn20, conn3,conn31,conn32,conn5,conn222,conn2221,connqql,conn2222,conn22222,connsai2;
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

	public  PhieuTra (String tieude)
	{
		super (tieude);
	}
	private void Addevent( String ten, String id,String tentt,String idtt ,String ngay)
	 {
		 try{ // tao bang ban dau
				stmt2 = conn2.createStatement();
				String id000 = id + "000";
				String id999 = id + "999";
				System.out.println(id000);
				System.out.println(id999);
				rss2 = stmt2.executeQuery("select *from TraSach where (ID_Tra >= " + id000 + " AND ID_Tra <= "+id999 +")");
				
				dt.setNumRows(0);
		        if(rss2!=null){ 
		                while(rss2.next()){
		                	System.out.println("OKKKK123");
		                    Vector <String> vtRow=new Vector();
		                    vtRow.add(rss2.getString(1));		//tu ID_Docgia ---->lay dc idtra
		                    
		                    stmt20 = conn20.createStatement();
		                    rss20 = stmt20.executeQuery("select *from MuonSach where ID_Muon = " + rss2.getString(1));
		                    if (rss20!= null){
		                    	while (rss20.next()){
		                    		String tg = rss20.getString(3);
		                    		vtRow.add(tg);
				                    try{
				                    stmt222 = conn222.createStatement();
				                    rss222 = stmt222.executeQuery("select *from DauSach where ID_DauSach = " + Integer.toString((Integer.parseInt(tg)) / 10000));
				                    if (rss222!=null){
				                    	while (rss222.next()) vtRow.add(rss222.getString(2));
				                    }
				                    }catch (Exception ex1){}
				                    vtRow.add(rss2.getString(2)); // ngay tra    
				                    vtRow.add(rss2.getString(3)); // id thu thu
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
		                    	}
		                    }
		                    
		                    dt.addRow(vtRow);                
		            }
		        }
		}catch (Exception ex){}
		 RowPast = bang.getRowCount();
		 
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
					String sql = "INSERT INTO TraSach (ID_Tra,NgayTra,ID_Thuthu) VALUES(?,?,?)";
					try{
						
						String sqql = "select max(ID_Muon) as maxid from MuonSach where ID_CuonSach = "+ tidcuon.getText()+ " and ID_Docgia = "+ id ;   
						try{
							int ok = 0;
							//Ktra
							stmtsai2 = connsai2.createStatement();
							int solgdangmuon = 0;
							rsssai2=stmtsai2.executeQuery("SELECT COUNT(*) AS count2 FROM MuonSach LEFT JOIN TraSach ON MuonSach.ID_Muon=TraSach.ID_Tra WHERE TraSach.ID_Tra IS NULL AND MuonSach.ID_CuonSach = " + tidcuon.getText() + " AND MuonSach.ID_Docgia = " + id);
							if (rsssai2!=null){
								while(rsssai2.next()) solgdangmuon = rsssai2.getInt("count2");
								if (solgdangmuon ==0){ // Tra sach ko trong danh sach dang dc muon 
									 int output = JOptionPane.showConfirmDialog(null,"Sách đã trả hoặc chưa mượn, vui lòng nhập lại",""
								               ,JOptionPane.OK_CANCEL_OPTION); 
								     if(output == JOptionPane.OK_CANCEL_OPTION){
								            	JOptionPane.getRootFrame().dispose();
								     }
									ok =1;	
								}
							}
							if (ok==0){
							stmtqql = connqql.createStatement();
							rssqql = stmtqql.executeQuery(sqql);
							if (rssqql!=null){
								while (rssqql.next()){
									String idmuonmax = rssqql.getString("maxid");
									stmt = conn.prepareStatement(sql);
									stmt.setString(1,idmuonmax); // tim dc ID_MuonSach max chinh = ID_tra luon !!1
									stmt.setString(2,ngay);
									stmt.setString(3,idtt);
									stmt.executeUpdate();
									Vector<String> vec = new Vector<String>();
									vec.add(idmuonmax);
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
							}
							}
						}catch(Exception ex){}
					}catch (Exception ex){}
				}
		});
		 
		 btn5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int ii = bang.getSelectedRow();
					if ((ii >= RowPast)) {
					String sql5 = "DELETE FROM TraSach WHERE ID_Tra = ?";
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
		 btn3.addActionListener(new ActionListener() { // thoat
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
			lTieude=new JLabel("Thông tin phiếu trả");
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
			dt.addColumn("ID trả");
			dt.addColumn("ID cuốn sách");
			dt.addColumn("Tên sách");
			dt.addColumn("Ngày trả");
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
			 llIDcuon = new JLabel("ID cuốn sách trả: ");
			 tidcuon = new JTextField();
			 p7.add(llIDcuon);
			 p7.add(tidcuon);
			 total.add(p7);
			 p9=new JPanel();
			 p9.setLayout(new BoxLayout(p9, BoxLayout.X_AXIS));
			 lTong = new JLabel("Tổng tiền trả đọc giả: ");
			 tTong = new JTextField();
			 p9.add(lTong);
			 p9.add(tTong);
			 total.add(p9);
			 total.add(p6);
			 con.add(total);
			 try{
					conn= ConnectionFunc.getConnection();
					conn2= ConnectionFunc.getConnection();
					conn20= ConnectionFunc.getConnection();
					conn3= ConnectionFunc.getConnection();
					conn31= ConnectionFunc.getConnection();
					conn32= ConnectionFunc.getConnection();
					conn5= ConnectionFunc.getConnection();
					conn222= ConnectionFunc.getConnection();
					conn2221= ConnectionFunc.getConnection();
					connqql= ConnectionFunc.getConnection();
					conn2222= ConnectionFunc.getConnection();
					conn22222= ConnectionFunc.getConnection();
					connsai2= ConnectionFunc.getConnection();
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

