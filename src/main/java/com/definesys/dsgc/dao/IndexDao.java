package com.definesys.dsgc.dao;

import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class IndexDao {
    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;

    //获取服务实例
    public Map<String, Object> getServeExam() {
      Map<String, Object> result = sw.buildQuery()
                .sql("select sum(TOTAL_TIMES) TOTAL_TIMES, sum(TOTAL_TIMES_S) TOTAL_TIMES_S,SUM(TOTAL_TIMES_F) TOTAL_TIMES_F from RP_SERV_Hour")
                .doQueryFirst();
        return result;
    }

    //获取服务资产数据
    public Object getServices() {
//        Map<String, Object> result = sw.buildQuery()
//                .sql("select count(1) vcount from dsgc_SERVICES")
//                .doQueryFirst();
//        return result.get("vcount");
        List<Map<String, Object>> result = sw.buildQuery()
                .sql("select * from dsgc_SERVICES")
                .doQuery();
        return result.size();
    }

    //获取服务资产数据
    public Object getSystems() {
        List<Map<String, Object>> result = sw.buildQuery()
                .sql("select * from dsgc_SYSTEM")
                .doQuery();
        return result.size();
    }

    //调用统计top5数据
    public List<Map<String, Object>> getTotalTop5() {
        List<Map<String, Object>> result = sw.buildQuery()
                .sql("select SERV_NO,sum(TOTAL_TIMES) TOTAL_TIMES,sum(TOTAL_TIMES_S) TOTAL_TIMES_S, SUM(TOTAL_TIMES_F) TOTAL_TIMES_F from RP_SERV_TOTAL t where rownum < 6 GROUP BY SERV_NO order by TOTAL_TIMES_F desc")
                .doQuery();
        return result;
    }

    //实时指标
    public Map<String, Object> getRealTime() {
     Map<String, Object> result = sw.buildQuery()
                .sql("select TO_CHAR(SYSDATE, 'YYYY') YEAR,TO_CHAR(SYSDATE, 'MM') MONTH,TO_CHAR(SYSDATE, 'DD') DAY,TO_CHAR(SYSDATE, 'HH24') HOUR,COUNT(INST_STATUS) TOTAL_TIMES,SUM(CASE WHEN INST_STATUS = 1 AND BIZ_STATUS = 1 THEN 1 ELSE 0 END) TOTAL_TIMES_S,SUM(CASE WHEN INST_STATUS != 1 OR BIZ_STATUS != 1 THEN 1 ELSE 0 END) TOTAL_TIMES_F ,MAX((TO_NUMBER((RES_TIME + 0) - (START_TIME + 0)) * 1440 * 60) + TO_NUMBER(SUBSTR(TO_CHAR(RES_TIME - START_TIME), 21, 3))) MAX_COST,MIN((TO_NUMBER((RES_TIME + 0) - (START_TIME + 0)) * 1440 * 60) + TO_NUMBER(SUBSTR(TO_CHAR(RES_TIME - START_TIME), 21, 3))) MIN_COST,ROUND(AVG((TO_NUMBER((RES_TIME + 0) - (START_TIME + 0)) * 1440 * 60) + TO_NUMBER(SUBSTR(TO_CHAR(RES_TIME - START_TIME), 21, 3))),2) AVG_COST,MAX(MSG_NUM) MAX_MSG_NUM,MIN(MSG_NUM) MIN_MSG_NUM,ROUND(AVG(MSG_NUM), 2) AVG_MSG_NUM,MAX(MSG_SIZE) MAX_MSG_SIZE,MIN(MSG_SIZE) MIN_MSG_SIZE,ROUND(AVG(MSG_SIZE), 2) AVG_MSG_SIZE FROM dsgc_log_instance WHERE CREATION_DATE > sysdate - 1 / 24 / 6")
                .doQueryFirst();
        return result;
    }

    //服务分布数据
    public List<Map<String, Object>> getServerDistriub() {
        List<Map<String, Object>> result = sw.buildQuery()
                .sql("SELECT SUBSTR(SERV_NO, 0, 1) AS SERV_NO, count(1) AS SERV_SIZE FROM dsgc_SERVICES group by SUBSTR(SERV_NO, 0, 1)")
                .doQuery();
        return result;
    }

}
