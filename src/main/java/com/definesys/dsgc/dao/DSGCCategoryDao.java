package com.definesys.dsgc.dao;

import com.definesys.dsgc.bean.DSGCCategoryFirst;
import com.definesys.dsgc.bean.DSGCUser;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import com.definesys.mpaas.query.db.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhenglong
 * @Description:
 * @Date 2019/3/7 16:16
 */
@Repository("category")
public class DSGCCategoryDao{
    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;

    public String createCategory(DSGCCategoryFirst categoryFirst) {
        logger.debug(categoryFirst.toString());
        sw.buildQuery().
                doInsert(categoryFirst);
        return categoryFirst.getCategoryId();
    }

    public String updateCategory(DSGCCategoryFirst categoryFirst) {
        logger.debug(categoryFirst.toString());
        sw.buildQuery()
                .eq("category_id", categoryFirst.getCategoryId())
                .doUpdate(categoryFirst);
        return categoryFirst.getCategoryId();
    }

    public String deleteCategoryById(DSGCCategoryFirst dsgcCategoryFirst) {
        logger.debug(dsgcCategoryFirst.toString());
        sw.buildQuery()
                .eq("category_Id", dsgcCategoryFirst.getCategoryId())
                .doDelete(dsgcCategoryFirst);
        return dsgcCategoryFirst.getCategoryId();
    }

    public PageQueryResult<DSGCCategoryFirst> query(DSGCCategoryFirst dsgcCategoryFirst, int pageSize, int pageIndex) {
        logger.debug(dsgcCategoryFirst.toString());
        return sw.buildQuery()
                .or()
                .like("category_name", dsgcCategoryFirst.getCategoryName())
                .like("category_code", dsgcCategoryFirst.getCategoryCode())
                .like("category_desc", dsgcCategoryFirst.getCategoryDesc())
                .eq("category_id", dsgcCategoryFirst.getCategoryId())
                .doPageQuery(pageIndex, pageSize,DSGCCategoryFirst.class );
    }

    public List<DSGCCategoryFirst> findAll() {
        return sw.buildQuery()
                .doQuery(DSGCCategoryFirst.class );
    }


    public DSGCCategoryFirst findCategoryById(DSGCCategoryFirst dsgcCategoryFirst) {
        logger.debug(dsgcCategoryFirst.toString());
        dsgcCategoryFirst = sw.buildQuery()
                .eq("category_Id", dsgcCategoryFirst.getCategoryId())
                .doQueryFirst(DSGCCategoryFirst.class);
        return dsgcCategoryFirst;
    }
}
