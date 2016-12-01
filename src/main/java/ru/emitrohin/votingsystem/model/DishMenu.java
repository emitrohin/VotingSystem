package ru.emitrohin.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author emitrohin
 * @version 1.0
 *          27.11.2016
 */

@Entity
@Table(name = "menus_dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"dish_id", "menu_id"}, name = "dish_id_menu_id_idx")})
public class DishMenu extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "menu_id")
    @JsonBackReference
    private Menu menu;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @Column(precision = 10, scale = 2)
    @NotNull
    private Double price;

    public DishMenu() {
    }

    public DishMenu(Integer id, Menu menu, Dish dish, Double price) {
        super(id);
        this.menu = menu;
        this.dish = dish;
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
