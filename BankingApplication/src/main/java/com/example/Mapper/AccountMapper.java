package com.example.Mapper;

import com.example.dto.AccountDto;
import com.example.entity.Account;

public class AccountMapper {
//to convert Accountdto to account kyuki data aacountdto main aa rha hai jo humhne aacountdto objet bnaya tha for security to bab bapis accountdto ko account main convert krne k liya mapper use kr rhe.

	//ishmain accoiuntdto se data account main gya
	public static Account mapToAccount(AccountDto accountDto) {//Accountdto main jo data aayga wo map kr rhe accout ko.
		
		Account account= new Account(            //to ish account main accountdto ka sara data dal rhe hai taki ko repository main<account diya hai na to database main chala jaye.>
				accountDto.getId(),
				accountDto.getAccountHolderName(),
				accountDto.getBalance()
				);
		return account;
	}

	//here account se data accountdto main jana chahiye.ye ishliya taki suppose database se to data account main aayga or account main jo data hai wo humko accountdto ko dena hoga directb thorina frontend to database se account k through response dage isliya bapis asa kena hoga.
	public static AccountDto mapToAccountDto(Account account) {
		AccountDto accountDto=new AccountDto(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()

				);
		return accountDto;
	}
}
