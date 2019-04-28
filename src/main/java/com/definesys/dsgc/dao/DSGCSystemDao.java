package com.definesys.dsgc.dao;

import com.definesys.dsgc.bean.DSGCSystem;
import com.definesys.dsgc.bean.DSGCServRouting;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import com.definesys.mpaas.query.db.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.SocketHandler;

/**
 * @author zhenglong
 * @Description:
 * @Date 2019/3/12 14:33
 */
@Repository("system")
public class DSGCSystemDao {

    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;
    /**
     *   查询所有
     */

    public List<DSGCSystem> findAll() {
      return sw.buildQuery().doQuery(DSGCSystem.class);
    }

    /**
     * 查询系统与接口对应的关系
     * @param systemAndInterface
     * @param pageIndex
     * @param pageSize
     * @return
     */

    public PageQueryResult<DSGCServRouting> query(DSGCSystem systemAndInterface, int pageIndex, int pageSize) {
         logger.debug(systemAndInterface.toString());
           PageQueryResult<DSGCServRouting> routeSystemCode = sw.buildQuery()
                .like("routeSystemCode", systemAndInterface.getSystemCode())
                .doPageQuery( DSGCServRouting.class);
        return routeSystemCode;
    }
}
