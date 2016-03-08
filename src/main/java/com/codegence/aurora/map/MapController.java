package com.codegence.aurora.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by lmorganti on 04/03/16.
 */
@RestController
@RequestMapping(value = "api/map")
public class MapController {
    @Autowired
    private MapService mapService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Map> listMap(OAuth2Authentication user,
                             @RequestParam(value = "index") Integer index,
                             @RequestParam(value = "size") Integer size) {
        return mapService.listMap(new PageRequest(index, size));
    }
}
