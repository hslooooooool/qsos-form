package vip.qsos.form_n.widget.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import vip.qsos.form_lib.callback.OnTListener
import vip.qsos.form_n.R

/**
 * @author 华清松
 * @doc 类说明：单选列表容器
 */
class OperationAdapter internal constructor(
        private val mContext: Context,
        private val operations: List<Operation>?
) : RecyclerView.Adapter<OperationAdapter.RecyclerViewHolder>() {

    private var onTListener: OnTListener<Operation>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.form_item_operation, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerViewHolder, position: Int) {
        viewHolder.operationTv.tag = position
        viewHolder.operationTv.text = operations!![position].key

        val drawable = ContextCompat.getDrawable(mContext, operations[position].iconId)

        if (drawable != null) {
            // 必须设置图片大小，否则不显示
            drawable.setBounds(0, 0, 40, 40)
            viewHolder.operationTv.setCompoundDrawables(drawable, null, null, null)
        }
        viewHolder.operationTv.setOnClickListener {
            if (onTListener != null) {
                onTListener!!.back(operations[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return operations?.size ?: 0
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val operationTv: TextView = itemView.findViewById(R.id.operation_tv)
    }

    fun setOnItemClickListener(onTListener: OnTListener<Operation>) {
        this.onTListener = onTListener
    }
}