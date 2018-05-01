package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import part.project.Category;

@Stateless
public class CategoryDAO {
	private final static String UNIT_NAME = "jsfCards-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Category w){
		em.persist(w);
	}
	
	public Category merge(Category category) {
		return em.merge(category);
	}

	public void remove(Category category) {
		em.remove(em.merge(category));
	}
	
	public Category find(Object id) {
		return em.find(Category.class, id);
	}

	public List<Category> getFullList() {
		List<Category> list = null;

		Query query = em.createQuery("select p from Category p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
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
