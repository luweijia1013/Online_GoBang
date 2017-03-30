package dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.User;
import Util.HibernateUtil;

public class UserDAO {
	/* add a user object to database */
	public void adduser(User user){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		tx.begin();
			session.save(user);
		tx.commit();
		//session.close();
	}
	
	/* get a user object from database according to username */
	public User getuser(String username){
		User result=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		Query query = session.createQuery("from User user where user.name=:req_name");
		query.setString("req_name", username);
		List<User> list = query.list();
		session.getTransaction().commit();
		//session.close();
		if(list.isEmpty()) return null;
		else {
			result=(User)list.get(0);
			return result;
		}
	}
	
	/* delete a user from database according to username */
	public void deleteuser(String username){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Query query = session.createQuery("from User user where user.name=:req_name");
				query.setString("req_name", username);
		List<User> list = query.list();
		session.delete(list.get(0));
		tx.commit();
		//session.close();
	}
	
	/* update a user in database which has the same username with parameter user's username */
	public void updateuser(User user){
		List<User> result= new ArrayList();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Query query= session.createQuery("from User user where user.name=:req_name");
		query.setString("req_name", user.getName());
		result=query.list();
		result.get(0).setAge(user.getAge());
		result.get(0).setPassword(user.getPassword());
		result.get(0).setRole(user.getRole());
		session.save(result.get(0));
		tx.commit();
		//session.close();
	}
	
	/* get all users from database */
	public List<User> listuser(){
		List<User> result= new ArrayList();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Query query= session.createQuery("from User user");
		result=query.list();
		tx.commit();
		return result;
	}
}
