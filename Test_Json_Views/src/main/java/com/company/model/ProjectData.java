package com.company.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ponomarev_ia on 03.09.2019.
 */
@Data
@Entity
@Table(name = "DATA_TABLE", schema = "USERS")
public class ProjectData {

    @Id
    @Column(name = "login")
    String  login;
    @Column(name = "vendor_code")
    Integer vendor_code;
    @Column(name = "access")
    String address;

    public ProjectData(){}

    public ProjectData(String login , Integer vendor_code , String address){
        this.login = login;
        this.vendor_code = vendor_code;
        this.address = address;
    }

}
