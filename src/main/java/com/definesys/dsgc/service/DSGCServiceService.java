package com.definesys.dsgc.service;

import com.definesys.dsgc.bean.*;
import com.definesys.dsgc.bean.vo.DSGCServiceVO;
import com.definesys.dsgc.dao.DSGCServiceDao;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.db.PageQueryResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author zhenglong
 * @Description:
 * @Date 2019/3/13 16:30
 */
@Service
public class DSGCServiceService {
    @Autowired
    private DSGCServiceDao dsgcServiceDao;
    @Autowired
    SWordLogger logger;

    @Transactional
    public String insertDsgcService(DSGCServiceVO serviceVO) {
        Map<String, Object> map = GetInstace(serviceVO);
        DSGCService ds = (DSGCService) map.get("DSGCService");
        List<DSGCValidResutl> as = (List<DSGCValidResutl>) map.get("DSGCValidResutl");
        return this.dsgcServiceDao.insertDsgcService(ds, as);
    }

    @Transactional
    public String updateDsgcService(DSGCServiceVO serviceVO) {
        Map<String, Object> map = GetInstace(serviceVO);
        DSGCService ds = (DSGCService) map.get("DSGCService");
        List<DSGCValidResutl> as = (List<DSGCValidResutl>) map.get("DSGCValidResutl");
        this.dsgcServiceDao.updateDsgcService(ds, as);
        return serviceVO.getServNo();
    }

    @Transactional
    public String deleteById(DSGCServiceVO serviceVO) {
        Map<String, Object> map = GetInstace(serviceVO);
        DSGCService ds = (DSGCService) map.get("DSGCService");

        DSGCValidResutl as = (DSGCValidResutl) map.get("DSGCValidResutl");
        this.dsgcServiceDao.deleteById(ds, as);
        return ds.getServNo();
    }

    /**
     * 提取共同的封装对象的方法
     */
    public Map<String, Object> GetInstace(DSGCServiceVO serviceVO) {

        DSGCService ds = new DSGCService();
        ds.setServNo(serviceVO.getServNo());
        ds.setServStatus(serviceVO.getServStatus());
        ds.setServName(serviceVO.getServName());
        ds.setServUrl(serviceVO.getServUrl());
        ds.setTechType(serviceVO.getTechType());
        ds.setTransType(serviceVO.getTransType());
        ds.setCategoryLevel1(serviceVO.getCategoryLevel1());
        ds.setCategoryLevel2(serviceVO.getCategoryLevel2());
        ds.setCategoryLevel3(serviceVO.getCategoryLevel3());
        ds.setCategoryLevel4(serviceVO.getCategoryLevel4());
        ds.setBizDesc(serviceVO.getBizDesc());
        ds.setTechDesc(serviceVO.getTechDesc());
        ds.setServDesc(serviceVO.getServDesc());
        ds.setFileName(serviceVO.getFileName());
        ds.setFilePath(serviceVO.getFilePath());
        ds.setServId(serviceVO.getServId());
        ds.setServTempLate(serviceVO.getServTemplate());
        ds.setDataTypeCode(serviceVO.getDataTypeCode());


        DSGCValidResutl as = new DSGCValidResutl();
        List<DSGCValidResutl> list = serviceVO.getServiceBaseInfoSucType();

        Map<String, Object> map = new HashMap<>();
        map.put("DSGCService", ds);
        map.put("DSGCValidResutl", list);
        return map;
    }

    public PageQueryResult<DSGCService> query(String isAdmin, DSGCService service, int pageSize, int pageIndex) {
        return this.dsgcServiceDao.query(isAdmin, service, pageIndex, pageSize);
    }

    public List<DSGCService> findAllService(DSGCService service) {
        return this.dsgcServiceDao.findAllService(service);
    }

    public JSONObject findServiceByServNo(DSGCService service) {
        JSONObject jo = new JSONObject();
        jo.put("service", this.dsgcServiceDao.findServiceByServNo(service));
        jo.put("valids", this.dsgcServiceDao.findValidByServNo(service));
        jo.put("readonly", this.dsgcServiceDao.checkReadonly(service));
        return jo;
    }

    public DSGCService checkServNo(DSGCService service) {
        return this.dsgcServiceDao.findServiceByServNo(service);
    }

    public JSONObject getLogMonitoring(DSGCService service) {
        JSONObject jo = new JSONObject();
        jo.put("logMonitoring", this.dsgcServiceDao.findDSGCServInterfaceNodeByServNo(service));
        jo.put("service", this.dsgcServiceDao.findServiceByServNo(service));
        return jo;
    }

