package com.company.model;

import com.company.model.View.Views;
import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PojoUser {
    private int id;

    @NotNull
    @Size(min = 1 , max = 10)
    @JsonView(Views.Public.class)
    private String name;
    @JsonView(Views.Public.class)
    private String password;
    @JsonView(Views.Public.class)
    private String secondName;
    @JsonView(Views.Internal.class)
    private String status;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    //необходим для создания экземпляров из запроса
    public PojoUser(){}

    public PojoUser(int i , String name, String secondName , String status , String password){
        this.id = i;
        this.name = name;
        this.secondName = secondName;
        this.status = status;
        this.password = password;
    }


    public PojoUser(int i, String name, String secondName, String status) {
        this.id = i;
        this.name = name;
        this.secondName = secondName;
        this.status = status;
        this.password = null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "{ " + "\"id\"" + " : " + "\"" + id + "\"" + ", " +
                "\"name\"" + " : " + "\"" + name + "\"" + ", " +
                "\"password\"" + " : " + "\"" + password + "\"" + ", " +
                "\"second_name\"" + " : " + "\"" + secondName + "\"" + ", " +
                "\"status\"" + " : " + "\"" + status + "\""
                + " }";
    }
}