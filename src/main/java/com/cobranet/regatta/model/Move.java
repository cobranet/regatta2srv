package com.cobranet.regatta.model;



public class Move {
    enum What {
        PLACE,
        SLIDE,
        ACTAIVATE,
        PICK_UP,
        PIVOT,
    }

    public What getWhat() {
        return what;
    }

    public void setWhat(What what) {
        this.what = what;
    }

    public Integer getWho() {
        return who;
    }

    public void setWho(Integer who) {
        this.who = who;
    }

    public Boolean getBonus() {
        return isBonus;
    }

    public void setBonus(Boolean bonus) {
        isBonus = bonus;
    }

    public TilePosition getTo() {
        return to;
    }

    public void setTo(TilePosition to) {
        this.to = to;
    }

    What what;

    public Boolean getDeactivated() {
        return isDeactivated;
    }

    public void setDeactivated(Boolean deactivated) {
        isDeactivated = deactivated;
    }

    Integer who;
    Boolean isBonus;
    TilePosition to;
    Boolean isDeactivated;
}
