package com.definesys.dsgc.dao;

import com.definesys.dsgc.bean.DSGCServFlowContCfg;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InterfaceConfigurationDao {
    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;

    public void insertOsbServFlowContCfg(DSGCServFlowContCfg osbServFlowContCfg){
        this.logger.debug(" osbServFlowContCfg : "+osbServFlowContCfg);
        this.sw
                .buildQuery()
                .doInsert(osbServFlowContCfg);
        this.logger.debug("flowId : "+osbServFlowContCfg.getFlowId());
    }

    public List<DSGCServFlowContCfg> getOsbServFlowContCfgByServNo(String servNo){
        this.logger.debug(" servNo : "+servNo);
        return sw.buildQuery()
                .eq("servNo",servNo)
                .doQuery(DSGCServFlowContCfg.class);
    }



    public void deleteOsbServFlowContCfgByFlowId(String flowId){
        this.logger.debug(" flowId : "+flowId);
        sw
                .buildQuery()
                .eq("flowId",flowId)
                .doDelete(DSGCServFlowContCfg.class);
    }

    public void updateOsbServFlowContCfgByFlowId(DSGCServFlowContCfg osbServFlowContCfg){
        this.logger.debug(" osbServFlowContCfg : "+osbServFlowContCfg);
        this.sw
                .buildQuery()
                .eq("flowId",osbServFlowContCfg.getFlowId())
                .doUpdate(osbServFlowContCfg);
    }
}
