package giaodien;

import java.awt.*;

import javax.swing.*;
//import javax.swing.JPanel;

public class CuaSoChonChucNangDG extends JFrame {
	
	public CuaSoChonChucNangDG(String Title){
		super(Title);	
		addControls1();
	}
	public void addControls1()
	{
		Container con = getContentPane();
		JPanel pn = new JPanel();
		pn.setBackground(Color.CYAN);
		pn.setLayout(new FlowLayout());
		
		
		JButton btn1 = new JButton("Tìm độc giả");
		JButton btn2 = new JButton("Thêm độc giả");
		
		pn.add(btn1);
		pn.add(btn2);
	
		
		con.add(pn);
	}
	public void RunCuaSoChonChucNangDG(){
		this.setSize(300,100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

}
