package com.company.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ponomarev_ia on 29.08.2019.
 */
@Entity
@Table(name = "DRAFT" , schema = "USERS")
public class DraftEntity {
    @Id
    @Column(name = "number")
    Integer number;
    @Column(name = "letter")
    String letter;
    @Column(name = "flag")
    boolean flag;

    public DraftEntity(String letter, Integer number, boolean flag) {
        this.flag = flag;
        this.letter = letter;
        this.number = number;
    }
    public DraftEntity(){}
}
