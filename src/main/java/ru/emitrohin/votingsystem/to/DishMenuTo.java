package ru.emitrohin.votingsystem.to;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author emitrohin
 * @version 1.0
 *          27.11.2016
 */
public class DishMenuTo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull
    private Integer menuId;

    @NotNull
    private Integer dishId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfMenu;

    @NotEmpty
    @SafeHtml
    @Length(min = 3)
    private String name;

    @Column(precision = 10, scale = 2)
    @NotNull
    private Double price;

    public DishMenuTo() {
    }

    public DishMenuTo(Integer id, Integer menuId, Integer dishId, LocalDate dateOfMenu, String name, Double price) {
        this.id = id;
        this.dateOfMenu = dateOfMenu;
        this.name = name;
        this.price = price;
        this.menuId = menuId;
        this.dishId = dishId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public LocalDate getDateOfMenu() {
        return dateOfMenu;
    }

    public void setDateOfMenu(LocalDate dateOfMenu) {
        this.dateOfMenu = dateOfMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
