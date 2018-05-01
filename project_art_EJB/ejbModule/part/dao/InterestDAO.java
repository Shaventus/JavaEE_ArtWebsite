package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import part.project.Interest;

@Stateless
public class InterestDAO {
	private final static String UNIT_NAME = "jsfCards-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Interest w){
		em.persist(w);
	}
	
	public Interest merge(Interest interest) {
		return em.merge(interest);
	}

	public void remove(Interest interest) {
		em.remove(em.merge(interest));
	}
	
	public Interest find(Object id) {
		return em.find(Interest.class, id);
	}

	public List<Interest> getFullList() {
		List<Interest> list = null;

		Query query = em.createQuery("select p from Interest p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Interest> getList(Map<String, Object> searchParams) {
		List<Interest> list = null;

		String select = "select p ";
		String from = "from Interest p ";
		String where = "";
		String orderby = "order by p.idinterest asc";
		
		int idinterest = (int) searchParams.get("idinterest");
		if (idinterest != 0) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.idinterest = :idinterest ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);


		if (idinterest != 0) {
			query.setParameter("idinterest", idinterest);
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}