package com.definesys.dsgc.controller;

import com.definesys.dsgc.bean.DSGCServInterfaceNode;
import com.definesys.dsgc.bean.DSGCXmlNodeMapping;
import com.definesys.dsgc.bean.FileMetaBean;
import com.definesys.dsgc.service.DSGCServiceService;

import com.definesys.dsgc.utils.GetExcelDataUtil;
import com.definesys.mpaas.common.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/dsgc/uploadAndInsertFiled")
public class uploadFileController {

    //String excelPath = "/Users/zhouquan/Desktop/test" ;
    String excelPath = "/home/oracle/ServNode" ;

    @Autowired
    DSGCServiceService dsgcServiceService;

    LinkedList<FileMetaBean> files = new LinkedList<FileMetaBean>();
    FileMetaBean fileMeta = null;
    /***************************************************
     * URL: /rest/controller/upload
     * upload(): receives files
     * @param request : MultipartHttpServletRequest auto passed
     * @param response : HttpServletResponse auto passed
     * @return LinkedList<FileMeta> as json format
     ****************************************************/
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody
    LinkedList<FileMetaBean> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        //1. build an iterator
        Iterator<String> itr =  request.getFileNames();
        MultipartFile mpf = null;
        //2. get each file
        while(itr.hasNext()){
            //2.1 get next MultipartFile
            mpf = request.getFile(itr.next());
            System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());

            //2.2 if files > 10 remove the first from the list
            if(files.size() >= 10)
                files.pop();

            //2.3 create new fileMeta
            fileMeta = new FileMetaBean();
            fileMeta.setFileName(mpf.getOriginalFilename());
            fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
            fileMeta.setFileType(mpf.getContentType());

