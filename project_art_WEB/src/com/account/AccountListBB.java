package com.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.security.User;

import part.dao.AccountDAO;
import part.project.Account;

@ManagedBean
@ViewScoped
public class AccountListBB {
	private static final String PAGE_ACCOUNT_EDIT = "accountEdit?faces-redirect=true";
	
	@EJB
	AccountDAO accountDAO;
	
	private String login;
	private String email;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private AccountLazyDataModel lazyModel = null;

	public AccountLazyDataModel getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(AccountLazyDataModel lazyModel) {
		this.lazyModel = lazyModel;
	}

	public List<Account> getFullList(){
		return accountDAO.getFullList();
	}
	
	@PostConstruct
	public void init() {
		if(lazyModel == null){
			lazyModel = new AccountLazyDataModel();
		}
	}
	
	public AccountLazyDataModel getLazyList() {
		
		Map<String, Object> searchParams = new HashMap<String, Object>();

		if(login != null){
			searchParams.put("login", login);
		}
		if(email != null){
			searchParams.put("email", email);
		}
		
		lazyModel.setSearchParams(searchParams);
		lazyModel.setAccountDAO(accountDAO);
		return lazyModel;
	}

	public List<Account> getList(){
		List<Account> list = null;
		
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		list = accountDAO.getList(searchParams);
		
		return list;
	}
	
	public String newAccount(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		Account account = new Account();
		session.setAttribute("account", account);
		return PAGE_ACCOUNT_EDIT;
	}

	public String editAccount(Account account){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		session.setAttribute("account", account);
		return PAGE_ACCOUNT_EDIT;
	}
	
	public String editLoggedAccount(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		User u = (User) session.getAttribute("user");
		Account account = accountDAO.find(u.getId());
		session.setAttribute("account", account);
		return PAGE_ACCOUNT_EDIT;
	}

}