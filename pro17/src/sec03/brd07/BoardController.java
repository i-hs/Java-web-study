package sec03.brd07;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class MemberController
 */
//@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	public static String ARTICLE_IMAGE_REPO = "/Users/ihyeonseung/Downloads";
	BoardService boardService;
	ArticleVO articleVO;
	
	public void init() throws ServletException{
		boardService = new BoardService();
		articleVO = new ArticleVO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session;
		String action = request.getPathInfo();
		System.out.println("action: "+action);
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			if(action == null) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board06/listArticles.jsp";
			}else if (action.contentEquals("/listArticles.do"))
			{
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board06/listArticles.jsp";
			}else if (action.contentEquals("/articleForm.do"))
			{
				nextPage = "/board06/articleForm.jsp";
			}else if (action.contentEquals("/addArticle.do"))
			{	
				int articleNO = 0;
				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				articleVO.setParentNO(0);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				if(imageFileName != null && imageFileName.length() != 0)
				{
				articleVO.setImageFileName(imageFileName);
				}
				articleNO = boardService.addArticle(articleVO);
				
				if(imageFileName != null && imageFileName.length() != 0)
				{
					File srcFile = new File(ARTICLE_IMAGE_REPO+"/"+"temp"+"/"+imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO+"/"+articleNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + " alert('새 글을 추가했습니다.');"
						+ " location.href='" + request.getContextPath() +"/board/listArticles.do';"
						+"</script>"
						);
				return;
			}else if(action.equals("/viewArticle.do")){
				String articleNO = request.getParameter("articleNO");
				articleVO = boardService.viewArticle(Integer.parseInt(articleNO));
				request.setAttribute("article", articleVO);
				nextPage = "/board06/viewArticle.jsp";
				
			}else if(action.contentEquals("/modArticle.do")) {
				Map<String, String> articleMap = upload(request, response);
				int articleNO = Integer.parseInt(articleMap.get("articleNO"));
				articleVO.setArticleNO(articleNO);
				
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				
				articleVO.setParentNO(0);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				
				String imageFileName = articleMap.get("imageFileName");
				if (imageFileName != null)
				{
				articleVO.setImageFileName(imageFileName);
				}
				
				boardService.modArticle(articleVO);
							
				if(imageFileName != null && imageFileName.length() != 0)
				{
					String originalFileName = articleMap.get("originalFileName");
					File tempPath = new File(ARTICLE_IMAGE_REPO+"/"+"temp");
					tempPath.mkdirs();  // temp folder 경로가 없으면 생성
					
					File srcFile = new File(ARTICLE_IMAGE_REPO+"/"+"temp"+"/"+imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO+"/"+articleNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					
					File oldFile = new File(ARTICLE_IMAGE_REPO + "/" + articleNO + "/" 
							+ originalFileName);
					oldFile.delete();
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + " alert('새 글을 수정했습니다.');"
						+ " location.href='" + request.getContextPath() 
						+ "/board/viewArticle.do?articleNO="
						+ articleNO + "';"
						+"</script>"
						);
				return;
			
			}else if (action.contentEquals("/removeArticle.do"))
			{
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));
				List<Integer> articleNOList = boardService.removeArticle(articleNO);
				for(int _articleNO : articleNOList)
				{
					File imgDir = new File(ARTICLE_IMAGE_REPO+"/"+ _articleNO);
					if(imgDir.exists())
					{
						FileUtils.deleteDirectory(imgDir);
					}
					
					PrintWriter pw = response.getWriter();
					pw.print("<script>" + "alert('글을 삭제했습니다.');" + "location.href='"
							+ request.getContextPath() + "/board/listArticles.do';"
							+ "</script>"
							);
					return;
				}
			}else if (action.contentEquals("/replyForm.do"))
			{	
				System.out.println("request.getParameter(\"parentNO\"):"+request.getParameter("parentNO"));
				int parentNO = Integer.parseInt(request.getParameter("parentNO"));
				session = request.getSession();
				session.setAttribute("parentNO", parentNO);
				nextPage = "/board06/replyForm.jsp";
				
			}else if(action.contentEquals("/addReply.do")) 
			{	
				session = request.getSession();
				int parentNO = (Integer) session.getAttribute("parentNO");
				session.removeAttribute("parentNO");

				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				articleVO.setParentNO(parentNO);
				articleVO.setId("lee");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				if(imageFileName != null && imageFileName.length() != 0)
				{
				articleVO.setImageFileName(imageFileName);
				}
				int articleNO = boardService.addReply(articleVO);
				
				if(imageFileName != null && imageFileName.length() != 0)
				{
					File srcFile = new File(ARTICLE_IMAGE_REPO+"/"+"temp"+"/"+imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO+"/"+articleNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + " alert('답글을 추가했습니다.');"
						+ " location.href='" + request.getContextPath() 
						+"/board/viewArticles.do?articleNO="+ articleNO 
						+"';" + "</script>"
						);
				return;
			}else
			{
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board06/listArticles.jsp";
			}
			
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) 
										throws ServletException, IOException
	{
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try
		{
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++)
			{
				FileItem fileItem = (FileItem) items.get(i);
				if(fileItem.isFormField())
				{
					System.out.println(fileItem.getFieldName()
							+ "="+ fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}else {
					System.out.println("파라미터이름: "+ fileItem.getFieldName());
					System.out.println("파일이름: "+ fileItem.getName());
					System.out.println("파일크기: "+ fileItem.getSize()+"bytes");
					if(fileItem.getSize()>0)
					{
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx==-1)
						{
							idx = fileItem.getName().lastIndexOf("/");
						}
						
						String fileName = fileItem.getName().substring(idx + 1);
						articleMap.put(fileItem.getFieldName(), fileName);

						/* temp folder가 미리 만들어져있지 않았기 때문에 모든 것이 실행이 안됨.*/
						File tempFolderPath = new File(currentDirPath + "/temp/");
						tempFolderPath.mkdirs();
						
						File uploadFile = new File(currentDirPath + "/temp/" + fileName);
						fileItem.write(uploadFile);
						
						
					} // end if
				}  // end else
			}  // end for  
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return articleMap;
		
	}
		
}
