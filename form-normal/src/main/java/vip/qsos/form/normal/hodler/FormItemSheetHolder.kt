package vip.qsos.form.normal.hodler

import android.view.View
import kotlinx.android.synthetic.main.form_item_sheet.view.*
import vip.qsos.form.lib.callback.OnTListener
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.normal.widgets.SheetView

/**表格类型视图
 * @author : 华清松
 */
class FormItemSheetHolder(itemView: View) : AbsFormHolder(itemView) {

    override fun setData(position: Int, data: FormItemEntity) {
        super.setData(position, data)

        val sheet = data.formValues.map {
            SheetView.Sheet(
                    type = it.value!!.sheetType,
                    title = it.value!!.sheetTitle,
                    value = it.value!!.sheetContent,
                    notice = it.value!!.sheetNotice,
                    position = it.value!!.sheetPosition
            )
        }
        itemView.item_form_sheet.setData(sheet)
        itemView.item_form_sheet.addValueListener(object : OnTListener<SheetView.Sheet> {
            override fun back(t: SheetView.Sheet) {
                data.formValues.find {
                    it.value?.sheetPosition == t.position
                }?.let {
                    it.value?.sheetContent = t.value
                }
            }
        })
    }
}