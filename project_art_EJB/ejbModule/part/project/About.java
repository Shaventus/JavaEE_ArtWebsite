package part.project;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the about database table.
 * 
 */
@Entity
@NamedQuery(name="About.findAll", query="SELECT a FROM About a")
public class About implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAbout;

	private String brithday;

	private String description;

	private String location;

	private String realname;

	private String website;

	//bi-directional many-to-one association to Account
	@ManyToOne
	private Account account;

	//bi-directional many-to-one association to Interest
	@OneToMany(mappedBy="about")
	private List<Interest> interests;

	public About() {
	}

	public int getIdAbout() {
		return this.idAbout;
	}

	public void setIdAbout(int idAbout) {
		this.idAbout = idAbout;
	}

	public String getBrithday() {
		return this.brithday;
	}

	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Interest> getInterests() {
		return this.interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}

	public Interest addInterest(Interest interest) {
		getInterests().add(interest);
		interest.setAbout(this);

		return interest;
	}

	public Interest removeInterest(Interest interest) {
		getInterests().remove(interest);
		interest.setAbout(null);

		return interest;
	}

}