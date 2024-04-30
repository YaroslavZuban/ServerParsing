package com.example.serverparsing.graph_excel;

import com.aspose.cells.Workbook;
import com.example.serverparsing.p_enum.AnalyticTitleEnum;
import org.springframework.core.io.Resource;

import java.util.Map;

public interface GraphExcelFile {
    Resource getHistogramFile(Map<String, Integer> statistics, AnalyticTitleEnum analyticTitleEnum);
}