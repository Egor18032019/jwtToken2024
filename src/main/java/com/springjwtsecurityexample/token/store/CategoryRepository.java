package com.springjwtsecurityexample.token.store;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryStore, Long> {
    List<CategoryStore> findAllByUsername(String username);

    CategoryStore findAllByUsernameAndName(String username, String name);
}
