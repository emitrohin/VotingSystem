package ru.emitrohin.votingsystem.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author emitrohin
 * @version 1.0
 *          27.11.2016
 */

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "dishes_name_idx")})
public class Dish extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @NotEmpty
    @SafeHtml
    @Length(min = 3)
    private String name;

    @NotNull
    private Long price;

    @SafeHtml
    private String imageLink;

    public Dish() {
    }

    public Dish(Dish dish) {
        this(dish.getId(), dish.getName(), dish.getPrice(), dish.getImageLink());
    }

    public Dish(Integer id, String name, Long price, String imageLink) {
        super(id);
        this.name = name;
        this.imageLink = imageLink;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
