package ru.emitrohin.votingsystem.model;

/**
 * Author:        E_Mitrohin
 * Creation date: 05.10.2016
 * Project:       votingsystem
 */
public class Restaurant extends NamedEntity {

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String imageLink) {
        super(id, name, imageLink);
    }
}
