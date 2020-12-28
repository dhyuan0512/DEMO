package com.example.demo.util;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * <p>
 * </p>
 *
 * @author zzq
 * @version 1.0.0
 * @date 2020-04-20 13:19
 */
public class ExcelUtils {

    public static <T> void readExcel(String filePath, Class<? extends BaseRowModel> clazz, int sheetNo, int headLineNum, AnalysisEventListener<T> excelListener) throws FileNotFoundException {

        ExcelReader reader = new ExcelReader(new FileInputStream(filePath), ExcelTypeEnum.XLSX, excelListener);
        reader.read(new Sheet(sheetNo, headLineNum, clazz));
    }

    public static <T> void readExcel(InputStream stream, Class<? extends BaseRowModel> clazz, int sheetNo, int headLineNum, AnalysisEventListener<T> excelListener) throws FileNotFoundException {

        ExcelReader reader = new ExcelReader(stream, ExcelTypeEnum.XLSX, excelListener);
        reader.read(new Sheet(sheetNo, headLineNum, clazz));
    }
}
