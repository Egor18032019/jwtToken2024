package com.springjwtsecurityexample.token.api;

import com.springjwtsecurityexample.token.model.Category;
import com.springjwtsecurityexample.token.model.ExpenseResponse;
import com.springjwtsecurityexample.token.service.ExpenseService;
import com.springjwtsecurityexample.token.utils.EndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndPoint.API)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Общая информация")
public class ExpenseController {
    ExpenseService expenseService;

    @GetMapping(EndPoint.EXPENSE)
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Получаем данные пользователя")
    public ResponseEntity<ExpenseResponse> giveMeAllInfo() {
        // после того как залогонились вытасикваем данные полльзователя
        ExpenseResponse expenseResponse = expenseService.giveMeAllInfo();
        return ResponseEntity.ok(expenseResponse);
    }

    @PutMapping(EndPoint.EXPENSE)
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Изменяем одну категорию")
    public ResponseEntity<Category> changeOneCategory(Category category) {
        // изменяем одну категорию.
        // нужны ли все поля??
        Category categoryAfterSave = expenseService.changeOneCategoryAndGiveMeAllInfo(category);

        return ResponseEntity.ok(categoryAfterSave);
    }

    @PostMapping(EndPoint.EXPENSE)
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Добавляем одну категорию")
    public ResponseEntity<Category> addOneCategory(Category category) {
        // добавляем одну категорию.
        Category categoryAfterSave = expenseService.addOneCategoryAndGiveMeCategory(category);

        return ResponseEntity.ok(categoryAfterSave);
    }
}
