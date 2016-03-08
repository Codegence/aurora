package com.codegence.aurora.game;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lmorganti on 07/03/16.
 */
@EnableScan
public interface GameRopository extends CrudRepository<Game, String> {
}
