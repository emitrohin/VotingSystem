package ru.emitrohin.votingsystem.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author emitrohin
 * @version 1.0
 *          27.11.2016
 */

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "dishes_name_idx")})
public class Dish extends BaseEntity {

    @NotEmpty
    @SafeHtml
    @Length(min = 3)
    private String name;

    @SafeHtml
    private String imageLink;

    public Dish() {
    }

    public Dish(Dish dish) {
        this(dish.getId(), dish.getImageLink(), dish.getImageLink());
    }

    public Dish(Integer id, String name, String imageLink) {
        super(id);
        this.name = name;
        this.imageLink = imageLink;
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
}
