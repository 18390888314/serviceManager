package com.definesys.dsgc.dao;

import com.definesys.dsgc.bean.RpServTotal;
import com.definesys.dsgc.bean.StaticResourceLoading;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaticResourceLoadingDao {
    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;
    public List<StaticResourceLoading> getStaticResource(){
        return sw
                .buildQuery()
                .sql("SELECT PROPERTY_ID,PROPERTY_KEY,PROPERTY_VALUE FROM dsgc_system_properties")
                .doQuery(StaticResourceLoading.class);
    }

}
