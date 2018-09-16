package part.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import part.dao.api.IDAO;
import part.model.Account;

public abstract class DAO<T extends Serializable> implements IDAO<T> {
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	private Class<T> entityClass;
	
	public final void setEntityClass(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void create(T t){
		em.persist(t);
	}
	
	public T merge(T t){
		return em.merge(t);
	}

	public void remove(T t){
		em.remove(em.merge(t));
	}
	
	public T find(Object id){
		return em.find(entityClass, id);
	}
	
	public List<T> getFullList() {
		List<T> list = null;

		Query query = em.createQuery("select p from ".concat(entityClass.getName()).concat(" p"));

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
