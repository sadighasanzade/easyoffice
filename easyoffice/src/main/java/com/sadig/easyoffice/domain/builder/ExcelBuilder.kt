package com.sadig.easyoffice.domain.builder

import android.content.Context
import com.sadig.easyoffice.di.Inject
import java.io.IOException

class ExcelBuilder(private val context: Context) {
    private var fileName: String = "default_name"
    private var data: HashMap<String, List<String>> = HashMap()

    fun setFileName(name: String): ExcelBuilder {
        this.fileName = name
        return this
    }

    fun setData(data: HashMap<String, List<String>>): ExcelBuilder {
        this.data = data
        return this
    }

    @Throws(IOException::class)
    fun build(): String{
        val converter = Inject.provideConvertToExcelUseCase(data)
        val filledWorkbook = converter()
        val saver = Inject.provideSaveExcelUseCase(context, filledWorkbook, fileName)

        return saver()
    }
}