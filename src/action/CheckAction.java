package action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CheckAction  extends ActionSupport {
	private static final long serialVersionUID = 1L;
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

	/* check whether a user is login */
	 public String checklogin(){
    	 HttpServletRequest request = ServletActionContext.getRequest();
    	 int islogin;
		 if(request.getSession().getAttribute("myID")==null){
			 islogin=0;
			 Map map=ActionContext.getContext().getSession();
		   	 map.put("mentioncontent","outoftime");
		   	jsonResult="{\"login\":"+islogin+"}";
			 return SUCCESS;
		 }
		 islogin=1;
		 jsonResult="{\"login\":"+islogin+"}";
		 
		 return SUCCESS;
    }
	 
	 /* check whether a user should be allowed into ppgame page according to its color information */
	  public String checkcolor(){
	    	 HttpServletRequest request = ServletActionContext.getRequest();
	    	 int iscolor;
			 if(request.getSession().getAttribute("mycolor")==null){
				 iscolor=0;
				 Map map=ActionContext.getContext().getSession();
			   	 map.put("mentioncontent","notinpp");
			   	jsonResult="{\"color\":"+iscolor+"}";
				 return SUCCESS;
			 }
			 iscolor=1;
			 jsonResult="{\"color\":"+iscolor+"}";
			 System.err.println(jsonResult);
			 return SUCCESS;
	    }
}
