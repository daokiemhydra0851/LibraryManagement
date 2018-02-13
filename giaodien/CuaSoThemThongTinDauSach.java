package giaodien;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.*;
import java.util.*;
import FT.ConnectionFunc;
import java.sql.*;
import javax.swing.*;
import giaodien.CuaSoThemDauSachFix;
//import javax.swing.JPanel;

public class CuaSoThemThongTinDauSach extends JFrame {
	
	public CuaSoThemThongTinDauSach(String Title){
		super(Title);	
		addControls();
		AddThemEvent3();
		
	}
	private JButton btn9;
	private CuaSoThemDauSachFix form5;
	private JTextField f1,f2,f3,f4;
	public void AddThemEvent3()
	{		// Xu ly nut cap nhat
			btn9.addActionListener(new ActionListener() {
				//	@Override
					public void actionPerformed(ActionEvent e) {
						form5 = new CuaSoThemDauSachFix("",f1.getText(),f2.getText(),f3.getText(),f4.getText());             
						form5.RunCuaSoThemDauSachFix();

							
					}
			});
			
			
	}

	public void addControls()
	{
		Container con = getContentPane();
		JPanel pn = new JPanel();
		pn.setBackground(Color.CYAN);
		//pn.setLayout(new FlowLayout());
		pn.setLayout(new BoxLayout(pn,BoxLayout.Y_AXIS));
		JLabel LNO = new JLabel("Nhập đầu sách mới ");
		pn.add(LNO);
		JPanel pn1 = new JPanel();
		pn1.setLayout(new BoxLayout(pn1,BoxLayout.X_AXIS));
		JLabel LN1 = new JLabel("ID đầu sách mới       ");
		f1 = new JTextField(20);
		pn1.add(LN1);pn1.add(f1);
		pn.add(pn1);
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new BoxLayout(pn2,BoxLayout.X_AXIS));
		JLabel LN2 = new JLabel("Tên đầu sách mới      ");
		f2 = new JTextField(20);
		pn2.add(LN2);pn2.add(f2);
		pn.add(pn2);
		
		JPanel pn3 = new JPanel();
		pn3.setLayout(new BoxLayout(pn3,BoxLayout.X_AXIS));
		JLabel LN3 = new JLabel("Nhóm tác giả          ");
		f3 = new JTextField(20);
		pn3.add(LN3);pn3.add(f3);
		pn.add(pn3);
		
		JPanel pn4 = new JPanel();
		pn4.setLayout(new BoxLayout(pn4,BoxLayout.X_AXIS));
		JLabel LN4 = new JLabel("Nhà xuất bản          ");
		f4 = new JTextField(20);
		pn4.add(LN4);pn4.add(f4);
		pn.add(pn4);
		
		JPanel pn5 = new JPanel();
		pn5.setLayout(new FlowLayout());
		//JButton btn1 = new JButton("OK");
		 btn9 = new JButton("Thêm cuốn sách");
		
		//pn5.add(btn1);
		pn5.add(btn9);
		pn.add(pn5);
		
		con.add(pn);
	}
	public void RunCuaSoThemThongTinDauSach(){
		this.setSize(350,170);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

}
