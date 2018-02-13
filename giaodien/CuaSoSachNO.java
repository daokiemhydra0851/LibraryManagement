package giaodien;

import java.awt.*;
import JFrame.*;
import java.awt.event.*;

import giaodien.CuaSoThemThongTinDauSach;

import javax.swing.*;
//import javax.swing.JPanel;

public class CuaSoSachNO extends JFrame {
	
	public CuaSoSachNO(String Title){
		super(Title);	
		addControls();
		AddThemEvent();
		Add2();
	}
	private JButton btn1,btn2;
	private CuaSoThemThongTinDauSach form; 
	public void AddThemEvent()
	{
		btn1.addActionListener(new ActionListener() {
		//	@Override
			public void actionPerformed(ActionEvent e) {
				CuaSoThemThongTinDauSach form = new CuaSoThemThongTinDauSach("Nhập đầu sách mới");
				form.RunCuaSoThemThongTinDauSach();
			}
		});
	}
	public void Add2()
	{
		btn2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent et) {
					dispose();
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
		JLabel LNO = new JLabel(" Đầu sách không tồn tại");
		pn.add(LNO);
		JLabel LN1 = new JLabel("Muốn thêm đầu sách mới ?");
		pn.add(LN1);
		JLabel LS = new JLabel("");
		pn.add(LS);
		
		JPanel pn22 = new JPanel();
		
		pn22.setLayout(new FlowLayout());
		btn1 = new JButton("YES");
		btn2 = new JButton("NO");
		pn22.setBackground(Color.CYAN);
		pn22.add(btn1);
		pn22.add(btn2);
		
		pn.add(pn22);
		con.add(pn);
	}
	public void RunCuaSoSachNO(){
		this.setSize(300,80);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

}
