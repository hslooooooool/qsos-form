package vip.qsos.form.normal.hodler

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.form_item_file.view.*
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.lib.model.ValueEntity
import vip.qsos.form.normal.adapter.FormFileAdapter
import vip.qsos.form.normal.adapter.FormItemFileItemHolder
import vip.qsos.lib.select.OnSelectListener

/**文件类型视图
 * @author : 华清松
 */
abstract class AbsFormItemFileHolder(
        itemView: View
) : AbsFormHolder(itemView) {

    /**点击文件添加后方法调用*/
    abstract fun takeFile(type: ValueEntity.Type, data: FormItemEntity, listener: OnSelectListener<Boolean>)

    /**点击文件封面后方法调用*/
    abstract fun clickFile(position: Int, data: FormValueEntity)

    override fun setData(position: Int, data: FormItemEntity) {
        super.setData(position, data)
        itemView.form_item_file_take_camera.visibility = View.GONE
        itemView.form_item_file_take_album.visibility = View.GONE
        itemView.form_item_file_take_video.visibility = View.GONE
        itemView.form_item_file_take_audio.visibility = View.GONE
        itemView.form_item_file_take_file.visibility = View.GONE

        data.limitTypeList?.forEach {
            when (ValueEntity.getFileTypeByMime(it)) {
                ValueEntity.Type.IMAGE -> {
                    itemView.form_item_file_take_camera.visibility = View.VISIBLE
                    itemView.form_item_file_take_album.visibility = View.VISIBLE
                }
                ValueEntity.Type.VIDEO -> itemView.form_item_file_take_video.visibility = View.VISIBLE
                ValueEntity.Type.AUDIO -> itemView.form_item_file_take_audio.visibility = View.VISIBLE
                else -> {
                    itemView.form_item_file_take_camera.visibility = View.VISIBLE
                    itemView.form_item_file_take_album.visibility = View.VISIBLE
                    itemView.form_item_file_take_video.visibility = View.VISIBLE
                    itemView.form_item_file_take_audio.visibility = View.VISIBLE
                    itemView.form_item_file_take_file.visibility = View.VISIBLE
                }
            }
        }

        itemView.rv_item_form_files.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
        itemView.rv_item_form_files.adapter = FormFileAdapter(
                data.formValues,
                object : FormItemFileItemHolder.OnItemListener {
                    override fun item(position: Int, data: FormValueEntity) {
                        clickFile(position, data)
                    }
                },
                object : FormItemFileItemHolder.OnDeleteListener {
                    override fun delete(position: Int) {
                        itemView.rv_item_form_files.adapter?.notifyDataSetChanged()
                    }
                }
        )

        val size = data.formValues.size
        val limitMax = data.limitMax
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
                takeFile(ValueEntity.Type.IMAGE, data, listener)
            }
        }
        itemView.form_item_file_take_album.setOnClickListener {
            if (canAdd) {
                takeFile(ValueEntity.Type.ALBUM, data, listener)
            }
        }
        itemView.form_item_file_take_video.setOnClickListener {
            if (canAdd) {
                takeFile(ValueEntity.Type.VIDEO, data, listener)
            }
        }
        itemView.form_item_file_take_audio.setOnClickListener {
            if (canAdd) {
                takeFile(ValueEntity.Type.AUDIO, data, listener)
            }
        }
        itemView.form_item_file_take_file.setOnClickListener {
            if (canAdd) {
                takeFile(ValueEntity.Type.FILE, data, listener)
            }
        }

    }
}