    public String modifyLogMonitoring(JSONObject jo) {
        List<DSGCServInterfaceNode> nodes = new ArrayList<>();
        DSGCService service = new DSGCService();
        service.setServNo(jo.getString("servNo"));
        service.setNtyPolicy(jo.getString("ntyPolicy"));
        service.setLogPurge(jo.getString("logPurge"));
        service.setBodyStoreType(jo.getString("bodyStoreType"));
        service.setLogDetail(jo.getString("logDetail"));

        JSONArray jsonArray = jo.getJSONArray("servNodes");
        if (jsonArray != null && jsonArray.size() > 0) {
            for (int i = 0; i < jsonArray.size(); i++) {
                DSGCServInterfaceNode dsgcServInterfaceNode = new DSGCServInterfaceNode();
                dsgcServInterfaceNode.setServNo(service.getServNo());
                dsgcServInterfaceNode.setNodeName((String) jsonArray.get(i));
                nodes.add(dsgcServInterfaceNode);
            }
        }
        this.dsgcServiceDao.modifyLogMonitoring(nodes, service);
        return service.getServNo();
    }

//    public List<DSGCServiceUser> findDSGCServiceUserByServNo(DSGCService service) {
//        return this.dsgcServiceDao.findDSGCServiceUserByServNo(service);
//    }


    public String modifyDSGCServiceUser(JSONObject jo) {
        String servNo = jo.getString("servNo");
        List<DSGCServiceUser> users = new ArrayList<>();

        this.dsgcServiceDao.modifyDSGCServiceUser(servNo, users);
        return servNo;
    }

    @Transactional
    public String saveServiceUser(String body) {
        JSONObject jo = JSONObject.fromObject(body);
        String servNo = jo.getString("servNo");
        JSONArray js = jo.getJSONArray("users");
        if (js != null && js.size() > 0) {
            for (int i = 0; i < js.size(); i++) {
                JSONObject jsonObject = js.getJSONObject(i);
                DSGCServiceUser dsgcServiceUser = new DSGCServiceUser();
                dsgcServiceUser.setIsModify("N");
                dsgcServiceUser.setIsShow("N");
                dsgcServiceUser.setServNo(servNo);
                dsgcServiceUser.setUserId(jsonObject.getString("userId"));
                dsgcServiceUser.setUserName(jsonObject.getString("userName"));
                this.dsgcServiceDao.saveServiceUser(dsgcServiceUser);
            }
        }
        return servNo;
    }

    public String updateServiceUser(DSGCServiceUser serviceUser) {
        return this.dsgcServiceDao.updateServiceUser(serviceUser);
    }

    public String deleteServiceUser(DSGCServiceUser serviceUser) {
        return this.dsgcServiceDao.deleteServiceUser(serviceUser);
    }

    public List<DSGCServiceUser> findDSGCServiceUserByServNo(String servNo) {
        return this.dsgcServiceDao.findDSGCServiceUserByServNo(servNo);
    }


    public String saveDSGCServWarningCfg(DSGCServWarningCfg warningCfg) {
        return this.dsgcServiceDao.saveDSGCServWarningCfg(warningCfg);
    }

    public String updateDSGCServWarningCfg(DSGCServWarningCfg warningCfg) {
        return this.dsgcServiceDao.updateDSGCServWarningCfg(warningCfg);
    }

    public String deleteDSGCServWarningCfg(DSGCServWarningCfg warningCfg) {
        return this.dsgcServiceDao.deleteDSGCServWarningCfg(warningCfg);
    }

    public List<DSGCServWarningCfg> findDSGCServWarningCfg(DSGCServWarningCfg warningCfg) {
        return this.dsgcServiceDao.findDSGCServWarningCfg(warningCfg.getServNo());
    }


    public List<DSGCServInterfaceNode> getInterfaceField(String servNo) {
        return this.dsgcServiceDao.getInterfaceField(servNo);
    }

    public void updateInterfaceField(DSGCServInterfaceNode dsgcServInterfaceNode) {
        this.dsgcServiceDao.updateInterfaceField(dsgcServInterfaceNode);
    }

    public void addInterfaceField(DSGCServInterfaceNode dsgcServInterfaceNode) {
        this.dsgcServiceDao.addInterfaceField(dsgcServInterfaceNode);
    }

    public void addInterfaceField(List<DSGCServInterfaceNode> dsgcServInterfaceNodes) {
        if (dsgcServInterfaceNodes != null && dsgcServInterfaceNodes.size() > 0) {
            for (int i = 0; i < dsgcServInterfaceNodes.size(); i++) {
                this.dsgcServiceDao.addInterfaceField(dsgcServInterfaceNodes.get(i));
            }
        }
    }

