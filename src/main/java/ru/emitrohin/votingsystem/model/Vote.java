package ru.emitrohin.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Author: E_Mitrohin
 * Date:   28.11.2016.
 */

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "user_id", "vote_date"}, name = "restaurant_user_id_date_idx")})
public class Vote extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @NotNull
    @Column(name = "vote_date", columnDefinition = "timestamp default now()")
    private LocalDate voteDate;

    public Vote() {
    }

    public Vote(Vote vote) {
        this(vote.getId(), vote.getRestaurant(), vote.getUser(), vote.getVoteDate());
    }

    public Vote(Integer id, Restaurant restaurant, User user, LocalDate voteDate) {
        super(id);
        this.restaurant = restaurant;
        this.user = user;
        this.voteDate = voteDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDate currentDate) {
        this.voteDate = currentDate;
    }
}
