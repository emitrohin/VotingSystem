package ru.emitrohin.votingsystem.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "login", name = "users_unique_email_idx")})
public class User extends BaseEntity {

    @NotEmpty
    @SafeHtml
    private String login;

    @NotEmpty
    @Length(min = 8)
    @SafeHtml
    private String password;

    @NotEmpty
    @SafeHtml
    private String email;

    @NotEmpty
    @SafeHtml
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @SafeHtml
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered = new Date();

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getLogin(), u.getPassword(), u.getEmail(), u.getFirstName(), u.getLastName(), u.isEnabled());
    }

    public User(Integer id, String login, String password, String email, String firstName, String lastName) {
        this(id, login, password, email, firstName, lastName, true);
    }

    public User(Integer id, String login, String password, String email, String firstName, String lastName, boolean enabled) {
        super(id);
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }
}
