package vip.qsos.form.normal.utils

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.ParcelFileDescriptor
import java.io.IOException

object DrawableUtils {

    @Throws(IOException::class)
    fun getBitmapFromUri(application: Application, uri: Uri): Bitmap? {
        val parcelFileDescriptor: ParcelFileDescriptor? = application.contentResolver.openFileDescriptor(uri, "r")
        var image: Bitmap? = null
        parcelFileDescriptor?.fileDescriptor?.let {
            image = BitmapFactory.decodeFileDescriptor(it)
        }
        parcelFileDescriptor?.close()
        return image
    }

}