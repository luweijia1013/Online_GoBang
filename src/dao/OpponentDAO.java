package dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sun.jmx.snmp.Timestamp;

import model.Opponent;
import model.User;
import Util.HibernateUtil;

public class OpponentDAO {
	
	/*
	 * function:addoponent(Opponent opponent)
	 * use:add a complete opponent tuple into Opponent table;
	 */
	public void addopponent(Opponent opponent){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		session.save(opponent);
		tx.commit();
		//session.close();
	}
	
	/*
	 * function:deleteopponent(integer id)
	 * use:delete opponent tuple which ID_black=id and ID_black=0;
	 */
	public void deleteopponent(int id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Query query = session.createQuery("from Opponent opponent where opponent.ID_black=:req_name and opponent.ID_white=0");
		query.setInteger("req_name", id);
		List<Opponent> list = query.list();
		session.delete(list.get(0));
		tx.commit();
		//session.close();
	}
	
	/*
	 * function:updateopponent(Opponent opponent)
	 * use:update opponent tuple into the Opponent object input;
	 */
	public void updateopponent(Opponent opponent){
		List<Opponent> result= new ArrayList();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Query query= session.createQuery("from Opponent opponent where opponent.opp_id=:req_name");
		query.setInteger("req_name", opponent.getOpp_id());
		result=query.list();
		result.get(0).setID_black(opponent.getID_black());
		result.get(0).setID_white(opponent.getID_white());
		result.get(0).setWinner(opponent.getWinner());
		result.get(0).setCon_time(opponent.getCon_time());
		session.save(result.get(0));
		tx.commit();
		//session.close();
	}
	
	/*
	 * function:getopponents (integer userID)
	 * use:get rivals the "userID" met in history;
	 */
	public List<Opponent>  getopponents (int userID){
		Opponent result=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		Query query = session.createQuery("from Opponent opponent where opponent.ID_black=:req_name or opponent.ID_white=:req_name");
		query.setInteger("req_name", userID);
		List<Opponent> list = query.list();
		session.getTransaction().commit();
		//session.close();
		if(list.isEmpty()) return null;
		else return list;
	}
	
	/*
	 * function:getopponents (integer opp_id)
	 * use:get specific opponent tuple;
	 */
	public Opponent  getopponentbyopp (int opp_id){
		Opponent result=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		Query query = session.createQuery("from Opponent opponent where opponent.opp_id=:req_name");
		query.setInteger("req_name", opp_id);
		List<Opponent> list = query.list();
		session.getTransaction().commit();
		//session.close();
		if(list.isEmpty()) return null;
		else return list.get(0);
	}
	
	/*
	 * function:matchopponent ()
	 * use:get rival waited in queue;
	 */
	public Opponent  matchopponent (){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		Query query = session.createSQLQuery("select * from Opponent where opponent.ID_white=0");
		//query.setInteger("req_int", 0);
		List<Object[]> result = query.list();
		List<Opponent> opponents=new ArrayList<>();
		for(Object[] row:result){
			Opponent o=new Opponent();
			o.setOpp_id((int)row[0]);
			o.setID_black((int)row[1]);
			o.setID_white((int)row[2]);
			o.setWinner((int)row[3]);
			o.setCon_time((java.sql.Timestamp)row[4]);
			opponents.add(o);
		}
		session.getTransaction().commit();
		//session.close();
		if(opponents.isEmpty()) { return null;}
		else { return (Opponent)opponents.get(0);}
	}
	
	
	/*
	 * function: boolean  IsMatched (int opp_id)
	 * use:check whether the opp_id has found the rival;
	 */
	public boolean  IsMatched (int opp_id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		Query query = session.createSQLQuery("select * from Opponent where opponent.opp_id=:req_int");
		query.setInteger("req_int", opp_id);
		List<Object[]> result = query.list();
		session.getTransaction().commit();
		for(Object[] row:result){
			if((int)row[2]!=0)
				return true;
		}
		return false;
	}
	
}
