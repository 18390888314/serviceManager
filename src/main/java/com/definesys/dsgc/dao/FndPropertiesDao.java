package com.definesys.dsgc.dao;

import com.definesys.dsgc.bean.FndProperties;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import com.definesys.mpaas.query.db.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FndPropertiesDao {

    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;

    public String createFndProperties(FndProperties fndProperties) {
        logger.debug(fndProperties.toString());
        sw.buildQuery().
                doInsert(fndProperties);
        return fndProperties.getPropertyId();
    }

    public String updateFndProperties(FndProperties fndProperties) {
        logger.debug(fndProperties.toString());
        sw.buildQuery()
                .eq("propertyId", fndProperties.getPropertyId())
                .doUpdate(fndProperties);
        return fndProperties.getPropertyId();
    }

    public String deleteFndPropertiesById(FndProperties fndProperties) {
        logger.debug(fndProperties.toString());
        sw.buildQuery()
                .eq("propertyId", fndProperties.getPropertyId())
                .doDelete(fndProperties);
        return fndProperties.getPropertyId();
    }

    public PageQueryResult<FndProperties> query(FndProperties fndProperties, int pageSize, int pageIndex) {
        logger.debug(fndProperties.toString());
        return sw.buildQuery()
                .like("propertyValue", fndProperties.getPropertyValue())
                .like("propertyKey", fndProperties.getPropertyKey())
                .doPageQuery(pageIndex, pageSize, FndProperties.class);
    }

    public FndProperties findFndPropertiesById(FndProperties fndProperties) {
        logger.debug(fndProperties.toString());
        return sw.buildQuery().eq("propertyId", fndProperties.getPropertyId()).doQueryFirst(FndProperties.class);
    }

}
