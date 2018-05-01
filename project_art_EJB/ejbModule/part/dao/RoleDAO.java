package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import part.project.Role;

@Stateless
public class RoleDAO {
	private final static String UNIT_NAME = "jsfCards-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Role w){
		em.persist(w);
	}
	
	public Role merge(Role role) {
		return em.merge(role);
	}

	public void remove(Role role) {
		em.remove(em.merge(role));
	}
	
	public Role find(Object id) {
		return em.find(Role.class, id);
	}

	public List<Role> getFullList() {
		List<Role> list = null;

		Query query = em.createQuery("select p from Role p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Role> getList(Map<String, Object> searchParams) {
		List<Role> list = null;

		String select = "select p ";
		String from = "from Role p ";
		String where = "";
		String orderby = "order by p.idrole asc";
		
		int idrole = (int) searchParams.get("idrole");
		if (idrole != 0) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.idrole = :idrole ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);


		if (idrole != 0) {
			query.setParameter("idrole", idrole);
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
