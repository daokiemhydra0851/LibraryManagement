package adminGUI;

import java.awt.*;
import javax.swing.*;


import adminGUI.tabQLDG;
import adminGUI.tabQLSach;
import adminGUI.tabQLTT;
public class main1 extends JFrame {
	
	public main1(String Title){
		super(Title);
		addControl();
	}
	private JTabbedPane tab1,tab2,tab3;
	public void addControl(){
		Container con = getContentPane();
		tab1 = new JTabbedPane();
		JPanel p1 = tabQLDG.addControls();
		JPanel p2 = tabQLSach.addControls1();
		JPanel p3 = tabMuontra.addControls1();
		JPanel p4= tabQLTT.addControls();
		tab1.add(p1,"Quản lý đọc giả");
		tab1.add(p2,"Quản lý sách");
		tab1.add(p3,"Quản lý mượn trả");
		tab1.add(p4, "Quản lý thủ thư");
		con.add(tab1);
		
	}
	public void Runmain1(){
		this.setSize(700,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	//	this.setResizable(false);
		this.setVisible(true);
	}

}
