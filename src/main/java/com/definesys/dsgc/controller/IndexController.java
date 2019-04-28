package com.definesys.dsgc.controller;

import com.definesys.dsgc.service.IndexService;
import com.definesys.mpaas.common.http.Response;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/dsgc/index")
@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 首页报表
     * @return
     */
    @RequestMapping(value="/getIndexData" ,method= RequestMethod.GET)
    public Response getIndexData() {
        Map<String,Object> map=new HashMap<String,Object>();
        Map<String, Object> servermap= indexService.getServeInfo();
       Map<String,Object> serverExam = indexService.getServeExam();
        JSONObject serverAsset = indexService.getServerAsset();
        List<Map<String,Object>> totalTop5 = indexService.getTotalTop5();
        Map<String,Object> realTime = indexService.getRealTime();
        List<Map<String,Object>> serverDistriub = indexService.getServerDistriub();
		map.put("topFive", totalTop5);   //失败数最多的前5个
		map.put("serviceExample", serverExam);  //服务实例
		map.put("serviceDistribution", serverDistriub);  //服务分布
		map.put("realTime", realTime);
		map.put("serverAsset", serverAsset);
        map.put("serverInfo", servermap);//获取服务器信息
        return Response.ok().data(map);
    }

    //获取服务实例
    @RequestMapping(value="/getServeExam" ,method= RequestMethod.GET)
    public Response getServeExam() {
        Map<String,Object> map=new HashMap<String,Object>();
        Map<String,Object> serverExam = indexService.getServeExam();
        map.put("ServerExam", serverExam);//获取服务实例
        return Response.ok().data(map);
    }

    //获取服务资产数据
    @RequestMapping(value="/getServerAsset" ,method= RequestMethod.GET)
    public Response getServerAsset() {
        Map<String,Object> map=new HashMap<String,Object>();
        JSONObject serverAsset = indexService.getServerAsset();
        map.put("ServerAsset", serverAsset);//获取服务资产数据
        return Response.ok().data(map);
    }

    //调用统计top5数据
    @RequestMapping(value="/getTotalTop5" ,method= RequestMethod.GET)
    public Response getTotalTop5() {
        Map<String,Object> map=new HashMap<String,Object>();
        List<Map<String,Object>> totalTop5 = indexService.getTotalTop5();
        map.put("TotalTop5", totalTop5);//调用统计top5数据
        return Response.ok().data(map);
    }

    //实时指标
    @RequestMapping(value="/getRealTime" ,method= RequestMethod.GET)
    public Response getRealTime() {
        Map<String,Object> map=new HashMap<String,Object>();
        Map<String,Object> realTime = indexService.getRealTime();
        map.put("RealTime", realTime);//实时指标
        return Response.ok().data(map);
    }

    //服务分布数据
    @RequestMapping(value="/getServerDistriub" ,method= RequestMethod.GET)
    public Response getServerDistriub() {
        Map<String,Object> map=new HashMap<String,Object>();
        List<Map<String,Object>> serverDistriub = indexService.getServerDistriub();
        map.put("ServerDistriub", serverDistriub);//服务分布数据
        return Response.ok().data(map);
    }
}

