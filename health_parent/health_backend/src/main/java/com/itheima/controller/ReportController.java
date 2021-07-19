package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entiy.Result;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import com.itheima.service.ReportService;
import com.itheima.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 报表操作
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    @Reference
    private MemberService memberService;
    @Reference
    private SetmealService setmealService;
    @Reference
    private ReportService reportService;
    //会员数量折线图
    @RequestMapping("/getMemberReport")
    public Result getMemberReport(){
        Map<String,Object> map = new HashMap<>();
        List<String> months = new ArrayList<>();
        //获得日历对象，模拟时间就是当前时间
        Calendar calendar= Calendar.getInstance();
        //计算过去一年一年的12个月
        calendar.add(Calendar.MONTH,-36);//获得当前时间往前推12个月时间
        for (int i=0;i<12;i++){
            calendar.add(Calendar.MONTH,1);//获得当前时间往后推一个月日期
            Date date = calendar.getTime();
            months.add(new SimpleDateFormat("yyyy-MM").format(date));
        }
        map.put("months",months);
        List<Integer> memberCount =memberService.findMemberCountByMonths(months);
        map.put("memberCount",memberCount);
        return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
    }
    //套餐预约占比饼型图
    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport(){
        //
        Map<String,Object> data = new HashMap<>();
        try {List<Map<String,Object>> setmealCount= setmealService.findSetmealCount();
            data.put("setmealCount",setmealCount);
            List<String> setmealNames = new ArrayList<>();
            for (Map<String, Object> map : setmealCount) {
                String name = (String) map.get("name");
                setmealNames.add(name);
            }
            data.put("setmealNames",setmealNames);
            return new Result(true,MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,data);

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }

    }
    //运营数据
    @RequestMapping("/getBusinessReportData")
    public Result   getBusinessReportData(){
        try{
            Map<String,Object> data = reportService.getBusinessReportDate();
            return new Result(true,MessageConstant.GET_BUSINESS_REPORT_SUCCESS,data);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }
}
