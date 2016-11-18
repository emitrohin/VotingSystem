package ru.emitrohin.votingsystem.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */
public class MenuDish extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    private Double price;

    public MenuDish(Integer id, Double price) {
        super(id);
        this.price = price;
    }

}
