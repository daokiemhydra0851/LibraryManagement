package Register;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

import FT.ConnectionFunc;
import ThuThuGUI.main2;
import adminGUI.main1;
import DocgiaGUI.main3;
public class Register extends JFrame {   
	
	JLabel lblTaiKhoan, lblMatKhau,lblBanla ;
	JButton bntDangNhap,bntThoat;
	JTextField txtTaikhoan;//,txtMatKhau ;
	JPasswordField txtMatKhau;
	static String arrSl[]={"Độc giả","Thủ thư","Admin"
							
							
						  };
	static String matkhau;
	static private Connection conn1,conn2;
	static private ResultSet rss1,rss2;
	static private Statement stmt1,stmt2;
	public Register (String tieude)
	{
		super (tieude);
		showWindow();
		addcontrols();
		addEvent();
	}
	public void addcontrols()
	{  
		Container con = getContentPane();
		JPanel pnmain =new JPanel();
		pnmain.setLayout(new BoxLayout(pnmain, BoxLayout.Y_AXIS));//tạo thành cac khối DOC
		con.add( pnmain);
		
		JPanel pnTitle =new JPanel();
		pnTitle.setLayout(new FlowLayout());                      //sap xep cac controls tren 1 dong khi nao het cho chua' thi xuong dong
		JLabel lbltieude=new JLabel(" Chào mừng đến với thư viện");
		pnTitle.add(lbltieude);
		pnmain.add(pnTitle);
		lbltieude.setForeground(Color.RED);                       // Chỉnh sửa màu cho Flowlayout
		Font fontTieuDe=new Font("arial", Font.BOLD, 20);         // chỉnh sủa fone chữ
		lbltieude.setFont(fontTieuDe);                           
		
		JPanel pnTaiKhoan=new JPanel();
		pnTaiKhoan.setLayout(new FlowLayout());
	    lblTaiKhoan= new JLabel("Tài khoản:");
	    txtTaikhoan=new JTextField(22);
	    //txtTaikhoan = new JPasswordField(25);
		pnTaiKhoan.add(lblTaiKhoan);
		pnTaiKhoan.add(txtTaikhoan);
		pnmain.add(pnTaiKhoan);
		
		JPanel pnMatkhau = new JPanel();
		pnMatkhau.setLayout( new FlowLayout());
	    lblMatKhau = new JLabel("Mật khẩu:");
		//txtMatKhau= new JTextField(15);
	    txtMatKhau = new JPasswordField(22);
		pnMatkhau.add(lblMatKhau);
		pnMatkhau.add(txtMatKhau);
		pnmain.add(pnMatkhau);
		
	
		JPanel pnDangnhap = new JPanel();
		pnDangnhap.setLayout(new FlowLayout());
	    bntDangNhap= new JButton("Đăng nhập");
		bntThoat = new JButton("Thoát");
		//bntThoat.setIcon(new ImageIcon("hinh/exit.png"));
		pnDangnhap.add(bntDangNhap);
		pnDangnhap.add(bntThoat);
		pnmain.add(pnDangnhap);
		
	}
	private void addEvent() {
		 try{
				conn1 = ConnectionFunc.getConnection();
				conn2 = ConnectionFunc.getConnection();
		 }catch (Exception ex){}
		 
	     bntThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});	
	     bntDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matkhau = new String(txtMatKhau.getPassword());
				System.out.println(matkhau);
				//matkhau = "finalwave2";
				System.out.println(txtMatKhau.getPassword());
				if (txtTaikhoan.getText().equals("admin")==true && matkhau.equals("qwert")==true){
					main1 cs = new main1("Hệ thống quản lý thư viện");
					cs.Runmain1();
					dispose();
				}
				else{
				try{
					stmt1 = conn1.createStatement();
					rss1=stmt1.executeQuery("select *from Docgia_Pass where ID_Docgia =  " + txtTaikhoan.getText() + " AND Pass_Docgia = '"+ matkhau + "'");
					
			        if(rss1!=null){
			           while(rss1.next()){	
				           main3 fui = new main3("Hệ thống quản lý thư viện",txtTaikhoan.getText());
				      	   fui.Runmain3();
				      	   dispose();
			           }
			        }
		        }catch (Exception ex){}
		        
		        try{
		        stmt2 = conn2.createStatement();
				rss2=stmt2.executeQuery("select *from Thuthu_Pass where ID_Thuthu =  " + txtTaikhoan.getText() + " AND Pass_Thuthu = '"+ matkhau+"'");
		        if(rss2!=null){
		        	while(rss2.next()){	
		        		main2 fui2 = new main2("Hệ thống quản lý thư viện",txtTaikhoan.getText());
		        		fui2.Runmain2();
		        		dispose();
		        	}
		        }
		        }catch (Exception ex){}
				}
			}
		});	
	    
		      
	}
	public void showWindow() 
	{
      this.setSize(400, 250);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
	}
	
}