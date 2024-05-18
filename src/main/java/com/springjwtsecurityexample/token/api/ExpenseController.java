package com.springjwtsecurityexample.token.api;

import com.springjwtsecurityexample.token.utils.EndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPoint.api)
@RequiredArgsConstructor
@Tag(name = "Общая информация")
public class ExpenseController {

    @GetMapping(EndPoint.expense)
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Доступен только авторизованным пользователям с ролью USER")
    public ResponseEntity<?> giveMeExpense() {

        return ResponseEntity.ok("Admin");
    }
}
