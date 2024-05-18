package com.springjwtsecurityexample.token.store;

import com.springjwtsecurityexample.token.model.Category;
import com.springjwtsecurityexample.token.store.base.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryStore extends AbstractBaseEntity {
    //todo username зависимость от таблицы user
    @Column(nullable = false)
    String username;
    @Column(nullable = false, unique = true)
    String name;
    @Column(nullable = false)
    Long money;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    Long quota;

    public Category getCategory() {
        return new Category(this.name, this.money, this.description, this.quota);
    }
}
