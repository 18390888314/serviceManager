package com.definesys.dsgc.controller;

import com.definesys.dsgc.bean.DSGCCategoryFirst;
import com.definesys.dsgc.bean.DSGCUser;
import com.definesys.dsgc.service.DSGCCategoryService;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.db.PageQueryResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhenglong
 * @Description:一级分类的controller
 * @Date 2019/3/7 15:53
 */
@RequestMapping(value = "/dsgc/category/first")
@RestController
public class DSGCCategoryController {

    @Autowired
    private DSGCCategoryService dsgcCategoryService;
    @Autowired
    private SWordLogger logger;



    @ApiOperation(value = "新增一级分类", notes = "新增一级分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "分类对象", dataType="DSGCCategoryFirst")
    })
    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public Response createUser(@RequestBody DSGCCategoryFirst categoryFirst) {
        String cid = this.dsgcCategoryService.createCategory(categoryFirst);
        this.logger.debug("cid " + cid);
        return Response.ok().data(cid);
    }

    @ApiOperation(value = "更新一级分类信息", notes = "更新一级分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "分类对象", dataType="DSGCCategoryFirst")
    })
    @RequestMapping(value = "/updateCategroy", method = RequestMethod.POST)
    public Response updateUser(@RequestBody DSGCCategoryFirst c) {
        String cid = this.dsgcCategoryService.updateCategory(c);
        this.logger.debug("cid " + cid);
        return Response.ok().data(cid);
    }

    @ApiOperation(value = "删除一级分类", notes = "删除一级分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "分类对象", dataType="DSGCCategoryFirst")
    })
    @RequestMapping(value = "/deleteCategoryById", method = RequestMethod.POST)
    public Response deleteUserById(@RequestBody DSGCCategoryFirst dsgcCategoryFirst) {
        String cid = this.dsgcCategoryService.deleteCategoryById(dsgcCategoryFirst);
        this.logger.debug("uid " + cid);
        return Response.ok().data(cid);
    }

    @ApiOperation(value = "编辑一级分类信息", notes = "根据一级分类Id来判断，有就修改，没有就新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "分类对象", dataType="DSGCCategoryFirst")
    })
    @RequestMapping(value = "/modifyCategory", method = RequestMethod.POST)
    public Response modifyUser(@RequestBody  DSGCCategoryFirst dsgcCategoryFirst) {
        String cid = "";
        if (dsgcCategoryFirst.getCategoryId() == null || dsgcCategoryFirst.getCategoryId().trim().length() == 0) {
            cid = this.dsgcCategoryService.createCategory(dsgcCategoryFirst);
        } else {
            cid = this.dsgcCategoryService.updateCategory(dsgcCategoryFirst);
        }
        this.logger.debug("cid " + cid);
        return Response.ok().data(cid);
    }

    @ApiOperation(value = "查询一级分类信息", notes = "根据一级分类信息，查询符合条件的一级分类对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "分类对象", dataType="DSGCCategoryFirst"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataType="int"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页", dataType="int")
    })
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response query(@RequestBody DSGCCategoryFirst dsgcCategoryFirst,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                          @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex) {
        this.logger.debug("pageSize " + pageSize);
        this.logger.debug("pageIndex " + pageIndex);
        this.logger.debug("category " + dsgcCategoryFirst);
        PageQueryResult<DSGCCategoryFirst> categorys = this.dsgcCategoryService.query(dsgcCategoryFirst, pageSize, pageIndex);
        this.logger.debug("getResult " + categorys.getResult());
        return Response.ok().data(categorys);
    }
    @RequestMapping(value = "/findCategoryById", method = RequestMethod.POST)
    public Response findUserById(@RequestBody DSGCCategoryFirst dsgcCategoryFirst) {
        DSGCCategoryFirst cid = this.dsgcCategoryService.findCategoryById(dsgcCategoryFirst);
        this.logger.debug("uid " + cid);
        return Response.ok().data(cid);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public Response findAll() {
        List<DSGCCategoryFirst> categorys = this.dsgcCategoryService.findAll();
        return Response.ok().data(categorys);
    }

}
