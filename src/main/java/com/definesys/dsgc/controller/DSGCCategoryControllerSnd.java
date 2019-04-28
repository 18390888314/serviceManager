package com.definesys.dsgc.controller;

import com.definesys.dsgc.bean.DSGCCategoryFirst;
import com.definesys.dsgc.bean.DSGCCategorySnd;
import com.definesys.dsgc.service.DSGCCategoryService;
import com.definesys.dsgc.service.DSGCCategorySndService;
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
 * @Description:二级分类的controller
 * @Date 2019/3/8 10:02
 */
@RestController
@RequestMapping(value = "/dsgc/category/snd")
public class DSGCCategoryControllerSnd {
    @Autowired
    private DSGCCategorySndService dsgcCategoryService;
    @Autowired
    private SWordLogger logger;



    @ApiOperation(value = "新增二级分类", notes = "新增二级分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "二级分类对象", dataType="DSGCCategorySnd")
    })
    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public Response createUser(@RequestBody DSGCCategorySnd categorysnd) {
        String cid = this.dsgcCategoryService.createCategory(categorysnd);
        this.logger.debug("cid " + cid);
        return Response.ok().data(cid);
    }

    @ApiOperation(value = "更新二级分类信息", notes = "更新二级分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "二级分类对象", dataType="DSGCCategorySnd")
    })
    @RequestMapping(value = "/updateCategroy", method = RequestMethod.POST)
    public Response updateUser(@RequestBody DSGCCategorySnd c) {
        String cid = this.dsgcCategoryService.updateCategory(c);
        this.logger.debug("cid " + cid);
        return Response.ok().data(cid);
    }

    @ApiOperation(value = "删除二级级分类", notes = "删除二级分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "二级分类对象", dataType="DSGCCategorySnd")
    })
    @RequestMapping(value = "/deleteCategoryById", method = RequestMethod.POST)
    public Response deleteUserById(@RequestBody DSGCCategorySnd dsgcCategorySnd) {
        String cid = this.dsgcCategoryService.deleteCategoryById(dsgcCategorySnd);
        this.logger.debug("uid " + cid);
        return Response.ok().data(cid);
    }

    @RequestMapping(value = "/modifyCategory", method = RequestMethod.POST)
    public Response modifyCategory(@RequestBody DSGCCategorySnd dsgcCategorySnd) {
        String cid = "";
        if (dsgcCategorySnd.getCategoryId() == null || dsgcCategorySnd.getCategoryId().trim().length() == 0) {
            cid = this.dsgcCategoryService.createCategory(dsgcCategorySnd);
        } else {
            cid = this.dsgcCategoryService.updateCategory(dsgcCategorySnd);
        }
        return Response.ok().data(cid);
    }


    @ApiOperation(value = "查询一二级分类信息", notes = "根据一级分类姓名，二级分类的信息，查询符合条件的所有分类对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "分类对象", dataType="DSGCCategorySnd"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataType="int"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页", dataType="int")
    })
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response query(@RequestBody DSGCCategorySnd dsgcCategoryFirst,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                          @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex) {
        this.logger.debug("pageSize " + pageSize);
        this.logger.debug("pageIndex " + pageIndex);
        this.logger.debug("category " + dsgcCategoryFirst);
        PageQueryResult<DSGCCategorySnd> categorys = this.dsgcCategoryService.query(dsgcCategoryFirst, pageSize, pageIndex);
        this.logger.debug("getResult " + categorys.getResult());
        return Response.ok().data(categorys);
    }


    @RequestMapping(value = "/findCategoryById", method = RequestMethod.POST)
    public Response FindUserById(@RequestBody DSGCCategorySnd dsgcCategorySnd) {
        DSGCCategorySnd cid = this.dsgcCategoryService.findCategoryById(dsgcCategorySnd);
        this.logger.debug("uid " + cid);
        return Response.ok().data(cid);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public Response findAll() {
        List<DSGCCategorySnd> categorys = this.dsgcCategoryService.findAll();
        return Response.ok().data(categorys);
    }


}
