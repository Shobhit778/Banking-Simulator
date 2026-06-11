package com.shobhit.bankingsimulator;

import com.shobhit.bankingsimulator.config.AppConfig;
import com.shobhit.bankingsimulator.enums.AccountType;
import com.shobhit.bankingsimulator.model.Account;
import com.shobhit.bankingsimulator.service.AccountService;
import com.shobhit.bankingsimulator.service.TransactionService;
import com.shobhit.bankingsimulator.ui.BankMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BankMenu menu = context.getBean(BankMenu.class);
        menu.start();
    }
}
