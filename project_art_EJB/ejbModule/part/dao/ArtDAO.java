package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import part.model.Art;

@Stateless
public class ArtDAO extends DAO<Art>{
	
	public ArtDAO(){
		setEntityClass(Art.class);
	}
	
	@Override
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