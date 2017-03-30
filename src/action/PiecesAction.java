package action;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.eclipse.jdt.internal.compiler.classfmt.NonNullDefaultAwareTypeAnnotationWalker;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.PieceDAO;
import javassist.expr.NewArray;
import model.Piece;

public class PiecesAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	PieceDAO pDao=new PieceDAO();
	Piece piece=new Piece();
	String jsonResult;
	
	/**
	 * @return the jsonResult
	 */
	public String getJsonResult() {
		return jsonResult;
	}

	/**
	 * @param jsonResult the jsonResult to set
	 */
	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	
	/* when user makes a piece or give up or overtime, a piece will be stored into database through this function */
	public void storepiece() {
		HttpServletRequest request = ServletActionContext.getRequest();
     	int x=Integer.parseInt(request.getParameter("x_cordinate"));
		int y=Integer.parseInt(request.getParameter("y_cordinate"));
		int opp_id=(Integer) request.getSession().getAttribute("opponent_id");
		java.sql.Timestamp con_time = new java.sql.Timestamp(System.currentTimeMillis());
		piece.setOpp_id(opp_id);
		if(x==-1){
			int co=0;
			String mycolor=(String) request.getSession().getAttribute("mycolor");
			int max=pDao.getpieceid(opp_id);
			if(mycolor.equals("white")) piece.setPiece_id(max-(max%2)+2);
			if(mycolor.equals("black")) piece.setPiece_id(max-((max+1)%2)+2);
		}
		else {piece.setPiece_id(pDao.getpieceid(opp_id)+1);}
		piece.setX_cordinate(x);
		piece.setY_cordinate(y);
		piece.setCon_time(con_time);
		pDao.addpiece(piece);
		return ;
	}
	
	/* function that get the piece rival click */
	public String waitpiece() {
		HttpServletRequest request = ServletActionContext.getRequest();
     	int waitnum=Integer.parseInt(request.getParameter("wait_num"));
     	int opp_id=(Integer) request.getSession().getAttribute("opponent_id");
     	int count=120-2;/*120 means time limit which is 60s, minusing 2 is the way to fix deviation*/
     	while(piece==null||piece.getPiece_id()==0){
     		if(count==0){
     			Piece piece=new Piece();
     			piece.setOpp_id(opp_id);
     			piece.setCon_time(new java.sql.Timestamp(System.currentTimeMillis()));
     			piece.setX_cordinate(-1);
     			piece.setY_cordinate(-1);
     			piece.setPiece_id(pDao.getpieceid(opp_id)+1);
     			pDao.addpiece(piece);
     		}
     		count--;
     		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     		piece=pDao.getpiece(opp_id, waitnum);
     	}
     	jsonResult="{\"x\":"+piece.getX_cordinate()+",\"y\":"+piece.getY_cordinate()+"}";
     	return SUCCESS;
	}
}