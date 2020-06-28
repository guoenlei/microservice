package com.aldwx.bigdata.modules.event.util;

import com.aldwx.bigdata.common.page.PageUtil;
import com.aldwx.bigdata.common.util.Arith;
import com.aldwx.bigdata.modules.gameEvent.entity.AldGameEventParamEntity;
import com.aldwx.bigdata.modules.gameEvent.vo.AldGameEventParamVo;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import com.facebook.presto.jdbc.internal.guava.collect.Maps;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;


/**
 * 小游戏事件 时间段查询 数据累加计算并分页
 */
public class GameEventDataUtil {



    public static List<AldGameEventParamEntity> mergePage(List<AldGameEventParamEntity> prestoList, List<AldGameEventParamEntity> mysqlList,
                                                          AldGameEventParamVo aldGameEventParamVo) {

        //合并数据
        List<AldGameEventParamEntity> mergeList = merge(prestoList, mysqlList, aldGameEventParamVo);
        //排序 分页处理
        List<AldGameEventParamEntity> resultlist = sortAndPage(mergeList, aldGameEventParamVo);

        return resultlist;
    }


    /**
     * 数据合并
     * 分别取mysql库中 多页数据 presto中多页数据
     * 对数据进行重新分统计 排序 并分页
     * 按type_ak_ek_date缓存
     * @return
     */
    public static List<AldGameEventParamEntity> merge(List<AldGameEventParamEntity> prestoList, List<AldGameEventParamEntity> mysqlList,
                                                  AldGameEventParamVo aldGameEventParamVo) {

        if(null != prestoList && prestoList.size() > 0 && null != mysqlList && mysqlList.size() > 0) {
            //
            Map<String, AldGameEventParamEntity> resultDataMap = Maps.newConcurrentMap();

            for(AldGameEventParamEntity aldGameEventParamEntity : prestoList) {
                String key = aldGameEventParamEntity.getEvParasValue();
                if(StringUtil.isNotEmpty(key)) {
                    resultDataMap.put(key, aldGameEventParamEntity);
                }
            }

            for(AldGameEventParamEntity aldGameEventParamEntity : mysqlList) {
                AldGameEventParamEntity aldGameEventParamEntity1 = resultDataMap.get(aldGameEventParamEntity.getEvParasValue());
                if(null == aldGameEventParamEntity1) {
                    String key = aldGameEventParamEntity.getEvParasValue();
                    if(StringUtils.isNotBlank(key)) {
                        resultDataMap.put(key, aldGameEventParamEntity);
                    }
                } else {
                    //将mysql中数值和presto中累加
                    int evParasUvCount = (int)Arith.add(Long.parseLong(aldGameEventParamEntity1.getEvParasUv()),
                            Long.parseLong(aldGameEventParamEntity.getEvParasUv()));
                    int evParasCount = (int)Arith.add(Long.parseLong(aldGameEventParamEntity1.getEvParasCount()),
                            Long.parseLong(aldGameEventParamEntity.getEvParasCount()));

                    aldGameEventParamEntity.setEvParasUv(evParasUvCount + "");
                    aldGameEventParamEntity.setEvParasCount(evParasCount + "");

                    resultDataMap.put(aldGameEventParamEntity.getEvParasValue(), aldGameEventParamEntity);
                }
            }

            List<AldGameEventParamEntity> joinDataList = Lists.newArrayList();
            if(null != resultDataMap) {
                for(Map.Entry<String, AldGameEventParamEntity> entityEntry : resultDataMap.entrySet()) {
                    AldGameEventParamEntity aldGameEventParamEntity = entityEntry.getValue();
                    joinDataList.add(aldGameEventParamEntity);
                }
                return joinDataList;
            }
        } else {
            if(null != prestoList && prestoList.size() > 0) {
                return prestoList;
            } else {
                return mysqlList;
            }
        }

        return null;
    }


    /**
     * 排序并分页
     * @param aldGameEventParamEntityList
     * @param aldGameEventParamVo
     * @return
     */
    private static List<AldGameEventParamEntity> sortAndPage(List<AldGameEventParamEntity> aldGameEventParamEntityList,
                                                             AldGameEventParamVo aldGameEventParamVo) {
        List<AldGameEventParamEntity> resultDataList = null;

        String order = aldGameEventParamVo.getOrder();
        String prop = aldGameEventParamVo.getProp();
        String currentPage = aldGameEventParamVo.getCurrentPage();
        String total = aldGameEventParamVo.getTotal();

        if(null != aldGameEventParamEntityList && aldGameEventParamEntityList.size() > 0) {
            if(StringUtils.isNotBlank(order) && StringUtils.isNotBlank(prop)) {
                boolean isSort = false;
                if(order.toUpperCase().contains("ASC")) {
                    isSort = true; //正序
                }
                //排序 分页
                List<AldGameEventParamEntity> sortList = fastSort(aldGameEventParamEntityList, aldGameEventParamVo, isSort);
                resultDataList = page(sortList, aldGameEventParamVo);
            } else {
                //不排序直接分页
                resultDataList = page(aldGameEventParamEntityList, aldGameEventParamVo);
            }
        }

        return resultDataList;
    }


