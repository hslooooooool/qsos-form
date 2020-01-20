package vip.qsos.form_n.widget.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import vip.qsos.form_n.R

/**
 * @author 华清松
 * @doc 类说明：多选列表容器
 */
class ConditionChoseAdapter internal constructor(
        mContext: Context,
        val data: List<Operation>
) : RecyclerView.Adapter<ConditionChoseAdapter.ViewHolder>() {
    private val inflate: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate.inflate(R.layout.form_item_chose_multiple, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkBox.text = data[position].key
        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = data[position].isCheck
        holder.checkBox.setOnCheckedChangeListener { _, isChecked -> data[position].isCheck = isChecked }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkBox: CheckBox = itemView.findViewById(R.id.chose_multiple_cb)
    }

}
