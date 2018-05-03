package com.account;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import part.dao.AccountDAO;
import part.dao.RoleDAO;
import part.project.Account;
import part.project.Role;

@ManagedBean
@ViewScoped
public class AccountEditBB {
	private static final String PAGE_LOGIN = "/pages/login?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	@EJB
	AccountDAO accountDAO;
	
	@EJB
	RoleDAO roleDAO;
	
	private String login;
	private String password;
	private String email;
	private String avatar;
	private Date date;
	
	private Account account;
	private Role role;
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@PostConstruct
	public void postConstruct() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		account = (Account) session.getAttribute("account");
		
		if (account != null && account.getIdAccount() != 0) {
			session.removeAttribute("account");
			setLogin(account.getLogin());
			setPassword(account.getPassword());
			setDate(account.getDate());
			setEmail(account.getEmail());
		} else {
			setDate(new Date());
			account = new Account();
		}

	}
	
	private boolean validate() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		boolean result = false;

		if (login == null || login.trim().length() == 0) {
			ctx.addMessage(null, new FacesMessage("Login"));
		}
		
		if (password == null || password.trim().length() == 0) {
			ctx.addMessage(null, new FacesMessage("Password"));
		}
		
		if (email == null || email.trim().length() == 0) {
			ctx.addMessage(null, new FacesMessage("Email"));
		}
		
		if (ctx.getMessageList().isEmpty()) {
			account.setLogin(login);
			account.setPassword(password);
			account.setDate(date);
			account.setEmail(email);
			
			role = new Role();
			role = roleDAO.find(2);
			role.getAccounts().add(account);
			
			result = true;
		}

		return result;
	}

	public String saveData() {
		
		if (!validate()) {
			return PAGE_STAY_AT_THE_SAME;
		}
		
		if (account == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Incorrect system usage"));
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (account.getIdAccount() == 0) {
				accountDAO.create(account);
			} else {
				accountDAO.merge(account);
			}
			if(role.getIdRole() != 0) {
				roleDAO.merge(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("An error while saving"));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_LOGIN;
	}
	
	public String doLogin(){
		return PAGE_LOGIN;
	}
}
