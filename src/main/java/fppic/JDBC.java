package fppic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
public class JDBC {
	String driver = "oracle.jdbc.OracleDriver";  
    String url = "jdbc:Oracle:thin:@172.168.1.156:1521:orcl";  
    Statement stmt = null;  
    ResultSet res = null;  
    Connection conn = null;  
    CallableStatement proc = null;
    
    public JDBC(){
    	try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "fupin", "fupin");  
	        stmt = conn.createStatement();  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
    }
	public  Map getHidAndSfz(String xm){
		Map result = new HashMap<String, String>();
        String sql = "select a.zjhm,a.hid from fpb_knhjb a "
        		   + " where a.szcun='"+Fppic.cunID+"' and a.nd='2017' and a.xm='"+xm+"'";  
        try {  
            
            res = stmt.executeQuery(sql);  
            while(res.next())  
            {  
                String ZJHM = res.getString("ZJHM");  
                String HID = res.getString("HID");  
                result.put("ZJHM", ZJHM);
                result.put("HID", HID);
            }  
        } catch (SQLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          return result;
	}
	public void close(){
		
		try {
			res.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
