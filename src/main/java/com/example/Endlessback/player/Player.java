package com.example.Endlessback.player;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Date;
@Entity
@Table
public class Player {
    @Id
    @SequenceGenerator(
            name = "player_sequence",
            sequenceName = "player_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private Double level;//дабл для расчета прогресса
    private Integer[] projects;//сделвй как айди для таблицы с проектми
    private LocalDate last_online;
    private String avatar_path;
    private Integer coins;
    @Transient
    private Integer bonus;

    public Player() {

    }

    public Player(Long id, String name, String email, Double level, Integer[] projects, LocalDate last_online, String avatar_path, Integer coins) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.level = level;
        this.projects = projects;
        this.last_online = last_online;
        this.avatar_path = avatar_path;
        this.coins = coins;
    }

    public Player(String name, String email, Double level, Integer[] projects, LocalDate last_online, String avatar_path, Integer coins) {
        this.name = name;
        this.email = email;
        this.level = level;
        this.projects = projects;
        this.last_online = last_online;
        this.avatar_path = avatar_path;
        this.coins = coins;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public Integer[] getProjects() {
        return projects;
    }

    public void setProjects(Integer[] projects) {
        this.projects = projects;
    }

    public LocalDate getLast_online() { return last_online; }

    public void setLast_online(LocalDate last_online) { this.last_online = last_online; }

    public String getAvatar_path() {
        return avatar_path;
    }

    public void setAvatar_path(String avatar_path) {
        this.avatar_path = avatar_path;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Integer getBonus() {
        //calculate bonus
        int lost_days = Period.between(this.last_online, LocalDate.now()).getDays();
        return lost_days + this.level.intValue() + this.coins;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", level=" + level +
                ", projects=" + Arrays.toString(projects) +
                ", last_online=" + last_online +
                ", avatar_path='" + avatar_path + '\'' +
                ", coins=" + coins +
                ", bonus=" + bonus +
                '}';
    }
}
