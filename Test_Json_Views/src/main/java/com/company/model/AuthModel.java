package com.company.model;

import com.company.model.View.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ponomarev_ia on 29.08.2019.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthModel {
    @JsonView(Views.Public.class)
    private String login;
    @JsonView(Views.Public.class)
    private String role;
}
