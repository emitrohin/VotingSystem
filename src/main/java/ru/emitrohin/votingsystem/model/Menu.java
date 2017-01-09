package ru.emitrohin.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "menu")
    private List<Dish> dishList;

    public Menu() {
    }

    public Menu(Menu dishList) {
        this(dishList.getId(), dishList.getDateOfMenu());
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

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }
}
