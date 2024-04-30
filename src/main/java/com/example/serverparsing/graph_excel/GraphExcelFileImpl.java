package com.example.serverparsing.graph_excel;


import com.aspose.cells.*;
import com.example.serverparsing.p_enum.AnalyticTitleEnum;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.Map;


public class GraphExcelFileImpl implements GraphExcelFile {
    @Override
    public Resource getHistogramFile(Map<String, Integer> statistics, AnalyticTitleEnum analyticTitleEnum) {
        if (statistics == null) {
            return null;
        }

        Workbook workbook = new Workbook();

        Worksheet worksheet = workbook.getWorksheets().get(0);

        int row = 0;

        worksheet.getCells().get(0, 1).putValue("Количество");

        for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            row++;

            worksheet.getCells().get(row, 0).putValue(key);
            worksheet.getCells().get(row, 1).putValue(value);
        }

        int charIndex = worksheet.getCharts().add(ChartType.COLUMN, 0, 3,
                15, 7);

        Chart chart = worksheet.getCharts().get(charIndex);
        row++;

        chart.setChartDataRange("A1:B" + row, true);

        return toResource(workbook);
    }

    public Resource toResource(Workbook workbook) {
        var outputStream = new ByteArrayOutputStream();

        try {
            workbook.save(outputStream, SaveFormat.XLSX);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ByteArrayResource(outputStream.toByteArray());
    }
}