package com.account;

import java.lang.reflect.Field;
import java.util.Comparator;

import org.primefaces.model.SortOrder;

import part.model.Account;

public class AccountSorter implements Comparator<Account> {
	
    private String sortField;
    
    private SortOrder sortOrder;
     
    public AccountSorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
    
    @Override
    public int compare(Account account1, Account account2) {
        try {
        	Field field1 = account1.getClass().getDeclaredField(this.sortField);
            Field field2 = account2.getClass().getDeclaredField(this.sortField);
            field1.setAccessible(true);
            field2.setAccessible(true);
            Object value1 = field1.get(account1);
            Object value2 = field2.get(account2);

            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }

}
