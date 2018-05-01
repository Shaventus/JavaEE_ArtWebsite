package part.project;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the favoriteaccount database table.
 * 
 */
@Entity
@NamedQuery(name="Favoriteaccount.findAll", query="SELECT f FROM Favoriteaccount f")
public class Favoriteaccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idFavoriteAccount;

	@Temporal(TemporalType.DATE)
	private Date date;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="Account_idAccount")
	private Account account1;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="Account_idAccount1")
	private Account account2;

	public Favoriteaccount() {
	}

	public int getIdFavoriteAccount() {
		return this.idFavoriteAccount;
	}

	public void setIdFavoriteAccount(int idFavoriteAccount) {
		this.idFavoriteAccount = idFavoriteAccount;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Account getAccount1() {
		return this.account1;
	}

	public void setAccount1(Account account1) {
		this.account1 = account1;
	}

	public Account getAccount2() {
		return this.account2;
	}

	public void setAccount2(Account account2) {
		this.account2 = account2;
	}

}