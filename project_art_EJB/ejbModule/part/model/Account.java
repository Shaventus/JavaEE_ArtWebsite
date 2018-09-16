package part.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAccount;

	private String avatar;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;

	private String login;

	private String password;

	//bi-directional many-to-one association to About
	@OneToMany(mappedBy="account")
	private List<About> abouts;

	//bi-directional many-to-many association to Role
	@ManyToMany(mappedBy="accounts", cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	private List<Role> roles;

	//bi-directional many-to-one association to Art
	@OneToMany(mappedBy="account")
	private List<Art> arts;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="account")
	private List<Comment> comments;

	//bi-directional many-to-one association to Favorite
	@OneToMany(mappedBy="account")
	private List<Favorite> favorites;

	//bi-directional many-to-one association to Favoriteaccount
	@OneToMany(mappedBy="account1")
	private List<Favoriteaccount> favoriteaccounts1;

	//bi-directional many-to-one association to Favoriteaccount
	@OneToMany(mappedBy="account2")
	private List<Favoriteaccount> favoriteaccounts2;

	//bi-directional many-to-one association to Favoritecomment
	@OneToMany(mappedBy="account")
	private List<Favoritecomment> favoritecomments;

	//bi-directional many-to-one association to Statistic
	@OneToMany(mappedBy="account")
	private List<Statistic> statistics;

	public Account() {
	}

	public int getIdAccount() {
		return this.idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<About> getAbouts() {
		return this.abouts;
	}

	public void setAbouts(List<About> abouts) {
		this.abouts = abouts;
	}

	public About addAbout(About about) {
		getAbouts().add(about);
		about.setAccount(this);

		return about;
	}

	public About removeAbout(About about) {
		getAbouts().remove(about);
		about.setAccount(null);

		return about;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Art> getArts() {
		return this.arts;
	}

	public void setArts(List<Art> arts) {
		this.arts = arts;
	}

	public Art addArt(Art art) {
		getArts().add(art);
		art.setAccount(this);

		return art;
	}

	public Art removeArt(Art art) {
		getArts().remove(art);
		art.setAccount(null);

		return art;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setAccount(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setAccount(null);

		return comment;
	}

	public List<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public Favorite addFavorite(Favorite favorite) {
		getFavorites().add(favorite);
		favorite.setAccount(this);

		return favorite;
	}

	public Favorite removeFavorite(Favorite favorite) {
		getFavorites().remove(favorite);
		favorite.setAccount(null);

		return favorite;
	}

	public List<Favoriteaccount> getFavoriteaccounts1() {
		return this.favoriteaccounts1;
	}

	public void setFavoriteaccounts1(List<Favoriteaccount> favoriteaccounts1) {
		this.favoriteaccounts1 = favoriteaccounts1;
	}

	public Favoriteaccount addFavoriteaccounts1(Favoriteaccount favoriteaccounts1) {
		getFavoriteaccounts1().add(favoriteaccounts1);
		favoriteaccounts1.setAccount1(this);

		return favoriteaccounts1;
	}

	public Favoriteaccount removeFavoriteaccounts1(Favoriteaccount favoriteaccounts1) {
		getFavoriteaccounts1().remove(favoriteaccounts1);
		favoriteaccounts1.setAccount1(null);

		return favoriteaccounts1;
	}

	public List<Favoriteaccount> getFavoriteaccounts2() {
		return this.favoriteaccounts2;
	}

	public void setFavoriteaccounts2(List<Favoriteaccount> favoriteaccounts2) {
		this.favoriteaccounts2 = favoriteaccounts2;
	}

	public Favoriteaccount addFavoriteaccounts2(Favoriteaccount favoriteaccounts2) {
		getFavoriteaccounts2().add(favoriteaccounts2);
		favoriteaccounts2.setAccount2(this);

		return favoriteaccounts2;
	}

	public Favoriteaccount removeFavoriteaccounts2(Favoriteaccount favoriteaccounts2) {
		getFavoriteaccounts2().remove(favoriteaccounts2);
		favoriteaccounts2.setAccount2(null);

		return favoriteaccounts2;
	}

	public List<Favoritecomment> getFavoritecomments() {
		return this.favoritecomments;
	}

	public void setFavoritecomments(List<Favoritecomment> favoritecomments) {
		this.favoritecomments = favoritecomments;
	}

	public Favoritecomment addFavoritecomment(Favoritecomment favoritecomment) {
		getFavoritecomments().add(favoritecomment);
		favoritecomment.setAccount(this);

		return favoritecomment;
	}

	public Favoritecomment removeFavoritecomment(Favoritecomment favoritecomment) {
		getFavoritecomments().remove(favoritecomment);
		favoritecomment.setAccount(null);

		return favoritecomment;
	}

	public List<Statistic> getStatistics() {
		return this.statistics;
	}

	public void setStatistics(List<Statistic> statistics) {
		this.statistics = statistics;
	}

	public Statistic addStatistic(Statistic statistic) {
		getStatistics().add(statistic);
		statistic.setAccount(this);

		return statistic;
	}

	public Statistic removeStatistic(Statistic statistic) {
		getStatistics().remove(statistic);
		statistic.setAccount(null);

		return statistic;
	}

}