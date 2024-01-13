package com.javaEdu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javaEdu.dto.MemberDto;

public class MemberDao {

	private static MemberDao instance = new MemberDao();	// singleton pattern
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
	private MemberDao()	{
		
	}
	public static MemberDao getInstance()	{
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
	
	public int insertMember(MemberDto dto)	{
		int ri = 0;
		try	{
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into members values(?,?,?,?,?,?)");
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setTimestamp(5, dto.getrDate());
			pstmt.setString(6, dto.getAddress());
			
			pstmt.executeUpdate();
			ri =1;
		}catch(Exception e)	{
			e.printStackTrace();
		}finally	{
			try	{
				if(pstmt !=null)	pstmt.close();
				if(conn != null)		conn.close();				
				
			}catch(Exception e2)	{
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public int confirmId(String id)	{
		int ri =0;
		try	{
			conn = getConnection();
			pstmt = conn.prepareStatement("select id from members where id =?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())	{
				ri=1;
			}else	{
				ri=0;
			}
			
		}catch(Exception e)	{
			e.printStackTrace();
		}finally	{
			try	{
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e2)	{
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int userCheck(String id, String pw)	{
		int ri =0;
		String dbPw;
		
		try	{
			
			conn = getConnection();
			pstmt = conn.prepareStatement("select pw from members where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
		
			if(rs.next())	{
				dbPw = rs.getString("pw");
				if(dbPw.equals(pw))	{
					ri=1;
				}else	{
					ri=0;
				}
			}else
				ri=-1;
			
		}catch(Exception e)	{
			e.printStackTrace();
		}finally	{
			try	{
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e2)	{
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public MemberDto getMember(String id)	{
		MemberDto dto = null;
		
		try	{

			conn = getConnection();
			pstmt = conn.prepareStatement("select * from members where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())	{
				dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setrDate(rs.getTimestamp("rDate"));
				dto.setAddress(rs.getString("address"));
			}
			
		}catch(Exception e)	{
			e.printStackTrace();
		}finally	{
			try	{
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e2)	{
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public int updateMember(MemberDto dto)	{
		int ri=0;
		
		try	{
			conn = getConnection();
			pstmt = conn.prepareStatement("update members set pw=?, email=?, address=? where id=?");
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getId());
			ri = pstmt.executeUpdate();

		}catch(Exception e)	{
			e.printStackTrace();
		}finally	{
			try	{
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e2)	{
				e2.printStackTrace();
			}
		}

		return ri;
	}
	
	public List<MemberDto> membersAll()	{

		List<MemberDto> dto = new ArrayList<>();
		
		
		try	{
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from members");
			rs = pstmt.executeQuery();
			
			System.out.println("================");
			while(rs.next())	{
				MemberDto dtos = new MemberDto();
				dtos.setId(rs.getString("id"));
				dtos.setPw(rs.getString("pw"));
				dtos.setName(rs.getString("name"));
				dtos.setEmail(rs.getString("email"));
				dtos.setrDate(rs.getTimestamp("rDate"));
				dtos.setAddress(rs.getString("address"));
				dto.add(dtos);
			}
			System.out.println("------------------------------------------");

		}catch(Exception e)	{
			e.printStackTrace();
		}finally	{
			try	{
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e2)	{
				e2.printStackTrace();
			}
		}
		
		return dto;
		
	}
	
}
