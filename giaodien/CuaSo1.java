package giaodien;

import java.awt.*;

import javax.swing.*;
//import javax.swing.JPanel;

public class CuaSo1 extends JFrame {
	
	public CuaSo1(String Title){
		super(Title);	
		addControls();
	}
	public void addControls()
	{
		Container con = getContentPane();
		JPanel pn = new JPanel();
		pn.setBackground(Color.CYAN);
		//pn.setLayout(new FlowLayout());
		//pn.setLayout(new BoxLayout(pn,BoxLayout.Y_AXIS));
		JButton btn1 = new JButton("Quản lý độc giả");
		JButton btn2 = new JButton("Quản lý sách");
		JButton btn3 = new JButton("Quản lý mượn trả");
		pn.add(btn1);
		pn.add(btn2);
		pn.add(btn3);
		
		con.add(pn);
	}
	public void RunCuaSo1(){
		this.setSize(500,400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

}
