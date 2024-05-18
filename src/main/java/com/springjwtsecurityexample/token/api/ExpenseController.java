package com.springjwtsecurityexample.token.api;

import com.springjwtsecurityexample.token.model.Category;
import com.springjwtsecurityexample.token.model.ExpenseResponse;
import com.springjwtsecurityexample.token.service.ExpenseService;
import com.springjwtsecurityexample.token.utils.EndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPoint.api)
@RequiredArgsConstructor
@Tag(name = "Общая информация")
public class ExpenseController {
    ExpenseService expenseService;

    @GetMapping(EndPoint.expense)
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Получаем данные пользователя")
    public ResponseEntity<ExpenseResponse> giveMeAllInfo() {
        // после того как залогонились вытасикваем данные полльзователя
        ExpenseResponse expenseResponse = expenseService.giveMeAllInfo();
        return ResponseEntity.ok(expenseResponse);
    }

    @PostMapping(EndPoint.expense)
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Изменяем одну категорию")
    public ResponseEntity<ExpenseResponse> changeOneCategory(Category category) {
        // изменяем одну категорию.
        // нужны ли все поля??
        ExpenseResponse expenseResponse = expenseService.changeOneCategoryAndGiveMeAllInfo(category);

        return ResponseEntity.ok(expenseResponse);
    }

    @PostMapping(EndPoint.expense)
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Добавляем одну категорию")
    public ResponseEntity<ExpenseResponse> addOneCategory(Category category) {
        // добавляем одну категорию.
        ExpenseResponse expenseResponse = expenseService.addOneCategoryAndGiveMeAllInfo(category);

        return ResponseEntity.ok(expenseResponse);
    }
}
