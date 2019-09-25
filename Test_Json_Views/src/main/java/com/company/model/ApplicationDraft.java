package com.company.model;

import com.company.model.View.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ponomarev_ia on 29.08.2019.
 * в экземпляр этого класса сериализуется Json , который мы передаём , чтобы в дальнейшем засунуть в БД
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationDraft {
    @JsonView(Views.Public.class)
    int number;
    @JsonView(Views.Public.class)
    String letter;
    @JsonView(Views.Public.class)
    boolean flag;
}