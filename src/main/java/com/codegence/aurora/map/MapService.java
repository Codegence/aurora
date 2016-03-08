package com.codegence.aurora.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Created by lmorganti on 04/03/16.
 */
@Service
public class MapService {
    @Autowired
    private MapRepository mapRepository;

    public Page<Map> listMap(Pageable page) {
        return mapRepository.findAll(page);
    }
}
