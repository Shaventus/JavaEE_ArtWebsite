package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import part.project.Account;

@Stateless
public class AccountDAO {
	private final static String UNIT_NAME = "jsfCards-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Account w){
		em.persist(w);
	}
	
	public Account merge(Account account) {
		return em.merge(account);
	}

	public void remove(Account account) {
		em.remove(em.merge(account));
	}
	
	public Account find(Object id) {
		return em.find(Account.class, id);
	}

	public List<Account> getFullList() {
		List<Account> list = null;

		Query query = em.createQuery("select p from Account p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Account> getList(Map<String, Object> searchParams) {
		List<Account> list = null;

		String select = "select p ";
		String from = "from Account p ";
		String where = "";
		String orderby = "order by p.idaccount asc";
		
		int idaccount = (int) searchParams.get("idaccount");
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
			query.setParameter("idaccount", idaccount);
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
