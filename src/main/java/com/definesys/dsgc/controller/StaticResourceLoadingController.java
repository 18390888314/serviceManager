package com.definesys.dsgc.controller;

import com.definesys.dsgc.bean.StaticResourceLoading;
import com.definesys.dsgc.service.StaticResourceLoadingService;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.log.SWordLogger;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/dsgc/StaticResourceLoading")
@RestController
public class StaticResourceLoadingController {
    @Autowired
    private SWordLogger logger;
    @Autowired
    StaticResourceLoadingService staticResourceLoadingService;
    @RequestMapping(value = "/getStaticResource", method = RequestMethod.GET)
    public Response  getStaticResource(){
      List<StaticResourceLoading> staticResourceList = staticResourceLoadingService.getStaticResource();
        this.logger.debug("serviceList : "+staticResourceList);
       JSONObject jsonObject = staticResourceLoadingService.DataFiltering(staticResourceList);
        return Response.ok().data(jsonObject);
    }

}


