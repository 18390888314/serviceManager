package com.definesys.dsgc.service;

import com.definesys.dsgc.bean.DSGCCategorySnd;
import com.definesys.dsgc.bean.DSGCCategoryTrd;
import com.definesys.dsgc.dao.DSGCCategorySendDao;
import com.definesys.dsgc.dao.DSGCCategoryTrdDao;
import com.definesys.mpaas.query.db.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhenglong
 * @Description:三级分类的service
 * @Date 2019/3/8 10:05
 */
@Service
public class DSGCCategoryTrdService {
    @Autowired
    private DSGCCategoryTrdDao dsgcCategoryDao;

    @Transactional
    public String createCategory(DSGCCategoryTrd categorysnd) {

      return  this.dsgcCategoryDao.createGategory(categorysnd);

    }

    @Transactional
    public String updateCategory(DSGCCategoryTrd c) {
        return  this.dsgcCategoryDao.updateCategory(c);
    }

    @Transactional
    public String deleteCategoryById(DSGCCategoryTrd dsgcCategorySnd) {
        return  this.dsgcCategoryDao.deleteCategoryById(dsgcCategorySnd);
    }

    public PageQueryResult<DSGCCategoryTrd> query(DSGCCategoryTrd dsgcCategoryFirst, int pageSize, int pageIndex) {
         return  this.dsgcCategoryDao.query(dsgcCategoryFirst,pageSize ,pageIndex);
    }

    public List<DSGCCategoryTrd> findAll() {
        return  this.dsgcCategoryDao.findAll();
    }

    public DSGCCategoryTrd findCategoryById(DSGCCategoryTrd dsgcCategorySnd) {
        return  this.dsgcCategoryDao.findCategoryById(dsgcCategorySnd);
    }
}
