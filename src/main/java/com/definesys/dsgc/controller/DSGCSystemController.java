package com.definesys.dsgc.controller;


import com.definesys.dsgc.bean.DSGCSystem;
import com.definesys.dsgc.bean.DSGCServRouting;
import com.definesys.dsgc.service.DSGCSystemService;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.db.PageQueryResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhenglong
 * @Description: 当前系统所对应的controller
 * @Date 2019/3/12 14:24
 */
@RestController
@RequestMapping("/dsgc/system")
public class DSGCSystemController {
    @Autowired
    private DSGCSystemService dsgcSystemService;
    @Autowired
    private SWordLogger logger;

   @ApiOperation(value = "查询所有", notes = "查询所有的系统信息")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "system", value = "系统信息对象", dataType="DSGCSystem")
   })
   @RequestMapping(value = "/findAll", method = RequestMethod.GET)
   public Response findSystem() {
       List<DSGCSystem> list = this.dsgcSystemService.findAll();
       this.logger.debug("list" +  list);

       return Response.ok().data(list);
   }
    @ApiOperation(value = "根据系统的code查询接口对应的信息", notes = "根据系统的code查询接口对应的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemAndInterface", value = "系统与接口对应数据对象", dataType="DSGCSytemAndInterface"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataType="int"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页", dataType="int")
    })
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response query(@RequestBody DSGCSystem systemAndInterface,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                          @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex) {
        this.logger.debug("pageSize " + pageSize);
        this.logger.debug("pageIndex " + pageIndex);
        this.logger.debug("systemAndInterface " + systemAndInterface);
        PageQueryResult<DSGCServRouting> system = this.dsgcSystemService.query(systemAndInterface, pageSize, pageIndex);
        return Response.ok().data(system);
    }

}