    /**
     * 排序
     * @param list
     * @param aldGameEventParamVo
     * @param isSort
     * @return
     */
    public static List<AldGameEventParamEntity> fastSort(List<AldGameEventParamEntity> list, AldGameEventParamVo aldGameEventParamVo,
                                                         boolean isSort) {

        List<AldGameEventParamEntity> sortList = Lists.newArrayList();

        for(int i = 0; i < list.size(); i++) {
            for(int j = (i+1); j < list.size(); j++) {
                AldGameEventParamEntity gameEvent = new AldGameEventParamEntity();
                String prop = aldGameEventParamVo.getProp();

                Integer paramUV1 = Integer.parseInt(list.get(i).getEvParasUv());
                Integer paramCount1 = Integer.parseInt(list.get(i).getEvParasCount());
                Integer paramUV2 = Integer.parseInt(list.get(j).getEvParasUv());
                Integer paramCount2 = Integer.parseInt(list.get(j).getEvParasCount());

                if(prop.equals("ev_paras_uv")) { //基于用户数
                    if(isSort) { //正序 从小到大
                        if(paramUV1 > paramUV2) {
                            gameEvent = list.get(i);

                            list.set(i, list.get(j));
                            list.set(j, gameEvent);
                        }

                    } else { //倒序 从大到小
                        if(paramUV2 > paramUV1) {
                            gameEvent = list.get(i);

                            list.set(i, list.get(j));
                            list.set(j, gameEvent);
                        }
                    }
                } else if(prop.equals("ev_paras_count")) {
                    if(isSort) { //正序 从小到大
                        if(paramCount1 > paramCount2) {
                            gameEvent = list.get(i);

                            list.set(i, list.get(j));
                            list.set(j, gameEvent);
                        }

                    } else { //倒序 从大到小
                        if(paramCount2 > paramCount1) {
                            gameEvent = list.get(i);

                            list.set(i, list.get(j));
                            list.set(j, gameEvent);
                        }
                    }
                }
            }
        }

        return list;
    }


    /**
     * 分页
     * @param list
     * @param aldGameEventParamVo
     * @return
     */
    public static List<AldGameEventParamEntity> page(List<AldGameEventParamEntity> list, AldGameEventParamVo aldGameEventParamVo) {

        String currentPage = aldGameEventParamVo.getCurrentPage();
        String total = aldGameEventParamVo.getTotal();

        int startRow = PageUtil.startRow(Integer.parseInt(currentPage), Integer.parseInt(total));
        int endRow = PageUtil.endRow(Integer.parseInt(currentPage), Integer.parseInt(total));

        List<AldGameEventParamEntity> pageList = Lists.newArrayList();
        if(null != list && list.size() > 0) {
            for(int j = startRow; (j <= list.size() && j <= endRow); j++) {
                pageList.add(list.get(j-1));
            }
        }

        return pageList;
    }


    /**
     * 设置合并数据查询时分页信息
     * @param aldGameEventParamVo
     * @return
     */
    public static AldGameEventParamVo setPageRow(AldGameEventParamVo aldGameEventParamVo) {
        String currentPage = aldGameEventParamVo.getCurrentPage();
        String total = aldGameEventParamVo.getTotal();

        int startRow = PageUtil.reStartRow(Integer.parseInt(currentPage), Integer.parseInt(total));
        int endRow = PageUtil.reEndRow(Integer.parseInt(currentPage), Integer.parseInt(total));

        aldGameEventParamVo.setStartRow(startRow);
        aldGameEventParamVo.setEndRow(endRow);
        return aldGameEventParamVo;
    }



    public static void main(String[] args) {

        AldGameEventParamEntity a1 = new AldGameEventParamEntity();
        a1.setEvParasUv("1030");
        a1.setEvParasCount("4320");
        a1.setEvParasValue("首页id");

        AldGameEventParamEntity a2 = new AldGameEventParamEntity();
        a2.setEvParasUv("1340");
        a2.setEvParasCount("4420");
        a2.setEvParasValue("广告id");

        AldGameEventParamEntity a3 = new AldGameEventParamEntity();
        a3.setEvParasUv("1230");
        a3.setEvParasCount("4430");
        a3.setEvParasValue("地图");

        List<AldGameEventParamEntity> list1 = Lists.newArrayList();

        list1.add(a1);
        list1.add(a2);
        list1.add(a3);

        AldGameEventParamEntity b1 = new AldGameEventParamEntity();
        b1.setEvParasUv("1034230");
        b1.setEvParasCount("4321340");
        b1.setEvParasValue("首页id");

        AldGameEventParamEntity b2 = new AldGameEventParamEntity();
        b2.setEvParasUv("1343240");
        b2.setEvParasCount("4432420");
        b2.setEvParasValue("漫画");

        AldGameEventParamEntity b3 = new AldGameEventParamEntity();
        b3.setEvParasUv("123240");
        b3.setEvParasCount("4432340");
        b3.setEvParasValue("产品");

        List<AldGameEventParamEntity> list2 = Lists.newArrayList();

        list2.add(b1);
        list2.add(b2);
        list2.add(b3);

        AldGameEventParamVo vo = new AldGameEventParamVo();
        vo.setOrder("ASC");
        vo.setProp("ev_paras_count");

        vo.setCurrentPage("2");
        vo.setTotal("20");


        List<AldGameEventParamEntity> resultList = mergePage(list1, list2, vo);



        System.out.println("=======");
    }


}
