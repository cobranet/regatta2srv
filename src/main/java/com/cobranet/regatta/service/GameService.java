package com.cobranet.regatta.service;

import com.cobranet.regatta.model.ChatMessage;
import com.cobranet.regatta.model.Player;
import com.cobranet.regatta.model.RegataGameEntity;
import com.cobranet.regatta.model.RegattaGame;
import com.cobranet.regatta.oauth.User;
import com.cobranet.regatta.oauth.UserRepository;
import com.cobranet.regatta.repository.RegattaGameRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GameService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RegattaGameRepository regattaGameRepository;
    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;



    public void tileClicked(String username, Integer tileid ) throws JsonProcessingException {
     RegataGameEntity rgt =getMyEntityGame(username);
     RegattaGame reg = RegattaGame.getGameFromJSON(rgt.getJsonGame());
     Long userId = userRepository.findByEmail(username).get().getId();
     if( reg.clickTile(tileid,userId)){
           rgt.setJsonGame(reg.toJSON());
           regattaGameRepository.save(rgt);
         ChatMessage chatMessage = new ChatMessage();
         chatMessage.setType(ChatMessage.MessageType.LEAVE);
         chatMessage.setSender(username);

         messagingTemplate.convertAndSend("/queue/"+username, chatMessage);
     }

    }
    public void startGame(Long whiteId, Long blackId) throws JsonProcessingException {
        User white = userRepository.getOne(whiteId);
        User black = userRepository.getOne(blackId);
        Player wp = new Player();
        wp.setId(white.getId());
        wp.setImageUrl(white.getImageUrl());
        wp.setName(white.getName());
        Player bp = new Player();
        bp.setId(black.getId());
        bp.setImageUrl(black.getImageUrl());
        bp.setName(black.getName());
        RegattaGame rg =  RegattaGame.createRegattaGame(wp,bp);
        RegataGameEntity regEntity = new RegataGameEntity();
        regEntity.setActive(1);
        regEntity.setBlack(blackId);
        regEntity.setWhite(whiteId);
        regEntity.setStarted(LocalDate.now());
        regEntity.setJsonGame(rg.toJSON());
        regattaGameRepository.save(regEntity);
    }

    public RegataGameEntity getMyEntityGame(String username)throws JsonProcessingException {
        User me = userRepository.findByEmail(username).get();
        RegataGameEntity reg =  regattaGameRepository.myGame(me.getId()).get();
        return reg;
    }

    public RegattaGame getMyGame(String username) throws JsonProcessingException {
        RegataGameEntity reg = getMyEntityGame(username);
        RegattaGame rg = RegattaGame.getGameFromJSON(reg.getJsonGame());
        rg.setId(reg.getId());
        return rg;
    }
}
