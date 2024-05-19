package com.springjwtsecurityexample.token.service;

import com.springjwtsecurityexample.token.model.Category;
import com.springjwtsecurityexample.token.model.ExpenseResponse;
import com.springjwtsecurityexample.token.repository.ExpenseRepository;
import com.springjwtsecurityexample.token.store.CategoryRepository;
import com.springjwtsecurityexample.token.store.CategoryStore;
import com.springjwtsecurityexample.token.store.Expense;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Tag(name = "Expense  Service", description = "Все операции, связанные c бюджетом.")
public class ExpenseService {

    private final CategoryRepository repository;
    private final ExpenseRepository expenseRepository;

    public ExpenseResponse giveMeAllInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // получаем имя пользователя
        // тащим все данные из БД
        List<CategoryStore> allCategories = repository.findAllByUsername(username);
        //todo сделать мапер
        List<Category> categories = allCategories.stream().map(CategoryStore::getCategory).toList();
        return new ExpenseResponse(categories);
    }

    public Category changeOneCategoryAndGiveMeAllInfo(Category category) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // получаем имя пользователя
//TODO  сохраняем в БД(проверки если ли и т.п.)
        CategoryStore categoryStore = repository.findAllByUsernameAndName(username, category.getName());
        categoryStore.setMoney(category.getMoney());
        categoryStore.setDescription(category.getDescription());
        //todo нужен ли тут лимит ? или мы его задаем на ручке создание ?
        categoryStore.setQuota(category.getLimit());
        repository.save(categoryStore);
        return categoryStore.getCategory();
    }

    public Category addOneCategoryAndGiveMeCategory(Category category) {
        // сохраняем в БД(проверки если ли и т.п.)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // получаем имя пользователя
        CategoryStore categoryStore = new CategoryStore(username, category.getName(), category.getMoney(), category.getDescription(), category.getLimit());
        repository.save(categoryStore);
        return categoryStore.getCategory();
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Expense not found"));

        expense.setAmount(expenseDetails.getAmount());
        expense.setDate(expenseDetails.getDate());
        expense.setCategory(expenseDetails.getCategory());
        expense.setUser(expenseDetails.getUser());
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
