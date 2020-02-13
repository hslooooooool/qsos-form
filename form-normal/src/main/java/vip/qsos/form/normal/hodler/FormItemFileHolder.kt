package vip.qsos.form.normal.hodler

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.form_item_file.view.*
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.normal.adapter.FormFileAdapter
import vip.qsos.form.normal.model.FormValueOfFile

/**
 * @author : 华清松
 *
 * 文件类型视图
 */
class FormItemFileHolder(
        itemView: View
) : BaseFormHolder<FormItemEntity<FormValueOfFile>, FormValueOfFile>(itemView) {

    override fun setData(position: Int, data: FormItemEntity<FormValueOfFile>) {
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
        itemView.rv_item_form_files.adapter = FormFileAdapter(data.formValues!!)

        itemView.form_item_file_take_camera.setOnClickListener {
        }
        itemView.form_item_file_take_album.setOnClickListener {
        }
        itemView.form_item_file_take_video.setOnClickListener {
        }
        itemView.form_item_file_take_audio.setOnClickListener {
        }
        itemView.form_item_file_take_file.setOnClickListener {
        }

    }
}