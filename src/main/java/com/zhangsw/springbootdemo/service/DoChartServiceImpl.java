package com.zhangsw.springbootdemo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service
@Transactional
public class DoChartServiceImpl {

    /*public Map<String,Object> doClassNumber(Integer stationId, Date dateTime){
        Map<String,Object> countMap = new HashMap<>();

        //当前时间点所有上班人数量
        Long classUserCount = jsonTagJdbcDao.getJdbcTemplate().queryForObject(
                "SELECT  " +
                        "	count(tscpd.id)   " +
                        " FROM   " +
                        "	TblStationClassPlanDetail tscpd   " +
                        " WHERE   " +
                        "	? BETWEEN tscpd.dutyBeginDateTime AND tscpd.dutyEndDateTime   " +
                        " AND tscpd.signTime IS NOT NULL   " + //必须签到才算作上班
                        " AND tscpd.stationId = ? ",
                Long.class,
                dateTime,stationId);
        countMap.put("classUserCount", classUserCount);
        //当前时间点所有上岗人数量
        Long postingUserCount = jsonTagJdbcDao.getJdbcTemplate().queryForObject(
                "SELECT   " +
                        "	count(tsap.id)   " +
                        "FROM   " +
                        "	TblStationAssignPost tsap   " +
                        "LEFT JOIN TblStationClassPlanDetail tscpd on tscpd.id = tsap.stationClassPlanDetailId   " +
                        "WHERE   " +
                        "	? BETWEEN tscpd.dutyBeginDateTime AND tscpd.dutyEndDateTime   " +
                        "	AND tscpd.stationId = ?   " +
                        "	AND tsap.`status` = ? ",
                Long.class,
                dateTime,stationId,TblStationAssignPost.STATUS_WORKING);
        countMap.put("postingUserCount", postingUserCount);

        //当前时间点所有待岗人数量
        List<Map<String,Object>> assignPostNoWorkingList = jsonTagJdbcDao.getJdbcTemplate().queryForList(
                "SELECT " +
                        "	* " +
                        "FROM " +
                        "	TblStationAssignPost " +
                        "WHERE " +
                        "	stationClassPlanDetailId IN ( " +
                        "		SELECT " +
                        "			ID " +
                        "		FROM " +
                        "			TblStationClassPlanDetail " +
                        "		WHERE " +
                        "			? BETWEEN dutyBeginDateTime AND dutyEndDateTime " +
                        " 			AND signTime IS NOT NULL   " + //必须签到才算作上班
                        "			AND stationId = ? " +
                        "	) " +
                        "	AND `status` = ?" ,
                dateTime,stationId,TblStationAssignPost.STATUS_NO_WORKING);
        Iterator<Map<String,Object>> it = assignPostNoWorkingList.iterator();
        while(it.hasNext()) {
            List<Map<String,Object>> workingList = jsonTagJdbcDao.getJdbcTemplate().queryForList(
                    "SELECT * FROM TblStationAssignPost WHERE STATUS = ? AND stationClassPlanDetailId = ? " ,
                    TblStationAssignPost.STATUS_WORKING,it.next().get("stationClassPlanDetailId"));
            if(workingList.size()>0) {
                it.remove();
            }
        }
        Integer waitingPostCount = assignPostNoWorkingList.size();
        countMap.put("waitingPostCount", waitingPostCount);
    }*/

}
