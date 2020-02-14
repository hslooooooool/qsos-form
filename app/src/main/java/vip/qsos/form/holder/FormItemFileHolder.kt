package vip.qsos.form.holder

import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import vip.qsos.filepicker.lib.FilePicker
import vip.qsos.filepicker.lib.OnTListener
import vip.qsos.filepicker.lib.Sources
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.normal.hodler.AbsFormItemFileHolder
import vip.qsos.form.normal.model.FormValueOfFile
import vip.qsos.lib.select.OnSelectListener

/**
 * @author : 华清松
 *
 * 文件类型视图
 */
class FormItemFileHolder(
        itemView: View
) : AbsFormItemFileHolder(itemView) {

    override fun takeFile(type: FormValueOfFile.Type, data: FormItemEntity<FormValueOfFile>, listener: OnSelectListener<Boolean>) {
        val size = data.formValues!!.size
        val limitMax = data.limitMax ?: 0
        val activity = itemView.context as AppCompatActivity
        val value = FormValueEntity<FormValueOfFile>()
        if (limitMax > 0 && size < limitMax) {
            when (type) {
                FormValueOfFile.Type.IMAGE -> {
                    FilePicker.with(activity.supportFragmentManager).takeImage(Sources.DEVICE, listener = object : OnTListener<Uri> {
                        override fun back(t: Uri) {
                            value.value = FormValueOfFile(fileUrl = t.toString())
                            data.formValues!!.add(value)
                            listener.select(true)
                        }
                    })
                }
                FormValueOfFile.Type.ALBUM -> {
                    FilePicker.with(activity.supportFragmentManager).takeImage(Sources.ONE, listener = object : OnTListener<Uri> {
                        override fun back(t: Uri) {
                            value.value = FormValueOfFile(fileUrl = t.toString())
                            data.formValues!!.add(value)
                            listener.select(true)
                        }
                    })
                }
                FormValueOfFile.Type.AUDIO -> {
                    FilePicker.with(activity.supportFragmentManager).takeAudio(listener = object : OnTListener<Uri> {
                        override fun back(t: Uri) {
                            value.value = FormValueOfFile(fileUrl = t.toString())
                            data.formValues!!.add(value)
                            listener.select(true)
                        }
                    })
                }
                FormValueOfFile.Type.VIDEO -> {
                    FilePicker.with(activity.supportFragmentManager).takeVideo(listener = object : OnTListener<Uri> {
                        override fun back(t: Uri) {
                            value.value = FormValueOfFile(fileUrl = t.toString())
                            data.formValues!!.add(value)
                            listener.select(true)
                        }
                    })
                }
                FormValueOfFile.Type.FILE -> {
                    FilePicker.with(activity.supportFragmentManager).takeFile(listener = object : OnTListener<Uri> {
                        override fun back(t: Uri) {
                            value.value = FormValueOfFile(fileUrl = t.toString())
                            data.formValues!!.add(value)
                            listener.select(true)
                        }
                    })
                }
            }
        } else {
            listener.select(false)
        }
    }

    override fun clickFile(position: Int, data: FormValueEntity<FormValueOfFile>) {
        Toast.makeText(itemView.context, data.value?.fileName, Toast.LENGTH_SHORT).show()
    }

}