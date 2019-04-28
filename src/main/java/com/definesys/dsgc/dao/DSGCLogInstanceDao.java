package com.definesys.dsgc.dao;


import com.definesys.dsgc.bean.DSGCLogAudit;
import com.definesys.dsgc.bean.DSGCLogInstance;
import com.definesys.dsgc.bean.DSGCLogOutBound;
import com.definesys.dsgc.utils.StringUtils;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import com.definesys.mpaas.query.db.PageQueryResult;
import com.definesys.mpaas.query.session.MpaasSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component("dsgcLogInstanceDao")
public class DSGCLogInstanceDao {

    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;

    public PageQueryResult<DSGCLogInstance> query(String isAdmin,DSGCLogInstance instance, int pageSize, int pageIndex) throws Exception {
        logger.debug(instance.toString());
        if("Y".equals(isAdmin)){
            return sw.buildQuery()
                    .like("servNo", instance.getServNo())
                    .like("servName", instance.getServName())
                    .like("token", instance.getToken())
                    .like("instStatus", instance.getInstStatus())
                    .like("bizStatus", instance.getBizStatus())
                    .like("reqFrom", instance.getReqFrom())
                    .lteq("endTime", instance.getEndTimeDate())
                    .gteq("startTime", instance.getStartTimeDate())
                    .orderBy("creationDate", "desc")
                    .doPageQuery(pageIndex, pageSize, DSGCLogInstance.class);
        }
        return sw.buildQuery()
                .sql("SELECT * FROM ( SELECT logs.* FROM dsgc_service_user su, dsgc_log_instance logs WHERE (su.serv_no = logs.serv_no AND su.user_id = #userId AND su.is_show = 'Y') )")
                .setVar("userId", MpaasSession.getCurrentUser())
                .like("servNo", instance.getServNo())
                .like("servName", instance.getServName())
                .like("token", instance.getToken())
                .like("instStatus", instance.getInstStatus())
                .like("bizStatus", instance.getBizStatus())
                .like("reqFrom", instance.getReqFrom())
                .lteq("endTime", instance.getEndTimeDate())
                .gteq("startTime", instance.getStartTimeDate())
                .orderBy("creationDate", "desc")
                .doPageQuery(pageIndex, pageSize, DSGCLogInstance.class);
    }

    public DSGCLogInstance findLogById(DSGCLogInstance instance) {
        logger.debug(instance.toString());
        return sw.buildQuery().eq("trackId", instance.getTrackId()).doQueryFirst(DSGCLogInstance.class);
    }

    public DSGCLogInstance findLogById(String trackId) {
        return sw.buildQuery().eq("trackId", trackId).doQueryFirst(DSGCLogInstance.class);
    }

    public List<String> getLogPartition() {
        List<String> list = new ArrayList<>();
        List<Map<String, Object>> result = sw.buildQuery()
                .sql("select PARTITION_NAME from USER_TAB_PARTITIONS where TABLE_NAME = 'dsgc_log_instance'")
                .doQuery();
        for (Map<String, Object> item : result) {
            list.add((String) item.get("PARTITION_NAME"));
        }
        return list;
    }


    public void doRetry(JSONArray jsonArray) {
        if (jsonArray != null && jsonArray.size() > 0){
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                sw.buildQuery()
                        .sql("insert into dsgc_log_retry_job (job_id,track_id,retry_system,status) values (#job_id,#track_id,#retry_system,#status)")
                        .setVar("job_id", UUID.randomUUID().toString())
                        .setVar("track_id",jo.getString("trackId"))
                        .setVar("retry_system",jo.getString("sys"))
                        .setVar("status","0")
                        .doQuery();
            }

        }

    }

    public List<DSGCLogAudit> getAuditLog(String trackId) {
        return sw.buildQuery()
                .eq("trackId", trackId)
                .doQuery(DSGCLogAudit.class);
    }

    public List<DSGCLogOutBound> getStackLog(String trackId) {
        return sw.buildQuery()
                .eq("trackId", trackId)
                .doQuery(DSGCLogOutBound.class);
    }

    public String getHeaderPayload(String trackId, String ibLob) {
        List<Map<String, Object>> result = this.sw.buildQuery()
                .sql("SELECT PAYLOAD_DATA FROM DSGC_LOG_HEADER_PAYLOAD WHERE TRACK_ID = #TRACK_ID and PL_ID =#PL_ID")
                .setVar("TRACK_ID", trackId)
                .setVar("PL_ID", ibLob)
                .doQuery();
        for (Map<String, Object> item : result) {
            Object PAYLOAD_DATA = item.get("PAYLOAD_DATA");
            if (PAYLOAD_DATA != null) {
//                return PAYLOAD_DATA.toString();
                return StringUtils.ClobToString((Clob) PAYLOAD_DATA);
            }
        }
        return "";
    }

    public String getBodyPayload(String ibLob) {
        List<Map<String, Object>> result = this.sw.buildQuery()
                .sql("SELECT PAYLOAD_DATA FROM DSGC_LOG_BODY_PAYLOAD WHERE PL_ID =#PL_ID")
                .setVar("PL_ID", ibLob)
                .doQuery();
        for (Map<String, Object> item : result) {
            Object PAYLOAD_DATA = item.get("PAYLOAD_DATA");
            if (PAYLOAD_DATA != null) {
//                return PAYLOAD_DATA.toString();
                return StringUtils.ClobToString((Clob) PAYLOAD_DATA);
            }
        }
        return "";
    }

    public String getErrMsg(String ibLob) {
        List<Map<String, Object>> result = this.sw.buildQuery()
                .sql("SELECT PAYLOAD_DATA FROM DSGC_LOG_ERROR_PAYLOAD WHERE PL_ID =#PL_ID")
                .setVar("PL_ID", ibLob)
                .doQuery();
        for (Map<String, Object> item : result) {
            Object PAYLOAD_DATA = item.get("PAYLOAD_DATA");
            if (PAYLOAD_DATA != null) {
//                return PAYLOAD_DATA.toString();
                return StringUtils.ClobToString((Clob) PAYLOAD_DATA);
            }
        }
        return "";
    }
}
