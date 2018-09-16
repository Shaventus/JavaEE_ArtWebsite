package part.dao.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.LocalBean;

public interface IDAO<T extends Serializable>{
	
	String UNIT_NAME = "jsfCards-simplePU";
	
	public void create(T t);
	
	public T merge(T t);

	public void remove(T t);
	
	public T find(Object id);

	public List<T> getFullList();
	
	public List<T> getList(Map<String, Object> searchParams);

}
