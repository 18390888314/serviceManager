package com.definesys.dsgc.service;

import com.definesys.dsgc.bean.DSGCSystem;
import com.definesys.dsgc.bean.DSGCServRouting;
import com.definesys.dsgc.dao.DSGCSystemDao;
import com.definesys.mpaas.query.db.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenglong
 * @Description:
 * @Date 2019/3/12 14:27
 */
@Service
public class DSGCSystemService {
    @Autowired
    private DSGCSystemDao systemDao;

    public List<DSGCSystem> findAll() {
        return  this.systemDao.findAll();
    }

    public PageQueryResult<DSGCServRouting> query(DSGCSystem systemAndInterface, int pageSize, int pageIndex) {
      return  this.systemDao.query(systemAndInterface,pageIndex,pageSize);
    }
}
