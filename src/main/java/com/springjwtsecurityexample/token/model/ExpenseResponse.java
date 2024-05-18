package com.springjwtsecurityexample.token.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Список категорий")
public class ExpenseResponse {
    // после того как залогинились и у нас уже есть сессия
    List<Category> categories;

}
