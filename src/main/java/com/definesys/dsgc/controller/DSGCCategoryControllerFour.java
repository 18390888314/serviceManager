package com.definesys.dsgc.controller;

import com.definesys.dsgc.bean.DSGCCategoryFour;
import com.definesys.dsgc.bean.DSGCCategoryTrd;
import com.definesys.dsgc.service.DSGCCategoryFourService;
import com.definesys.dsgc.service.DSGCCategoryService;
import com.definesys.dsgc.service.DSGCCategoryTrdService;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.db.PageQueryResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhenglong
 * @Description:四级分类的controller
 * @Date 2019/3/8 10:02
 */
@RestController
@RequestMapping(value = "/dsgc/category/four")
public class DSGCCategoryControllerFour {
    @Autowired
    private DSGCCategoryFourService dsgcCategoryService;
    @Autowired
    private SWordLogger logger;



    @ApiOperation(value = "新增四级分类", notes = "新增四级分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "四级分类对象", dataType="DSGCCategoryFour")
    })
    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public Response createUser(@RequestBody DSGCCategoryFour categorysnd) {
        String cid = this.dsgcCategoryService.createCategory(categorysnd);
        this.logger.debug("cid " + cid);
        return Response.ok().data(cid);
    }

    @ApiOperation(value = "更新四级分类信息", notes = "更新四级分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "四级分类对象", dataType="DSGCCategoryFour")
    })
    @RequestMapping(value = "/updateCategroy", method = RequestMethod.POST)
    public Response updateUser(@RequestBody DSGCCategoryFour c) {
        String cid = this.dsgcCategoryService.updateCategory(c);
        this.logger.debug("cid " + cid);
        return Response.ok().data(cid);
    }

    @ApiOperation(value = "删除四级分类", notes = "删除四级分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "四级分类对象", dataType="DSGCCategoryFour")
    })
    @RequestMapping(value = "/deleteCategoryById", method = RequestMethod.POST)
    public Response deleteUserById(@RequestBody DSGCCategoryFour dsgcCategorySnd) {
        String cid = this.dsgcCategoryService.deleteCategoryById(dsgcCategorySnd);
        this.logger.debug("uid " + cid);
        return Response.ok().data(cid);
    }



    @ApiOperation(value = "查询四级分类信息", notes = "根据是三级级分类姓名，三级分类的信息，查询符合条件的所有分类对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "分类对象", dataType="DSGCCategoryFour"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataType="int"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页", dataType="int")
    })
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response query(@RequestBody DSGCCategoryFour dsgcCategoryFirst,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                          @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex) {
        this.logger.debug("pageSize " + pageSize);
        this.logger.debug("pageIndex " + pageIndex);
        this.logger.debug("category " + dsgcCategoryFirst);
        PageQueryResult<DSGCCategoryFour> categorys = this.dsgcCategoryService.query(dsgcCategoryFirst, pageSize, pageIndex);
        this.logger.debug("getResult " + categorys.getResult());
        return Response.ok().data(categorys);
    }

    @RequestMapping(value = "/modifyCategory", method = RequestMethod.POST)
    public Response modifyCategory(@RequestBody DSGCCategoryFour dsgcCategorySnd) {
        String cid = "";
        if (dsgcCategorySnd.getCategoryId() == null || dsgcCategorySnd.getCategoryId().trim().length() == 0) {
            cid = this.dsgcCategoryService.createCategory(dsgcCategorySnd);
        } else {
            cid = this.dsgcCategoryService.updateCategory(dsgcCategorySnd);
        }
        return Response.ok().data(cid);
    }

    @RequestMapping(value = "/findCategoryById", method = RequestMethod.POST)
    public Response FindUserById(@RequestBody DSGCCategoryFour dsgcCategorySnd) {
        DSGCCategoryFour cid = this.dsgcCategoryService.finaCategoryById(dsgcCategorySnd);
        this.logger.debug("uid " + cid);
        return Response.ok().data(cid);
    }



}
