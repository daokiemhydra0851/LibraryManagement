package ThuThuGUI;

import java.awt.*;
import javax.swing.*;


import ThuThuGUI.tabThuThuInfo;
import ThuThuGUI.tabQLMuonTra;
public class main2 extends JFrame {
	
	public main2(String Title,String tgian){
		super(Title);
		addControl(tgian);
	}
	private JTabbedPane tab1,tab2,tab3;
	public void addControl(String tgian){
		Container con = getContentPane();
		tab1 = new JTabbedPane();
		JPanel p1 = tabThuThuInfo.addControls(tgian);
		JPanel p2 = tabQLMuonTra.addControls(tgian);
		tab1.add(p1,"Thông tin cá nhân");
		
		tab1.add(p2,"Quản lý mượn trả");
		
		con.add(tab1);
		
	}	
	public void Runmain2(){
		this.setSize(700,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	//	this.setResizable(false);
		this.setVisible(true);
	}

}
