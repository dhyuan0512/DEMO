package com.example.demo.test.Excell;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.demo.bean.UserBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DemoListener extends AnalysisEventListener<DemoModel> {

    private List<UserBean> list = new ArrayList<>();


    @Override
    public void invoke(DemoModel demoModel, AnalysisContext context) {
        UserBean userBean = new UserBean();
        userBean.setId(1);
        userBean.setName(demoModel.getName());
        userBean.setName(demoModel.getOrderNo());
        list.add(userBean);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

}
