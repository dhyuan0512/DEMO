package com.example.demo.test.Excell;

import com.example.demo.util.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class Excell {
    public void getExcell(MultipartFile uploadFile, DemoListener listener) {
        try {
            ExcelUtils.readExcel(uploadFile.getInputStream(), DemoModel.class, 1, 1, listener);
            log.info("---导入成功----");
        } catch (Exception e) {
            log.error("导入失败,payFlowNo={}", e);
        }
    }
}
