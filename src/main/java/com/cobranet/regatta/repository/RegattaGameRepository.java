package com.cobranet.regatta.repository;


import com.cobranet.regatta.model.RegataGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegattaGameRepository extends JpaRepository<RegataGameEntity, Long> {

    Optional<RegataGameEntity> findById(Long id);

    @Query( "select r from RegataGameEntity r  where (white = ?1 or black = ?1) and active = 1" )
    Optional<RegataGameEntity> myGame(Long id);
}