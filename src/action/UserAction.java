package action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Password;
import org.hibernate.Session;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserDAO;
import model.User;

public class UserAction  extends ActionSupport {
	private static final long serialVersionUID = 1L;

	UserDAO uDao=new UserDAO();
	User user;
	private List<User> users;
	private List<User> jsonResult;
	String jsonusers;
	
	
	/**
	 * @return the jsonusers
	 */
	public String getJsonusers() {
		return jsonusers;
	}
	/**
	 * @param jsonusers the jsonusers to set
	 */
	public void setJsonusers(String jsonusers) {
		this.jsonusers = jsonusers;
	}
	/**
	 * @return the jsonResult
	 */
	public List<User> getJsonResult() {
		return jsonResult;
	}
	/**
	 * @param jsonResult the jsonResult to set
	 */
	public void setJsonResult(List<User> jsonResult) {
		this.jsonResult = jsonResult;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	/* add user into database */
	 public String add() {
		 	HttpServletRequest request = ServletActionContext.getRequest();
		 	String pwd_cof=request.getParameter("password_confirm");
		 	String agestr=request.getParameter("userage");
		 	int age;
		 	try {
				age=Integer.parseInt(agestr);
			} catch (Exception e) {
				/*means "userage" is not a valid number */
				age=999;
			}
		 	int len=user.getPassword().length();
		 	int ulen=user.getName().length();
		 	if(!(age>=0&&age<150)){
		 		Map map=ActionContext.getContext().getSession();
				map.put("mentioncontent", "invalidage");
				return "fail";
		 	}
		 	user.setAge(age);
		 	if(!pwd_cof.equals(user.getPassword())){
		 		Map map=ActionContext.getContext().getSession();
				map.put("mentioncontent", "twopwddifferent");
				return "fail";
		 	}
		 	if(len>12||ulen>12){
		 		Map map=ActionContext.getContext().getSession();
				map.put("mentioncontent", "pwdorusnametoolong");
				return "fail";
		 	}
		 	for(int i=0;i<len;i++){
		 		char now=user.getPassword().charAt(i);
		 		if(!((now>='0'&&now<='9')||(now>='a'&&now<='z')||(now>='A'&&now<='Z')||now=='~'||now=='!'||now=='_')){
				Map map=ActionContext.getContext().getSession();
				map.put("mentioncontent", "invalidchar");
				return "fail";
		 		}
		 	}
		 	if(uDao.getuser(user.getName())==null){
		 		 uDao.adduser(user);
		 		Map map=ActionContext.getContext().getSession();
				map.put("mentioncontent", "successregister");
			    return "add";
		 	}
		 	else{
		 		Map map=ActionContext.getContext().getSession();
				map.put("mentioncontent", "repeatregister");
		 		return "fail";
		 	}
	    }
	 
		/* update a user's information */
	    public void updateuser() {
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	int age=Integer.parseInt(request.getParameter("age"));
			String name=request.getParameter("name");
			String pwd=request.getParameter("password");
			int role=Integer.parseInt(request.getParameter("role"));
			user.setAge(age);
			user.setPassword(pwd);
			user.setRole(role);
	        uDao.updateuser(user);
	    }
	    
	    /* delete a user */
	    public void deleteuser() {
	        uDao.deleteuser(user.getName());
	    }
	    
	    /* list all users */
	    public String listusers(){
			jsonResult=uDao.listuser();
			return SUCCESS;
		}
		
	    /* check whether a user who is logging in is using the valid username and password */
	    public String check() throws IOException {
			 HttpServletResponse response = ServletActionContext.getResponse();  
			 String pwd=user.getPassword();
			 user=uDao.getuser(user.getName());
			if(user==null){
				Map map=ActionContext.getContext().getSession();
				map.put("mentioncontent", "wrongname");
				return"fail";
			}
			else{
				if(!user.getPassword().equals(pwd)) {
					Map map=ActionContext.getContext().getSession();
					map.put("mentioncontent", "wrongpwd");
					return"fail";
				}
				else{
					int role=user.getRole();
					Map map=ActionContext.getContext().getSession();
					map.put("myname", user.getName());
					map.put("myID", user.getId());
					map.put("myrole", role);
					map.put("myage", user.getAge());
					if(role==1) {
						map.put("myroles", "manager");
						return"manager";
					}
					else{
						map.put("myroles", "player");
						return"buyer";
					}
				}
			}
		}
		
	    /* clean the session when logout */
	    public String logout() {
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	request.getSession().invalidate();
	    	return "success";
		}
}	
