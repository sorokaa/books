package io.srk.export.helper.impl;

import io.srk.export.exception.ExportProcessingException;
import io.srk.export.model.export.dto.BookStatisticDto;
import io.srk.export.model.export.dto.BooksStatisticExportRequestDto;
import lombok.experimental.UtilityClass;
import org.apache.poi.xssf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@UtilityClass
public class BookStatisticExportHelper {

    public byte[] export(BooksStatisticExportRequestDto request) throws ExportProcessingException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            generateTable(request, workbook);
            return writeToFile(workbook);
        } catch (IOException e) {
            throw new ExportProcessingException(e.getMessage());
        }
    }

    private byte[] writeToFile(XSSFWorkbook workbook) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workbook.write(bos);
            return bos.toByteArray();
        }
    }

    private void generateTable(BooksStatisticExportRequestDto request, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet("Book statistic");
        generateTableHeader(sheet, workbook);
        generateStatistic(request, sheet);
    }

    private void generateTableHeader(XSSFSheet sheet, XSSFWorkbook workbook) {
        XSSFRow headerRow = sheet.createRow(0);
        int columnIndex = 0;
        createHeaderCell("Book name", columnIndex++, headerRow, workbook);
        createHeaderCell("Book author", columnIndex++, headerRow, workbook);
        createHeaderCell("Book year", columnIndex++, headerRow, workbook);
        createHeaderCell("Book price", columnIndex++, headerRow, workbook);
        createHeaderCell("Orders count", columnIndex, headerRow, workbook);
    }

    private void generateStatistic(BooksStatisticExportRequestDto request, XSSFSheet sheet) {
        var booksStatistic = request.getBooksStatistic();
        for (int i = 1; i < booksStatistic.size(); i++) {
            generateStatisticRow(i, booksStatistic.get(i), sheet);
        }
    }

    private void generateStatisticRow(int rowIndex, BookStatisticDto statistic, XSSFSheet sheet) {
        XSSFRow row = sheet.createRow(rowIndex);
        int columnIndex = 0;
        createCell(statistic.getBookName(), columnIndex++, row);
        createCell(statistic.getAuthorName(), columnIndex++, row);
        createCell(String.valueOf(statistic.getYear()), columnIndex++, row);
        createCell(String.valueOf(statistic.getPrice()), columnIndex++, row);
        createCell(String.valueOf(statistic.getOrders()), columnIndex, row);
    }

    private void createCell(Object value, int index, XSSFRow row) {
        XSSFCell cell = row.createCell(index);
        cell.setCellValue(String.valueOf(value));
    }

    private void createHeaderCell(Object value, int index, XSSFRow row, XSSFWorkbook workbook) {
        XSSFCell cell = row.createCell(index);
        cell.setCellValue(String.valueOf(value));
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
    }
}
