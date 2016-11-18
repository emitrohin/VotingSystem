package ru.emitrohin.votingsystem.model;

import org.springframework.util.CollectionUtils;

import javax.persistence.Entity;
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
public class Dish extends NamedEntity {

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
