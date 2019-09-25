package com.company.model;

/**
 * Created by ponomarev_ia on 28.08.2019.
 * Можно удалять , нигде не пригодится , сделал для примера как выводить xml
 */
import javax.xml.bind.annotation.*;

import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

    @XmlElement
    private String name;
    @XmlAttribute
    private int pin;
    @XmlElement
    private String secondName;

}
