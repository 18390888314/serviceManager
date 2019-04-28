package com.definesys.dsgc.service;

import com.definesys.dsgc.bean.DSGCServFlowContCfg;
import com.definesys.dsgc.dao.InterfaceConfigurationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class InterfaceConfigurationService {
    @Autowired
    InterfaceConfigurationDao interfaceConfigurationDao;

    public void insertOsbServFlowContCfg(DSGCServFlowContCfg osbServFlowContCfg){
        interfaceConfigurationDao.insertOsbServFlowContCfg(osbServFlowContCfg);
    }


    public List<DSGCServFlowContCfg> getOsbServFlowContCfgByServNo(String servNo){
        return interfaceConfigurationDao.getOsbServFlowContCfgByServNo(servNo);
    }

    public void deleteOsbServFlowContCfgByFlowId(String flowId){
        interfaceConfigurationDao.deleteOsbServFlowContCfgByFlowId(flowId);
    }

    public void updateOsbServFlowContCfgByFlowId(DSGCServFlowContCfg osbServFlowContCfg){
        interfaceConfigurationDao.updateOsbServFlowContCfgByFlowId(osbServFlowContCfg);
    }
}
