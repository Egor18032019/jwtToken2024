package com.springjwtsecurityexample.token.service;

import com.springjwtsecurityexample.token.model.Category;
import com.springjwtsecurityexample.token.model.ExpenseResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ExpenseService {

    public ExpenseResponse giveMeAllInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // получаем имя пользователя
        System.out.println(username + "username");
        // тащим все данные из БД
        return new ExpenseResponse();
    }

    public ExpenseResponse changeOneCategoryAndGiveMeAllInfo(Category category) {
// сохраняем в БД(проверки если ли и т.п.)
        return new ExpenseResponse();
    }

    public ExpenseResponse addOneCategoryAndGiveMeAllInfo(Category category) {
        // сохраняем в БД(проверки если ли и т.п.)
        return new ExpenseResponse();
    }
}
