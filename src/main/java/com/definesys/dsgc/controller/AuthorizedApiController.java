package com.definesys.dsgc.controller;

import com.definesys.dsgc.bean.AuthorizedApi;
import com.definesys.dsgc.service.AuthorizedApiService;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.log.SWordLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/dsgc/authorizedInterface",method = RequestMethod.GET)
@Api(description = "接口授权",tags = "查询所有已授权用户")
public class AuthorizedApiController {
    @Autowired
    private AuthorizedApiService authorizedApiService;
    @Autowired
    private SWordLogger logger;

    @ApiOperation(value ="查询所有用户",notes = "查询所有授权的用户")
    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    public Response findUser(){
        List<AuthorizedApi> list = this.authorizedApiService.findAllUser();
        this.logger.debug("list" +  list);
        return Response.ok().data(list);
    }
}
