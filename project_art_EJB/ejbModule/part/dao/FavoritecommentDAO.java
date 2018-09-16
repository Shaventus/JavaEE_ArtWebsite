package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import part.model.Favoritecomment;

@Stateless
public class FavoritecommentDAO extends DAO<Favoritecomment>{

	public FavoritecommentDAO(){
		setEntityClass(Favoritecomment.class);
	}
	
	@Override
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