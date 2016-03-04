package com.codegence.aurora.player;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lmorganti on 18/02/16.
 */
@EnableScan
public interface PlayerRepository extends CrudRepository<Player, String> {
    Player findByNickName(String nickName);
    Player findByGoogleID(String googleID);

}
