package com.definesys.dsgc.controller;

import com.definesys.dsgc.bean.*;
import com.definesys.dsgc.bean.vo.DSGCServiceVO;
import com.definesys.dsgc.service.DSGCServiceService;
import com.definesys.dsgc.service.InterfaceConfigurationService;
import com.definesys.mpaas.common.exception.MpaasBusinessException;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.db.PageQueryResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhenglong
 * @Description: 服务基本信息controller
 * @Date 2019/3/13 16:29
 */
@RestController
@RequestMapping("/dsgc/service")
public class DSGCServiceController {

    @Autowired
    private DSGCServiceService dsgcServiceService;
    @Autowired
    InterfaceConfigurationService interfaceConfigurationService;

    @Autowired
    private SWordLogger logger;

    @ApiOperation(value = "新增基本信息", notes = "新增基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dsgc_service", value = "系统基本信息", dataType = "DSGCService")
    })
    @RequestMapping(value = "/createService", method = RequestMethod.POST)
    public Response insertDsgcService(@RequestBody DSGCServiceVO DSGCServiceVO) {
        String id = this.dsgcServiceService.insertDsgcService(DSGCServiceVO);
        this.logger.debug("servNo" + id);
        return Response.ok().data(id);
    }

    @ApiOperation(value = "更新基本信息", notes = "更新基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "/Service", value = "系统基本信息", dataType = "DSGCService")
    })
    @RequestMapping(value = "/updateService", method = RequestMethod.POST)
    public Response updateUser(@RequestBody DSGCServiceVO DSGCServiceVO) {
        String id = this.dsgcServiceService.updateDsgcService(DSGCServiceVO);
        this.logger.debug("serNo " + id);
        return Response.ok().data(id);
    }

    @ApiOperation(value = "删除系统基本信息", notes = "删除系统基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "DSGCService", value = "系统对象", dataType = "DSGCService")
    })
    @RequestMapping(value = "/deleteService", method = RequestMethod.POST)
    public Response deleteUserById(@RequestBody DSGCServiceVO DSGCServiceVO) {
        String id = this.dsgcServiceService.deleteById(DSGCServiceVO);
        this.logger.debug("serNo " + id);
        return Response.ok().data(id);
    }

    @ApiOperation(value = "查询所有系统信息", notes = "查询所有系统信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dsgc_service", value = "当前系统的所有信息", dataType = "DSGCService"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "int"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页", dataType = "int")
    })
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response query(@RequestBody DSGCService service,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                          @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                          HttpServletRequest request) {
        this.logger.debug("pageSize " + pageSize);
        this.logger.debug("pageIndex " + pageIndex);
        String  isAdmin = request.getHeader("isAdmin");
        PageQueryResult<DSGCService> categorys = this.dsgcServiceService.query(isAdmin,service, pageSize, pageIndex);
        this.logger.debug("getResult " + categorys.getResult());
        return Response.ok().data(categorys);
    }

    @RequestMapping(value = "/findAllService", method = RequestMethod.POST)
    public Response findAllService(DSGCService service) {
        return Response.ok().data(this.dsgcServiceService.findAllService(service));
    }

    @RequestMapping(value = "/findServiceByServNo", method = RequestMethod.POST)
    public Response findServiceByServNo(@RequestBody DSGCService service) {
        JSONObject id = this.dsgcServiceService.findServiceByServNo(service);
        return Response.ok().data(id);
    }

    @RequestMapping(value = "/checkServNo", method = RequestMethod.POST)
    public Response checkServNo(@RequestBody DSGCService service) {
        DSGCService service1 = this.dsgcServiceService.checkServNo(service);
        if (service1 == null) {
            return Response.ok().data("");
        }
        throw new MpaasBusinessException("接口编号已存在");
    }

    @RequestMapping(value = "/modifyService", method = RequestMethod.POST)
    public Response modifyCategory(@RequestBody DSGCServiceVO serviceVO) {
        String cid = "";
        if (serviceVO.getServId() == null || serviceVO.getServId().trim().length() == 0) {
            cid = this.dsgcServiceService.insertDsgcService(serviceVO);
        } else {
            cid = this.dsgcServiceService.updateDsgcService(serviceVO);
        }
        return Response.ok().data(cid);
    }


    /**
     * 新增流量控制规则
     *
     * @author 刘兴科
     * @time 2019.3.13
     */
    @ApiOperation(value = "新增流量控制规则", notes = "返回空的Response")
    @ApiImplicitParam(value = "流量控制规则", dataType = "DSGCServFlowContCfg")
    @RequestMapping(value = "/insertOsbServFlowContCfg", method = RequestMethod.POST)
    public Response insertOsbServFlowContCfg(@RequestBody DSGCServFlowContCfg osbServFlowContCfg) {
        this.logger.debug(" osbServFlowContCfg : " + osbServFlowContCfg);
        this.interfaceConfigurationService.insertOsbServFlowContCfg(osbServFlowContCfg);
        return Response.ok();
    }

    /**
     * 查询流量控制规则
     *
     * @author 刘兴科
     * @time 2019.3.14
     */
    @ApiOperation(value = "查询流量控制规则", notes = "返回对应的全部流量控制规则")
    @ApiImplicitParam(name = "servNo", value = "接口编号", dataType = "string")
    @RequestMapping(value = "/getOsbServFlowContCfgByServNo", method = RequestMethod.GET)
    public Response getOsbServFlowContCfgByServNo(@RequestParam("servNo") String servNo) {
        this.logger.debug(" servNo : " + servNo);
        List<DSGCServFlowContCfg> osbServFlowContCfgList = interfaceConfigurationService.getOsbServFlowContCfgByServNo(servNo);
        this.logger.debug(" osbServFlowContCfgList : " + osbServFlowContCfgList);
        return Response.ok().table(osbServFlowContCfgList);
    }

    /**
     * 删除流量控制规则
     *
     * @author 刘兴科
     * @time 2019.3.14
     */
    @ApiOperation(value = "删除流量控制规则", notes = "返回空的Response")
    @ApiImplicitParam(name = "flowId", value = "唯一标识,传输平台自动生成的ID", dataType = "string")
    @RequestMapping(value = "/deleteOsbServFlowContCfg", method = RequestMethod.GET)
    public Response deleteOsbServFlowContCfgByFlowId(@RequestParam("flowId") String flowId) {
        this.logger.debug(" flowId :" + flowId);
        this.interfaceConfigurationService.deleteOsbServFlowContCfgByFlowId(flowId);
        return Response.ok();
    }

    /**
     * 修改流量控制规则
     *
     * @author 刘兴科
     * @time 2019.3.14
     */
    @ApiOperation(value = "修改流量控制规则", notes = "返回空的Response")
    @ApiImplicitParam(value = "更新后的流量控制规则", dataType = "DSGCServFlowContCfg")
    @RequestMapping(value = "/updateOsbServFlowContCfg", method = RequestMethod.POST)
    public Response updateOsbServFlowContCfgByFlowId(@RequestBody DSGCServFlowContCfg osbServFlowContCfg) {
        this.logger.debug(" osbServFlowContCfg : " + osbServFlowContCfg);
        this.interfaceConfigurationService.updateOsbServFlowContCfgByFlowId(osbServFlowContCfg);
        return Response.ok();
    }

    @RequestMapping(value = "/getLogMonitoring", method = RequestMethod.POST)
    public Response getLogMonitoring(@RequestBody DSGCService service) {
        return Response.ok().data(this.dsgcServiceService.getLogMonitoring(service));
    }

    @RequestMapping(value = "/modifyLogMonitoring", method = RequestMethod.POST)
    public Response modifyLogMonitoring(@RequestBody String body) {
        JSONObject jo = JSONObject.fromObject(body);
        this.dsgcServiceService.modifyLogMonitoring(jo);
        return Response.ok();
    }

    @RequestMapping(value = "/saveServiceUser", method = RequestMethod.POST)
    public Response saveServiceUser(@RequestBody String body) {

        return Response.ok().data(this.dsgcServiceService.saveServiceUser(body));
    }

    @RequestMapping(value = "/updateServiceUser", method = RequestMethod.POST)
    public Response updateServiceUser(@RequestBody DSGCServiceUser service) {
        return Response.ok().data(this.dsgcServiceService.updateServiceUser(service));
    }

    @RequestMapping(value = "/deleteServiceUser", method = RequestMethod.POST)
    public Response deleteServiceUser(@RequestBody DSGCServiceUser service) {
        return Response.ok().data(this.dsgcServiceService.deleteServiceUser(service));
    }

    @RequestMapping(value = "/findDSGCServiceUserByServNo", method = RequestMethod.POST)
    public Response findDSGCServiceUserByServNo(@RequestBody DSGCServiceUser service) {
        return Response.ok().data(this.dsgcServiceService.findDSGCServiceUserByServNo(service.getServNo()));
    }


    @RequestMapping(value = "/saveDSGCServWarningCfg", method = RequestMethod.POST)
    public Response saveDSGCServWarningCfg(@RequestBody DSGCServWarningCfg service) {
        return Response.ok().data(this.dsgcServiceService.saveDSGCServWarningCfg(service));
    }

    @RequestMapping(value = "/updateDSGCServWarningCfg", method = RequestMethod.POST)
    public Response updateDSGCServWarningCfg(@RequestBody DSGCServWarningCfg service) {
        return Response.ok().data(this.dsgcServiceService.updateDSGCServWarningCfg(service));
    }

    @RequestMapping(value = "/deleteDSGCServWarningCfg", method = RequestMethod.POST)
    public Response deleteDSGCServWarningCfg(@RequestBody DSGCServWarningCfg service) {
        return Response.ok().data(this.dsgcServiceService.deleteDSGCServWarningCfg(service));
    }

    @RequestMapping(value = "/findDSGCServWarningCfg", method = RequestMethod.POST)
    public Response findDSGCServWarningCfg(@RequestBody DSGCServWarningCfg service) {
        return Response.ok().data(this.dsgcServiceService.findDSGCServWarningCfg(service));
    }


    @RequestMapping(value = "/getInterfaceField", method = RequestMethod.POST)
    public Response getInterfaceField(@RequestBody DSGCServInterfaceNode service) {
        return Response.ok().data(this.dsgcServiceService.getInterfaceField(service.getServNo()));
    }

    @RequestMapping(value = "/updateInterfaceField", method = RequestMethod.POST)
    public Response updateInterfaceField(@RequestBody DSGCServInterfaceNode service) {
        this.dsgcServiceService.updateInterfaceField(service);
        return Response.ok().data("");
    }

    @RequestMapping(value = "/addInterfaceField", method = RequestMethod.POST)
    public Response addInterfaceField(@RequestBody DSGCServInterfaceNode service) {
        this.dsgcServiceService.addInterfaceField(service);
        return Response.ok().data("");
    }

    @RequestMapping(value = "/deleteInterfaceField", method = RequestMethod.POST)
    public Response deleteInterfaceField(@RequestBody DSGCServInterfaceNode service) {
        this.dsgcServiceService.deleteInterfaceField(service);
        return Response.ok().data("");
    }

    @RequestMapping(value = "/findInterfaceFieldByNodeID", method = RequestMethod.POST)
    public Response findInterfaceFieldByNodeID(@RequestBody DSGCServInterfaceNode service) {
        return Response.ok().data(this.dsgcServiceService.findInterfaceFieldByNodeID(service));
    }


    @RequestMapping(value = "/getSystems", method = RequestMethod.POST)
    public Response getSystems(DSGCSystem service) {
        return Response.ok().data(this.dsgcServiceService.getSystems(service.getServNo()));
    }

    @RequestMapping(value = "/updateSystems", method = RequestMethod.POST)
    public Response updateSystems(DSGCSystem service) {
        this.dsgcServiceService.updateSystems(service);
        return Response.ok().data("");
    }

    @RequestMapping(value = "/addSystems", method = RequestMethod.POST)
    public Response addSystems(DSGCSystem service) {
        this.dsgcServiceService.addSystems(service);
        return Response.ok().data("");
    }

    @RequestMapping(value = "/deleteSystems", method = RequestMethod.POST)
    public Response deleteSystems(DSGCSystem service) {
        this.dsgcServiceService.deleteSystems(service);
        return Response.ok().data("");
    }

    @RequestMapping(value = "/findSystemsByID", method = RequestMethod.POST)
    public Response findSystemsByID(DSGCSystem service) {
        return Response.ok().data(this.dsgcServiceService.findSystemsByID(service));
    }


    /**
     * 添加需要进行数据转换的接收系统
     *
     * @param d
     * @return
     * @author xiezhongyuan
     * @time 2018.10
     */
    @RequestMapping(value = "/addReceivingSystemNeedChange", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Response addReceivingSystemNeedChange(@RequestBody String d) {
        DSGCServRouting fieldMappingNoChange = new DSGCServRouting();
        List<DSGCXmlNodeMapping> list = new ArrayList<DSGCXmlNodeMapping>();
        String routingId = "";
        try {
            JSONObject jsonObj = JSONObject.fromObject(d);
            JSONObject data = JSONObject.fromObject(jsonObj.getString("data"));
            fieldMappingNoChange.setServNo(data.getString("servNo"));
            fieldMappingNoChange.setRouteFuncName(data.getString("routeFuncName"));
            fieldMappingNoChange.setRouteSystemCode(data.getString("routeSystemCode"));
            fieldMappingNoChange.setRoutingId(data.getString("routingId"));
            fieldMappingNoChange.setDataTransCode(data.getString("dataTransCode"));
            fieldMappingNoChange.setRuleStr(data.getString("ruleStr"));
            routingId = data.getString("routingId");

            try {
                dsgcServiceService.addReceivingSystemNeedChange(fieldMappingNoChange);
            } catch (Exception e) {
                e.printStackTrace();
                return Response.error(e.getMessage());
            }
            JSONArray fieldMapping = JSONArray.fromObject(jsonObj.getString("fieldMapping"));
            for (int i = 0; i < fieldMapping.size(); i++) {
                DSGCXmlNodeMapping addFieldMapping = new DSGCXmlNodeMapping();
                JSONObject jsonObject = (JSONObject) fieldMapping.get(i);
                addFieldMapping.setSourceNode(jsonObject.getString("sourceNode"));
                addFieldMapping.setRoutingId("routingId");
                addFieldMapping.setSourceModNum(jsonObject.getString("sourceModNum"));
                addFieldMapping.setNodeId(jsonObject.getString("nodeId"));
                addFieldMapping.setConstant(jsonObject.getString("constant"));
                addFieldMapping.setMappingNode(jsonObject.getString("mappingNode"));
                addFieldMapping.setModelNodeNum(jsonObject.getString("modelNodeNum"));
                addFieldMapping.setServNo(data.getString("servNo"));
                list.add(addFieldMapping);
            }
            try {
                dsgcServiceService.addFieldMappingNeedChange(list, routingId);
            } catch (Exception e) {
                e.printStackTrace();
                return Response.error(e.getMessage());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Response.ok();
    }

    /**
     * 更改接收系统信息为不需要数据转换类型
     *
     * @param fieldMappingNoChange
     * @return
     * @author xiezhongyuan
     * @time 2018.10
     */
    @PostMapping("/updateReceivingSystemToUnchange")
    public Response updateReceivingSystemToUnchange(@RequestBody DSGCServRouting fieldMappingNoChange) {
        try {
            dsgcServiceService.updateReceivingSystemToUnchange(fieldMappingNoChange);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
        return Response.ok();
    }


    /**
     * 更新接收系统为需要数据转换类型
     *
     * @param d
     * @return
     * @author xiezhongyuan
     * @time 2018.10
     */
    @PostMapping("/updateReceivingSystemToDataChange")
    public Response updateReceivingSystemToDataChange(@RequestBody String d) {
        DSGCServRouting fieldMappingNoChange = new DSGCServRouting();
        List<DSGCXmlNodeMapping> list = new ArrayList<DSGCXmlNodeMapping>();
        String routingId = "";
        try {
            JSONObject jsonObj = JSONObject.fromObject(d);
            JSONObject data = JSONObject.fromObject(jsonObj.getString("data"));
            fieldMappingNoChange.setRoutingId(data.getString("routingId"));
            fieldMappingNoChange.setServNo(data.getString("servNo"));
            fieldMappingNoChange.setRouteFuncName(data.getString("routeFuncName"));
            fieldMappingNoChange.setRouteSystemCode(data.getString("routeSystemCode"));
            fieldMappingNoChange.setDataTransCode(data.getString("dataTransCode"));
            fieldMappingNoChange.setRuleStr(data.getString("ruleStr"));
            routingId = data.getString("routingId");

            dsgcServiceService.updateReceivingSystemToUnchange(fieldMappingNoChange);


            JSONArray fieldMapping = JSONArray.fromObject(jsonObj.getString("fieldMapping"));
            for (int i = 0; i < fieldMapping.size(); i++) {
                DSGCXmlNodeMapping addFieldMapping = new DSGCXmlNodeMapping();
                DSGCXmlNodeMapping addOrder = new DSGCXmlNodeMapping();
                JSONObject jsonObject = (JSONObject) fieldMapping.get(i);
//                if (jsonObject.getString("mappingId") != null) {
                addFieldMapping.setSourceNode(jsonObject.getString("sourceNode"));
                addFieldMapping.setSourceModNum(jsonObject.getString("sourceModNum"));
                addFieldMapping.setNodeId(jsonObject.getString("nodeId"));
                addFieldMapping.setConstant(jsonObject.getString("constant"));
                addFieldMapping.setMappingNode(jsonObject.getString("mappingNode"));
                if (jsonObject.has("nodeNum")) {
                    addFieldMapping.setNodeNum(jsonObject.getString("nodeNum"));
                }
                addFieldMapping.setModelNodeNum(jsonObject.getString("modelNodeNum"));
                addFieldMapping.setRoutingId(jsonObject.getString("routingId"));
                addFieldMapping.setServNo(jsonObject.getString("servNo"));
                list.add(addFieldMapping);
            }
            dsgcServiceService.addFieldMappingNeedChange(list, routingId);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Response.ok();
    }

    /**
     * 更改字段映射
     *
     * @param d
     * @return
     * @author xiezhongyuan
     * @time 2018.10
     */
    @PostMapping("/updateFieldMapping")
    public Response updateFieldMapping(@RequestBody String d) {
        List<DSGCXmlNodeMapping> list = new ArrayList<DSGCXmlNodeMapping>();
        try {
            JSONObject jsonObj = JSONObject.fromObject(d);
            JSONArray fieldMapping = JSONArray.fromObject(jsonObj.getString("data"));
            for (int i = 0; i < fieldMapping.size(); i++) {
                DSGCXmlNodeMapping addFieldMapping = new DSGCXmlNodeMapping();
                JSONObject jsonObject = (JSONObject) fieldMapping.get(i);
                addFieldMapping.setMappingId(jsonObject.getString("mappingId"));
                addFieldMapping.setNodeId(jsonObject.getString("nodeId"));
                addFieldMapping.setSourceModNum(jsonObject.getString("sourceModNum"));
                if (jsonObject.has("nodeNum")) {
                    addFieldMapping.setNodeNum(jsonObject.getString("nodeNum"));
                }
                addFieldMapping.setConstant(jsonObject.getString("constant"));
                addFieldMapping.setMappingNode(jsonObject.getString("mappingNode"));
                addFieldMapping.setSourceNode(jsonObject.getString("sourceNode"));
                addFieldMapping.setServNo(jsonObject.getString("servNo"));
                list.add(addFieldMapping);
                try {
                    dsgcServiceService.updateFieldMapping(list);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Response.error(e.getMessage());
                }
            }
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
        return Response.ok();
    }


    @PostMapping("/addFieldMapping")
    public Response addFieldMapping(@RequestBody String d) {
        List<DSGCXmlNodeMapping> list = new ArrayList<DSGCXmlNodeMapping>();
        try {
            JSONObject jsonObj = JSONObject.fromObject(d);
            JSONArray fieldMapping = JSONArray.fromObject(jsonObj.getString("data"));
            for (int i = 0; i < fieldMapping.size(); i++) {
                DSGCXmlNodeMapping addFieldMapping = new DSGCXmlNodeMapping();
                JSONObject jsonObject = (JSONObject) fieldMapping.get(i);
                addFieldMapping.setNodeId(jsonObject.getString("nodeId"));
                addFieldMapping.setSourceModNum(jsonObject.getString("sourceModNum"));
                addFieldMapping.setRoutingId(jsonObject.getString("routingId"));
                addFieldMapping.setModelNodeNum(jsonObject.getString("modelNodeNum"));
                addFieldMapping.setConstant(jsonObject.getString("constant"));
                addFieldMapping.setMappingNode(jsonObject.getString("mappingNode"));
                addFieldMapping.setSourceNode(jsonObject.getString("sourceNode"));
                addFieldMapping.setServNo(jsonObject.getString("servNo"));
                if (jsonObject.has("nodeNum")) {
                    addFieldMapping.setNodeNum(jsonObject.getString("nodeNum"));
                }
                list.add(addFieldMapping);
                try {
                    dsgcServiceService.addFieldMapping(list);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Response.error(e.getMessage());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
        return Response.ok();
    }

    /**
     * 通过routingId条件获取接收系统信息（用于修改接收系统信息）
     *
     * @return receivingSystem
     * @author xiezhongyuan
     * @time 2018.10
     */
    //@RequestParam("routingId") String r_Id
    @PostMapping("/getReceivingSystemByRoutingId")
    public Response getReceivingSystemByRoutingId(@RequestBody DSGCServRouting osbServRoutingBean) {
        DSGCServRouting receivingSystem;
        try {
            receivingSystem = dsgcServiceService.getReceivingSystemByRoutingId(osbServRoutingBean);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
//        return new ReturnMsg("200","success",receivingSystem);
        return Response.ok().data(receivingSystem);
    }

    @PostMapping("/getSequenceRoutingId")
    public Response getSequenceRoutingId() {
        return Response.ok().data(UUID.randomUUID().toString());
    }

    @PostMapping("/getReceivingSystem")
    public Response getReceivingSystem(@RequestBody DSGCServRouting osbServRoutingBean) {
        List list;
        try {
            list = dsgcServiceService.getReceivingSystem(osbServRoutingBean);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
        return Response.ok().data(list);
    }

    /**
     * 获取接口字段
     *
     * @param servNo
     * @return List<OsbServInterfaceNodeBean>
     * @author xiezhongyuan
     * @time 2018.10
     */
    @GetMapping("/getInterfaceField")
    public Response getInterfaceField(@RequestParam("servNo") String servNo) {
        List<DSGCServInterfaceNode> list = dsgcServiceService.getInterfaceField(servNo);
        return Response.ok().data(list);
    }

    /**
     * 获取字段映射
     *
     * @param r_Id
     * @return
     * @author xiehzhongyuan
     * @time 2018.10
     */
    @GetMapping("/getFieldMapping")
    public Response getFieldMapping(@RequestParam("routingId") String r_Id) {

        List<DSGCXmlNodeMapping> list = new ArrayList<DSGCXmlNodeMapping>();
        try {
            list = dsgcServiceService.getFieldMapping(r_Id);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
        return Response.ok().data(list);
    }

    /**
     * 删除字段映射
     *
     * @param mappingId
     * @return
     * @ahthor xiezhongyuan
     * @time 2018.10
     */
    @PostMapping("/deleteFieldMapping")
    public Response deleteFieldMapping(@RequestBody DSGCXmlNodeMapping dsgcXmlNodeMapping) {
        try {
            dsgcServiceService.deleteFieldMapping(dsgcXmlNodeMapping.getMappingId());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
        return Response.ok();
    }

    @PostMapping("/addFieldMappingNoChange")
    public Response addFieldMappingNoChange(@RequestBody DSGCServRouting fieldMappingNoChange){

        //   System.out.println(new Date(Long.parseLong(fieldMappingNoChange.getLastUpdateDate())));
        try {
            dsgcServiceService.addFieldMappingNoChange(fieldMappingNoChange);
        }catch (Exception e){
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
        return Response.ok();

    }

}
