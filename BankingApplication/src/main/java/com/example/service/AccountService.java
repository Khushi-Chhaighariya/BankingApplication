package com.example.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.AccountDto;
import com.example.entity.Account;


public interface AccountService {
	////yha pr sare abstract method bnane the bina abstract keyword k kyuki compile automatic kr deta hai add.
//interface kyu nnmaya service main to ish interface main epstract method bnayge or implemente krage accountserviceimplememt class main.
	//Account matlab entity class
	//Account createAccount(Account account);//abstract method agar abstract lagana ho to lagao nhi to compiler laga deta hai
//hum direct Account name us nhi krage uske liya ek objectt bnayge suppose frontend ya postman se data aayga to securety perpose hum obje main sotore kr lage for more security,

//ab likhage accountdto obje, security k liya direct aacount ka name na late hue accountdto name ka obje bnakr le lo.
	AccountDto createAccount(AccountDto account);
	
	//user ne id provide kr di client ko to usko uska account milna chahiye
	AccountDto getAccountById(Long id);
	
	//deposite user your own ammount,AccountDto yha return object hai.
	AccountDto deposit(Long id,double amount);//id se user ka account create krage jismain amount deposite krna hai ,or amount main jitna amount aayga utna deposite krage.
	
	//withdrow nikalna
	AccountDto withdraw(Long id,double amount);//uske id se withrow krna hai or jo wo amount bolega wo amount with drow krna hai.

	//getall account k liya
	List<AccountDto> getAllAccounts();
	
	//delete
	void deleteAccount(Long id);//id se delete kr dage.
	
}
