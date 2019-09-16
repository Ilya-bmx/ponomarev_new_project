package com.company.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ponomarev_ia on 29.08.2019.
 */
@Data
@Entity
@Table(name = "ACCESS_TABLE", schema = "USERS")
public class AccessModel {
    @Id
    @Column(name = "login")
    String login;
    @Column(name = "accession")
    String accession;

    public AccessModel(String login, String accession) {
        this.login = login;
        this.accession = accession;
    }
    public AccessModel(){}
}
