package com.cobranet.regatta.model;

public class Tile {
    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }


    public TilePosition getPosition() {
        return position;
    }

    public void setPosition(TilePosition position) {
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    TilePosition position;

    public TilePosition getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(TilePosition oldPosition) {
        this.oldPosition = oldPosition;
    }

    TilePosition oldPosition;
    Integer id;
    Integer color;
}