    public void deleteInterfaceField(DSGCServInterfaceNode dsgcServInterfaceNode) {
        this.dsgcServiceDao.deleteInterfaceField(dsgcServInterfaceNode);
    }

    public DSGCServInterfaceNode findInterfaceFieldByNodeID(DSGCServInterfaceNode dsgcServInterfaceNode) {
        return this.dsgcServiceDao.findInterfaceFieldByNodeID(dsgcServInterfaceNode);
    }


    public List<DSGCSystem> getSystems(String servNo) {
        return this.dsgcServiceDao.getSystems(servNo);
    }

    public void updateSystems(DSGCSystem dsgcSystem) {
        this.dsgcServiceDao.updateSystems(dsgcSystem);
    }

    public void addSystems(DSGCSystem dsgcSystem) {
        this.dsgcServiceDao.addSystems(dsgcSystem);
    }

    public void deleteSystems(DSGCSystem dsgcSystem) {
        this.dsgcServiceDao
                .deleteSystems(dsgcSystem);
    }

    public DSGCSystem findSystemsByID(DSGCSystem dsgcSystem) {
        return this.dsgcServiceDao.findSystemsByID(dsgcSystem);
    }

    public void addReceivingSystemNeedChange(DSGCServRouting fieldMappingNoChange) {
        dsgcServiceDao.addReceivingSystemNeedChange(fieldMappingNoChange);
    }

    @Transactional
    public void addFieldMappingNeedChange(List<DSGCXmlNodeMapping> list, String routingId) {

        dsgcServiceDao.daleteFieldMappingNeedChange(routingId);
        for (DSGCXmlNodeMapping temp : list) {
            dsgcServiceDao.addFieldMappingNeedChange(temp);
        }
    }

    public void updateReceivingSystemToUnchange(DSGCServRouting fieldMappingNoChange) {
        dsgcServiceDao.updateReceivingSystemToUnchange(fieldMappingNoChange);
        if (fieldMappingNoChange.getDataTransCode() == "Entry转Entry") {
            String routingId = fieldMappingNoChange.getRoutingId();
            dsgcServiceDao.deleteReceivingSystem_1(routingId);
        }
    }

    public void addOrder(List<DSGCXmlNodeMapping> list) {
        for (DSGCXmlNodeMapping temp : list) {
            dsgcServiceDao.addOrder(temp);
        }
    }

    public void updateFieldMapping(List<DSGCXmlNodeMapping> list) {
        for (DSGCXmlNodeMapping temp : list) {
            dsgcServiceDao.updateFieldMapping(temp);
        }
    }

    public void addFieldMapping(List<DSGCXmlNodeMapping> list) {
        for (DSGCXmlNodeMapping temp : list) {
            dsgcServiceDao.addFieldMappingNeedChange(temp);
        }
    }

    public DSGCServRouting getReceivingSystemByRoutingId(DSGCServRouting osbServRoutingBean) {
        return dsgcServiceDao.getReceivingSystemByRoutingId(osbServRoutingBean);
    }

    public List getReceivingSystem(DSGCServRouting osbServRoutingBean) {
        return dsgcServiceDao.getReceivingSystem(osbServRoutingBean);
    }

    public List<DSGCXmlNodeMapping> getFieldMapping(String routingId) {
        return dsgcServiceDao.getFieldMapping(routingId);
    }

    public void deleteFieldMapping(String mappingId) {
        dsgcServiceDao.deleteFieldMapping(mappingId);
    }

    public void addFieldMappingNoChange(DSGCServRouting fieldMappingNoChange) {
        dsgcServiceDao.addFieldMappingNoChange(fieldMappingNoChange);
    }

    public void deleteServFiledByServNo(String servNo) {
        dsgcServiceDao.deleteServFiledByServNo(servNo);
    }

    public String getNodeIdbyNodeName(String nodeName, String servNo, String modelNodeNum) {
        List<DSGCServInterfaceNode> dataList = dsgcServiceDao.getNodeIdByNodeName(nodeName, servNo, modelNodeNum);
        if (dataList != null && !dataList.isEmpty() && dataList.size() >= 0) {
            return dataList.get(0).getNodeId();
        } else {
            return null;
        }
    }

    public void deleteServFiledMappingByServNoAndRoutingId(String servNo,String routingId){
        dsgcServiceDao.deleteServFiledMappingByServNoAndRoutingId(servNo,routingId);
    }
}
