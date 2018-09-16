package part.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCategory;

	private String description;

	private String name;

	private String photo;

	//bi-directional many-to-one association to Art
	@OneToMany(mappedBy="category")
	private List<Art> arts;

	public Category() {
	}

	public int getIdCategory() {
		return this.idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<Art> getArts() {
		return this.arts;
	}

	public void setArts(List<Art> arts) {
		this.arts = arts;
	}

	public Art addArt(Art art) {
		getArts().add(art);
		art.setCategory(this);

		return art;
	}

	public Art removeArt(Art art) {
		getArts().remove(art);
		art.setCategory(null);

		return art;
	}

}