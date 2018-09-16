package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import part.model.Favoriteaccount;

@Stateless
public class FavoriteaccountDAO extends DAO<Favoriteaccount>{

	public FavoriteaccountDAO(){
		setEntityClass(Favoriteaccount.class);
	}
	
	@Override
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