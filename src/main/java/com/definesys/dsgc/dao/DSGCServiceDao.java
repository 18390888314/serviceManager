package com.definesys.dsgc.dao;

import com.definesys.dsgc.bean.*;
import com.definesys.dsgc.bean.vo.DSGCServiceVO;
import com.definesys.dsgc.utils.StringUtils;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import com.definesys.mpaas.query.db.PageQueryResult;
import com.definesys.mpaas.query.session.MpaasSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Clob;
import java.util.List;

/**
 * @author zhenglong
 * @Description:
 * @Date 2019/3/13 16:30
 */
@Repository("dsgc_service")
public class DSGCServiceDao {
    @Autowired
    private MpaasQueryFactory sw;
    @Autowired
    private SWordLogger logger;

    public String insertDsgcService(DSGCService ds, List<DSGCValidResutl> as) {
        sw.buildQuery()
                .doInsert(ds);
        if (as != null && as.size() > 0) {
            for (int i = 0; i < as.size(); i++) {
                sw.buildQuery()
                        .doInsert(as.get(i));
            }
        }
        return ds.getServNo();
    }

    public String updateDsgcService(DSGCService ds, List<DSGCValidResutl> as) {

        sw.buildQuery()
                .eq("servNo", ds.getServNo())
                .doUpdate(ds);
        sw.buildQuery()
                .eq("servNo", ds.getServNo())
                .doDelete(DSGCValidResutl.class);
        if (as != null && as.size() > 0) {
            for (int i = 0; i < as.size(); i++) {
                sw.buildQuery()
                        .doInsert(as.get(i));
            }
        }
        return ds.getServNo();
    }

    /**
     * delete
     *
     * @param
     * @return
     */
    public String deleteById(DSGCService ds, DSGCValidResutl as) {

        sw.buildQuery()
                .eq("servNo", as.getServNo())
                .doDelete(as);
        sw.buildQuery()
                .eq("servNo", ds.getServNo())
                .doDelete(DSGCValidResutl.class);
        return ds.getServNo();
    }


    public PageQueryResult<DSGCServiceVO> findAll(int pageIndex, int pageSize) {
        return sw.buildQuery()
                .sql("select  ds.* ,dr.system_code,dr.valid_type,dr.valid_column,dr.valid_value  from dsgc_services  ds ,dsgc_valid_result dr where ds.serv_no = dr.serv_no")
                .doPageQuery(pageIndex, pageSize, DSGCServiceVO.class);
    }

    public PageQueryResult<DSGCService> query(String isAdmin, DSGCService service, int pageIndex, int pageSize) {
        if("Y".equals(isAdmin)){
            return sw.buildQuery()
                    .like("servNo", service.getServNo())
                    .like("servName", service.getServName())
                    .like("servStatus", service.getServStatus())
                    .like("servTempLate", service.getServTempLate())
                    .like("dataDecode", service.getDataDecode())
                    .like("servDesc", service.getServDesc())
                    .doPageQuery(pageIndex, pageSize, DSGCService.class);
        }
        return sw.buildQuery()
                .sql("SELECT * FROM ( SELECT ser.* FROM dsgc_services ser, dsgc_service_user su WHERE (ser.serv_no = su.serv_no(+) AND su.user_id = #userId AND su.is_show = 'Y') )")
                .like("servNo", service.getServNo())
                .like("servName", service.getServName())
                .like("servStatus", service.getServStatus())
                .like("servTempLate", service.getServTempLate())
                .like("dataDecode", service.getDataDecode())
                .like("servDesc", service.getServDesc())
                .setVar("userId", MpaasSession.getCurrentUser())
                .doPageQuery(pageIndex, pageSize, DSGCService.class);
    }

    public List<DSGCService> findAllService(DSGCService service) {
        return sw.buildQuery()
                .like("servNo", service.getServNo())
                .like("servName", service.getServName())
                .like("servStatus", service.getServStatus())
                .like("servTempLate", service.getServTempLate())
                .like("dataDecode", service.getDataDecode())
                .like("servDesc", service.getServDesc())
                .doQuery(DSGCService.class);
    }

