package action;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.OpponentDAO;
import model.Opponent;

public class OpponentAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	OpponentDAO oDao=new OpponentDAO();
	Opponent opponent=new Opponent();
	
	/* bug here: transaction conflict 
	* (several get the same opp_id waited and update its white rival for sevral times)*/
	/* when user clicks start and is in the queue, findopponent() will be operated to find its rival */
	public String findopponent(){
		 HttpServletRequest request = ServletActionContext.getRequest();
		 int opp_id;
		 if(request.getSession().getAttribute("myID")==null){
			 Map map=ActionContext.getContext().getSession();
		   	 map.put("mentioncontent","outoftime");
			 return"outoftime";
		 }
		 int id=(int) request.getSession().getAttribute("myID");
		 Map map=ActionContext.getContext().getSession();
	   	 map.put("IsQueued",1);
		 opponent=oDao.matchopponent();
		 if(opponent==null){
			 opponent=new Opponent();
			 opponent.setID_black(id);
			 java.sql.Timestamp con_time = new java.sql.Timestamp(System.currentTimeMillis());
			 opponent.setCon_time(con_time);
			 oDao.addopponent(opponent);
			 opp_id=oDao.matchopponent().getOpp_id();
			 int count=300;
			 while(count>0){
			 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 count--;
			if(oDao.IsMatched(opp_id)){
			   	 map.put("mycolor","black");
				break;
				}
			if((int)request.getSession().getAttribute("IsQueued")== 0){
				break;
				}
			 }
		 }
		 else{
			 opp_id=opponent.getOpp_id();
			 opponent.setID_white(id);
			 java.sql.Timestamp con_time = new java.sql.Timestamp(System.currentTimeMillis());
			 opponent.setCon_time(con_time);
			 oDao.updateopponent(opponent);
		   	 map.put("mycolor","white");
		 }
	   	 map.put("opponent_id",opp_id);
		 return "find";
	}
	
	/* delete user's record in the queue when user decided to cancel queuing */
	public String deleteopponent(){
		 HttpServletRequest request = ServletActionContext.getRequest();
		 int id=(int) request.getSession().getAttribute("myID");
		 oDao.deleteopponent(id);
		 Map map=ActionContext.getContext().getSession();
	   	 map.put("IsQueued",0);
		 return "delete";
	}

	/* when game ends, winner will be set through this method */
	public String setwinner(){
		 HttpServletRequest request = ServletActionContext.getRequest();
		 int opp_id=(int) request.getSession().getAttribute("opponent_id");
		 int winner=Integer.parseInt(request.getParameter("winner_num"));
		 opponent=oDao.getopponentbyopp(opp_id);
		 opponent.setWinner(winner);
		 oDao.updateopponent(opponent);
		 Map map=ActionContext.getContext().getSession();
	   	 map.put("mycolor",null);
		 return SUCCESS;
	}
}
