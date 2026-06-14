package com.shobhit.bankingsimulator;

import com.shobhit.bankingsimulator.config.AppConfig;
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
