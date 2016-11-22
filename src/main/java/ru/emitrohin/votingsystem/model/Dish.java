package ru.emitrohin.votingsystem.model;

import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Author:        E_Mitrohin
 * Creation date: 05.10.2016
 * Project:       votingsystem
 */

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "dishes_name_idx")})
public class Dish extends NamedEntity {

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "categories", joinColumns = @JoinColumn(name = "dish_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "category")
    private Set<Category> categories;

    public Dish() {
    }

    public Dish(Integer id, String name, String imageLink, Set<Category> categories) {
        super(id, name, imageLink);
        setCategories(categories);
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = CollectionUtils.isEmpty(categories) ? Collections.emptySet() : new HashSet<>(categories);
    }
}
