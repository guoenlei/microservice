package com.aldwx.app.modules.portrait.controller;

import com.aldwx.app.common.base.BaseController;
import com.aldwx.app.common.util.DateUtil;
import com.aldwx.app.modules.portrait.service.PortraitService;
import com.aldwx.app.modules.portrait.vo.PortraitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 用户画像接口
 *
 * @author
 * @description
 * @createTime
 **/
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/aldstat/app/")
public class PortraitController extends BaseController {
    @Autowired
    private PortraitService portraitService;

    /**
     * 用户画像-地域分析-分布
     *
     * @param app_key
     * @param date
     * @param app_type
     * @param data_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "area/view", method = RequestMethod.POST)
    public Map<String, Object> findPortraitCityTotal(@NotNull String app_key, @NotNull String date, String app_type, String data_type) {
        PortraitVo vo = getPortraitVo(app_key, date, app_type, data_type);
        vo.setIsTop("yes");
        Map<String, Object> aldstat = this.portraitService.selectCityTop(vo);
        return aldstat;
    }

    /**
     * 地域分析-分布明细
     *
     * @param app_key
     * @param date
     * @param app_type
     * @param data_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "area/particular", method = RequestMethod.POST)
    public Map<String, Object> findPortraitCityDetail(@NotNull String app_key, @NotNull String date, String app_type, String data_type) {
        PortraitVo vo = getPortraitVo(app_key, date, app_type, data_type);
        Map<String, Object> aldstat = this.portraitService.selectCityDetail(vo);
        return aldstat;
    }

    /**
     * 机型分析-机型分布（TOP10）
     *
     * @param app_key
     * @param date
     * @param app_type
     * @param data_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "phone/view", method = RequestMethod.POST)
    public Map<String, Object> findPortraitModelTotal(@NotNull String app_key, @NotNull String date, String app_type, String data_type) {
        PortraitVo vo = getPortraitVo(app_key, date, app_type, data_type);
        vo.setIsTop("yes");
        Map<String, Object> aldstat = this.portraitService.selectModelTop(vo);
        return aldstat;
    }

    /**
     * 机型分析-设备明细
     *
     * @param app_key
     * @param date
     * @param app_type
     * @param data_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "phone/particular", method = RequestMethod.POST)
    public Map<String, Object> findPortraitModelDetail(@NotNull String app_key, @NotNull String date, String app_type, String data_type) {
        PortraitVo vo = getPortraitVo(app_key, date, app_type, data_type);
        Map<String, Object> aldstat = this.portraitService.selectModelDetail(vo);
        return aldstat;
    }


    /**
     * 终端分析-地区分布（TOP10）
     *
     * @param app_key
     * @param date
     * @param app_type
     * @param data_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "terminal/view", method = RequestMethod.POST)
    public Map<String, Object> findPortraitTerminalTotal(@NotNull String app_key, @NotNull String date, String app_type, String data_type) {
        PortraitVo vo = getPortraitVo(app_key, date, app_type, data_type);
        vo.setIsTop("yes");
        Map<String, Object> aldstat = this.portraitService.selectTerminalTop(vo);
        return aldstat;
    }

    /**
     * 终端分析-地域分布明细
     *
     * @param app_key
     * @param date
     * @param app_type
     * @param data_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "terminal/particular", method = RequestMethod.POST)
    public Map<String, Object> findPortraitTerminalDetail(@NotNull String app_key, @NotNull String date, String app_type, String data_type) {
        PortraitVo vo = getPortraitVo(app_key, date, app_type, data_type);
        Map<String, Object> aldstat = this.portraitService.selectTerminalDetail(vo);
        return aldstat;
    }

    /**
     * 网络分布-分布类型
     *
     * @param app_key
     * @param date
     * @param app_type
     * @param data_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "network/view", method = RequestMethod.POST)
    public Map<String, Object> findPortraitNetTotal(@NotNull String app_key, @NotNull String date, String app_type, String data_type) {
        PortraitVo vo = getPortraitVo(app_key, date, app_type, data_type);
        vo.setIsTop("yes");
        Map<String, Object> aldstat = this.portraitService.selectNetTop(vo);
        return aldstat;
    }

    /**
     * 网络分布-类型明细
     *
     * @param app_key
     * @param date
     * @param app_type
     * @param data_type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "network/particular", method = RequestMethod.POST)
    public Map<String, Object> findPortraitNetDetail(@NotNull String app_key, @NotNull String date, String app_type, String data_type) {
        PortraitVo vo = getPortraitVo(app_key, date, app_type, data_type);
        Map<String, Object> aldstat = this.portraitService.selectNetDetail(vo);
        return aldstat;
    }

    /**
     * 处理参数方法VO
     *
     * @author
     * @description
     * @createTime
     **/
    public static PortraitVo getPortraitVo(String app_key, String date, String app_type, String data_type) {
        PortraitVo vo = new PortraitVo();
        vo.setAppKey(app_key);
        vo.setAppType(app_type);
        vo.setDataType(data_type);
        if (date.equals("1")) {
            vo.setDateStart(DateUtil.getTodayDate());
            vo.setDateEnd(vo.getDateStart());
        } else if (date.equals("2")) {
            String yesterday = DateUtil.getYesterday();
            vo.setDateStart(yesterday);
            vo.setDateEnd(yesterday);
        } else if (date.equals("3")) {
            String[] day = DateUtil.getNearly7Day();
            vo.setDateStart(day[0]);
            vo.setDateEnd(day[1]);
        } else if (date.equals("4")) {
            String[] day = DateUtil.getNearly30Day();
            vo.setDateStart(day[0]);
            vo.setDateEnd(day[1]);
        } else {
            String dateRange[] = date.split("~");
            vo.setDateStart(dateRange[0].trim());
            vo.setDateEnd(dateRange[1].trim());
        }
        return vo;
    }

}
