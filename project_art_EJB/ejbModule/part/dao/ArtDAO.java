package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import part.project.Art;

@Stateless
public class ArtDAO {
	private final static String UNIT_NAME = "jsfCards-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Art w){
		em.persist(w);
	}
	
	public Art merge(Art art) {
		return em.merge(art);
	}

	public void remove(Art art) {
		em.remove(em.merge(art));
	}
	
	public Art find(Object id) {
		return em.find(Art.class, id);
	}

	public List<Art> getFullList() {
		List<Art> list = null;

		Query query = em.createQuery("select p from Art p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Art> getList(Map<String, Object> searchParams) {
		List<Art> list = null;

		String select = "select p ";
		String from = "from Art p ";
		String where = "";
		String orderby = "order by p.idart asc";
		
		int idart = (int) searchParams.get("idart");
		if (idart != 0) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.idart = :idart ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);


		if (idart != 0) {
			query.setParameter("idart", idart);
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}