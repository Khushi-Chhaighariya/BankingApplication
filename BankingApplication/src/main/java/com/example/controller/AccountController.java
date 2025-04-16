package com.example.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.AccountDto;
import com.example.service.AccountService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
 
	private AccountService accountService;
	//@autowired v laga sakte the but construtor lena is best option so 
	
	 public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	 @GetMapping
	    public ResponseEntity<String> getAccounts() {
	        return ResponseEntity.ok("Accounts fetched successfully!");
	    }
//add account rest api
@PostMapping
	public ResponseEntity<AccountDto>addAccount(@RequestBody AccountDto accountDto){
	System.out.println(accountDto);
		 return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	 }
//get single account details
@GetMapping("/{id}")
public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
	AccountDto accountDto = accountService.getAccountById(id);//accountDto main le rhe hai response service ki method se
	return ResponseEntity.ok(accountDto);
}
//deposit
@PutMapping("/{id}deposit")//phale se jo amount hai usko update krna hai like deposit to isliya put liya
public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double>request){
Double amount=request.get("amount");                                                      
AccountDto accountDto = accountService.deposit(id,amount);
return ResponseEntity.ok(accountDto);

}
 //withdraw
@PutMapping("/{id}/withdraw")
public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double>request){
	
	Double amount =request.get("amount");
	AccountDto accountDto=accountService.withdraw(id,amount);
	return ResponseEntity.ok(accountDto);
}

@GetMapping("/all")
public ResponseEntity<List<AccountDto>>getAllAccounts(){
	List<AccountDto> accountDto=accountService.getAllAccounts();
	return ResponseEntity.ok(accountDto);
}

@DeleteMapping("/{id}")
public ResponseEntity<String>deleteAccount (@PathVariable Long id){
	accountService.deleteAccount(id);
	return ResponseEntity.ok("Account deleted successfully !!");
}

}
