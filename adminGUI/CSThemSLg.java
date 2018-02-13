package adminGUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import FT.ConnectionFunc;
import java.lang.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CSThemSLg extends JFrame {
	public CSThemSLg(String Title){
		super("Thêm số lượng sách ");	
		addControls(Title);
		ok = 0 ;
		AddThemEvent();
	}
	private JButton btn1;
	public static JTextField jt;
	public static String st;
	public static int ok ;
	public void AddThemEvent()
	{
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent et) {
				st = jt.getText();
				//System.out.println("hehe" + st);
				ok = 1;
				dispose();
			}
		});
	}
	public void addControls(String aaa)
	{
		Container con = getContentPane();
		JPanel pn = new JPanel();
		pn.setBackground(Color.CYAN);
		pn.setLayout(new BoxLayout(pn,BoxLayout.Y_AXIS));
		JPanel pnnn = new JPanel();
		pnnn.setBackground(Color.CYAN);
		pnnn.setLayout(new BoxLayout(pnnn,BoxLayout.X_AXIS));
		JLabel LNO = new JLabel(" Số lượng cuốn sách thêm: ");
		
		pnnn.add(LNO);	
		jt = new JTextField(15);
		
		pnnn.add(jt);
		JPanel pnn2 = new JPanel();
		pnn2.setBackground(Color.CYAN);
		//pn.setLayout(new FlowLayout());
		pnn2.setLayout(new BoxLayout(pnn2,BoxLayout.X_AXIS));
		JLabel LN1 = new JLabel(" Đầu sách:  " + aaa);
		pnn2.add(LN1);
		
		JPanel pn22 = new JPanel();
		pn22.setLayout(new FlowLayout());
		pn22.setBackground(Color.CYAN);
		btn1 = new JButton("Xong");
		pn22.add(btn1);
		
		pn.add(pnn2);
		pn.add(pnnn);
		pn.add(pn22);
		con.add(pn);
	}
	public void RunCSThemSlg(){
		this.setSize(300,80);
		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		 
	}
}
