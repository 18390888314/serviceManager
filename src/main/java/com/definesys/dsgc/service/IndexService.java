package com.definesys.dsgc.service;

import com.definesys.dsgc.dao.IndexDao;
import com.definesys.dsgc.service.weblogic.WeblogicInfoComponent;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class IndexService {

    @Autowired
    private WeblogicInfoComponent weblogicInfoComponent;

    @Autowired
    private IndexDao indexDao;


    public Map<String, Object> getServeInfo() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = weblogicInfoComponent.getServerInfo();
        } catch (Exception e) {
            map.put("error", "获取服务器信息失败");
            e.printStackTrace();
        }
        return map;
    }

    //获取服务实例
    public Map<String, Object> getServeExam() {
      Map<String, Object> map =new HashMap<>();

        map = indexDao.getServeExam();

        return map;
    }

    //获取服务资产数据
    public JSONObject getServerAsset() {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("systemCount", indexDao.getSystems());
        jsonObject.put("serviceCount", indexDao.getServices());
//        System.out.println(jsonObject);
        return jsonObject;
    }

    //调用统计top5数据
    public List<Map<String, Object>> getTotalTop5() {
        List<Map<String, Object>> list = new ArrayList<>();

        list = indexDao.getTotalTop5();
        return list;
    }

    //实时指标
    public Map<String, Object> getRealTime() {
        Map<String, Object> map = new HashMap<>();
        map = indexDao.getRealTime();
        return map;
    }

    //服务分布数据
    public List<Map<String, Object>> getServerDistriub() {
        List<Map<String, Object>> list = new ArrayList<>();

        list = indexDao.getServerDistriub();

        return list;
    }
}
