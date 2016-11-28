package ru.emitrohin.votingsystem.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   25.11.2016.
 */

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx")})
public class Restaurant extends BaseEntity {

    @NotEmpty
    @SafeHtml
    @Length(min = 3)
    private String name;

    @SafeHtml
    private String imageLink;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Menu> menus;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String imageLink) {
        super(id);
        this.name = name;
        this.imageLink = imageLink;
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getImageLink());
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

    public List<Menu> getMenus() {
        return menus;
    }
}

