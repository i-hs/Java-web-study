package sec02.ex01;

//import java.sql.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	
	/*
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user = "scott";
	private static final String pwd = "tiger";
	 */
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> listMembers(MemberVO memberVO){
		List<MemberVO> list = new ArrayList<MemberVO>();
		String _name = memberVO.getName();
		try {
//			connDB();
			con= dataFactory.getConnection();
			String query = "select * from t_member ";
			
			if((_name != null && _name.length() != 0)) {
				query+=" where name=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1,  _name);
			}else {
				pstmt = con.prepareStatement(query);
			}			
			System.out.println("prepareStatement: "+query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addMember(MemberVO memberVO) {
		try
		{
			con = dataFactory.getConnection();
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			
			String query = "insert into t_member";
			query += "(id, pwd, name, email)";
			query += "values(?, ?, ?, ?)";
			System.out.println("prepareStatement: "+query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delMember(String id)
	{
		try
		{
			con = dataFactory.getConnection();
			
			String query = "delete from t_member" + " where id=?";
			System.out.println("prepareStatement: "+query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,  id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean isExisted(MemberVO memberVO) {
		boolean result = false;
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		
		try {
			con = dataFactory.getConnection();
			String query = "select decode(count(*), 1, 'true', 'false') as result from t_member ";
			query += "where id=? and pwd=?";
			System.out.println("query: "+ query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			rs.next();  // 커서를 첫 번째 레코드로 위치시킴.
			result = Boolean.parseBoolean(rs.getString("result"));
			System.out.println("result: "+result);
			
		}catch(Exception e) {
			e.getStackTrace();
		}
		return result;
	}
	/*
	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}*/
}
