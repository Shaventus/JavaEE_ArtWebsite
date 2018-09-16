package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import part.model.Favorite;

@Stateless
public class FavoriteDAO extends DAO<Favorite>{
	
	public FavoriteDAO(){
		setEntityClass(Favorite.class);
	}
	
	@Override
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