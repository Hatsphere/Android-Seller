package com.example.yashladha.android_seller.classes

import android.content.Context
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore

/**
 * Created by yashladha on 10/11/17.
 */

class FileUriHelper {
    companion object {
        fun getFileUri(itemUri: Uri?, context: Context): String {
            val wholeId = DocumentsContract.getDocumentId(itemUri)
            val id = wholeId.split(":")[1]
            val filePathColumn = Array(1, { MediaStore.Images.Media.DATA })
            val sel = MediaStore.Images.Media._ID + "=?"
            val cursor = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, filePathColumn, sel, Array(1, { id }), null)
            val columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            if (cursor.moveToFirst()) {
                val filePath = cursor.getString(columnIndex)
                cursor.close()
                return filePath
            }
            return ""

        }
    }
}