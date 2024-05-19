package com.springjwtsecurityexample.token.repository;

import com.springjwtsecurityexample.token.store.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}