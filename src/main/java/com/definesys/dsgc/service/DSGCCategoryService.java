package com.definesys.dsgc.service;

import com.definesys.dsgc.bean.DSGCCategoryFirst;
import com.definesys.dsgc.bean.DSGCUser;
import com.definesys.dsgc.dao.DSGCCategoryDao;
import com.definesys.mpaas.query.db.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhenglong
 * @Description:
 * @Date 2019/3/7 15:56
 */
@Service
public class DSGCCategoryService {
    @Autowired
    private DSGCCategoryDao dsgcCategoryDao;

    @Transactional
    public String createCategory(DSGCCategoryFirst categoryFirst) {
            return  this.dsgcCategoryDao.createCategory(categoryFirst);
    }
    @Transactional
    public String updateCategory(DSGCCategoryFirst c) {
        return  this.dsgcCategoryDao.updateCategory(c);
    }
    @Transactional
    public String deleteCategoryById(DSGCCategoryFirst dsgcCategoryFirst) {
        return  this.dsgcCategoryDao.deleteCategoryById(dsgcCategoryFirst);
    }

    public PageQueryResult<DSGCCategoryFirst> query(DSGCCategoryFirst dsgcCategoryFirst, int pageSize, int pageIndex) {
        return  this.dsgcCategoryDao.query(dsgcCategoryFirst,pageSize,pageIndex);
    }
    public List<DSGCCategoryFirst> findAll() {
        return  this.dsgcCategoryDao.findAll();
    }


    public DSGCCategoryFirst findCategoryById(DSGCCategoryFirst dsgcCategoryFirst) {
        return  this.dsgcCategoryDao.findCategoryById(dsgcCategoryFirst);
    }
}
