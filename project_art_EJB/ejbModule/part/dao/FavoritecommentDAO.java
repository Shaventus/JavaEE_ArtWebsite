package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import part.project.Favoritecomment;

@Stateless
public class FavoritecommentDAO {
	private final static String UNIT_NAME = "jsfCards-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Favoritecomment w){
		em.persist(w);
	}
	
	public Favoritecomment merge(Favoritecomment favoritecomment) {
		return em.merge(favoritecomment);
	}

	public void remove(Favoritecomment favoritecomment) {
		em.remove(em.merge(favoritecomment));
	}
	
	public Favoritecomment find(Object id) {
		return em.find(Favoritecomment.class, id);
	}

	public List<Favoritecomment> getFullList() {
		List<Favoritecomment> list = null;

		Query query = em.createQuery("select p from Favorite p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Favoritecomment> getList(Map<String, Object> searchParams) {
		List<Favoritecomment> list = null;

		String select = "select p ";
		String from = "from Favoritecomment p ";
		String where = "";
		String orderby = "order by p.idfavoritecomment asc";
		
		int idfavoritecomment = (int) searchParams.get("idfavoritecomment");
		if (idfavoritecomment != 0) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.idfavoritecomment = :idfavoritecomment ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);


		if (idfavoritecomment != 0) {
			query.setParameter("idfavoritecomment", idfavoritecomment);
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}