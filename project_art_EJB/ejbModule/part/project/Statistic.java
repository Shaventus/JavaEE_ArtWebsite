package part.project;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the statistic database table.
 * 
 */
@Entity
@NamedQuery(name="Statistic.findAll", query="SELECT s FROM Statistic s")
public class Statistic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idStatistic;

	@Temporal(TemporalType.DATE)
	private Date date;

	private int viewpage;

	//bi-directional many-to-one association to Account
	@ManyToOne
	private Account account;

	public Statistic() {
	}

	public int getIdStatistic() {
		return this.idStatistic;
	}

	public void setIdStatistic(int idStatistic) {
		this.idStatistic = idStatistic;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getViewpage() {
		return this.viewpage;
	}

	public void setViewpage(int viewpage) {
		this.viewpage = viewpage;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}