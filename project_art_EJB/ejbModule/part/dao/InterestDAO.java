package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import part.model.Interest;

@Stateless
public class InterestDAO extends DAO<Interest>{
	
	public InterestDAO(){
		setEntityClass(Interest.class);
	}
	
	@Override
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