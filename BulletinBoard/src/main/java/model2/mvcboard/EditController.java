package model2.mvcboard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/mvcboard/edit.do")
@MultipartConfig( // 파일 업로드를 위한 multipart 설정
		maxFileSize = 1024 * 1024 * 1,
		maxRequestSize = 1024 * 1024 * 10
		)
public class EditController extends HttpServlet
{
	private static final long serialVersionUID = 1L; // 없으면 경고 뜸
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		String idx = req.getParameter("idx");
		MVCBoardDAO dao = new MVCBoardDAO();
		MVCBoardDTO dto = dao.selectView(idx); // 기존 게시물 내용을 담을 DTO 객체 생성
		req.setAttribute("dto", dto); // 인출된 내용은 request 영역에 저장 
		req.getRequestDispatcher("/14MVCBoard/Edit.jsp").forward(req, resp);
	}
	
//	게시물 수정처리 및 파일 업로드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
//		1. 파일 업로드 처리 
		String saveDirectory =  req.getServletContext().getRealPath("/Uploads");
//		파일 업로드
		String originalFileName = "";
		try
		{
			originalFileName = FileUtil.uploadFile(req, saveDirectory);
		} catch (Exception e)
		{
			JSFunction.alertBack(resp, "파일업로드 오류입니다.");
			return;
		}
		
//		2. 파일 업로드 외 처리 
		String idx = req.getParameter("idx");
		String prevOfile = req.getParameter("prevOfile");
		String prevSfile = req.getParameter("prevSfile");
		
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
//		비밀번호는 세션에서 가져오기
		HttpSession session = req.getSession();
		String pass = (String)session.getAttribute("pass");
		
//		DTO에 저장 
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setIdx(idx);
		dto.setName(name);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setPass(pass);
		
//		원본파일명과 저장된 파일 으름 설정
		if (originalFileName != "")
		{
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
			dto.setOfile(originalFileName); // 원본파일 이름 저장 
			dto.setSfile(savedFileName); // 서버에 저장된 파일 이름
			
//			기존 파일 삭제해줘야 함
			FileUtil.deleteFile(req, "/Uploads", prevSfile);
		} else
		{
			dto.setOfile(prevOfile);
			dto.setSfile(prevSfile);
		}
//		DB에 수정 내용 반영
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.updatePost(dto);
		dao.close();
		
//		성공 or 실패
		if(result == 1) {
//			수정 성공
			session.removeAttribute("pass"); 
			resp.sendRedirect("../mvcboard/view.do?idx=" + idx);
		} else {
			JSFunction.alertLocation(resp, "비밀번호 검증을 다시 진행해주세요.", "../mvcboard/view.do?idx=" + idx);
		}
	}
}
