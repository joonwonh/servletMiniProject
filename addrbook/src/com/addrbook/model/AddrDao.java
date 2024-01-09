package com.addrbook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AddrDao {
	private static AddrDao instance = new AddrDao();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    public AddrDao() {
    	
    }
 
    public static AddrDao getInstance()	{
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

    public ArrayList<AddrDto> getList()	{
    	ArrayList<AddrDto> addrList = new ArrayList<>();
    	
    	try	{
    		conn = getConnection();
    		pstmt = conn.prepareStatement("SELECT * FROM addrbook");
    		rs = pstmt.executeQuery();
    		
    		while(rs.next())	{
    			int id = rs.getInt("ab_id");
    			String name = rs.getString("ab_name");
    			String email = rs.getString("ab_email");
    			String comdept = rs.getString("ab_comdept");
    			String birth = rs.getString("ab_birth");
    			String tel = rs.getString("ab_tel");
    			String memo = rs.getString("ab_memo");
    			  			
    			addrList.add(new AddrDto(id, name, email, comdept, birth, tel, memo));
    			
    		}
    		rs.close();
			pstmt.close();
			conn.close();
    	}catch(SQLException e)	{
    		e.printStackTrace();
    	}
		return addrList;
    }
    
    
	public void insertAddr(AddrDto addr) {
		try	{
			conn = getConnection();
			pstmt = conn.prepareStatement("INSERT INTO addrbook VALUES(addr_seq.NEXTVAL, ?,?,?,?,?,?)");
			pstmt.setString(1, addr.getName());
			pstmt.setString(2, addr.getEmail());
			pstmt.setString(3, addr.getComdept());
			pstmt.setString(4, addr.getBirth());
			pstmt.setString(5, addr.getTel());
			pstmt.setString(6, addr.getMemo());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(SQLException e)	{
			e.printStackTrace();
		}
	}
	
	public AddrDto readById(int id)		{
		AddrDto addrDto = null;
        
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM addrbook WHERE ab_id = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("ab_name");
                String email = rs.getString("ab_email");
                String comdept = rs.getString("ab_comdept");
                String birth = rs.getString("ab_birth");
                String tel = rs.getString("ab_tel");
                String memo = rs.getString("ab_memo");

                addrDto = new AddrDto();
                addrDto.setId(id);
                addrDto.setName(name);
                addrDto.setEmail(email);
                addrDto.setComdept(comdept);
                addrDto.setBirth(birth);
                addrDto.setTel(tel);
                addrDto.setMemo(memo);
            }
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
            rs.close();
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addrDto;
	}
	
	public void updateAddr(AddrDto addr) {
		try	{
            conn = getConnection();
            pstmt = conn.prepareStatement("UPDATE addrbook SET ab_name=?, ab_email=?, ab_comdept=?, ab_birth=?, ab_tel=?, ab_memo=? WHERE ab_id=?");
            pstmt.setString(1, addr.getName());
            pstmt.setString(2, addr.getEmail());
            pstmt.setString(3, addr.getComdept());
            pstmt.setString(4, addr.getBirth());
            pstmt.setString(5, addr.getTel());
            pstmt.setString(6, addr.getMemo());
            pstmt.setInt(7, addr.getId());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(SQLException e)	{
			e.printStackTrace();
		}
	}

	
	public void delAddr(int id)	{
		try {
        conn = getConnection();
        pstmt = conn.prepareStatement("DELETE FROM addrbook WHERE ab_id=?");
        pstmt.setInt(1, id);

        pstmt.executeUpdate();

		}catch(SQLException e)	{
			e.printStackTrace();
	}
	}
}
	

