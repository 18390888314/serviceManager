package com.definesys.dsgc.service;

import com.definesys.dsgc.bean.DSGCCategorySnd;
import com.definesys.dsgc.dao.DSGCCategorySendDao;
import com.definesys.mpaas.query.db.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhenglong
 * @Description:二级分类的service
 * @Date 2019/3/8 10:05
 */
@Service
public class DSGCCategorySndService {
    @Autowired
    private DSGCCategorySendDao dsgcCategoryDao;

    @Transactional
    public String createCategory(DSGCCategorySnd categorysnd) {

      return  this.dsgcCategoryDao.createGategory(categorysnd);

    }

    @Transactional
    public String updateCategory(DSGCCategorySnd c) {
        return  this.dsgcCategoryDao.updateCategory(c);
    }

    @Transactional
    public String deleteCategoryById(DSGCCategorySnd dsgcCategorySnd) {
        return  this.dsgcCategoryDao.deleteCategoryById(dsgcCategorySnd);
    }

    public PageQueryResult<DSGCCategorySnd> query(DSGCCategorySnd dsgcCategoryFirst, int pageSize, int pageIndex) {
         return  this.dsgcCategoryDao.query(dsgcCategoryFirst,pageSize ,pageIndex);
    }

    public DSGCCategorySnd findCategoryById(DSGCCategorySnd dsgcCategorySnd) {
        return  this.dsgcCategoryDao.findCategoryById(dsgcCategorySnd);
    }

    public List<DSGCCategorySnd> findAll() {
        return  this.dsgcCategoryDao.findAll();
    }
}
