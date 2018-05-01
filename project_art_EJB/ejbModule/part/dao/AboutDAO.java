package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import part.project.About;

@Stateless
public class AboutDAO {
	private final static String UNIT_NAME = "jsfCards-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(About w){
		em.persist(w);
	}
	
	public About merge(About about) {
		return em.merge(about);
	}

	public void remove(About about) {
		em.remove(em.merge(about));
	}
	
	public About find(Object id) {
		return em.find(About.class, id);
	}

	public List<About> getFullList() {
		List<About> list = null;

		Query query = em.createQuery("select p from About p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<About> getList(Map<String, Object> searchParams) {
		List<About> list = null;

		String select = "select p ";
		String from = "from About p ";
		String where = "";
		String orderby = "order by p.idabout asc";
		
		int idabout = (int) searchParams.get("idabout");
		if (idabout != 0) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.idabout = :idabout ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);


		if (idabout != 0) {
			query.setParameter("idabout", idabout);
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
