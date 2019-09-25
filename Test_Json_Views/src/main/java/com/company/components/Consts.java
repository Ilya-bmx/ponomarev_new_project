package com.company.components;

import com.company.service.Registrate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by ponomarev_ia on 03.09.2019.
 * Добавить констант final для уровней доступа , чтобы лучше ориентироваться
 */
@Component
public class Consts {

    public static ArrayList<String> access_level;
    static {
        access_level = new ArrayList<>();
        access_level.add("buyer");
        access_level.add("worker");
        access_level.add("admin");
    }
}
