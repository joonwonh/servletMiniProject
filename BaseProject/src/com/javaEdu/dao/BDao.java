package com.javaEdu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javaEdu.dto.BDto;


public class BDao {

	DataSource dataFactory;
	Connection conn = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;
	
	public BDao()	{
		
    	try	{
    		Context context = new InitialContext();
    		context = new InitialContext();
    		dataFactory = (DataSource) context.lookup("java:/comp/env/jdbc/Oracle11g");
    		conn = dataFactory.getConnection();
    	
    	}catch(Exception e)	{
    		e.printStackTrace();
    	}
	}
	
	public void write(String bName, String bTitle, String bContent)	{
		
		try	{
			
			conn = dataFactory.getConnection();
			preparedStatement = conn.prepareStatement("insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)");	
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			int rn = preparedStatement.executeUpdate();
		}catch(Exception e)	{
			e.printStackTrace();
		}finally	{
			try	{
				if(preparedStatement != null) preparedStatement.close();
				if(conn != null)	conn.close();
			}catch(Exception e2)	{
				e2.printStackTrace();
			}
		}
	}
	
	public ArrayList<BDto>list()	{
		ArrayList<BDto> dtos = new ArrayList<>();
		
		try	{	
			conn = dataFactory.getConnection();
			preparedStatement = conn.prepareStatement("select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc");
			rs = preparedStatement.executeQuery();

			while(rs.next())	{
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
				
			}
		
		}catch(Exception e)	{
			e.printStackTrace();
		}finally	{
			try	{
				if(rs != null)	rs.close();
				if(preparedStatement != null) preparedStatement.close();
				if(conn != null)	conn.close();
			}catch(Exception e2)	{
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public BDto contentView(String strID)	{
		upHit(strID);
		
		BDto dto = null;
		
		try	{	
			conn = dataFactory.getConnection();
			preparedStatement = conn.prepareStatement("select * from mvc_board where bId=?");
			preparedStatement.setInt(1, Integer.parseInt(strID));
			rs = preparedStatement.executeQuery();

			if(rs.next())	{
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);				
			}
		
		}catch(Exception e)	{
			e.printStackTrace();
		}finally	{
			try	{
				if(rs != null)	rs.close();
				if(preparedStatement != null) preparedStatement.close();
				if(conn != null)	conn.close();
			}catch(Exception e2)	{
				e2.printStackTrace();
			}
		}
		return dto;
	}

	private void upHit(String bId) {

		try {
			conn = dataFactory.getConnection();
			preparedStatement = conn.prepareStatement("update mvc_board set bHit = bHit + 1 where bId = ?");
			
			preparedStatement.setInt(1, Integer.parseInt(bId));

			int rn = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally	{
			try	{
				if(preparedStatement != null) preparedStatement.close();
				if(conn != null)	conn.close();
			}catch(Exception e2)	{
				e2.printStackTrace();
			}
		}
		
	}

	public void modify(String bId, String bName, String bTitle, String bContent)	{
		
		try	{
			conn = dataFactory.getConnection();
			preparedStatement = conn.prepareStatement("update mvc_board set bName=?, bTitle=?, bContent=? where bId=?");	
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bId));
			int rn = preparedStatement.executeUpdate();
		}catch(Exception e)	{
			e.printStackTrace();
		}finally	{
			try	{
				if(preparedStatement != null) preparedStatement.close();
				if(conn != null)	conn.close();
			}catch(Exception e2)	{
				e2.printStackTrace();
			}
		}
	}
	
	public void delete(String bId)	{
		try	{
			conn = dataFactory.getConnection();
			preparedStatement = conn.prepareStatement("delete mvc_board where bId=?");	
			preparedStatement.setInt(1, Integer.parseInt(bId));
			int rn = preparedStatement.executeUpdate();
		}catch(Exception e)	{
			e.printStackTrace();
		}finally	{
			try	{
				if(preparedStatement != null) preparedStatement.close();
				if(conn != null)	conn.close();
			}catch(Exception e2)	{
				e2.printStackTrace();
			}
		}
	}
	
	public BDto reply_view(String str)	{
		BDto dto = null;
		
		try {
			conn = dataFactory.getConnection();
			preparedStatement = conn.prepareStatement("select * from mvc_board where bId=?");
			preparedStatement.setInt(1, Integer.parseInt(str));
			rs = preparedStatement.executeQuery();
			
			if(rs.next())	{
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");	// 원래 그룹 number, 즉 원래글과 답변글의 하나의 그룹 number임
				int bStep = rs.getInt("bStep");	// 원래 글에서 아래로 몇번째 답글이 될것인지를 나타내는 number임
				int bIndent = rs.getInt("bIndent");	// 답변글이 얼마만큼 안으로 들어가서 쓰여질 것인지를 나타내는 number임
				
				System.out.println("bGroup : " + bGroup);
				System.out.println("bStep : " + bStep);
				System.out.println("bIndent : " + bIndent);
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally	{
			try	{
				if(preparedStatement != null) preparedStatement.close();
				if(conn != null)	conn.close();
			}catch(Exception e2)	{
				e2.printStackTrace();
			}
		}
		return dto;
	}

	public void reply(String bId, String bName,  String bTitle,  String bContent,  String bGroup,  String bStep,  String bIndent)	{
		
		replyShape(bGroup, bStep);
		
		try {
			conn = dataFactory.getConnection();
			preparedStatement = conn.prepareStatement("insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?");
			
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bGroup));
			preparedStatement.setInt(5, Integer.parseInt(bStep) +1);
			preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1);

			int rn = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally	{
			try	{
				if(preparedStatement != null) preparedStatement.close();
				if(conn != null)	conn.close();
			}catch(Exception e2)	{
				e2.printStackTrace();
			}
		}				
	}

	private void replyShape(String strGroup, String strStep) {

		try {
			conn = dataFactory.getConnection();
			preparedStatement = conn.prepareStatement("update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?");	// 그룹은 같고(strGroup), 원래 글 step보다 큰 것들의 step 수를 하나씩 증가 시킴
			
			preparedStatement.setInt(1, Integer.parseInt(strGroup));
			preparedStatement.setInt(2, Integer.parseInt(strStep));

			int rn = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally	{
			try	{
				if(preparedStatement != null) preparedStatement.close();
				if(conn != null)	conn.close();
			}catch(Exception e2)	{
				e2.printStackTrace();
			}
		}		
		
	}
	
	
	
	
	
	
}
	

