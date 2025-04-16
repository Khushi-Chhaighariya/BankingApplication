package com.example.entity;


import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;                           //data ko secutred rkhne k liya private liya.
	
	
	@Column(name = "account_holder_name")
    private String accountHolderName;
   
   private double balance;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getAccountHolderName() {
	return accountHolderName ;
}
public void setAccountHolderName(String accountHolderName) {
	this.accountHolderName  = accountHolderName;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}
@Override
public String toString() {
	return "Account [id=" + id + ", AccountHolderName=" + accountHolderName  + ", balance=" + balance + "]";
}
public Account() {
}
public Account(Long id, String accountHolderName, double balance) {
	super();
	this.id = id;
	this.accountHolderName  = accountHolderName;
	this.balance = balance;
}
   
   
   
}
