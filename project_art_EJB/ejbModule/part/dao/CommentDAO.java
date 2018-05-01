package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import part.project.Comment;

@Stateless
public class CommentDAO {
	private final static String UNIT_NAME = "jsfCards-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Comment w){
		em.persist(w);
	}
	
	public Comment merge(Comment comment) {
		return em.merge(comment);
	}

	public void remove(Comment comment) {
		em.remove(em.merge(comment));
	}
	
	public Comment find(Object id) {
		return em.find(Comment.class, id);
	}

	public List<Comment> getFullList() {
		List<Comment> list = null;

		Query query = em.createQuery("select p from Comment p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Comment> getList(Map<String, Object> searchParams) {
		List<Comment> list = null;

		String select = "select p ";
		String from = "from Comment p ";
		String where = "";
		String orderby = "order by p.idcomment asc";
		
		int idcomment = (int) searchParams.get("idcomment");
		if (idcomment != 0) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.idcomment = :idcomment ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);


		if (idcomment != 0) {
			query.setParameter("idcomment", idcomment);
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}