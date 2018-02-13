package DocgiaGUI;

import java.awt.*;
import javax.swing.*;


import DocgiaGUI.tabDocgiaInfo;
//import DocgiaGUI.tabQLMuonTra;
public class main3 extends JFrame {
	
	public main3(String Title,String tgian){
		super(Title);
		addControl(tgian);
	}
	private JTabbedPane tab1,tab2,tab3;
	public void addControl(String tgian){
		Container con = getContentPane();
		tab1 = new JTabbedPane();
		JPanel p1 = tabDocgiaInfo.addControls(tgian);
		JPanel p2 = tabTracuusach.addControls();
		tab1.add(p1,"Thông tin cá nhân");
		
		tab1.add(p2,"Tra cứu sách");
		
		con.add(tab1);
		
	}	
	public void Runmain3(){
		this.setSize(700,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	//	this.setResizable(false);
		this.setVisible(true);
	}

}
