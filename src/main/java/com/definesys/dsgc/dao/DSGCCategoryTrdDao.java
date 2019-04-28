package com.definesys.dsgc.dao;

import com.definesys.dsgc.bean.DSGCCategorySnd;
import com.definesys.dsgc.bean.DSGCCategoryTrd;
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
@Repository("categoryTrd")
public class DSGCCategoryTrdDao {
    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;

    public String deleteCategoryById(DSGCCategoryTrd dsgcCategorySnd) {
        logger.debug(dsgcCategorySnd.toString());
        sw.buildQuery()
                .eq("category_Id", dsgcCategorySnd.getCategoryId())
                .doDelete(dsgcCategorySnd);
        return dsgcCategorySnd.getCategoryId();
    }

    public String updateCategory(DSGCCategoryTrd c) {
        logger.debug(c.toString());
        sw.buildQuery().eq("category_id", c.getCategoryId())
                .doUpdate(c);
        return c.getCategoryId();
    }

    public String createGategory(DSGCCategoryTrd categorysnd) {
        logger.debug(categorysnd.toString());
        sw.buildQuery().
                doInsert(categorysnd);
        return categorysnd.getCategoryId();
    }

    public PageQueryResult<DSGCCategoryTrd> query(DSGCCategoryTrd dsgcCategoryFirst, int pageSize, int pageIndex) {
        return sw.buildQuery()
                .eq("sndCategoryId", dsgcCategoryFirst.getSndCategoryId())
                .like("categoryName", dsgcCategoryFirst.getCategoryName())
                .like("categoryCode", dsgcCategoryFirst.getCategoryCode())
                .like("categoryDesc", dsgcCategoryFirst.getCategoryDesc())
                .eq("categoryId", dsgcCategoryFirst.getCategoryId())
                .doPageQuery(pageIndex, pageSize, DSGCCategoryTrd.class);

    }

    public List<DSGCCategoryTrd> findAll() {
        return sw.buildQuery()
                .doQuery( DSGCCategoryTrd.class);

    }

    public DSGCCategoryTrd findCategoryById(DSGCCategoryTrd c) {
        return sw.buildQuery()
                .eq("category_id", c.getCategoryId())
                .doQueryFirst( DSGCCategoryTrd.class);

    }
}
