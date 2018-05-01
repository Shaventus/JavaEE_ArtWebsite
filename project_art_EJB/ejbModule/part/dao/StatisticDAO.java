package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import part.project.Statistic;

@Stateless
public class StatisticDAO {
	private final static String UNIT_NAME = "jsfCards-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Statistic w){
		em.persist(w);
	}
	
	public Statistic merge(Statistic statistic) {
		return em.merge(statistic);
	}

	public void remove(Statistic statistic) {
		em.remove(em.merge(statistic));
	}
	
	public Statistic find(Object id) {
		return em.find(Statistic.class, id);
	}

	public List<Statistic> getFullList() {
		List<Statistic> list = null;

		Query query = em.createQuery("select p from Statistic p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Statistic> getList(Map<String, Object> searchParams) {
		List<Statistic> list = null;

		String select = "select p ";
		String from = "from Statistic p ";
		String where = "";
		String orderby = "order by p.idstatistic asc";
		
		int idstatistic = (int) searchParams.get("idstatistic");
		if (idstatistic != 0) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.idstatistic = :idstatistic ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);


		if (idstatistic != 0) {
			query.setParameter("idstatistic", idstatistic);
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}