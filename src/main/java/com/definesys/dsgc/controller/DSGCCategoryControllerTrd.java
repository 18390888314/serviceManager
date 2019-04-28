package com.definesys.dsgc.controller;

import com.definesys.dsgc.bean.DSGCCategorySnd;
import com.definesys.dsgc.bean.DSGCCategoryTrd;
import com.definesys.dsgc.service.DSGCCategorySndService;
import com.definesys.dsgc.service.DSGCCategoryTrdService;
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
 * @Description:三级分类的controller
 * @Date 2019/3/8 10:02
 */
@RestController
@RequestMapping(value = "/dsgc/category/trd")
public class DSGCCategoryControllerTrd {
    @Autowired
    private DSGCCategoryTrdService dsgcCategoryService;
    @Autowired
    private SWordLogger logger;



    @ApiOperation(value = "新增三级分类", notes = "新增三级分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "三级分类对象", dataType="DSGCCategoryTrd")
    })
    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public Response createUser(@RequestBody DSGCCategoryTrd categorysnd) {
        String cid = this.dsgcCategoryService.createCategory(categorysnd);
        this.logger.debug("cid " + cid);
        return Response.ok().data(cid);
    }

    @ApiOperation(value = "更新三级分类信息", notes = "更新三级分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "三级分类对象", dataType="DSGCCategoryTrd")
    })
    @RequestMapping(value = "/updateCategroy", method = RequestMethod.POST)
    public Response updateUser(@RequestBody DSGCCategoryTrd c) {
        String cid = this.dsgcCategoryService.updateCategory(c);
        this.logger.debug("cid " + cid);
        return Response.ok().data(cid);
    }

    @ApiOperation(value = "删除三级级分类", notes = "删除三级分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "三级分类对象", dataType="DSGCCategoryTrd")
    })
    @RequestMapping(value = "/deleteCategoryById", method = RequestMethod.POST)
    public Response deleteUserById(@RequestBody DSGCCategoryTrd dsgcCategorySnd) {
        String cid = this.dsgcCategoryService.deleteCategoryById(dsgcCategorySnd);
        this.logger.debug("uid " + cid);
        return Response.ok().data(cid);
    }

    @ApiOperation(value = "查询三级分类信息", notes = "根据二级分类姓名，三级分类的信息，查询符合条件的所有分类对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "c", value = "分类对象", dataType="DSGCCategoryTrd"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataType="int"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页", dataType="int")
    })
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response query(@RequestBody DSGCCategoryTrd dsgcCategoryFirst,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                          @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex) {
        this.logger.debug("pageSize " + pageSize);
        this.logger.debug("pageIndex " + pageIndex);
        this.logger.debug("category " + dsgcCategoryFirst);
        PageQueryResult<DSGCCategoryTrd> categorys = this.dsgcCategoryService.query(dsgcCategoryFirst, pageSize, pageIndex);
        this.logger.debug("getResult " + categorys.getResult());
        return Response.ok().data(categorys);
    }

    @RequestMapping(value = "/modifyCategory", method = RequestMethod.POST)
    public Response modifyCategory(@RequestBody DSGCCategoryTrd dsgcCategoryTrd) {
        String cid = "";
        if (dsgcCategoryTrd.getCategoryId() == null || dsgcCategoryTrd.getCategoryId().trim().length() == 0) {
            cid = this.dsgcCategoryService.createCategory(dsgcCategoryTrd);
        } else {
            cid = this.dsgcCategoryService.updateCategory(dsgcCategoryTrd);
        }
        return Response.ok().data(cid);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public Response findAll() {
        List<DSGCCategoryTrd> categorys = this.dsgcCategoryService.findAll();
        return Response.ok().data(categorys);
    }

    @RequestMapping(value = "/findCategoryById", method = RequestMethod.POST)
    public Response findCategoryById(@RequestBody DSGCCategoryTrd dsgcCategorySnd) {
        DSGCCategoryTrd cid = this.dsgcCategoryService.findCategoryById(dsgcCategorySnd);
        return Response.ok().data(cid);
    }

}