            try {
                fileMeta.setBytes(mpf.getBytes());
                // copy file to local disk (make sure the path "e.g. D:/temp/files" exists)
                if(mpf.getOriginalFilename().contains("mapping")){
                    FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(excelPath+"/mapping/"+mpf.getOriginalFilename()));
                }else{
                    FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(excelPath+"/serv/"+mpf.getOriginalFilename()));
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //2.4 add to files
            files.add(fileMeta);
        }
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return files;
    }
    /***************************************************
     * URL: /rest/controller/get/{value}
     * get(): get file as an attachment
     * @param response : passed by the server
     * @param value : value from the URL
     * @return void
     ****************************************************/
    @RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
    public void get(HttpServletResponse response, @PathVariable String value){
        FileMetaBean getFile = files.get(Integer.parseInt(value));
        try {
            response.setContentType(getFile.getFileType());
            response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
            FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping("/insertServFiledByExcle")
    public @ResponseBody
    Response insertFiledToDb(HttpServletRequest request,String servNo){
//        String servNo= request.getParameter("servNo");
        System.out.println("insertFiledToDb  servNo" + servNo);
        try{
            //在调用服务之前将之前的字段删除
            dsgcServiceService.deleteServFiledByServNo(servNo);
            dsgcServiceService.addInterfaceField(getServNodeInFile(servNo));
            return Response.ok().data("S");
        }catch (Exception e){
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

    /**
     * 解析excel将字段插入进数据库
     * @param servNo
     * @return
     */
    public List<DSGCServInterfaceNode> getServNodeInFile(String servNo){
        List<Map<String,String>> list = GetExcelDataUtil.getDataFromExcel(excelPath+"/serv/"+servNo+".xls");
        List<DSGCServInterfaceNode> osbServInterfaceNodeBeansList = new ArrayList<DSGCServInterfaceNode>();
        //遍历解析出来的list
        for (Map<String,String> map : list) {
            DSGCServInterfaceNode osbServInterfaceNodeBean = new DSGCServInterfaceNode();
            for (Map.Entry<String,String> entry : map.entrySet()) {
                String value = entry.getValue();
                String key = entry.getKey();

                //if("".equals())
                if("字段代码".equals(key)){
                    if("".equals(value) || value == null || value.length()  < 1){
                        break;
                    }else{
                        osbServInterfaceNodeBean.setNodeName(value);
                    }
                }
                if("字段描述".equals(key)){
                    osbServInterfaceNodeBean.setNodeDesc(entry.getValue());
                }
                if("父节点".equals(key)){
                    if("".equals(value) || value == null || value.length()  < 1){
                        break;
                    }else{
                        if("BillHeader".equals(value)){
                            osbServInterfaceNodeBean.setModelNodeNum("1");
                        }else if("BillBody_Entry".equals(value)){
                            osbServInterfaceNodeBean.setModelNodeNum("2");
                        }else if("Entry".equals(value)){
                            osbServInterfaceNodeBean.setModelNodeNum(value);
                        }
                    }
                }
            }
            osbServInterfaceNodeBean.setServNo(servNo);
            //System.out.println("osbServInterfaceNodeBean"+osbServInterfaceNodeBean.getNodeDesc()+"---"+osbServInterfaceNodeBean.getNodeName()+"----" + osbServInterfaceNodeBean.getModelNodeNum());
            osbServInterfaceNodeBeansList.add(osbServInterfaceNodeBean);
        }
        return osbServInterfaceNodeBeansList;
    }

    /**
     * 解析excel将字段插入进数据库
     * @param
     * @return
     */
    @RequestMapping("/insertServMappingByExcel")
    public @ResponseBody
    Response insertServMapping(HttpServletRequest request) {
        try {
            String servNo = request.getParameter("servNo");
            String routingId = request.getParameter("routingId");
            //String servNo="AW05";
            //String sysCode="SAP";
            System.out.println("servNo :" +servNo +"-------routingId"+routingId);
            List<Map<String, String>> list = GetExcelDataUtil.getDataFromMAppingExcel(excelPath+"/mapping/" + servNo + "_mapping.xls");
//            List<Map<String, String>> list = GetExcelDataUtil.getDataFromMAppingExcel("/Users/kokatasakai/Desktop/AD06_mapping.xls");

            List<DSGCXmlNodeMapping> osbServInterfaceNodeBeansList = new ArrayList<DSGCXmlNodeMapping>();
            //遍历解析出来的list
            for (Map<String, String> map : list) {
                DSGCXmlNodeMapping osbXmlNodeMappingBean = new DSGCXmlNodeMapping();
                String modelNodeNum = null;
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if ("源格式位置".equals(entry.getKey())) {
                        if("BillHeader".equals(entry.getValue())){
                            modelNodeNum="1";
                        }else if("BillBody_Entry".equals(entry.getValue())){
                            modelNodeNum = "2";
                        }else if("Entry".equals(entry.getValue())){
                            modelNodeNum = entry.getValue();
                        }
                        osbXmlNodeMappingBean.setModelNodeNum(modelNodeNum);
//                        if ("1.0".equals(entry.getValue())) {
//                            modelNodeNum = "1";
//                        } else if ("2.0".equals(entry.getValue())) {
//                            modelNodeNum = "2";
//                        } else {
//                            modelNodeNum = entry.getValue();
//                        }
//                        osbXmlNodeMappingBean.setModelNodeNum(modelNodeNum);
                    }
                    if ("源节点".equals(entry.getKey())) {
                        System.out.println("modelNodeNum: " + modelNodeNum);
                        System.out.println("entry.getValue(): " + entry.getValue());
                        String s = dsgcServiceService.getNodeIdbyNodeName(entry.getValue(), servNo, modelNodeNum);
                        System.out.println(s);
//                        Long a = null;
                        try {
//                             a = Long.parseLong(s);
                            osbXmlNodeMappingBean.setNodeId(s);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if ("目标字段".equals(entry.getKey())) {
                        osbXmlNodeMappingBean.setMappingNode(entry.getValue());
                    }
                    if ("目标字段位置".equals(entry.getKey())) {
                        osbXmlNodeMappingBean.setSourceModNum(entry.getValue());
                    }
                }
                osbXmlNodeMappingBean.setMapType("N");
                osbXmlNodeMappingBean.setRoutingId(routingId);
                //osbXmlNodeMappingBean.setRoutingId(Long.parseLong(interfaceConfigurationService.getRoutingIdByServ(servNo,sysCode)));
                osbXmlNodeMappingBean.setServNo(servNo);
                osbServInterfaceNodeBeansList.add(osbXmlNodeMappingBean);
            }
            //在插入前，先将之前的删除

            dsgcServiceService.deleteServFiledMappingByServNoAndRoutingId(servNo, routingId);
            dsgcServiceService.addFieldMappingNeedChange(osbServInterfaceNodeBeansList,routingId);
            return Response.ok().data("S");
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
    }

}
