package com.codegence.aurora.map;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by lmorganti on 04/03/16.
 */
@EnableScan
public interface MapRepository extends PagingAndSortingRepository<Map, String> {
    @EnableScanCount
    Page<Map> findAll(Pageable pageable);
}
