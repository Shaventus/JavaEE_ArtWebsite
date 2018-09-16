package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import part.model.Statistic;

@Stateless
public class StatisticDAO extends DAO<Statistic>{
	
	public StatisticDAO(){
		setEntityClass(Statistic.class);
	}
	
	@Override
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