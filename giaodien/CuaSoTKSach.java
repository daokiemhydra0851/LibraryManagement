package giaodien;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import FT.ConnectionFunc;
import java.sql.*;
import giaodien.CuaSoThemDauSachFix;
import giaodien.CuaSoSachNO;
//import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class CuaSoTKSach extends JFrame {
	DefaultTableModel dt;
	JTable bang;
	private Connection conn6,conn7,conn5,conn8,conn9;
	private ResultSet rs5,rs6,rs7,rs8,rs9,rs10,rs11;
	private Statement stmt5,stmt10,stmt11;
	private PreparedStatement stmt6,stmt7,stmt8,stmt9;
	private JTextField TID;
	private JTextField TName,TNxb,TTg;
	private int ok;
	private JButton btn1,btn0,btn2,btn3;
	private CuaSoThemDauSachFix form1;
	private CuaSoSachNO form2;
	public CuaSoTKSach(String Title){
		super(Title);	
		
		try{
			
			conn5 = ConnectionFunc.getConnection();
			conn6 = ConnectionFunc.getConnection();
			conn7 = ConnectionFunc.getConnection();
			conn8 = ConnectionFunc.getConnection();
			addControls2();
		}catch (Exception ex){}
		AddThemEvent();
		//mouseClicked(MouseEvent eee);
	}
	
	public void AddThemEvent()
	{
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
					TID.setText(tg1);
					TName.setText(tg2);
					TTg.setText(tg3);
					TNxb.setText(tg4);
				}
			});
			
			// Xu ly nut cap nhat
			btn2.addActionListener(new ActionListener() {
				//	@Override
					public void actionPerformed(ActionEvent e) {
							String t1 = TID.getText();
							String t2 = TName.getText();
							String t3 = TTg.getText();
							String t4 = TNxb.getText();
							String sql1 = "UPDATE DauSach SET Ten_DauSach = ?, NhomTgia = ?,NhaXuatBan = ? WHERE ID_DauSach = ?" ;

							try{
								stmt6 = conn6.prepareStatement(sql1);		
								stmt6.setString(1,t2);
								stmt6.setString(2,t3);
								stmt6.setString(3,t4);
								stmt6.setString(4, t1);
								
								stmt6.executeUpdate();
											
							}catch(Exception i){}
					}
			});
			// Xuly nut tim kiem
			btn0.addActionListener(new ActionListener() {
				//	@Override
					public void actionPerformed(ActionEvent e) {
						
						String sql44 = "SELECT COUNT(*)	AS count2 FROM DauSach WHERE ID_DauSach = " + TID.getText();
						
						try{
							stmt10 = conn7.createStatement();
							rs10 = stmt10.executeQuery(sql44);
							
							ok = 0;
							while(rs10.next()) ok = rs10.getInt("count2");
							 
						}catch(Exception pp){}
						if (ok!=0){
							String sql55 = "SELECT ID_DauSach,Ten_DauSach,NhomTgia,NhaXuatBan FROM DauSach WHERE ID_DauSach = " + TID.getText();
							try{
								stmt11 = conn8.createStatement();
								rs11 = stmt11.executeQuery(sql55);
								String k1,k2,k3,k4;
								while(rs11.next())
								{
									k1 = rs11.getString("ID_DauSach");
									k2 = rs11.getString("Ten_DauSach");
									k3 = rs11.getString("NhomTgia");
									k4 = rs11.getString("NhaXuatBan");
									CuaSoThemDauSachFix form1 = new CuaSoThemDauSachFix("",TID.getText(),k2,k3,k4);             
									form1.RunCuaSoThemDauSachFix();
								}
								
							}catch(Exception oo){}
						}
						else{
							form2 = new CuaSoSachNO("");
							form2.RunCuaSoSachNO();
						}
					}
			});
			
	}
	public void addControls2()
	{
		Container con = getContentPane();
		JPanel pn = new JPanel();
		pn.setBackground(Color.CYAN);
		pn.setLayout(new BoxLayout(pn,BoxLayout.Y_AXIS));
		
		
		JLabel lb = new JLabel("Tìm kiếm sách bạn muốn");
		pn.add(lb);
		JPanel pn1 = new JPanel();
		pn1.setLayout(new BoxLayout(pn1,BoxLayout.Y_AXIS));
		
		JPanel p1ID = new JPanel();
		JPanel p1Name = new JPanel();
		JPanel p1Nxb = new JPanel();
		JPanel p1Tg = new JPanel();
		p1ID.setLayout(new BoxLayout(p1ID,BoxLayout.X_AXIS));
		p1Name.setLayout(new BoxLayout(p1Name,BoxLayout.X_AXIS));
		p1Nxb.setLayout(new BoxLayout(p1Nxb,BoxLayout.X_AXIS));
		p1Tg.setLayout(new BoxLayout(p1Tg,BoxLayout.X_AXIS));
		
		JLabel lID = new JLabel("ID đầu sách: ");
		JLabel  lName = new JLabel("Tên đầu sách:  ");
		JLabel lTg = new JLabel("Nhóm tác giả:   ");
		JLabel lNxb = new JLabel("Nhà xuất bản:    ");
		TID = new JTextField(15);
		TName = new JTextField(40);
		TNxb = new JTextField(15);
		TTg = new JTextField(15);
		p1ID.add(lID);p1ID.add(TID);
		p1Name.add(lName);p1Name.add(TName);
		p1Tg.add(lTg);p1Tg.add(TTg);
		p1Nxb.add(lNxb);p1Nxb.add(TNxb);
		
		pn1.add(p1ID);
		pn1.add(new JLabel("OR"));
		pn1.add(p1Name);
		pn1.add(p1Tg);
		pn1.add(p1Nxb);
		
		pn.add(pn1);
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new BoxLayout(pn2,BoxLayout.X_AXIS));
		btn0= new JButton("Tìm kiếm");
		btn1 = new JButton("Thêm mới");
		btn2 = new JButton("Cập nhật");
		btn3 = new JButton("Xóa");
		pn2.add(btn1);pn2.add(btn0);
		pn2.add(btn2);
		pn2.add(btn3);
		
		pn.add(pn2);
	
		JPanel p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		
		dt=new DefaultTableModel();
		
		dt.addColumn("ID đầu sách");
		dt.addColumn("Tên đầu sách");
		dt.addColumn("Nhóm tác giả");
		dt.addColumn("Nhà xuất bản");
		
		bang = new JTable(dt);
		JScrollPane bangSc = new JScrollPane(
				bang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		p3.add(bangSc, BorderLayout.CENTER);
		pn.add(p3);
		
		con.add(pn);
		
		
        String sql5 = "SELECT * FROM DauSach;";
        
        try {
        	stmt5 = conn5.createStatement();  
			rs5 = stmt5.executeQuery(sql5);
            while(rs5.next()){ 
                String rows[] = new String[4];
                rows[0] = rs5.getString(1); 
                rows[1] = rs5.getString(2); 
                rows[2] = rs5.getString(3);
                rows[3] = rs5.getString(4);
                dt.addRow(rows); 
            }
        } catch (SQLException e1) {}
		
	}
	
	public void RunCuaSoTKSach(){
		this.setSize(700,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	//	this.setResizable(false);
		this.setVisible(true);
	}

}
