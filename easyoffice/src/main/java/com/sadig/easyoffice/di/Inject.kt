package com.sadig.easyoffice.di

import android.content.Context
import com.sadig.easyoffice.domain.usecase.ConvertToExcelUseCase
import com.sadig.easyoffice.domain.usecase.SaveExcelFileUseCase
import org.apache.poi.hssf.usermodel.HSSFDataFormat
import org.apache.poi.hssf.usermodel.HSSFWorkbook

class Inject() {
    companion object {
        fun provideConvertToExcelUseCase(
            data: HashMap<String, List<String>>
        ): ConvertToExcelUseCase {
            return ConvertToExcelUseCase(data)
        }

        fun provideSaveExcelUseCase(
            context: Context,
            workbook: HSSFWorkbook,
            fileName: String
        ): SaveExcelFileUseCase {
            return SaveExcelFileUseCase(context, workbook, fileName)
        }
    }
}