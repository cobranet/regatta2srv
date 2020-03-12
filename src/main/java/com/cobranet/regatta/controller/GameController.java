package com.cobranet.regatta.controller;

import com.cobranet.regatta.model.RegattaGame;
import com.cobranet.regatta.oauth.User;
import com.cobranet.regatta.oauth.UserRepository;
import com.cobranet.regatta.payload.StartGame;
import com.cobranet.regatta.service.GameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class GameController {

    @Autowired
    GameService gameService;
    @PostMapping("/games")
    public void startGame(@RequestBody StartGame startgame) throws JsonProcessingException {

        gameService.startGame(startgame.getWhiteId(),startgame.getBlackId());
    }

    @GetMapping("/games/click/{tileid}")
    public String tileClicked(Principal p, @PathVariable Integer tileid) throws JsonProcessingException {
        gameService.tileClicked(p.getName(),tileid);
        return "OK";
    }

    @GetMapping("/games/my")
    public RegattaGame getMygame(Principal p) throws JsonProcessingException {
        return gameService.getMyGame(p.getName());
    }

}
