package customer.signup.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.sql.DataSource;

public class SignUpDAO {
	private static SignUpDAO instance = new SignUpDAO();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    public static SignUpDAO getInstance()	{
    	return instance;
    }
    
    public Connection getConnection()	{
    	
    	Context context = null;
    	DataSource dataFactory = null;
    	
    	try	{
    		context = new InitialContext();
    		dataFactory = (DataSource) context.lookup("java:/comp/env/jdbc/Oracle11g");
    		conn = dataFactory.getConnection();
    	
    	}catch(Exception e)	{
    		e.printStackTrace();
    	}
		return conn;
    }
    
    public void insertCustomer(SignUpDTO dto) {
    	try {
    		conn = getConnection();
			pstmt = conn.prepareStatement("INSERT INTO BOARD_CUSTOMER VALUES(?,?,?,?)");
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void conFirm(SignUpDTO dto)	{
    	try	{
    		conn = getConnection();
    		pstmt = conn.prepareStatement("SELECT id, pw FROM BOARD_CUSTOMER WHERE ID=? AND PW=?");
    		rs = pstmt.executeQuery();
    		
    		pstmt.setString(1, dto.getId());
    		pstmt.setString(2, dto.getPw());
    		
    		
    		while(rs.next())		{
    			String id = rs.getString("id");
    			String pw = rs.getString("pw");

        		

    		}

    		
    		
    	}catch(Exception e)	{
    		
    	}
    	
    }
    
    
    
    
    
}
