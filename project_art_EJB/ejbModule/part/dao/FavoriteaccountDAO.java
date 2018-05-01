package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import part.project.Favoriteaccount;

@Stateless
public class FavoriteaccountDAO {
	private final static String UNIT_NAME = "jsfCards-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Favoriteaccount w){
		em.persist(w);
	}
	
	public Favoriteaccount merge(Favoriteaccount favoriteaccount) {
		return em.merge(favoriteaccount);
	}

	public void remove(Favoriteaccount favoriteaccount) {
		em.remove(em.merge(favoriteaccount));
	}
	
	public Favoriteaccount find(Object id) {
		return em.find(Favoriteaccount.class, id);
	}

	public List<Favoriteaccount> getFullList() {
		List<Favoriteaccount> list = null;

		Query query = em.createQuery("select p from Favoriteaccount p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Favoriteaccount> getList(Map<String, Object> searchParams) {
		List<Favoriteaccount> list = null;

		String select = "select p ";
		String from = "from Favoriteaccount p ";
		String where = "";
		String orderby = "order by p.idfavoriteaccount asc";
		
		int idfavoriteaccount = (int) searchParams.get("idfavoriteaccount");
		if (idfavoriteaccount != 0) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.idfavoriteaccount = :idfavoriteaccount ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);


		if (idfavoriteaccount != 0) {
			query.setParameter("idfavoriteaccount", idfavoriteaccount);
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}