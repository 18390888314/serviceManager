package com.definesys.dsgc.dao;

import com.definesys.dsgc.bean.DSGCCategoryFour;
import com.definesys.dsgc.bean.DSGCCategoryTrd;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import com.definesys.mpaas.query.db.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * @author zhenglong
 * @Description:
 * @Date 2019/3/8 10:34
 */
@Repository("categoryFour")
public class DSGCCategoryFourDao {
    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;

    public String deleteCategoryById(DSGCCategoryFour dsgcCategorySnd) {
        logger.debug(dsgcCategorySnd.toString());
        sw.buildQuery()
                .eq("category_Id", dsgcCategorySnd.getCategoryId())
                .doDelete(dsgcCategorySnd);
        return dsgcCategorySnd.getCategoryId();
    }

    public String updateCategory(DSGCCategoryFour c) {
        logger.debug(c.toString());
        sw.buildQuery().eq("category_id", c.getCategoryId())
                .doUpdate(c);
        return c.getCategoryId();
    }

    public String createGategory(DSGCCategoryFour categorysnd) {
        logger.debug(categorysnd.toString());
        sw.buildQuery().
                doInsert(categorysnd);
        return categorysnd.getCategoryId();
    }

    public PageQueryResult<DSGCCategoryFour> query(DSGCCategoryFour dsgcCategoryFirst, int pageSize, int pageIndex) {

        return sw.buildQuery()
                .eq("trdCategoryId", dsgcCategoryFirst.getTrdCategoryId())
                .like("categoryName", dsgcCategoryFirst.getCategoryName())
                .like("categoryCode", dsgcCategoryFirst.getCategoryCode())
                .like("categoryDesc", dsgcCategoryFirst.getCategoryDesc())
                .eq("categoryId", dsgcCategoryFirst.getCategoryId())
                .doPageQuery(pageIndex, pageSize, DSGCCategoryFour.class);
    }

    public DSGCCategoryFour finaCategoryById(DSGCCategoryFour categorysnd) {

        return sw.buildQuery()
                  .eq("trdCategoryId", categorysnd.getTrdCategoryId())
                .doQueryFirst(DSGCCategoryFour.class);
    }

}
