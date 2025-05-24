package membership.mvcboard;

public class BoardDTO
{
//	member 테이블 컬럼 (회원가입, 로그인)
	private String id;
	private String pass;
	private String name;
	private java.sql.Date regidate;
//	mvcboard 테이블 컬럼 (첨부형 게시판)
	private String idx;
	private String title;
	private String content;
	private java.sql.Date  postdate;
	private String ofile;
	private String sfile;
	private int downcount;
	private int visitcount;
	
	
//	getter / setter 설정
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getPass()
	{
		return pass;
	}
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public java.sql.Date getRegidate()
	{
		return regidate;
	}
	public void setRegidate(java.sql.Date regidate)
	{
		this.regidate = regidate;
	}
	public String getIdx()
	{
		return idx;
	}
	public void setIdx(String idx)
	{
		this.idx = idx;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public java.sql.Date getPostdate()
	{
		return postdate;
	}
	public void setPostdate(java.sql.Date postdate)
	{
		this.postdate = postdate;
	}
	public String getOfile()
	{
		return ofile;
	}
	public void setOfile(String ofile)
	{
		this.ofile = ofile;
	}
	public String getSfile()
	{
		return sfile;
	}
	public void setSfile(String sfile)
	{
		this.sfile = sfile;
	}
	public int getDowncount()
	{
		return downcount;
	}
	public void setDowncount(int downcount)
	{
		this.downcount = downcount;
	}
	public int getVisitcount()
	{
		return visitcount;
	}
	public void setVisitcount(int visitcount)
	{
		this.visitcount = visitcount;
	}
	
	
	
}
