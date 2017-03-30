package dao;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sun.org.apache.xpath.internal.operations.And;

import Util.HibernateUtil;
import model.Piece;

public class PieceDAO {
	
		/*
		 * function:void addpiece(Piece piece)
		 * use:add a complete piece tuple into Piece table;
		 */
		public void addpiece(Piece piece){
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.getTransaction();
			tx.begin();
			session.save(piece);
			tx.commit();
			//session.close();
		}
		
		/*
		 * function:deletepiece(integer opp_id,integer piece_id)
		 * use:delete specific piece through primary specific (opp_id , piece_id) pair;
		 */
		public void deletepiece(int opp_id,int piece_id){
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from Piece piece where piece.opp_id=:req_name and piece.piece_id=:req_piece");
			query.setInteger("req_name", opp_id);
			query.setInteger("req_piece", piece_id);
			List<Piece> list = query.list();
			session.delete(list.get(0));
			tx.commit();
			//session.close();
		}
		
		
		/*
		 * function: getpiece (integer opp_id,integer piece_id)
		 * use:get specific piece;
		 */
		public Piece  getpiece (int opp_id,int piece_id){
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			Query query = session.createQuery("from Piece piece where piece.opp_id=:req_name and piece.piece_id=:req_piece");
			query.setInteger("req_name", opp_id);
			query.setInteger("req_piece", piece_id);
			List<Piece> list = query.list();
			session.getTransaction().commit();
			//session.close();
			if(list.isEmpty()) return null;
			else return list.get(0);
		}
		
		/*
		 * function: getpieceid (integer opp_id)
		 * use:get max pieceid(return 0 if empty );
		 */
		public int getpieceid(int opp_id){
			int pieceid=0;
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			Query query = session.createSQLQuery("select * from pieces where pieces.opp_id="+opp_id+" and piece_id = (select max(piece_id)from pieces where pieces.opp_id="+opp_id+" and pieces.x_cordinate>-1)");
			List<Object[]> result = query.list();
			session.getTransaction().commit();			
			if(result.isEmpty()){
				return 0;
			}
			else{
				for (Object[] row : result) {
					pieceid=(int)row[1];
				}
			}
			return pieceid;
		}

}
