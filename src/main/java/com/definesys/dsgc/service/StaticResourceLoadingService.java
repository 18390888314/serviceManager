package com.definesys.dsgc.service;

import com.definesys.dsgc.bean.StaticResourceLoading;
import com.definesys.dsgc.dao.StaticResourceLoadingDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaticResourceLoadingService {

    @Autowired
    StaticResourceLoadingDao staticResourceLoadingDao;
    public List<StaticResourceLoading> getStaticResource(){
       return staticResourceLoadingDao.getStaticResource();
    }

    public JSONObject DataFiltering(List<StaticResourceLoading> arr){
      //  List<StaticResourceLoading> stl = new ArrayList<StaticResourceLoading>();
        JSONObject jsonObject = new JSONObject();
        for (StaticResourceLoading s:
             arr) {
            jsonObject.put(s.getPROPERTY_KEY(),s.getPROPERTY_VALUE());
//            System.out.println(s.getPROPERTY_ID()+"  "+s.getPROPERTY_KEY()+"  "+s.getPROPERTY_VALUE());
        }
        return jsonObject;
    }
}
