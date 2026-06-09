package com.shobhit.bankingsimulator;

import com.shobhit.bankingsimulator.config.AppConfig;
import com.shobhit.bankingsimulator.model.Account;
import com.shobhit.bankingsimulator.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AccountService acc = context.getBean(AccountService.class);

        Account account = new Account("Savings", "8299199737", "Shobhit Gupta");
        acc.createAccount(account);

        System.out.println(acc.getAccountDetails(1001));
        System.out.println(acc.checkBalance(1001));
        System.out.println(acc.deposit(1001, 1000));
        System.out.println(acc.withdraw(1001,500));
        System.out.println(acc.checkBalance(1001));
        System.out.println(account);
        acc.deleteAccount(1001);
        try {
            System.out.println(acc.getAccountDetails(1001));
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
        System.out.println("shobhit gupta");
    }
}
