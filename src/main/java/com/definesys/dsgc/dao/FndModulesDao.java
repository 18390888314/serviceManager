package com.definesys.dsgc.dao;

import com.definesys.dsgc.bean.FndModules;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import com.definesys.mpaas.query.db.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FndModulesDao {
    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;


    public String createFndModules(FndModules t) {
        logger.debug(t.toString());
        sw.buildQuery().
                doInsert(t);
        return t.getModuleId();
    }

    public String updateFndModules(FndModules t) {
        logger.debug(t.toString());
        sw.buildQuery()
                .eq("moduleId", t.getModuleId())
                .doUpdate(t);
        return t.getModuleId();
    }

    public String deleteFndModulesById(FndModules t) {
        logger.debug(t.toString());
        sw.buildQuery()
                .eq("moduleId", t.getModuleId())
                .doDelete(t);
        return t.getModuleId();
    }

    public PageQueryResult<FndModules> query(FndModules t, int pageSize, int pageIndex) {
        logger.debug(t.toString());
        return sw.buildQuery()
                .like("moduleCode", t.getModuleCode())
                .like("moduleName", t.getModuleName())
                .like("moduleDescription", t.getModuleDescription())
                .doPageQuery(pageIndex, pageSize, FndModules.class);
    }

    public FndModules findFndModulesById(FndModules t) {
        logger.debug(t.toString());
        return sw.buildQuery().eq("moduleId", t.getModuleId()).doQueryFirst(FndModules.class);
    }


}
