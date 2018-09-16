package part.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import part.model.Comment;

@Stateless
public class CommentDAO extends DAO<Comment>{
	
	public CommentDAO(){
		setEntityClass(Comment.class);
	}
	
	@Override
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