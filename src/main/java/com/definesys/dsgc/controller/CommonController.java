package com.definesys.dsgc.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/")
@RestController
@Api(description = "公共模块接口", tags = "公共")
public class CommonController {

//    @RequestMapping(value = "/doc", method = RequestMethod.GET)
//    public String doc() {
//        String rs = Swagger2docsify.build()
//                .api("http://localhost:8080/v2/api-docs")
//                .save("/Users/asan/document/004GC/")
//                .upload("http://api.definesys.com:32000/upload")
//                .code("07d2c49e")
//                .generateDocument();
//        return rs;
//    }

//    @RequestMapping(value = "/doc", method = RequestMethod.GET)
//    public String doc() {
//        String rs = Swagger2docsify.build()
//                .api("http://localhost:8888/v2/api-docs")
//                .save("/Users/asan/document/doc/")
//                .upload("http://api.definesys.com:32000/upload")
//                .generateDocument();
//        return rs;
//    }

}
