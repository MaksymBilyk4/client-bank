package com.clientbank.dev.resourses;

import com.clientbank.dev.models.Account;
import com.clientbank.dev.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account getOne(@PathVariable(name = "id") Long id) {
        return accountService.getOne(id);
    }

    @PutMapping("/transfer")
    public boolean transferMoney(
            @RequestParam(value = "toNumber") String toNumber,
            @RequestParam(value = "fromNumber") String fromNumber,
            @RequestParam(value = "amount") Double amount
    ) {
        return accountService.transferMoney(toNumber, fromNumber, amount);
    }

    @PutMapping("/withdraw")
    public boolean withdrawMoney(
            @RequestParam(value = "number") String number,
            @RequestParam(value = "amount") Double amount
    ) {
        return accountService.withdrawMoney(number, amount);
    }

    @PutMapping("/up")
    public boolean upMoney(
            @RequestParam(value = "number") String number,
            @RequestParam(value = "amount") Double amount
    ) {
        return accountService.toUpAccount(number, amount);
    }

}
