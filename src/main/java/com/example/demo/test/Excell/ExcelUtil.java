package com.example.demo.test.Excell;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @version 0.1
 * @auther yanxianghui
 * @application
 * @Copyright 上海危网信息科技有限公司版权所有
 * @company wwsoft
 * @time 2020年02月14日 14:07:12
 **/
public class ExcelUtil {
    private final static String excel2003L = ".xls";    //2003- 版本的excel
    private final static String excel2007U = ".xlsx";   //2007+ 版本的excel

    /**
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
     *
     * @param in,
     * @param fileName
     * @param n                从第n+2行开始获取数据
     * @param isLoadOtherSheet
     * @return
     * @throws IOException
     */
    public List<List<Object>> getBankListByExcel(InputStream in, String fileName, int n, boolean isLoadOtherSheet) throws Exception {
        List<List<Object>> list = new ArrayList<List<Object>>();

        //创建Excel工作薄
        Workbook work = this.getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;  //页数
        Row row = null;  //行数
        Cell cell = null;  //列数
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum() + n; j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() + n == j) {
                    continue;
                }

                //遍历所有的列
                boolean isAllBlank = false;
                int blankCellNum = 0;
                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    if (StringUtils.isBlank(cell.toString())) {
                        blankCellNum++;
                    }
                    if (cell.getCellType() == 0) {
                        BigDecimal bd = new BigDecimal(cell.toString());//要修改的值，需要string类型
                        String cell1 = bd.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString();
                        li.add(cell1);
                    } else {
                        li.add(this.getValue(cell));
                    }
                }
                if (blankCellNum == (row.getLastCellNum() - row.getFirstCellNum())) {
                    isAllBlank = true;
                }
                if (!isAllBlank) {
                    list.add(li);
                }
            }

            if (!isLoadOtherSheet) {
                break;
            }
        }

        return list;
    }

    public JSONArray getSheetListByExcel(InputStream in, String fileName, int n) throws Exception {
        //创建Excel工作薄
        Workbook work = this.getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;  //页数
        Row row = null;  //行数
        Cell cell = null;  //列数
        //遍历Excel中所有的sheet
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            List<List<Object>> list = new ArrayList<List<Object>>();
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum() + n; j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() + n == j) {
                    continue;
                }

                //遍历所有的列
                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(this.getValue(cell));
                }
                list.add(li);
            }
            jsonArray.add(list);
        }
        return jsonArray;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     *
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(inStr);  //2003-
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(inStr);  //2007+
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    //解决excel类型问题，获得数值
    public String getValue(Cell cell) {
        String value = "";
        if (null == cell) {
            return value;
        }
        switch (cell.getCellType()) {
            //数值型
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //如果是date类型则 ，获取该cell的date值
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    value = format.format(date);
                } else {// 纯数字
//                    BigDecimal big = new BigDecimal(cell.getStringCellValue());
                    double num = cell.getNumericCellValue();

                    BigDecimal big = new BigDecimal(String.valueOf(num));
                    value = big.toString();
                    //解决1234.0  去掉后面的.0
                    if (null != value && !"".equals(value.trim())) {
                        String[] item = value.split("[.]");
                        if (1 < item.length && "0".equals(item[1])) {
                            value = item[0];
                        }
                    }
                }
                break;
            //字符串类型
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue().toString();
                break;
            // 公式类型
            case Cell.CELL_TYPE_FORMULA:
                //读公式计算值
                try {
                    value = String.valueOf(cell.getNumericCellValue());
                    if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                        value = cell.getStringCellValue().toString();
                    }
                } catch (IllegalStateException e) {
                    value = String.valueOf(cell.getRichStringCellValue());
                }
                break;
            // 布尔类型
            case Cell.CELL_TYPE_BOOLEAN:
                value = " " + cell.getBooleanCellValue();
                break;
            //错误类型
            case Cell.CELL_TYPE_ERROR:
                value = "";
                break;
            default:
                value = cell.getStringCellValue().toString();
        }
        if ("null".endsWith(value.trim())) {
            value = "";
        }
        return value;
    }

    /**
     * excel验证
     *
     * @param validField
     * @param validTypeArray
     * @return
     */
    public static Boolean isPassValid(String validField, int[] validTypeArray) {
        boolean isPass = true;
        for (int validType : validTypeArray) {
            switch (validType) {
                case 1: //必填
                    if (StringUtils.isBlank(validField)) {
                        isPass = false;
                    }
                    break;
                case 2: //字符长度不超过50
                    if (validField.length() > 50) {
                        isPass = false;
                    }
                    break;
                case 3: //时间格式转换
                    try {
                        if (StringUtils.isNotBlank(validField)) {
                            if (validField.contains("/")) {
                                if (validField.length() > 10) {
                                    Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(validField);
                                } else {
                                    Date date = new SimpleDateFormat("yyyy/MM/dd").parse(validField);
                                }
                            } else if (validField.contains("-")) {
                                if (validField.length() > 10) {
                                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(validField);
                                } else {
                                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(validField);
                                }
                            } else {
                                isPass = false;
                                break;
                            }
                        }
                    } catch (Exception e) {
                        isPass = false;
                    }
                    break;
                case 4: //校验手机号码
                    /**
                     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
                     * 此方法中前三位格式有：
                     * 13+任意数
                     * 145,147,149
                     * 15+除4的任意数(不要写^4，这样的话字母也会被认为是正确的)
                     * 166
                     * 17+3,5,6,7,8
                     * 18+任意数
                     * 198,199
                     */
                    if (StringUtils.isNotBlank(validField)) {
                        //String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
                        String regex = "^((13[0-9])|(14[0-9])|(15[0-9])|(166)|(17[0-9])" +
                                "|(18[0-9])|(19[8,9]))\\d{8}$";
                        if (validField.length() != 11) {
                            isPass = false;
                            break;
                        } else {
                            Pattern p = Pattern.compile(regex);
                            Matcher m = p.matcher(validField);
                            if (!m.matches()) {
                                isPass = false;
                            }
                        }
                    } else {
                        isPass = false;
                    }
                    break;
                case 5: // 指定字符
                    if (validField.length() > 1 || !validField.matches("^[A|B|C|D]$")) {
                        isPass = false;
                    }
                    break;
                case 6: // 校验正整数
                    if (!validField.matches("^\\+?[0-9]\\d*$")) {
                        isPass = false;
                    }
                    break;
                default:
                    break;
            }
            if (!isPass) {
                break;
            }
        }
        return isPass;
    }

}