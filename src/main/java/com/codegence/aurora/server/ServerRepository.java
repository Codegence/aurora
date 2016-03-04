package com.codegence.aurora.server;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Created by lmorganti on 19/02/16.
 */
@EnableScan
@EnableScanCount
public interface ServerRepository extends PagingAndSortingRepository<Server, String> {
    Page<Server> findAllByPlayerID(String playerID, Pageable pageable);

    Server findByServerID(String playerID);

}
