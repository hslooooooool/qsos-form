package vip.qsos.form.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import qsos.core.form.db.entity.Value
import vip.qsos.form.model.dao.FormDao
import vip.qsos.form.model.dao.FormItemDao
import vip.qsos.form.model.dao.FormItemValueDao
import vip.qsos.form.model.entity.FormEntity
import vip.qsos.form.model.entity.FormItem

/**
 * @author : 华清松
 * 表单数据库，当前版本【1】
 */
@Database(
        version = 1,
        entities = [
            FormEntity::class,
            FormItem::class,
            Value::class
        ]
)
abstract class FormDatabase : RoomDatabase() {

    abstract val formDao: FormDao

    abstract val formItemDao: FormItemDao

    abstract val formItemValueDao: FormItemValueDao

    companion object {

        private var DB_NAME = "${FormDatabase::class.java.simpleName}.db"

        private var INSTANCE: FormDatabase? = null

        fun create(context: Context): FormDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context, FormDatabase::class.java, DB_NAME).build()
                        .also { INSTANCE = it }
            }
        }

    }
}