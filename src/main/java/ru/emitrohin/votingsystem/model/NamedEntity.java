package ru.emitrohin.votingsystem.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.MappedSuperclass;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */
@MappedSuperclass
public class NamedEntity extends BaseEntity {

    @NotEmpty
    @SafeHtml
    protected String name;

    @NotEmpty
    @SafeHtml
    protected String imageLink;

    public NamedEntity() {
    }

    protected NamedEntity(Integer id, String name, String imageLink) {
        super(id);
        this.name = name;
        this.imageLink = imageLink;
    }

    public String getName() {
        return this.name;
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

    @Override
    public String toString() {
        return name;
    }
}