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
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "user_id", "vote_timestamp"}, name = "votes_restaurant_id_user_id_vote_timestamp_idx")})
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
    @Column(name = "vote_timestamp", columnDefinition = "timestamp default now()")
    private LocalDate voteTimestamp;

    public Vote() {
    }

    public Vote(Vote vote) {
        this(vote.getId(), vote.getRestaurant(), vote.getUser(), vote.getVoteTimestamp());
    }

    public Vote(Integer id, Restaurant restaurant, User user, LocalDate voteTimestamp) {
        super(id);
        this.restaurant = restaurant;
        this.user = user;
        this.voteTimestamp = voteTimestamp;
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

    public LocalDate getVoteTimestamp() {
        return voteTimestamp;
    }

    public void setVoteTimestamp(LocalDate currentDate) {
        this.voteTimestamp = currentDate;
    }
}
