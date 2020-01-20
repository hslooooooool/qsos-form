package vip.qsos.form_n.adapter

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.form_item_file_item.view.*
import vip.qsos.form_lib.base.BaseHolder
import vip.qsos.form_lib.model.FormValueEntity
import vip.qsos.form_n.R
import vip.qsos.form_n.model.FormValueOfFile
import vip.qsos.form_n.utils.FormValueUtil

/**
 * @author : 华清松
 * 文件列表容器
 */
class FormFileAdapter(
        var data: ArrayList<FormValueEntity>
) : RecyclerView.Adapter<BaseHolder<FormValueEntity>>() {

    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<FormValueEntity> {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.form_item_file_item, parent, false)
        return FormItemFileItemHolder(view)
    }

    override fun onBindViewHolder(holder: BaseHolder<FormValueEntity>, position: Int) {
        holder.setData(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

/**
 * @author : 华清松
 * 文件布局
 */
class FormItemFileItemHolder(itemView: View) : BaseHolder<FormValueEntity>(itemView) {

    override fun setData(data: FormValueEntity, position: Int) {
        FormValueUtil.getValue(data.value, FormValueOfFile::class.java)?.let { file ->
            file.fileCover?.let {
                itemView.iv_item_form_file_icon.setTag(itemView.iv_item_form_file_icon.id, file.fileCover)
            }
            val tag = itemView.iv_item_form_file_icon.getTag(itemView.iv_item_form_file_icon.id) as String?
            if (tag == file.fileCover) {
                file.fileCover?.let {

                }
                when (FormValueOfFile.getFileTypeByMime(file.fileType)) {
                    "IMAGE" -> {
                        if (TextUtils.isEmpty(file.fileCover)) {
                            itemView.iv_item_form_file_icon.setImageDrawable(
                                    AppCompatResources.getDrawable(itemView.context, R.drawable.form_take_image)
                            )
                        } else {
                            itemView.iv_item_form_file_icon.setImageURI(Uri.parse(file.fileCover))
                        }
                    }
                    "VIDEO" -> {
                        if (TextUtils.isEmpty(file.fileCover)) {
                            itemView.iv_item_form_file_icon.setImageDrawable(
                                    AppCompatResources.getDrawable(itemView.context, R.drawable.form_take_video)
                            )
                        } else {
                            itemView.iv_item_form_file_icon.setImageURI(Uri.parse(file.fileCover))
                        }
                    }
                    "AUDIO" -> {
                        if (TextUtils.isEmpty(file.fileCover)) {
                            itemView.iv_item_form_file_icon.setImageDrawable(
                                    AppCompatResources.getDrawable(itemView.context, R.drawable.form_take_audio)
                            )
                        } else {
                            itemView.iv_item_form_file_icon.setImageURI(Uri.parse(file.fileCover))
                        }
                    }
                    else -> {
                        if (TextUtils.isEmpty(file.fileCover)) {
                            itemView.iv_item_form_file_icon.setImageDrawable(
                                    AppCompatResources.getDrawable(itemView.context, R.drawable.form_take_file)
                            )
                        } else {
                            itemView.iv_item_form_file_icon.setImageURI(Uri.parse(file.fileCover))
                        }
                    }
                }
            }

            itemView.tv_item_form_file_name.text = file.fileName

            itemView.iv_item_form_file_icon.setOnClickListener {
            }
            itemView.iv_item_form_file_delete.setOnClickListener {
            }

        }
    }
}

