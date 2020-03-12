package com.cobranet.regatta.model;

import com.cobranet.regatta.oauth.User;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class RegataGameEntity {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getWhite() {
        return white;
    }

    public void setWhite(Long white) {
        this.white = white;
    }

    public Long getBlack() {
        return black;
    }

    public void setBlack(Long black) {
        this.black = black;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public LocalDate getStarted() {
        return started;
    }

    public void setStarted(LocalDate started) {
        this.started = started;
    }

    public LocalDate getClosed() {
        return closed;
    }

    public void setClosed(LocalDate closed) {
        this.closed = closed;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getJsonGame() {
        return jsonGame;
    }

    public void setJsonGame(String jsonGame) {
        this.jsonGame = jsonGame;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    Long white;
    Long black;
    Integer active;
    LocalDate started;
    LocalDate closed;
    Integer score;

    @Column(length=32000)
    @Type(type="text")
    String jsonGame;


}
