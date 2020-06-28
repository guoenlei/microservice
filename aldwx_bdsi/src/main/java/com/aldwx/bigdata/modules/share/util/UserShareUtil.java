package com.aldwx.bigdata.modules.share.util;

import com.aldwx.bigdata.common.constants.Constants;
import com.aldwx.bigdata.common.util.DateUtil;
import com.aldwx.bigdata.common.util.StringUtil;
import com.aldwx.bigdata.modules.share.entity.AldUserShareEntity;
import com.aldwx.bigdata.modules.share.vo.AldUserShareVo;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class UserShareUtil {


    /**
     * 参数校验
     * @param vo
     * @return
     */
    public static Map<String, Object> checkParam(AldUserShareVo vo) {
        Map<String, Object> checkMap = Maps.newConcurrentMap();
        String date = vo.getDate();
        String ak = vo.getAppKey();
        String currentPage = vo.getCurrentPage();
        String total = vo.getTotal();
        if(StringUtils.isNotBlank(date)) {
            if(!date.contains(Constants.FLAG_01)) {
                boolean flag = false;
                switch (NumberUtils.toInt(date)) {
                    case 1:
                        flag = true;
                        break;
                    case 2:
                        flag = true;
                        break;
                    case 3:
                        flag = true;
                        break;
                    case 4:
                        flag = true;
                        break;
                    default:
                        break;
                }
                if(!flag) {
                    checkMap.put("code", "202");
                    checkMap.put("msg", Constants.ALERT_DATE_ERROR_MSG);
                }
            }
        } else {
            checkMap.put("code", "202");
            checkMap.put("msg", Constants.ALERT_DATE_ERROR_MSG);
        }
        if(StringUtils.isEmpty(ak)) {
            checkMap.put("code", "202");
            checkMap.put("msg", Constants.ALERT_NO_AK_MSG);
        } else if(StringUtils.isEmpty(currentPage) || StringUtils.isEmpty(total)
                || !StringUtils.isNumeric(currentPage) || !StringUtils.isNumeric(total)) {
            checkMap.put("code", "202");
            checkMap.put("msg", Constants.ALERT_PAGE_ERROR_MSG);
        }

        return checkMap;
    }


    /**
     * 格式化参数
     * @param vo
     * @return
     */
    public static AldUserShareVo requestParamFormat(AldUserShareVo vo) {
        String ak = vo.getAppKey();
        String date = vo.getDate();
        String order = vo.getOrder();
        //将分区时间仅为今天或者昨天的 替换为1或2 直接查询mysql库
        if(date.contains(Constants.FLAG_01)) {
            String[] dates = StringUtils.splitPreserveAllTokens(date.replaceAll("\\s*",""), Constants.FLAG_01);
            //判断时间区间是否仅包含2天
            if(dates.length == 2 && dates[0].equals(dates[1])) {
                if(dates[0].equals(DateUtil.getTodayDate())) {
                    vo.setDate(Constants.ALDSTAT_EVENT_TODAY_TIME);
                } else if(dates[0].equals(DateUtil.getYesterday())){
                    vo.setDate(Constants.ALDSTAT_EVENT_YESTERDAY_TIME);
                }
            }
        }
        if(StringUtils.isNotBlank(order)) {
            if(order.contains("asc")) {
                vo.setOrder("ASC");
            } else if(order.contains("desc")){
                vo.setOrder("DESC");
            }
        }

        return vo;
    }


    /**
     * 格式化返回数据
     * @param entityMap
     * @return
     */
    public static Map<String, Object> responseDataListFormat(Map<String, Object> entityMap) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        if(null != entityMap && entityMap.size() > 0) {
            List<AldUserShareEntity> aldUserShareEntityList = (List<AldUserShareEntity>)entityMap.get("data");
            Long dataCount = (Long)entityMap.get("count");
            if(null != aldUserShareEntityList && aldUserShareEntityList.size() > 0) {
                List<Map<String, Object>> mapList = Lists.newArrayList();
                for(AldUserShareEntity aldUserShareEntity : aldUserShareEntityList) {
                    if(null != aldUserShareEntity) {
                        Map<String, Object> dataMap = Maps.newConcurrentMap();
                        dataMap.put("sharer_uuid", aldUserShareEntity.getShareUuid());
                        dataMap.put("new_count", aldUserShareEntity.getNewCount());
                        dataMap.put("share_count", aldUserShareEntity.getShareCount());
                        dataMap.put("share_open_count", aldUserShareEntity.getShareOpenCount());
                        dataMap.put("nickname", aldUserShareEntity.getNickName());
                        dataMap.put("user_remark", aldUserShareEntity.getUserRemark());
                        dataMap.put("avatar_url", aldUserShareEntity.getAvatarUrl());
                        dataMap.put("share_open", StringUtil.formatPercent(NumberUtils.toFloat(aldUserShareEntity.getShareRefluxRatio())));

                        mapList.add(dataMap);
                    }
                }
                resultMap.put("data", mapList);
                resultMap.put("share_count", dataCount);
            }
        }

        return resultMap;
    }

//
//    /**
//     * 格式化分享回流比
//     * @return
//     */
//    public static String formatRefluxRatio(float refluxRatio) {
//        if(refluxRatio > 0) {
//            DecimalFormat fnum = new DecimalFormat("##0.00");
//            return fnum.format(refluxRatio) + "%";
//        }
//        return "0.00%";
//    }

}
