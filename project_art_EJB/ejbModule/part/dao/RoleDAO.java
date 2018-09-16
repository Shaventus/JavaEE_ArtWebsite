package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import part.model.Role;

@Stateless
public class RoleDAO extends DAO<Role> {
	
	public RoleDAO(){
		setEntityClass(Role.class);
	}
	
	@Override
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
