package com.definesys.dsgc.service;

import com.definesys.dsgc.bean.AuthorizedApi;
import com.definesys.dsgc.dao.AuthorizedApiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizedApiService {
    @Autowired
    private AuthorizedApiDao authorizedApiDao;

    public List<AuthorizedApi> findAllUser() {
        return this.authorizedApiDao.findAllUser();
    }
}
