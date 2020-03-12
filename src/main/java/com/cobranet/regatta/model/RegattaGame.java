package com.cobranet.regatta.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class RegattaGame {
    public ArrayList<ArrayList<Tile>> getTable() {
        return table;
    }

    public void setTable(ArrayList<ArrayList<Tile>> table) {
        this.table = table;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public Player getBlack() {
        return black;
    }

    public void setBlack(Player black) {
        this.black = black;
    }

    public Player getWhite() {
        return white;
    }

    public void setWhite(Player white) {
        this.white = white;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public Integer getOnMove() {
        return onMove;
    }

    public void setOnMove(Integer onMove) {
        this.onMove = onMove;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    private ArrayList<ArrayList<Tile>> table;
    private ArrayList<Tile> tiles;
    private Player black;
    private Player white;



    public Integer getSelectedTileId() {
        return selectedTileId;
    }

    public void setSelectedTileId(Integer selectedTileId) {
        this.selectedTileId = selectedTileId;
    }

    private Integer selectedTileId;
    private ArrayList<Move> moves;
    /* On move white = 0, black = 1 colors are same .. -1 end game */
    private Integer onMove;


    public static RegattaGame createRegattaGame(Player black, Player white){
        RegattaGame rg = new RegattaGame();
        rg.table = new ArrayList<>(64);
        rg.moves = new ArrayList<>();
        rg.tiles = new ArrayList<>(42);
        rg.onMove = 0;
        rg.black = black;
        rg.white = white;
        for(int i=0;i<8;i++){
            ArrayList<Tile> row = new ArrayList<Tile>();
            rg.table.add(row);
            for (int k=0;  k<8; k++ ){
                rg.table.get(i).add(null);
            }
        }
        for (int i=0;i<42;i++) {
            Tile t = new Tile();
            t.setId(i);
            t.setPosition(new TilePosition());
            t.setOldPosition(new TilePosition());
            if(i<21){
                t.setColor(0);
            } else {
                t.setColor(1);
            }
            rg.tiles.add(t);
        }
        return rg;
    }
    public static RegattaGame getGameFromJSON(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        RegattaGame g = mapper.readValue(json,RegattaGame.class);
        return g;
    }

    public Integer userNumber(Long userId){
        if(white.getId() == userId){
            return 0;
        }
        return 1;
    }

    public Boolean isUserOnMove(Long userId){
        if ( userNumber(userId) == onMove ){
            return true;
        }
        return false;
    }
    public Boolean clickTile(Integer tileId,Long userId){
        /* ako nije na potezu svakako  ne moze da klikne */
        if(!isUserOnMove(userId)){
            return false;
        }
        if(!isSelectable(tileId)){
            return false;
        }
        selectedTileId = tileId;
        return true;
    }

    public Boolean isSelectable(Integer tileId){
        if( tiles.get(tileId).color != onMove ){
            return false;
        }
        return true;
    }

    public String toJSON() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }


}
