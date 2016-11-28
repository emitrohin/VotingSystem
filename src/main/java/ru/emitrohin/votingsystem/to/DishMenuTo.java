package ru.emitrohin.votingsystem.to;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

    @Column(precision = 10, scale = 2)
    @NotNull
    private Double price;

    public DishMenuTo() {
    }

    public DishMenuTo(Integer id, Integer menuId, Integer dishId, Double price) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
