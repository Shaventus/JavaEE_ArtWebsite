package part.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the favoritecomment database table.
 * 
 */
@Entity
@NamedQuery(name="Favoritecomment.findAll", query="SELECT f FROM Favoritecomment f")
public class Favoritecomment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idFavoriteComment;

	@Temporal(TemporalType.DATE)
	private Date date;

	//bi-directional many-to-one association to Account
	@ManyToOne
	private Account account;

	//bi-directional many-to-one association to Comment
	@ManyToOne
	private Comment comment;

	public Favoritecomment() {
	}

	public int getIdFavoriteComment() {
		return this.idFavoriteComment;
	}

	public void setIdFavoriteComment(int idFavoriteComment) {
		this.idFavoriteComment = idFavoriteComment;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}