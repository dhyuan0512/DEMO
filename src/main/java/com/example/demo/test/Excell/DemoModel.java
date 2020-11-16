package com.example.demo.test.Excell;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DemoModel extends BaseRowModel {

    @ExcelProperty(index = 0,value = "支付日期")
    private Date payTime;

    @ExcelProperty(index = 1,value = "订单号")
    private String orderNo;

    @ExcelProperty(index = 2,value = "患者")
    private String name;

    @ExcelProperty(index = 3,value = "药品名称")
    private String commodityName;

    @ExcelProperty(index = 4,value = "单价")
    private BigDecimal price;

    @ExcelProperty(index = 5,value = "数量")
    private Integer quantity;

    @ExcelProperty(index = 6,value = "总价")
    private BigDecimal totalAmount;

    @ExcelProperty(index = 7,value = "折扣金额")
    private BigDecimal discountAmount;

    @ExcelProperty(index = 8,value = "安盛结算金额")
    private BigDecimal settleAmount;

}
