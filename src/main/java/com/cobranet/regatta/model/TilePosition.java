package com.cobranet.regatta.model;

public class TilePosition {


    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Boolean getOnTable() {
        return onTable;
    }

    public TilePosition(){
        onTable = false;
    }

    public void setOnTable(Boolean onTable) {
        this.onTable = onTable;
    }

    Integer angle;
    Integer row;
    Integer col;
    Boolean onTable;

}
