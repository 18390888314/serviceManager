package com.definesys.dsgc.dao;

import com.definesys.dsgc.bean.FndLookupType;
import com.definesys.dsgc.bean.FndLookupValue;
import com.definesys.dsgc.bean.FndModules;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import com.definesys.mpaas.query.conf.MpaasQueryConfig;
import com.definesys.mpaas.query.db.PageQueryResult;
import com.definesys.mpaas.query.util.MpaasQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class FndLookupTypeDao {

    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;

    @Autowired
    private MpaasQueryConfig config;

    public List getLookupValuesByType(FndLookupType lookupType) {
        FndLookupType resultlookupType = this.sw.buildQuery()
                .eq("LOOKUP_TYPE", lookupType.getLookupType())
                .doQueryFirst(FndLookupType.class);


        return this.sw.buildQuery().eq("LOOKUP_ID", resultlookupType.getLookupId())
                .doQuery(FndLookupValue.class);
    }

    public FndLookupType getFndLookupTypeByTypeId(FndLookupType fndLookupType) {
        FndLookupType fndLookupType1 = this.sw.buildQuery()
                .eq("lookupId", fndLookupType.getLookupId())
                .doQueryFirst(FndLookupType.class);
        FndModules modules = this.sw.buildQuery()
                .eq("moduleId", fndLookupType1.getModuleId())
                .doQueryFirst(FndModules.class);
        fndLookupType1.setFndModules(modules);
        List<FndLookupValue> values = this.sw.buildQuery()
                .eq("lookupId", fndLookupType1.getLookupId())
                .doQuery(FndLookupValue.class);
        fndLookupType1.setValues(values);
        return fndLookupType1;
    }

    public FndLookupType findFndLookupTypeByType(FndLookupType fndLookupType) {
        FndLookupType fndLookupType1 = this.sw.buildQuery()
                .eq("lookupType", fndLookupType.getLookupType())
                .doQueryFirst(FndLookupType.class);
        FndModules modules = this.sw.buildQuery()
                .eq("moduleId", fndLookupType1.getModuleId())
                .doQueryFirst(FndModules.class);
        fndLookupType1.setFndModules(modules);
        List<FndLookupValue> values = this.sw.buildQuery()
                .eq("lookupId", fndLookupType1.getLookupId())
                .doQuery(FndLookupValue.class);
        fndLookupType1.setValues(values);
        return fndLookupType1;
    }


    public FndLookupType getFndLookupTypeByType(String type) {
        FndLookupType fndLookupType1 = this.sw.buildQuery()
                .eq("lookupType", type)
                .doQueryFirst(FndLookupType.class);
        FndModules modules = this.sw.buildQuery()
                .eq("moduleId", fndLookupType1.getModuleId())
                .doQueryFirst(FndModules.class);
        fndLookupType1.setFndModules(modules);
        List<FndLookupValue> values = this.sw.buildQuery()
                .eq("lookupId", fndLookupType1.getLookupId())
                .doQuery(FndLookupValue.class);
        fndLookupType1.setValues(values);
        return fndLookupType1;
    }

    public List<Map<String, Object>> query(FndLookupType fndLookupType) {
        String sql = "SELECT distinct t.lookup_type,t.lookup_id,t.lookup_name,t.lookup_description,m.module_name,m.module_id  FROM FND_LOOKUP_TYPES T, FND_LOOKUP_VALUES V,FND_MODULES m  WHERE T.LOOKUP_ID = V.LOOKUP_ID(+) and t.module_id = m.module_id and (lookup_type like'%#lookup_type%' and lookup_name like'%#lookup_name%' and lookup_description like'%#lookup_description%')";
        sql = sql.replace("#lookup_name", fndLookupType.getLookupName());
        sql = sql.replace("#lookup_type", fndLookupType.getLookupType());
        sql = sql.replace("#lookup_description", fndLookupType.getLookupDescription());
        List<Map<String, Object>> result = sw.buildQuery()
                .sql(sql)
                .doQuery();
        return result;
    }

    public String deleteFndLookupType(FndLookupType fndLookupType) {
        fndLookupType = this.sw.buildQuery()
                .eq("lookupId", fndLookupType.getLookupId())
                .doQueryFirst(FndLookupType.class);
        this.sw.buildQuery()
                .eq("lookupId", fndLookupType.getLookupId())
                .doDelete(FndLookupValue.class);
        this.sw.buildQuery()
                .eq("lookupId", fndLookupType.getLookupId())
                .doDelete(FndLookupType.class);
        return fndLookupType.getLookupId();
    }

    public String updateFndLookupType(FndLookupType fndLookupType) {
        this.sw.buildQuery()
                .eq("lookupId", fndLookupType.getLookupId())
                .doDelete(FndLookupValue.class);
        List<FndLookupValue> values = fndLookupType.getValues();
        if (values != null && values.size() > 0) {
            for (int i = 0; i < values.size(); i++) {
                FndLookupValue v= values.get(i);
                v.setLookupId(fndLookupType.getLookupId());
                this.sw.buildQuery().doInsert(v);
            }
        }
        this.sw.buildQuery()
                .eq("lookupId", fndLookupType.getLookupId())
                .doUpdate(fndLookupType);
        return fndLookupType.getLookupId();
    }

    public String addFndLookupType(FndLookupType fndLookupType) {
        String lookupId = UUID.randomUUID().toString().replaceAll("-","");
        fndLookupType.setLookupId(lookupId);
        List<FndLookupValue> values = fndLookupType.getValues();
        this.sw.buildQuery()
                .doInsert(fndLookupType);
        this.logger.debug("getLookupId  " + fndLookupType.getLookupId());
        if (values != null && values.size() > 0) {
            for (int i = 0; i < values.size(); i++) {
                FndLookupValue va =  values.get(i);
                va.setLookupId(lookupId);
                this.sw.buildQuery()
                        .doInsert(va);
            }
        }
        return fndLookupType.getLookupId();
    }
}
