package com.definesys.dsgc.service;

import com.definesys.dsgc.bean.DSGCLogAudit;
import com.definesys.dsgc.bean.DSGCLogInstance;
import com.definesys.dsgc.bean.DSGCLogOutBound;
import com.definesys.dsgc.dao.DSGCLogInstanceDao;
import com.definesys.mpaas.common.exception.MpaasRuntimeException;
import com.definesys.mpaas.query.db.PageQueryResult;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Service("logService")
public class DSGCLogInstanceService {

    @Autowired
    DSGCLogInstanceDao dsgcLogInstanceDao;

    public PageQueryResult<DSGCLogInstance> query(String isAdmin, DSGCLogInstance instance, int pageSize, int pageIndex) {
        try {
            return this.dsgcLogInstanceDao.query(isAdmin,instance, pageSize, pageIndex);
        } catch (Exception e) {
            throw new MpaasRuntimeException(e);
        }
    }

    public DSGCLogInstance findLogById(DSGCLogInstance instance) {
        return this.dsgcLogInstanceDao.findLogById(instance);
    }

    public DSGCLogInstance findLogById(String trackId) {
        return this.dsgcLogInstanceDao.findLogById(trackId);
    }

    public List<String> getLogPartition() {
        return this.dsgcLogInstanceDao.getLogPartition();
    }

    public List<DSGCLogAudit> getAuditLog(String trackId) {
        return this.dsgcLogInstanceDao.getAuditLog(trackId);
    }

    public void doRetry(JSONArray jsonArray) {
        this.dsgcLogInstanceDao.doRetry(jsonArray);
    }

    public List<DSGCLogOutBound> getStackLog(String trackId) {
        return this.dsgcLogInstanceDao.getStackLog(trackId);
    }

    public String getHeaderPayload(String trackId, String ibLob) {
        return this.dsgcLogInstanceDao.getHeaderPayload(trackId, ibLob);
    }

    public String getBodyPayload(String ibLob) {
        return this.dsgcLogInstanceDao.getBodyPayload(ibLob);
    }

    public void noPayload(HttpServletResponse response) {
        String s = "该报文不存在，请联系管理员";
        showData(response, s);
    }

    public void showData(HttpServletResponse response, String str) {
        if (str.startsWith("<")) {
            response.setContentType("text/xml;charset=UTF-8");
        } else {
            response.setContentType("text/plain;charset=UTF-8");
        }

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(str);
        out.flush();
        out.close();
    }

    public String dealPath(String startTime, String servNo, String ibLob) {
        String t = StringUtils.substringBefore(startTime, " ");
        String folderName = t.replace("-", "");
        String folderName1 = folderName.substring(0, 6);
        System.out.println(folderName1);
        String path = "/home/oracle/ServicePayload/" + folderName1 + "/" + folderName + "/" + servNo + "/" + ibLob;
        return path;
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        String tempString = "";
        String fileContent = "";
        if (file.exists()) {
            try {
                reader = new BufferedReader(new FileReader(file));
                int line = 1;
                // 一次读入一行，直到读入null为文件结束
                while ((tempString = reader.readLine()) != null) {
                    // 显示行号
                    fileContent += tempString;
                    line++;
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e1) {
                    }
                }
            }
        } else {
            return "error";
        }
        return fileContent;
    }

    public String getErrMsg(String errLob) {
        return this.dsgcLogInstanceDao.getErrMsg(errLob);
    }

}
