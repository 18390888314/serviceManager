package com.definesys.dsgc.controller;

import com.definesys.dsgc.bean.FndProperties;
import com.definesys.dsgc.service.FndPropertiesService;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.db.PageQueryResult;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequestMapping(value = "/dsgc/fndProperties")
@RestController
public class FndPropertiesController {

    @Autowired
    FndPropertiesService fndPropertiesService;

    @Autowired
    private SWordLogger logger;

    @RequestMapping(value = "/createFndProperties", method = RequestMethod.POST)
    public Response createUser(@RequestBody FndProperties u) {
        String id = this.fndPropertiesService.createFndProperties(u);
        this.logger.debug("id " + id);
        return Response.ok().data(id);
    }


    @RequestMapping(value = "/modifyFndProperties", method = RequestMethod.POST)
    public Response modifyUser(@RequestBody FndProperties u) {
        String id = "";
        if (u.getPropertyId() == null || u.getPropertyId().trim().length() == 0) {
            id = this.fndPropertiesService.createFndProperties(u);
        } else {
            id = this.fndPropertiesService.updateFndProperties(u);
        }
        this.logger.debug("id " + id);
        return Response.ok().data(id);
    }

    @RequestMapping(value = "/query", method = {RequestMethod.POST, RequestMethod.GET})
    public Response query(@RequestBody FndProperties u,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                          @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                          HttpServletResponse response) {
        this.logger.debug("pageSize " + pageSize);
        this.logger.debug("pageIndex " + pageIndex);
        this.logger.debug("datas " + u);
        PageQueryResult<FndProperties> datas = this.fndPropertiesService.query(u, pageSize, pageIndex);
        this.logger.debug("getResult " + datas.getResult());
        return Response.ok().data(datas);
    }

    @RequestMapping(value = "/findFndPropertiesById", method = RequestMethod.POST)
    public Response findUserById(@RequestBody FndProperties u) {
        FndProperties fndProperties = this.fndPropertiesService.findFndPropertiesById(u);
        this.logger.debug("fndProperties " + fndProperties.getPropertyId());
        return Response.ok().data(fndProperties);
    }

    @RequestMapping(value = "/deleteFndPropertiesById", method = RequestMethod.POST)
    public Response deleteUserById(@RequestBody FndProperties u) {
        this.logger.debug("uid " + u.getPropertyId());
        String uid = this.fndPropertiesService.deleteFndPropertiesById(u);
        this.logger.debug("uid " + uid);
        return Response.ok().data(uid);
    }

}
