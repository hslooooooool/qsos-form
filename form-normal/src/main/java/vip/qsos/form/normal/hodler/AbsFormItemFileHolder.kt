package vip.qsos.form.normal.hodler

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.form_item_file.view.*
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.normal.adapter.FormFileAdapter
import vip.qsos.form.normal.adapter.FormItemFileItemHolder
import vip.qsos.form.normal.model.FormValueOfFile
import vip.qsos.lib.select.OnSelectListener

/**
 * @author : 华清松
 *
 * 文件类型视图
 */
abstract class AbsFormItemFileHolder(
        itemView: View
) : AbsFormHolder<FormItemEntity<FormValueOfFile>, FormValueOfFile>(itemView) {

    /**点击文件添加后方法调用*/
    abstract fun takeFile(type: FormValueOfFile.Type, data: FormItemEntity<FormValueOfFile>, listener: OnSelectListener<Boolean>)

    /**点击文件封面后方法调用*/
    abstract fun clickFile(position: Int, data: FormValueEntity<FormValueOfFile>)

    override fun setData(position: Int, data: FormItemEntity<FormValueOfFile>) {
        super.setData(position, data)
        itemView.form_item_file_take_camera.visibility = View.GONE
        itemView.form_item_file_take_album.visibility = View.GONE
        itemView.form_item_file_take_video.visibility = View.GONE
        itemView.form_item_file_take_audio.visibility = View.GONE
        itemView.form_item_file_take_file.visibility = View.GONE

        data.limitTypeList?.forEach {
            when (FormValueOfFile.getFileTypeByMime(it)) {
                FormValueOfFile.Type.IMAGE -> {
                    itemView.form_item_file_take_camera.visibility = View.VISIBLE
                    itemView.form_item_file_take_album.visibility = View.VISIBLE
                }
                FormValueOfFile.Type.VIDEO -> itemView.form_item_file_take_video.visibility = View.VISIBLE
                FormValueOfFile.Type.AUDIO -> itemView.form_item_file_take_audio.visibility = View.VISIBLE
                else -> {
                    itemView.form_item_file_take_camera.visibility = View.VISIBLE
                    itemView.form_item_file_take_album.visibility = View.VISIBLE
                    itemView.form_item_file_take_video.visibility = View.VISIBLE
                    itemView.form_item_file_take_audio.visibility = View.VISIBLE
                    itemView.form_item_file_take_file.visibility = View.VISIBLE
                }
            }
        }

        itemView.rv_item_form_files.layoutManager = GridLayoutManager(itemView.context, 4)
        itemView.rv_item_form_files.adapter = FormFileAdapter(
                data.formValues!!,
                object : FormItemFileItemHolder.OnItemListener {
                    override fun item(position: Int, data: FormValueEntity<FormValueOfFile>) {
                        clickFile(position, data)
                    }
                },
                object : FormItemFileItemHolder.OnDeleteListener {
                    override fun delete(position: Int) {
                        itemView.rv_item_form_files.adapter?.notifyDataSetChanged()
                    }
                }
        )

        val size = data.formValues!!.size
        val limitMax = data.limitMax ?: 0
        val canAdd = limitMax == 0 || size < limitMax
        val listener = object : OnSelectListener<Boolean> {
            override fun select(data: Boolean) {
                if (data) {
                    itemView.rv_item_form_files.adapter?.notifyDataSetChanged()
                }
            }
        }
        itemView.form_item_file_take_camera.setOnClickListener {
            if (canAdd) {
                takeFile(FormValueOfFile.Type.IMAGE, data, listener)
            }
        }
        itemView.form_item_file_take_album.setOnClickListener {
            if (canAdd) {
                takeFile(FormValueOfFile.Type.ALBUM, data, listener)
            }
        }
        itemView.form_item_file_take_video.setOnClickListener {
            if (canAdd) {
                takeFile(FormValueOfFile.Type.VIDEO, data, listener)
            }
        }
        itemView.form_item_file_take_audio.setOnClickListener {
            if (canAdd) {
                takeFile(FormValueOfFile.Type.AUDIO, data, listener)
            }
        }
        itemView.form_item_file_take_file.setOnClickListener {
            if (canAdd) {
                takeFile(FormValueOfFile.Type.FILE, data, listener)
            }
        }

    }
}