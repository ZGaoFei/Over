package com.example.overall.bitmap

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Log
import com.example.base.base.BaseActivity
import com.example.overall.R
import com.example.overall.databinding.ActivityBitmapTestBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class BitmapTestActivity : BaseActivity() {
    private lateinit var bindingView: ActivityBitmapTestBinding

    override fun bindingView() {
        super.bindingView()

        bindingView = ActivityBitmapTestBinding.inflate(layoutInflater)
        setContentView(bindingView.root)
    }

    override fun initView() {
        super.initView()

        getBitmap()
    }

    private fun getBitmap() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.logo)
        bindingView.ivOriginal.setImageBitmap(bitmap)
        Log.e("zgf", "==zgf======aaa======= ${bitmap.byteCount} ==== ${bitmap.allocationByteCount} ==== ${bmpToByteArray(bitmap, false)?.size}")

        val options = BitmapFactory.Options()
        options.inSampleSize = 4
        val compress = BitmapFactory.decodeResource(resources, R.drawable.logo, options)
        bindingView.ivCompress.setImageBitmap(compress)
        Log.e("zgf", "==zgf======bbb======= ${compress.byteCount} ==== ${compress.allocationByteCount} ==== ${bmpToByteArray(compress, false)?.size}")

        val absolutePath = cacheDir.absoluteFile.absolutePath
        val fileName = "${System.currentTimeMillis()}_test.jpg"
        Log.e("zgf", "==zgf========111========== $absolutePath ==== $fileName")
        compress(compress, CompressFormat.JPEG, 50, absolutePath, fileName)
        val bitmap1 = convertFileToBitmap("$absolutePath/$fileName")
        Log.e("zgf", "==zgf========111========== $bitmap1")
        bindingView.ivCompressQuality.setImageBitmap(bitmap1)
        Log.e("zgf", "==zgf======ccc======= ${bitmap1?.byteCount} ==== ${bitmap1?.allocationByteCount} ==== ${bitmap1?.let {
            bmpToByteArray(
                it, false)?.size
        }}")

        val matrix = Matrix()
        matrix.setScale(0.5f, 0.5f)
        val bitmap2 = Bitmap.createBitmap(compress)
        bindingView.ivCompressMatrix.setImageBitmap(bitmap2)
        Log.e("zgf", "==zgf======ddd======= ${bitmap2?.byteCount} ==== ${bitmap2?.allocationByteCount} ==== ${bmpToByteArray(bitmap2, false)?.size}")
    }

    private fun compress(
        bitmap: Bitmap,
        format: CompressFormat,
        quality: Int,
        resultFilePath: String,
        resultFileName: String
    ) {
        val bos = ByteArrayOutputStream()
        bitmap.compress(format, quality, bos)
        try {
            val fos = FileOutputStream(File(resultFilePath, resultFileName))
            fos.write(bos.toByteArray())
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun convertFileToBitmap(filePath: String?): Bitmap? {
        var fis: FileInputStream? = null
        return try {
            fis = FileInputStream(filePath)
            BitmapFactory.decodeStream(fis)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            try {
                fis?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun bmpToByteArray(bmp: Bitmap, needRecycle: Boolean): ByteArray? {
        val output = ByteArrayOutputStream()
        bmp.compress(CompressFormat.JPEG, 100, output)
        if (needRecycle) {
            bmp.recycle()
        }
        val result = output.toByteArray()
        try {
            output.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return result
    }
}