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
    }
}
