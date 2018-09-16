package part.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idComment;

	private String comment;

	@Temporal(TemporalType.DATE)
	private Date date;

	//bi-directional many-to-one association to Account
	@ManyToOne
	private Account account;

	//bi-directional many-to-one association to Art
	@ManyToOne
	private Art art;

	//bi-directional many-to-one association to Comment
	@ManyToOne
	@JoinColumn(name="Comment_idComment")
	private Comment commentBean;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="commentBean")
	private List<Comment> comments;

	//bi-directional many-to-one association to Favoritecomment
	@OneToMany(mappedBy="comment")
	private List<Favoritecomment> favoritecomments;

	public Comment() {
	}

	public int getIdComment() {
		return this.idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public Art getArt() {
		return this.art;
	}

	public void setArt(Art art) {
		this.art = art;
	}

	public Comment getCommentBean() {
		return this.commentBean;
	}

	public void setCommentBean(Comment commentBean) {
		this.commentBean = commentBean;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setCommentBean(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setCommentBean(null);

		return comment;
	}

	public List<Favoritecomment> getFavoritecomments() {
		return this.favoritecomments;
	}

	public void setFavoritecomments(List<Favoritecomment> favoritecomments) {
		this.favoritecomments = favoritecomments;
	}

	public Favoritecomment addFavoritecomment(Favoritecomment favoritecomment) {
		getFavoritecomments().add(favoritecomment);
		favoritecomment.setComment(this);

		return favoritecomment;
	}

	public Favoritecomment removeFavoritecomment(Favoritecomment favoritecomment) {
		getFavoritecomments().remove(favoritecomment);
		favoritecomment.setComment(null);

		return favoritecomment;
	}

}