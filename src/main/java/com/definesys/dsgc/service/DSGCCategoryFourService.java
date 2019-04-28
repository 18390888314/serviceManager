package com.definesys.dsgc.service;

import com.definesys.dsgc.bean.DSGCCategoryFour;
import com.definesys.dsgc.bean.DSGCCategoryTrd;
import com.definesys.dsgc.dao.DSGCCategoryDao;
import com.definesys.dsgc.dao.DSGCCategoryFourDao;
import com.definesys.dsgc.dao.DSGCCategoryTrdDao;
import com.definesys.mpaas.query.db.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhenglong
 * @Description:四级分类的service
 * @Date 2019/3/8 10:05
 */
@Service
public class DSGCCategoryFourService {
    @Autowired
    private DSGCCategoryFourDao dsgcCategoryDao;

    @Transactional
    public String createCategory(DSGCCategoryFour categorysnd) {

      return  this.dsgcCategoryDao.createGategory(categorysnd);

    }

    @Transactional
    public String updateCategory(DSGCCategoryFour c) {
        return  this.dsgcCategoryDao.updateCategory(c);
    }

    @Transactional
    public String deleteCategoryById(DSGCCategoryFour dsgcCategorySnd) {
        return  this.dsgcCategoryDao.deleteCategoryById(dsgcCategorySnd);
    }

    public PageQueryResult<DSGCCategoryFour> query(DSGCCategoryFour dsgcCategoryFirst, int pageSize, int pageIndex) {
         return  this.dsgcCategoryDao.query(dsgcCategoryFirst,pageSize ,pageIndex);
    }

    public DSGCCategoryFour finaCategoryById(DSGCCategoryFour dsgcCategorySnd) {
        return  this.dsgcCategoryDao.finaCategoryById(dsgcCategorySnd);
    }
}
