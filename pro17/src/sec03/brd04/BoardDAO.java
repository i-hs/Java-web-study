package sec03.brd04;

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


public class BoardDAO {
	
	/*
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user = "scott";
	private static final String pwd = "tiger";
	 */
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ArticleVO> selectAllArticles(){
		List<ArticleVO> articlesList = new ArrayList<ArticleVO>();		
		try {
//			connDB();
			con= dataFactory.getConnection();
			String query = "SELECT LEVEL, " + 
					"articleNO, " + 
					"parentNO, " + 
					"LPAD(' ', 4*(LEVEL-1)) || title title, " + 
					"content, " + 
					"writeDate, " + 
					"id " + 
					"from t_board " + 
					"start with parentNO=0 " + 
					"connect by prior articleNO = parentNO " + 
					"order siblings by articleNO desc";
			
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}
	
	private int getNewArticleNO()
	{
		try
		{
			con = dataFactory.getConnection();
			String query = "SELECT max(articleNO) from t_board ";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				return (rs.getInt(1) + 1);
			rs.close();
			pstmt.close();
			con.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		} 
		return 0;
	}

	public int insertNewArticle(ArticleVO article)
	{
		int articleNO = getNewArticleNO();
	try
	{
		con = dataFactory.getConnection();
		int parentNO = article.getParentNO();
		String title = article.getTitle();
		String content = article.getContent();
		String id = article.getId();
		String imageFileName = article.getImageFileName();
		String query = "INSERT INTO t_board (articleNO, parentNO, title, content, imageFileName,"
				+" id) VALUES (?, ?, ?, ?, ?, ?)";
		System.out.println(query);
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1,  articleNO);
		pstmt.setInt(2,  parentNO);
		pstmt.setString(3,  title);
		pstmt.setString(4,  content);
		pstmt.setString(5,  imageFileName);
		pstmt.setString(6,  id);
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		
	}catch(Exception e) {
		e.printStackTrace();
		}
	return articleNO;
	}
	
	public ArticleVO selectArticle(int articleNO) {
		ArticleVO article = new ArticleVO();
		try
		{
			con = dataFactory.getConnection();
			String query = "select articleNO, parentNO, title, content, imageFileName, id, writeDate"
					+" from t_board"+" where articleNO=?";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1,  articleNO);			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int _articleNO = rs.getInt("articleNO");
			int parentNO = rs.getInt("parentNO");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String imageFileName = rs.getString("imageFileName");
			String id = rs.getString("id");
			Date writeDate = rs.getDate("writeDate");
			
			article.setArticleNO(_articleNO);
			article.setParentNO(parentNO);
			article.setTitle(title);
			article.setContent(content);
			article.setImageFileName(imageFileName);
			article.setId(id);
			article.setWriteDate(writeDate);
				
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	
}
