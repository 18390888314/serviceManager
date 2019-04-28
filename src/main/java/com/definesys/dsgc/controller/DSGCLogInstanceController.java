package com.definesys.dsgc.controller;

import com.definesys.dsgc.bean.DSGCLogAudit;
import com.definesys.dsgc.bean.DSGCLogInstance;
import com.definesys.dsgc.bean.DSGCLogOutBound;
import com.definesys.dsgc.service.DSGCLogInstanceService;
import com.definesys.dsgc.utils.CommonUtils;
import com.definesys.dsgc.utils.MsgCompressUtil;
import com.definesys.mpaas.common.exception.MpaasBusinessException;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.db.PageQueryResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@RequestMapping(value = "/dsgc/logInstance")
@RestController
public class DSGCLogInstanceController {

    @Autowired
    DSGCLogInstanceService logService;

    @Autowired
    private SWordLogger logger;

    @RequestMapping(value = "/query", method = {RequestMethod.POST, RequestMethod.GET})
    public Response query(@RequestBody DSGCLogInstance u,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                          @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                          HttpServletRequest request) {
        this.logger.debug("pageSize " + pageSize);
        this.logger.debug("pageIndex " + pageIndex);
        this.logger.debug("user " + u);
        String  isAdmin = request.getHeader("isAdmin");
        PageQueryResult<DSGCLogInstance> datas = this.logService.query(isAdmin,u, pageSize, pageIndex);
        this.logger.debug("getResult " + datas.getResult());
        return Response.ok().data(datas);
    }

    @RequestMapping(value = "/findLogById", method = RequestMethod.POST)
    public Response findLogById(@RequestBody DSGCLogInstance u) {
        DSGCLogInstance log = this.logService.findLogById(u);
        List<DSGCLogAudit> audits = this.logService.getAuditLog(u.getTrackId());
        List<DSGCLogOutBound> logOutBounds = this.logService.getStackLog(u.getTrackId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logInstance", log);
        jsonObject.put("auditLogs", audits);
        jsonObject.put("trackLogs", logOutBounds);
        return Response.ok().data(jsonObject);
    }

    @RequestMapping(value = "/getLogPartition", method = RequestMethod.GET)
    public Response getLogPartition() {
        List<String> list = this.logService.getLogPartition();
        return Response.ok().data(list);
    }

    @RequestMapping(value = "/doRetry", method = RequestMethod.POST)
    public Response doRetry(HttpServletRequest request) {
        String body = CommonUtils.charReader(request);
        if ("".equals(body)) {
            throw new MpaasBusinessException("请求数据为空");
        }
        JSONArray js = JSONArray.fromObject(body);
        System.out.println(js);
        this.logService.doRetry(js);
        return Response.ok().data("");
    }

    @ResponseBody
    @RequestMapping(value = "/getHeaderPayload", method = RequestMethod.GET)
    public void getHeaderPayload(@RequestParam("trackId") String trackId,
                                 @RequestParam("ibLob") String ibLob,
                                 String startTime,
                                 String endTime,
                                 HttpServletResponse response) {
        try {
            response.setContentType("text/xml;charset=UTF-8");
            PrintWriter out = response.getWriter();
            if (trackId == null || "".equals(trackId)) {
                out.print("trackId 为空");
                out.flush();
                out.close();
                return;
            }
            if (ibLob == null || "".equals(ibLob)) {
                out.print("ibLob 为空");
                out.flush();
                out.close();
                return;
            }
            String str = logService.getHeaderPayload(trackId, ibLob);
            if (str != null) {
                if (str.trim().length() == 0) {
                    logService.showData(response, "报文为空");
                }else{
                    str.replaceAll(" ", "");
                    if (str.startsWith("<")) {
                        out.println(str);
                    } else {
                        String s = MsgCompressUtil.deCompress(str);
                        out.println(s);
                    }
                    out.flush();
                    out.close();
                }
            } else {
                logService.noPayload(response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBodyPayload", method = RequestMethod.GET)
    public void getBodyPayload(@RequestParam("ibLob") String ibLob,
                               @RequestParam("servNo") String servNo,
                               @RequestParam("trackId") String trackId,
                               @RequestParam("startTime") String startTime,
                               @RequestParam("endTime") String endTime,
                               HttpServletResponse response) {
        System.out.println(trackId + trackId.getClass());
        if ("N/A".equals(servNo) || "n/a".equals(servNo) || "".equals(servNo)) {
            logService.noPayload(response);
            return;
        }
        DSGCLogInstance detailsInterfaceData = logService.findLogById(trackId);
        String type = detailsInterfaceData.getPlStoreType();
//        detailsInterfaceData.
        System.out.println(type);
        if ("DB".equals(type)) {
            try {

                String str = logService.getBodyPayload(ibLob);

                if (str != null) {
                    if (str.trim().length() == 0) {
                        logService.showData(response, "报文为空");
                    } else {
                        str.replaceAll(" ", "");
                        System.out.println("|" + str + "|");
                        if (str.startsWith("<")) {
                            System.out.println("---xx---");
                            logService.showData(response, str);
                        } else {
//                     String s = MsgZLibUtil.decompress(str);
                            String s = MsgCompressUtil.deCompress(str);
                            logService.showData(response, s);
                        }
                    }

                } else {
                    logService.noPayload(response);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("BOTH".equals(type)) {
            try {
                String str = logService.getBodyPayload(ibLob);
                System.out.println(str);
                if (str != null) {
//                    if (str.indexOf("<?xml version=") != -1) {
                    if (str.startsWith("<")) {
                        logService.showData(response, str);
                    } else {
//                      String s = MsgZLibUtil.decompress(str);
                        String s = MsgCompressUtil.deCompress(str);
                        System.out.println(s);
                        logService.showData(response, s);
                    }
                } else {
                    String path = logService.dealPath(startTime, servNo, ibLob);
                    String fileContent = logService.readFileByLines(path);
                    if (fileContent != null && !"error".equals(fileContent)) {
//                        if (fileContent.indexOf("<?xml version=") != -1) {
                        if (fileContent.startsWith("<")) {
                            logService.showData(response, fileContent);
                        } else {
                            String s = MsgCompressUtil.deCompress(fileContent);
//                        String s = MsgZLibUtil.decompress(fileContent);
                            logService.showData(response, s);
                        }
                    } else {
                        logService.noPayload(response);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("FILE".equals(type)) {
            try {
                String path = logService.dealPath(startTime, servNo, ibLob);
                String fileContent = logService.readFileByLines(path);
                System.out.println(fileContent);
                if (fileContent != null && !"error".equals(fileContent)) {
//                    if (fileContent.indexOf("<?xml version=") != -1) {
                    if (fileContent.startsWith("<")) {
                        logService.showData(response, fileContent);
                    } else {
                        String s = MsgCompressUtil.deCompress(fileContent);
//                      String s = MsgZLibUtil.decompress(fileContent);
                        logService.showData(response, s);
                    }
                } else {
                    logService.noPayload(response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logService.noPayload(response);
        }
    }

    @RequestMapping(value = "/getErrMsg", method = RequestMethod.GET)
    @ResponseBody
    public void getErrMsg(String errLob, HttpServletResponse response) {
        try {
            response.setContentType("text/xml;charset=UTF-8");
            String str = logService.getErrMsg(errLob);
            if (!str.contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")) {
                str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><payload>" + str + "</payload>";
            }
            logService.showData(response, str);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
