package ru.emitrohin.votingsystem.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.CollectionUtils;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.*;

/**
 * Author:        E_Mitrohin
 * Creation date: 05.10.2016
 * Project:       votingsystem
 */
public class Menu extends BaseEntity {

    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MenuDish> dishes;

    @Column(name = "created", columnDefinition = "timestamp default now()")
    private Date created = new Date();

    public Menu() {

    }

    public Menu(Integer id, Set<MenuDish> dishes) {
        super(id);
        setDishes(dishes);
    }

    public Set<MenuDish> getDishes() {
        return dishes;
    }

    public void setDishes(Collection<MenuDish> dishes) {
        this.dishes = CollectionUtils.isEmpty(dishes) ? Collections.emptySet() : new HashSet<>(dishes);
    }


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
