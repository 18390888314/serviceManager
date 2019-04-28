package com.definesys.dsgc.dao;

import com.definesys.dsgc.bean.DSGCCategoryFirst;
import com.definesys.dsgc.bean.DSGCCategorySnd;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import com.definesys.mpaas.query.db.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author zhenglong
 * @Description:
 * @Date 2019/3/8 10:34
 */
@Repository("categorysnd")
public class DSGCCategorySendDao {
    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;

    public String deleteCategoryById(DSGCCategorySnd dsgcCategorySnd) {
        logger.debug(dsgcCategorySnd.toString());
        sw.buildQuery()
                .eq("category_Id", dsgcCategorySnd.getCategoryId())
                .doDelete(dsgcCategorySnd);
        return dsgcCategorySnd.getCategoryId();
    }

    public String updateCategory(DSGCCategorySnd c) {
        logger.debug(c.toString());
        sw.buildQuery().eq("category_id", c.getCategoryId())
                .doUpdate(c);
        return c.getCategoryId();
    }

    public String createGategory(DSGCCategorySnd categorysnd) {
        logger.debug(categorysnd.toString());
        sw.buildQuery().
                doInsert(categorysnd);
        return categorysnd.getCategoryId();
    }

    public PageQueryResult<DSGCCategorySnd> query(DSGCCategorySnd dsgcCategoryFirst, int pageSize, int pageIndex) {

        logger.debug(dsgcCategoryFirst.toString());
        return sw.buildQuery()
                .eq("firstCategoryId", dsgcCategoryFirst.getFirstCategoryId())
                .like("categoryName", dsgcCategoryFirst.getCategoryName())
                .like("categoryCode", dsgcCategoryFirst.getCategoryCode())
                .like("categoryDesc", dsgcCategoryFirst.getCategoryDesc())
                .eq("categoryId", dsgcCategoryFirst.getCategoryId())
                .doPageQuery(pageIndex, pageSize, DSGCCategorySnd.class);
    }

    public DSGCCategorySnd findCategoryById(DSGCCategorySnd categorysnd) {
        logger.debug(categorysnd.toString());
        return  sw.buildQuery()
                .eq("category_id", categorysnd.getCategoryId())
                .doQueryFirst(DSGCCategorySnd.class);
    }

    public List<DSGCCategorySnd> findAll() {
        return  sw.buildQuery().
                doQuery(DSGCCategorySnd.class);
    }

}
