package com.account;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import part.dao.AccountDAO;
import part.dao.PaginationInfo;
import part.project.Account;

public class AccountLazyDataModel extends LazyDataModel<Account>{

	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> searchParams;
	
	AccountDAO accountDAO;

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public void setSearchParams(Map<String, Object> searchParams) {
		this.searchParams = searchParams;
	}
	
	@Override
	public Account getRowData(String rowKey){
		return null;
	}
	
	@Override
	public Object getRowKey(Account account){
		return account.getIdAccount();
	}
	
	@Override
	public List<Account> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Account> list = new ArrayList<Account>();
		
		PaginationInfo info = new PaginationInfo();
		
		info.setLimit(pageSize);
		info.setOffset(first);
		list = accountDAO.lazyFunction(searchParams, info);
		setRowCount(info.getCount());
		
		if(sortField != null){
			Collections.sort(list, new AccountSorter(sortField, sortOrder));
		}
		
		return list;
	}
	
}
