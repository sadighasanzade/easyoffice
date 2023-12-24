package com.sadig.easyoffice.domain.usecase

import org.apache.poi.hssf.usermodel.HSSFWorkbook

class ConvertToExcelUseCase(
    private val data: HashMap<String, List<String>>
) {
    operator fun invoke(): HSSFWorkbook {
        val workbook = HSSFWorkbook()
        val sheet = workbook.createSheet()

        // create rows
        for (i in 0..data.size) {
            sheet.createRow(i)
        }

        // headers
        val row = sheet.getRow(0)
        for ((cellCount, key) in data.keys.withIndex()) {
            val cell = row.createCell(cellCount)
            cell.setCellValue(key)
        }

        // set values row by row
        for ((count, value) in data.values.withIndex()) {
            for ((index, cellValue) in value.withIndex()) {
                val row = sheet.getRow(index + 1)
                val cell = row.createCell(count)
                cell.setCellValue(cellValue)
            }
        }

        return workbook
    }
}