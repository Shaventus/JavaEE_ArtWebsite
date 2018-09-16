package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import part.model.Account;

@Stateless
public class AccountDAO extends DAO<Account>{
	
	public AccountDAO(){
		setEntityClass(Account.class);
	}
	
	@Override
	public List<Account> getList(Map<String, Object> searchParams) {
		List<Account> list = null;

		String select = "select p ";
		String from = "from Account p ";
		String where = "";
		String orderby = "order by p.idAccount asc";
		
		int idaccount = (int) searchParams.get("idAccount");
		if (idaccount != 0) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.idaccount = :idaccount ";
		}
		
		String login = (String) searchParams.get("login");
		if (login != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.login like :login ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);


		if (idaccount != 0) {
			query.setParameter("idAccount", idaccount);
		}
		
		if (login != null) {
				query.setParameter("login", login);
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Account> lazyFunction(Map<String, Object> searchParams, PaginationInfo info) {
		List<Account> list = null;

		String select = "select p ";
		String from = "from Account p ";
		String where = "";
		String orderby = "order by p.idAccount asc";
		
		String login = (String) searchParams.get("login");
		if (login != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.login like :login ";
		}
		
		String email = (String) searchParams.get("email");
		if (email != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.email like :email ";
		}
		
		Query querycount = em.createQuery("SELECT COUNT(p.idAccount) " + from + where);
		
		if (login != null) {
			querycount.setParameter("login", login+"%");
		}
		
		if (email != null) {
			querycount.setParameter("email", email+"%");
		}
			
		try {
			Number n = (Number) querycount.getSingleResult();
			info.setCount(n.intValue());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Query query = em.createQuery(select + from + where);
		query.setFirstResult(info.getOffset());
		query.setMaxResults(info.getLimit());

		if (login != null) {
			query.setParameter("login", login+"%");
		}
		
		if (email != null) {
			query.setParameter("email", email+"%");
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public Account getLogin(String Login, String Password){
		Account list = null;
		Query query = em.createQuery("from Account p where p.login=:login and p.password=:password");
		query.setParameter("login", Login);
		query.setParameter("password", Password);
		
		try {
			list = (Account)query.getResultList().get(0);
        } catch (IndexOutOfBoundsException e) {
        	list = null;
        }
		return list;
	}
	
}
