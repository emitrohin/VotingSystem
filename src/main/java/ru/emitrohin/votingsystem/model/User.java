package ru.emitrohin.votingsystem.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.util.CollectionUtils;
import ru.emitrohin.votingsystem.util.TimeUtil;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Role> roles;


    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private LocalDate registered = TimeUtil.now();

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getLogin(), u.getPassword(), u.getEmail(), u.getFirstName(), u.getLastName(), u.isEnabled(), u.getRoles());
    }

    public User(Integer id, String login, String password, String email, String firstName, String lastName, Role role, Role... roles) {
        this(id, login, password, email, firstName, lastName, true, EnumSet.of(role, roles));
    }

    public User(Integer id, String login, String password, String email, String firstName, String lastName, boolean enabled, Set<Role> roles) {
        super(id);
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        setRoles(roles);
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    public LocalDate getRegistered() {
        return registered;
    }
}
