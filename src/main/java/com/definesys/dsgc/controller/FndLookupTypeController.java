package com.definesys.dsgc.controller;

import com.definesys.dsgc.bean.FndLookupType;
import com.definesys.dsgc.bean.FndLookupValue;
import com.definesys.dsgc.service.FndLookupTypeService;
import com.definesys.dsgc.utils.CommonUtils;
import com.definesys.mpaas.common.exception.MpaasBusinessException;
import com.definesys.mpaas.common.http.Response;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/dsgc/fndLookupType")
@RestController
public class FndLookupTypeController {

    @Autowired
    FndLookupTypeService fndLookupTypeService;

    @RequestMapping(value = "/findFndLookupTypeById", method = RequestMethod.POST)
    public Response findFndLookupTypeById(@RequestBody FndLookupType u) {
        FndLookupType fndModules = this.fndLookupTypeService.getFndLookupTypeByTypeId(u);
        return Response.ok().data(fndModules);
    }

    @RequestMapping(value = "/findFndLookupTypeByType", method = RequestMethod.POST)
    public Response findFndLookupTypeByType(@RequestBody FndLookupType u) {
        FndLookupType fndModules = this.fndLookupTypeService.findFndLookupTypeByType(u);
        return Response.ok().data(fndModules);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response query(@RequestBody FndLookupType u) {
        List<Map<String, Object>> list = this.fndLookupTypeService.query(u);
        return Response.ok().data(list);
    }

    @RequestMapping(value = "/deleteFndLookupType", method = RequestMethod.POST)
    public Response deleteFndLookupType(@RequestBody FndLookupType u) {
        String id = this.fndLookupTypeService.deleteFndLookupType(u);
        return Response.ok().data(id);
    }

    @RequestMapping(value = "/updateFndLookupType", method = RequestMethod.POST)
    public Response updateFndLookupType(@RequestBody FndLookupType u) {
        String id = this.fndLookupTypeService.updateFndLookupType(u);
        return Response.ok().data(id);
    }

    @RequestMapping(value = "/addFndLookupType", method = RequestMethod.POST)
    public Response addFndLookupType(@RequestBody FndLookupType u) {
        String id = this.fndLookupTypeService.addFndLookupType(u);
        return Response.ok().data(id);
    }

    @RequestMapping(value = "/modifyFndLookupType", method = RequestMethod.POST)
    public Response modifyUser(HttpServletRequest request) {
        String body = CommonUtils.charReader(request);
        if ("".equals(body)) {
            throw new MpaasBusinessException("请求数据为空");
        }
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject lookupType = jsonObject.getJSONObject("lookupType");
        JSONArray lookupValue = jsonObject.getJSONArray("lookupValue");
        FndLookupType u = new FndLookupType();
        u.setLookupType(lookupType.getString("lookupType"));
        u.setLookupName(lookupType.getString("lookupName"));
        u.setLookupId(lookupType.getString("lookupId"));
        u.setLookupDescription(lookupType.getString("lookupDescription"));
        u.setModuleId(lookupType.getString("moduleId"));

        if (lookupValue != null && lookupValue.size() > 0) {
            List<FndLookupValue> values = new ArrayList<>();
            for (int i = 0; i < lookupValue.size(); i++) {
                JSONObject jo = lookupValue.getJSONObject(i);
                FndLookupValue va = new FndLookupValue();
                va.setLookupCode(jo.getString("lookupCode"));
                va.setMeaning(jo.getString("meaning"));
                va.setDescription(jo.getString("description"));
//                va.setDisplaySequence(jo.getInt("displaySequence"));
//                va.setEnabledFlag(jo.getString("enabledFlag"));
//                va.setStartDateActive(jo.getString("startDateActive"));
//                va.setEndDateActive(jo.getString("endDateActive"));
                va.setTag(jo.getString("tag"));
                values.add(va);
            }
            u.setValues(values);
        }

        String id = "";
        if (u.getLookupId() == null || u.getLookupId().trim().length() == 0) {
            id = this.fndLookupTypeService.addFndLookupType(u);
        } else {
            id = this.fndLookupTypeService.updateFndLookupType(u);
        }
        return Response.ok().data(id);
    }
}
