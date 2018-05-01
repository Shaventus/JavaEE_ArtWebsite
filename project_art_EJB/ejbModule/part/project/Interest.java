package part.project;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the interest database table.
 * 
 */
@Entity
@NamedQuery(name="Interest.findAll", query="SELECT i FROM Interest i")
public class Interest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idInterest;

	private String books;

	private String games;

	private String movies;

	private String platform;

	private String sports;

	//bi-directional many-to-one association to About
	@ManyToOne
	private About about;

	public Interest() {
	}

	public int getIdInterest() {
		return this.idInterest;
	}

	public void setIdInterest(int idInterest) {
		this.idInterest = idInterest;
	}

	public String getBooks() {
		return this.books;
	}

	public void setBooks(String books) {
		this.books = books;
	}

	public String getGames() {
		return this.games;
	}

	public void setGames(String games) {
		this.games = games;
	}

	public String getMovies() {
		return this.movies;
	}

	public void setMovies(String movies) {
		this.movies = movies;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getSports() {
		return this.sports;
	}

	public void setSports(String sports) {
		this.sports = sports;
	}

	public About getAbout() {
		return this.about;
	}

	public void setAbout(About about) {
		this.about = about;
	}

}