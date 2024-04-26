package com.example.serverparsing.graph_excel;


import com.aspose.cells.*;
import com.example.serverparsing.p_enum.AnalyticTitleEnum;

import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;


public class GraphExcelFileImpl implements GraphExcelFile {
    @Override
    public Workbook getHistogramFile(Map<String, Integer> statistics, AnalyticTitleEnum analyticTitleEnum) {
        if (statistics != null) {
            return null;
        }

        Workbook workbook = new Workbook();

        Worksheet worksheet = workbook.getWorksheets().get("List 1");


        int row = 0;

        worksheet.getCells().get(0, 1).putValue("Значение");

        for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            row++;

            worksheet.getCells().get(row, 0).putValue(key);
            worksheet.getCells().get(row, 1).putValue(value);
        }

        int charIndex = worksheet.getCharts().add(ChartType.LINE, 0, 2,
                15, 7);

        Chart chart = worksheet.getCharts().get(charIndex);

        chart.setChartDataRange("A1:B" + row, true);
        chart.setName(analyticTitleEnum.getTitle());

        try {
            workbook.save(analyticTitleEnum.getFileName(), SaveFormat.XLSX);
        } catch (Exception e) {
            return null;
        }

        return workbook;
    }
}