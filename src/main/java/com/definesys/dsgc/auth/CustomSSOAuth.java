package com.definesys.dsgc.auth;
import com.definesys.mpaas.common.adapter.IMpaasSSOAuthentication;
import com.definesys.mpaas.common.adapter.UserProfile;
import com.definesys.mpaas.common.exception.MpaasBusinessException;
import com.definesys.mpaas.log.SWordLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomSSOAuth implements IMpaasSSOAuthentication {
    @Autowired
    private SWordLogger logger;
    @Override
    public UserProfile ssoAuth(Map<String, String> header, Map<String, String> cookies) throws MpaasBusinessException {
        UserProfile user = new UserProfile();
        String token = header.get("token");
//        System.out.println("21314567890");
//       this.logger.debug(" token "  );
        //你的计算逻辑
        user.setAnonymous(false);
        user.setToken(token);
        user.setAttributes(header);
        user.setUid(header.get("uid"));
        user.setUserName(header.get("userName"));
        return user;
    }
}