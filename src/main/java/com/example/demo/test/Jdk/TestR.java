package com.example.demo.test.Jdk;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.DateBean;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TestR {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date oldparse = simpleDateFormat.parse("2012-05-12 15:16:00");
        Date newparse = simpleDateFormat.parse("2013-05-12 15:16:00");

        DateBean dateBean = new DateBean();
        dateBean.setDate(oldparse);
        DateBean dateBean1 = new DateBean();
        dateBean1.setDate(newparse);
        DateBean dateBean2 = new DateBean();
        dateBean2.setDate(new Date());


        List<DateBean> dates = new ArrayList<>();
        dates.add(dateBean);
        dates.add(dateBean1);
        dates.add(dateBean2);
        dates.forEach(e -> System.out.println("排序前 = " + simpleDateFormat.format(e.getDate())));
        List<DateBean> list = dates.stream().filter(date -> date != null)
                .sorted((o1, o2) -> o2.getDate()
                .compareTo(o1.getDate()))
                .collect(Collectors.toList());
        list.forEach(e -> System.out.println("排序后 = " + simpleDateFormat.format(e.getDate())));
    }

}
