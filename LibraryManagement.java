
import java.sql.*;
import Register.Register;
import giaodien.GiaodienUI;
import giaodien.CuaSo1;
import giaodien.CuaSo2;
import giaodien.CuaSoChonChucNangDG;
import giaodien.CuaSoThemDG;
import giaodien.CuaSoThemDauSach;
import giaodien.CuaSoSachNO;
import giaodien.CuaSoTKSach;
import ThuThuGUI.main2;
import adminGUI.main1;
import DocgiaGUI.*;
import adminGUI.*;
import java.lang.*;
import java.util.*;

import DocgiaGUI.main3;
import FT.ConnectionFunc;

public class LibraryManagement {
     
   public static void main(String[] args) {
  // Connection conn = null;
  // Statement stmt = null;
 //  try{
      //STEP 2: Register JDBC driver
	//   System.out.println("Creating statement..."); 
      //STEP 3: Open a connection
      //System.out.println("Con	necting to database...");
	  /*
      CuaSo1 desStart = new CuaSo1("WELCOME TO SYSTEM");
      desStart.RunCuaSo1();
      
      	CuaSo2 cs2 = new CuaSo2("Tra cuu thong tin mot doc gia");
      	cs2.RunCuaSo2();
      	
      CuaSoChonChucNangDG cs3 = new CuaSoChonChucNangDG("Quản lý độc giả");
      cs3.RunCuaSoChonChucNangDG();
      
      CuaSoThemDG cs4 = new CuaSoThemDG("Thông tin độc giả");
      cs4.RunCuaSoThemDG();
      
      
      CuaSoThemDauSach cs5 = new CuaSoThemDauSach("Thêm sách");
      cs5.RunCuaSoThemDauSach();
      
      CuaSoSachNO cs6 = new CuaSoSachNO("");
      cs6.RunCuaSoSachNO();
      
	   CuaSoTKSach cs7 = new CuaSoTKSach("");
	   cs7.RunCuaSoTKSach(); */
	   
	   Register csFirst = new Register("Quản lý thư viện");
	   csFirst.showWindow();
	   
	 //  main2 fui = new main2("huhuh");
	   //fui.Runmain2();
	   
	   //main3 giu = new main3("haha");
	   //giu.Runmain3();
	   
      //STEP 4: Execute a query
     
     // stmt = conn.createStatement();
      //String sql;
//      sql = "SELECT id, first, last, age FROM Employees";
      //sql = "SELECT id,name,gender FROM members";
      //ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
     
     // while(rs.next()){
         //Retrieve by column name
       //  int id  = rs.getInt("id");
        // String name = rs.getString("name");
        // char gender = rs.getChars("gender");
         
         //Display values
         //System.out.print("ID: " + id);
         //System.out.print(", Age: " + age);
         //System.out.println(" NAme la: " + name);
         
      //}
      
      //STEP 6: Clean-up environment
      //rs.close();
      //stmt.close();
      //conn.close(); 
   //}catch(SQLException se){
     // se.printStackTrace();//Xu ly sai cho JDBC
   //}catch(Exception e){
     // e.printStackTrace();       //Xu ly Loi cho  Class.forName
   //}finally{
	 //  try{
       //  if(stmt!=null)
         //   stmt.close();
      //}catch(SQLException se2){}
     // try{
        // if(conn!=null)
       //     conn.close();
     // }catch(SQLException se){
       //  se.printStackTrace();
     // }
  // } 
  // System.out.println("Goodbye!");
}
}

