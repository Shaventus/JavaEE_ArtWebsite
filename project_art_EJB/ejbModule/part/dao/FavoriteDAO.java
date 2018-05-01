package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import part.project.Favorite;

@Stateless
public class FavoriteDAO {
	private final static String UNIT_NAME = "jsfCards-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Favorite w){
		em.persist(w);
	}
	
	public Favorite merge(Favorite favorite) {
		return em.merge(favorite);
	}

	public void remove(Favorite favorite) {
		em.remove(em.merge(favorite));
	}
	
	public Favorite find(Object id) {
		return em.find(Favorite.class, id);
	}

	public List<Favorite> getFullList() {
		List<Favorite> list = null;

		Query query = em.createQuery("select p from Favorite p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Favorite> getList(Map<String, Object> searchParams) {
		List<Favorite> list = null;

		String select = "select p ";
		String from = "from Favorite p ";
		String where = "";
		String orderby = "order by p.idfavorite asc";
		
		int idfavorite = (int) searchParams.get("idfavorite");
		if (idfavorite != 0) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.idfavorite = :idfavorite ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);


		if (idfavorite != 0) {
			query.setParameter("idfavorite", idfavorite);
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}