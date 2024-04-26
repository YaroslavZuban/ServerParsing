package com.example.serverparsing.graph_excel;

import com.aspose.cells.Workbook;
import com.example.serverparsing.p_enum.AnalyticTitleEnum;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.Map;

public interface GraphExcelFile {
    Workbook getHistogramFile(Map<String, Integer> statistics, AnalyticTitleEnum analyticTitleEnum);
}