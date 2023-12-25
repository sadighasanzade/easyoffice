package com.sadig.easyoffice.domain.usecase

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class SaveExcelFileUseCase(
    private val context: Context,
    private val workbook: HSSFWorkbook,
    private val fileName: String,
) {
    operator fun invoke(): String{
        val file = File(context.getExternalFilesDir(null), fileName)
        var fileOutputStream: FileOutputStream? = null

        try{
            fileOutputStream = FileOutputStream(file)
            workbook.write(fileOutputStream)

        } catch (e: IOException) {
            Log.e(TAG, "Error writing Exception: ", e);
        } catch (e: Exception) {
            Log.e(TAG, "Failed to save file due to Exception: ", e);
        } finally {
            fileOutputStream?.close()
        }
        return file.absolutePath
    }

    companion object {
        const val TAG = "SAVE_EXCEL"
    }
}