package utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
public class JDBC {
	String driver = "oracle.jdbc.OracleDriver";  
    String url = "jdbc:Oracle:thin:@localhost:1521:orcl";  
    Statement stmt = null;  
    ResultSet res = null;  
    Connection conn = null;  
    CallableStatement proc = null;
    PreparedStatement pstmt = null;
    
    public JDBC(){
    	try { 
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "dangjian", "dangjian");  
	        stmt = conn.createStatement();
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
    }
    public  void insert(String tableName,Map<String,String> map) {
    	StringBuilder sbCol = new StringBuilder();
    	StringBuilder sbVal = new StringBuilder();
    	for(String key:map.keySet()){
    		String value = map.get(key);
    		sbCol.append("\"" + key + "\",");
    		sbVal.append("'" + value + "',");
    	}
    	String colString = sbCol.substring(0,sbCol.length()-1);
    	String valString = sbVal.substring(0,sbVal.length()-1);
    	String sql = "insert into " + tableName +" ("+ colString +") values("+ valString +")";
    	try{
    		stmt.execute(sql);
    		//System.out.println(sql);
    	}catch(Exception e){
    		//e.printStackTrace();
    		System.out.println(sql+";");
    	}
    	
    	
    }
	public  Map getHidAndSfz(String xm){
		Map result = new HashMap<String, String>();
        String sql = "";  
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
			//res.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
