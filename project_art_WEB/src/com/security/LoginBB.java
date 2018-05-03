package com.security;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import part.dao.AccountDAO;
import part.project.Account;

@ManagedBean
public class LoginBB {
	private static final String PAGE_USER_MAIN = "/pages/user/main?faces-redirect=true";
	private static final String PAGE_ADMIN_MAIN = "/pages/admin/main?faces-redirect=true";
	private static final String PAGE_LOGIN = "/pages/login";
	private static final String PAGE_REGISTR = "/pages/public/registr?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private static final String PAGE_ACCOUNT_LIST = "/pages/admin/accountList?faces-redirect=true";

	private String login;
	private String pass;
	
	@EJB
	AccountDAO accountDAO;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean validateData() {
		boolean result = true;
		FacesContext ctx = FacesContext.getCurrentInstance();

		if (login == null || login.length() == 0) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"enter login", "null"));
		}

		if (pass == null || pass.length() == 0) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"enter password", "null"));
		}

		if (ctx.getMessageList().isEmpty()) {
			result = true;
		} else {
			result = false;
		}
		return result;

	}

	public String doLogin() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		User user = null;

		if (!validateData()) {
			return PAGE_STAY_AT_THE_SAME;
		}

		user = getUserFromDatabase(login, pass);

		if (user == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Wrong login or password", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		HttpSession session = (HttpSession) ctx.getExternalContext()
				.getSession(true);
		session.setAttribute("user", user);

		if(user.getRole().equals("admin")){
			return PAGE_ADMIN_MAIN;
		} else {
			return PAGE_USER_MAIN;
		}
	}

	public User getUser() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		return (User) session.getAttribute("user");
	}
	
	public String doLogout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		session.invalidate();
		return PAGE_LOGIN;
	}
	
	public String doAccountList(){
		return PAGE_ACCOUNT_LIST;
	}
	
	public String doRegistr(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		Account account = new Account();
		session.setAttribute("account", account);
		return PAGE_REGISTR;
	}
	
	private User getUserFromDatabase(String login, String pass) {
		User u = null;
		Account account = accountDAO.getLogin(login, pass);
		if(account != null) {
			u = new User(login, pass);
			u.setName(account.getLogin());
			u.setRole(account.getRoles().get(0).getName());
			u.setId(account.getIdAccount());
		}

		return u;
	}

}
