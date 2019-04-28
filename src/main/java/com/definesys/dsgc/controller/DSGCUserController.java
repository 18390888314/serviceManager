package com.definesys.dsgc.controller;

import com.definesys.dsgc.bean.DSGCServiceUser;
import com.definesys.dsgc.bean.DSGCUser;
import com.definesys.dsgc.service.DSGCUserService;
import com.definesys.mpaas.common.adapter.UserProfile;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.db.PageQueryResult;
import com.definesys.mpaas.query.session.MpaasSession;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/dsgc/user")
@RestController
@Api(description = "用户模块接口", tags = "系统模块")
public class DSGCUserController {

    @Autowired
    DSGCUserService userService;

    @Autowired
    private SWordLogger logger;

    @ApiOperation(value = "测试接口", notes = "用于刚创建环境，验证环境信息")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Response test(String code) {
        return Response.ok().data(code);
    }

    @ApiOperation(value = "用户登陆接口", notes = "用户登陆接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u", value = "用户对象", dataType = "DSGCUser")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody DSGCUser u) {
        u = this.userService.login(u);
        return Response.ok().data(u);
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u", value = "用户对象", dataType = "DSGCUser")
    })
    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    public Response changePwd(@RequestBody DSGCUser u) {
        String uid = this.userService.changePwd(u);
        return Response.ok().data(uid);
    }

//    @ApiOperation(value = "创建用户", notes = "创建用户")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "u", value = "用户对象", dataType = "DSGCUser")
//    })
//    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
//    public Response createUser(@RequestBody DSGCUser u) {
//        String uid = this.userService.createUser(u);
//        this.logger.debug("uid " + uid);
//        return Response.ok().data(uid);
//    }

//    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "u", value = "用户对象", dataType = "DSGCUser")
//    })
//    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
//    public Response updateUser(@RequestBody DSGCUser u) {
//        String uid = this.userService.updateUser(u);
//        this.logger.debug("uid " + uid);
//        return Response.ok().data(uid);
//    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u", value = "用户对象", dataType = "DSGCUser")
    })
    @RequestMapping(value = "/deleteUserById", method = RequestMethod.POST)
    public Response deleteUserById(@RequestBody DSGCUser u) {
        String uid = this.userService.deleteUserById(u);
        this.logger.debug("uid " + uid);
        return Response.ok().data(uid);
    }

    @ApiOperation(value = "编辑用户信息", notes = "根据用户userId来判断，有就修改，没有就新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u", value = "用户对象", dataType = "DSGCUser")
    })
    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    public Response modifyUser(@RequestBody String body) {
        String uid = "";
        JSONObject jo = JSONObject.fromObject(body);
        DSGCUser u = new DSGCUser();
        u.setUserId(jo.getString("userId"));
        u.setUserName(jo.getString("userName"));
        u.setUserPassword(jo.getString("userPassword"));
        u.setIsAdmin(jo.getString("isAdmin"));
        u.setUserMail(jo.getString("userMail"));
        u.setUserDescription(jo.getString("userDescription"));
        u.setUserPhone(jo.getString("userPhone"));
        u.setUserDept(jo.getString("userDept"));
        List<DSGCServiceUser> dsgcServiceUsers = new ArrayList<>();
        JSONArray js = jo.getJSONArray("services");
        if(js!=null && js.size()>0){
            for (int i = 0; i <js.size() ; i++) {
                JSONObject jsonObject = js.getJSONObject(i);
                DSGCServiceUser serviceUser = new DSGCServiceUser();
                serviceUser.setUserId(jo.getString("userId"));
                serviceUser.setUserName(jo.getString("userName"));
                serviceUser.setServNo(jsonObject.getString("servNo"));
                serviceUser.setIsShow(jsonObject.getString("isShow"));
                serviceUser.setIsModify(jsonObject.getString("isModify"));
                dsgcServiceUsers.add(serviceUser);
            }

        }

        if (u.getUserId() == null || u.getUserId().trim().length() == 0) {
            uid = this.userService.createUser(u,dsgcServiceUsers);
        } else {
            uid = this.userService.updateUser(u,dsgcServiceUsers);
        }
        this.logger.debug("uid " + uid);
        return Response.ok().data(uid);
    }

    @ApiOperation(value = "查询用户信息", notes = "根据用户信息，查询符合条件的用户对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u", value = "用户对象", dataType = "DSGCUser"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "int"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页", dataType = "int")
    })
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response query(HttpServletRequest request,
                          @RequestBody DSGCUser u,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                          @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex) {
        this.logger.debug("pageSize " + pageSize);
        this.logger.debug("pageIndex " + pageIndex);
        this.logger.debug("user " + u);
        PageQueryResult<DSGCUser> users = this.userService.query(u, pageSize, pageIndex);
        this.logger.debug("getResult " + users.getResult());
//获取当前用户
        String currentUser = MpaasSession.getCurrentUser();
//获取当前用户信息
        UserProfile userProfile = MpaasSession.getUserProfile();

        this.logger.debug("currentUser " + currentUser);
        this.logger.debug("isAdmin " +request.getHeader("isAdmin"));
        return Response.ok().data(users);
    }

    @ApiOperation(value = "查找用户信息", notes = "根据用户userId查找用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u", value = "用户对象", dataType = "DSGCUser")
    })
    @RequestMapping(value = "/findUserById", method = RequestMethod.POST)
    public Response findUserById(@RequestBody DSGCUser u) {
        return Response.ok().data(this.userService.findUserById(u));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Response findAll() {
        List<DSGCUser> users = this.userService.findAll();
        return Response.ok().data(users);
    }

    @RequestMapping(value = "/checkUserName", method = RequestMethod.GET)
    public Response checkUserName(String userName) {
        return Response.ok().data( this.userService.checkUserName(userName));
    }
}