    public PageQueryResult<DSGCService> query(DSGCService service, int pageIndex, int pageSize, String unUse) {
        return sw.buildQuery()
                .like("servNo", service.getServNo())
                .like("servName", service.getServName())
                .like("servStatus", service.getServStatus())
                .like("servTempLate", service.getServTempLate())
                .like("dataDecode", service.getDataDecode())
                .like("servDesc", service.getServDesc())
                .doPageQuery(pageIndex, pageSize, DSGCService.class);
    }

    public DSGCService findServiceByServNo(DSGCService service) {
        DSGCService re = sw.buildQuery()
                .eq("servNo", service.getServNo())
                .doQueryFirst(DSGCService.class);
        return re;

    }


    public List<DSGCValidResutl> findValidByServNo(DSGCService service) {
        return sw.buildQuery()
                .eq("servNo", service.getServNo())
                .doQuery(DSGCValidResutl.class);
    }

    public List<DSGCServInterfaceNode> findDSGCServInterfaceNodeByServNo(DSGCService service) {
        return sw.buildQuery()
                .eq("servNo", service.getServNo())
                .doQuery(DSGCServInterfaceNode.class);
    }

    public String modifyLogMonitoring(List<DSGCServInterfaceNode> nodes, DSGCService service) {
        sw.buildQuery()
                .eq("servNo", service.getServNo())
                .doDelete(DSGCServInterfaceNode.class);
        if (nodes != null && nodes.size() > 0) {
            for (int i = 0; i < nodes.size(); i++) {
                sw.buildQuery()
                        .doInsert(nodes.get(i));
            }
        }
        DSGCService s = sw.buildQuery()
                .eq("servNo", service.getServNo())
                .doQueryFirst(DSGCService.class);
        s.setLogDetail(service.getLogDetail());
        s.setBodyStoreType(service.getBodyStoreType());
        s.setLogPurge(service.getLogPurge());
        s.setNtyPolicy(service.getNtyPolicy());

        sw.buildQuery()
                .eq("servNo", service.getServNo())
                .doUpdate(s);
        return service.getServNo();
    }

    public List<DSGCServiceUser> findDSGCServiceUserByServNo(String servNo) {
        return sw.buildQuery()
                .sql("select us.serv_user_id,us.serv_no,us.user_id,us.is_show,us.is_modify,us.remark,u.user_name from  dsgc_service_user us, dsgc_user  u where us.user_id= u.user_id and us.serv_No = #servNo")
                .setVar("servNo", servNo)
                .doQuery(DSGCServiceUser.class);
    }

    public String modifyDSGCServiceUser(String servNo, List<DSGCServiceUser> users) {
        sw.buildQuery()
                .eq("servNo", servNo)
                .doDelete(DSGCServiceUser.class);
        if (users != null && users.size() > 0) {
            for (int i = 0; i < users.size(); i++) {
                sw.buildQuery()
                        .doInsert(users.get(i));
            }
        }
        return servNo;
    }

    public String saveServiceUser(DSGCServiceUser serviceUser) {
        this.sw.buildQuery()
                .doInsert(serviceUser);
        return serviceUser.getServUserId();
    }

    public String updateServiceUser(DSGCServiceUser serviceUser) {
        this.sw.buildQuery()
                .eq("servNo", serviceUser.getServNo())
                .eq("userId", serviceUser.getUserId())
                .doUpdate(serviceUser);
        return serviceUser.getServUserId();
    }

    public String deleteServiceUser(DSGCServiceUser serviceUser) {
        this.sw.buildQuery()
                .eq("servNo", serviceUser.getServNo())
                .eq("userId", serviceUser.getUserId())
                .doDelete(serviceUser);
        return serviceUser.getServUserId();
    }

    public String deleteServiceUser(String userId) {
        this.sw.buildQuery()
                .eq("userId", userId)
                .doDelete(DSGCServiceUser.class);
        return userId;
    }

    public List<DSGCServiceUser> findServiceUserByUserId(String userId) {
        return this.sw.buildQuery()
                .eq("userId", userId)
                .doQuery(DSGCServiceUser.class);
    }


