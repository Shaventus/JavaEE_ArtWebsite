package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import part.model.Category;

@Stateless
public class CategoryDAO extends DAO<Category>{
	
	public CategoryDAO(){
		setEntityClass(Category.class);
	}
	
	@Override
	public List<Category> getList(Map<String, Object> searchParams) {
		List<Category> list = null;

		String select = "select p ";
		String from = "from Category p ";
		String where = "";
		String orderby = "order by p.idcategory asc";
		
		int idcategory = (int) searchParams.get("idcategory");
		if (idcategory != 0) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.idcategory = :idcategory ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);


		if (idcategory != 0) {
			query.setParameter("idcategory", idcategory);
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
