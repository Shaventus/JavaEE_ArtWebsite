package com.security;

import java.util.HashSet;

public class User {
	
	private String login;
	private String pass;
	private String name;
	private String surname;
	private String remoteAdr;
	private String remoteHost;
	private String remotePort;
	private String Role;
	private int id;

	private HashSet<String> roles = new HashSet<String>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public User(String login, String pass) {
		this.login = login;
		this.pass = pass;
	}
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRemoteAdr() {
		return remoteAdr;
	}

	public void setRemoteAdr(String remoteAdr) {
		this.remoteAdr = remoteAdr;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public String getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(String remotePort) {
		this.remotePort = remotePort;
	}

	public HashSet<String> getRoles() {
		return roles;
	}

	public void setRoles(HashSet<String> roles) {
		this.roles = roles;
	}
		
}
