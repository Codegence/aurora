package com.codegence.aurora.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 * Created by lmorganti on 19/02/16.
 */
@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    public Player findByGoogleID(String googleID) {
        Player player;
        try {
            player = playerRepository.findByGoogleID(googleID);
        } catch (EmptyResultDataAccessException e) {
            player = null;
        }
        return player;
    }

    public Player findByNickName(String nickName) {
        Player player;
        try {
            player = playerRepository.findByNickName(nickName);

        } catch (EmptyResultDataAccessException e) {
            player = null;
        }
        return player;
    }

    public void save(Player player) {
        playerRepository.save(player);
    }
}
