package ru.emitrohin.votingsystem.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author emitrohin
 * @version 1.0
 *          26.11.2016
 */

@Entity
@Table(name = "menus", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "dateOfMenu"}, name = "restaurant_id_created_idx")})
public class Menu extends BaseEntity {

    @Column(name = "dateOfMenu", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfMenu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Menu() {
    }

    public Menu(Menu menu) {
        this(menu.getId(), menu.getDateOfMenu());
    }

    public Menu(Integer id, LocalDate dateOfMenu) {
        super(id);
        this.dateOfMenu = dateOfMenu;
    }


    public LocalDate getDateOfMenu() {
        return dateOfMenu;
    }

    public void setDateOfMenu(LocalDate dateOfMenu) {
        this.dateOfMenu = dateOfMenu;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
