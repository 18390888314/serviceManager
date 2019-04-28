package com.definesys.dsgc.controller;

import com.definesys.dsgc.bean.FndModules;
import com.definesys.dsgc.service.FndModulesService;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.db.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/dsgc/fndModules")
@RestController
public class FndModulesController {

    @Autowired
    private SWordLogger logger;

    @Autowired
    FndModulesService fndModulesService;

    @RequestMapping(value = "/modifyFndModules", method = RequestMethod.POST)
    public Response modifyUser(@RequestBody FndModules u) {
        String id = "";
        if (u.getModuleId() == null || u.getModuleId().trim().length() == 0) {
            id = this.fndModulesService.createFndModules(u);
        } else {
            id = this.fndModulesService.updateFndModules(u);
        }
        this.logger.debug("id " + id);
        return Response.ok().data(id);
    }

    @RequestMapping(value = "/query", method = {RequestMethod.POST})
    public Response query(@RequestBody FndModules u,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                          @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex) {
        this.logger.debug("pageSize " + pageSize);
        this.logger.debug("pageIndex " + pageIndex);
        this.logger.debug("data " + u);
        PageQueryResult<FndModules> datas = this.fndModulesService.query(u, pageSize, pageIndex);
        this.logger.debug("getResult " + datas.getResult());
        return Response.ok().data(datas);
    }

    @RequestMapping(value = "/findFndModulesById", method = RequestMethod.POST)
    public Response findUserById(@RequestBody FndModules u) {
        FndModules fndModules = this.fndModulesService.findFndModulesById(u);
        this.logger.debug("fndProperties " + fndModules.getModuleId());
        return Response.ok().data(fndModules);
    }

}
