package com.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<Account> getFullList(){
		return accountDAO.getFullList();
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