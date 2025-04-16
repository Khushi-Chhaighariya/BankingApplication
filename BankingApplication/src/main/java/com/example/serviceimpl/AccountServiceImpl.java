package com.example.serviceimpl;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.AccountDto;
import com.example.entity.Account;
import com.example.Mapper.AccountMapper;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {
    //repository inject krne k liya ya fir @autowired annotation use krna nhi to construtor laga do to yha contrutor lagyaya hai source pr jakr right click than select construtor filed.
	private AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {//frontend se data sabse phale yha aayga AccountDto matlab controller k bad than repository main jakr database main jayhga.
		/*dekh ye jo 3 line hai yha to samkjo
		 * sabse phale data aaya frontend se to postman se aayga controller main then controller se data accountDto main aayga then
		 * but data database ,main nhi jayga kyuki database main jane k liya account main data hhona chahiye
		 * 1 line main data ko pass krte hai account main
		 * 2 line main database main jayga data or save ho jkayga
		 * 3 line main ab humhe return v krna hai na data to uske liya bapis data ko Accountdto main dalna hai to account ke data ko accountdto main convert krna hai jisse database se data aaccountDto main jakr frontend main sho kre.*/
		Account account=AccountMapper.mapToAccount(accountDto);//
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}//humhre pass data accountdto main aayga or humko bhajna hai account main to account main convert karne k liya mapper use krage.


	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));//id se account mili to give return otherwise give exception msg
				
		return AccountMapper.mapToAccountDto(account);//for security isko convert krage accountdton main now go to controller
		
	}


	@Override
	public AccountDto deposit(Long id, double amount) {
		//sabse user ka account get krage like kis user k account main deposit krna hai
		Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));//id se account mili to give return otherwise give exception msg//yha se account milega account get ho jayga isse
		double totalBalance= account.getBalance()+amount;//phale purana balance get krna padega.ismain hum add krage new balance jo aayga.ishko totalBalance main rkhage
		account.setBalance(totalBalance);//new balance hum ab account main set kr dage.
		Account savedAccount =accountRepository.save(account);//AB savedaccount main save krage account ko
		//ab savedAccount ko accountdto main convert
		return AccountMapper.mapToAccountDto(savedAccount);//yha transfer to Accountdto maim data ko then bhaj dega controller k pass then wha se client ko milega
	}


	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));//id se account mili to give return otherwise give exception msg//yha se account milega account get ho jayga isse
		//suppose clent k pass 200rupee hai or wo 300 rupee withdow kr rha hai to wo nhi kr payga kyuki uske account main balance nhi hai.
		if(account.getBalance()<amount) {//amount jada hai current balance se to exceptiom
			throw new RuntimeException("Insufficient Balance");
	
		}
		double totalBalance = account.getBalance()-amount;//jo account balance hai to usmain se - krage jo amount wo withdrow krna chahta hai.
		account.setBalance(totalBalance); //jo total balance hai usko save kr liya
		Account savedAccount = accountRepository.save(account);//yha repository k throught account ko save kr dogi db main.
		//convert accountdto
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public List<AccountDto> getAllAccounts() {
		return	accountRepository.findAll().stream().map((account)->AccountMapper.mapToAccountDto(account)).
				collect(Collectors.toList());//acountdto main convert ho rha or list k liya collector lagaya or stream se findall se sari mil jaygi.
	
	}


	@Override
	public void deleteAccount(Long id) {
		//id hai to give return otherviewe exception msg dega
		Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));//id se account mili to give return otherwise give exception msg//yha se account milega account get ho jayga isse
		
		accountRepository.delete(account);
	}
	
	
}