    public boolean checkReadonly(DSGCService service) {
        List list = this.sw.buildQuery()
                .sql("select su.* from dsgc_service_user su, dsgc_user u where su.user_id(+) = u.user_id and u.user_id = #userId and ((su.is_modify = 'Y' and su.serv_no = #servNo) or u.is_admin = 'Y')")
                .setVar("servNo", service.getServNo())
                .setVar("userId", MpaasSession.getCurrentUser())
                .doQuery();
        return list.size() == 0;
    }


    public String saveDSGCServWarningCfg(DSGCServWarningCfg dsgcServWarningCfg) {
        this.sw.buildQuery()
                .doInsert(dsgcServWarningCfg);
        return dsgcServWarningCfg.getWarningId();
    }

    public String updateDSGCServWarningCfg(DSGCServWarningCfg dsgcServWarningCfg) {
        this.sw.buildQuery()
                .eq("servNo", dsgcServWarningCfg.getServNo())
                .eq("warningId", dsgcServWarningCfg.getWarningId())
                .doUpdate(dsgcServWarningCfg);
        return dsgcServWarningCfg.getWarningId();
    }

    public String deleteDSGCServWarningCfg(DSGCServWarningCfg dsgcServWarningCfg) {
        this.sw.buildQuery()
                .eq("servNo", dsgcServWarningCfg.getServNo())
                .eq("warningId", dsgcServWarningCfg.getWarningId())
                .doDelete(dsgcServWarningCfg);
        return dsgcServWarningCfg.getWarningId();
    }

    public List<DSGCServWarningCfg> findDSGCServWarningCfg(String servNo) {
        return this.sw.buildQuery()
                .eq("servNo", servNo)
                .doQuery(DSGCServWarningCfg.class);
    }

    public List<DSGCServInterfaceNode> getInterfaceField(String servNo) {
        return this.sw.buildQuery()
                .eq("servNo", servNo)
                .doQuery(DSGCServInterfaceNode.class);
    }

    public void updateInterfaceField(DSGCServInterfaceNode dsgcServInterfaceNode) {
        this.sw.buildQuery()
                .eq("nodeId", dsgcServInterfaceNode.getNodeId())
                .doUpdate(dsgcServInterfaceNode);
    }

    public void addInterfaceField(DSGCServInterfaceNode dsgcServInterfaceNode) {
        this.sw.buildQuery()
                .doInsert(dsgcServInterfaceNode);
    }

    public void deleteInterfaceField(DSGCServInterfaceNode dsgcServInterfaceNode) {
        this.sw.buildQuery()
                .eq("nodeId", dsgcServInterfaceNode.getNodeId())
                .doDelete(dsgcServInterfaceNode);
    }

    public DSGCServInterfaceNode findInterfaceFieldByNodeID(DSGCServInterfaceNode dsgcServInterfaceNode) {
        return this.sw.buildQuery()
                .eq("nodeId", dsgcServInterfaceNode.getNodeId())
                .doQueryFirst(DSGCServInterfaceNode.class);
    }


    public List<DSGCSystem> getSystems(String servNo) {
        return this.sw.buildQuery()
                .eq("servNo", servNo)
                .doQuery(DSGCSystem.class);
    }

    public void updateSystems(DSGCSystem dsgcSystem) {
        this.sw.buildQuery()
                .eq("systemId", dsgcSystem.getSystemId())
                .doUpdate(dsgcSystem);
    }

    public void addSystems(DSGCSystem dsgcSystem) {
        this.sw.buildQuery()
                .doInsert(dsgcSystem);
    }

    public void deleteSystems(DSGCSystem dsgcSystem) {
        this.sw.buildQuery()
                .eq("systemId", dsgcSystem.getSystemId())
                .doDelete(dsgcSystem);
    }

    public DSGCSystem findSystemsByID(DSGCSystem dsgcSystem) {
        return this.sw.buildQuery()
                .eq("systemId", dsgcSystem.getSystemId())
                .doQueryFirst(DSGCSystem.class);
    }


