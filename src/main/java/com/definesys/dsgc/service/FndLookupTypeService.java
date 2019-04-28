package com.definesys.dsgc.service;

import com.definesys.dsgc.bean.FndLookupType;
import com.definesys.dsgc.dao.FndLookupTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class FndLookupTypeService {

    @Autowired
    FndLookupTypeDao fndLookupTypeDao;

    public FndLookupType getFndLookupTypeByTypeId(FndLookupType fndLookupType) {
        return this.fndLookupTypeDao.getFndLookupTypeByTypeId(fndLookupType);
    }

    public FndLookupType findFndLookupTypeByType(FndLookupType fndLookupType) {
        return this.fndLookupTypeDao.findFndLookupTypeByType(fndLookupType);
    }

    public List<Map<String, Object>> query(FndLookupType fndLookupType) {
        return this.fndLookupTypeDao.query(fndLookupType);
    }

    @Transactional
    public String deleteFndLookupType(FndLookupType fndLookupType){
        return this.fndLookupTypeDao.deleteFndLookupType(fndLookupType);
    }

    @Transactional
    public String updateFndLookupType(FndLookupType fndLookupType){
        return this.fndLookupTypeDao.updateFndLookupType(fndLookupType);
    }

    @Transactional
    public String addFndLookupType(FndLookupType fndLookupType){
        return this.fndLookupTypeDao.addFndLookupType(fndLookupType);
    }
}
