package vip.qsos.form_n.widget.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import vip.qsos.form_n.R

/**
 * @author 华清松
 * @doc 类说明：多选列表容器
 * @param limitMax 最多可选限制
 */
class ConditionChoseAdapter constructor(
        mContext: Context,
        val data: List<Operation>,
        private var checkedNum: Int,
        private val limitMax: Int
) : RecyclerView.Adapter<ConditionChoseAdapter.ViewHolder>() {

    private val inflate: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflate.inflate(R.layout.form_item_chose_multiple, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkBox.text = data[position].key
        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = data[position].isCheck
        holder.checkBox.setOnCheckedChangeListener { v, isChecked ->
            data[position].isCheck = isChecked
            if (isChecked) checkedNum++ else checkedNum--

            if (limitMax in 1 until checkedNum) {
                /**已达到最大可选数量,屏蔽所有未选项按钮点击*/
                Toast.makeText(holder.itemView.context, "已达最大可选限制", Toast.LENGTH_SHORT).show()
                v.isChecked = false
                data[position].isCheck = false
                checkedNum--
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkBox: CheckBox = itemView.findViewById(R.id.chose_multiple_cb)
    }

}