    public void updateReceivingSystemToUnchange(DSGCServRouting fieldMappingNoChange) {
        if ("不需要数据转换".equals(fieldMappingNoChange.getDataTransCode())) {
            fieldMappingNoChange.setDataTransCode("N/A");
        } else if ("Entry转Entry".equals(fieldMappingNoChange.getDataTransCode())) {
            fieldMappingNoChange.setDataTransCode("ENTRY_ENTRY");
        } else if ("Bill转Entry".equals(fieldMappingNoChange.getDataTransCode())) {
            fieldMappingNoChange.setDataTransCode("BILLBODY_ENTRY");
        } else if ("Bill转Bill".equals(fieldMappingNoChange.getDataTransCode())) {
            fieldMappingNoChange.setDataTransCode("BILL_BILL");
        }
        fieldMappingNoChange.setRouteEsbCode("001");
        this.sw.buildQuery()
                .eq("routingId", fieldMappingNoChange.getRoutingId())
                .doMerge(fieldMappingNoChange);
    }

    public void addReceivingSystemNeedChange(DSGCServRouting fieldMappingNoChange) {
        this.sw.buildQuery()
                .doInsert(fieldMappingNoChange);
    }

    public void addFieldMappingNeedChange(DSGCXmlNodeMapping xmlNodeMapping) {
        this.sw.buildQuery()
                .doInsert(xmlNodeMapping);
    }

    public void deleteReceivingSystem_1(String routingId) {
        this.sw.buildQuery()
                .eq("routingId", routingId)
                .doDelete(DSGCXmlNodeMapping.class);
    }

    public void addOrder(DSGCXmlNodeMapping dsgcXmlNodeMapping) {
        this.sw.buildQuery()
                .eq("mappingId", dsgcXmlNodeMapping.getMappingId())
                .doMerge(DSGCXmlNodeMapping.class);
    }

    public void updateFieldMapping(DSGCXmlNodeMapping dsgcXmlNodeMapping) {
        this.sw.buildQuery()
                .eq("mappingId", dsgcXmlNodeMapping.getMappingId())
                .doMerge(DSGCXmlNodeMapping.class);
    }

    public DSGCServRouting getReceivingSystemByRoutingId(DSGCServRouting dsgcServRouting) {
        return this.sw.buildQuery()
                .eq("routingId", dsgcServRouting.getRoutingId())
                .doQueryFirst(DSGCServRouting.class);
    }

    public void daleteFieldMappingNeedChange(String  routingId) {
         this.sw.buildQuery()
                .eq("routingId", routingId)
                .doDelete(DSGCXmlNodeMapping.class);
    }

    public List getReceivingSystem(DSGCServRouting dsgcServRouting) {
        return this.sw
                .buildQuery()
                .sql("select * from dsgc_serv_routing r ,dsgc_system sy where r.route_system_code = sy.system_code and r.serv_no = #serv_no ")
                .setVar("serv_no",dsgcServRouting.getServNo())
                .doQuery();
    }

    public List<DSGCXmlNodeMapping> getFieldMapping(String routingId) {
        return this.sw.buildQuery()
                .eq("routingId", routingId)
                .doQuery(DSGCXmlNodeMapping.class);
    }

    public void deleteFieldMapping(String mappingId) {
         this.sw.buildQuery()
                .eq("mappingId", mappingId)
                .doDelete(DSGCXmlNodeMapping.class);
    }

    public void addFieldMappingNoChange(DSGCServRouting dsgcServRouting) {
         this.sw.buildQuery()
                .doInsert(dsgcServRouting);
    }

    public void deleteServFiledByServNo(String servNo) {
        this.sw.buildQuery()
                .eq("servNo",servNo)
                .doDelete(DSGCServInterfaceNode.class);
    }

    public List<DSGCServInterfaceNode> getNodeIdByNodeName(String nodeName,String servNo, String modelNodeNum){
       return this.sw.buildQuery()
                .eq("servNo",servNo)
                .eq("nodeName",nodeName)
                .eq("modelNodeNum",modelNodeNum)
                .doQuery(DSGCServInterfaceNode.class);
    }

    public void deleteServFiledMappingByServNoAndRoutingId(String servNo,String routingId) {
        this.sw.buildQuery()
                .eq("servNo",servNo)
                .eq("routingId",routingId)
                .doDelete(DSGCXmlNodeMapping.class);
    }

}
