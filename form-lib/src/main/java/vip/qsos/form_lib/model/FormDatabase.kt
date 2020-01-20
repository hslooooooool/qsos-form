package vip.qsos.form_lib.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @author : 华清松
 * 表单数据库，当前版本【1】
 */
@Database(
        version = 1,
        entities = [
            FormEntity::class,
            FormItemEntity::class,
            FormValueEntity::class
        ]
)
abstract class FormDatabase : RoomDatabase() {

    abstract val formDao: FormDao

    abstract val formItemDao: FormItemDao

    abstract val formValueDao: FormValueDao

    companion object {

        private var DB_NAME = "${FormDatabase::class.java.simpleName}.db"

        var INSTANCE: FormDatabase? = null

        fun create(context: Context): FormDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context, FormDatabase::class.java, DB_NAME).build()
                        .also { INSTANCE = it }
            }
        }

    }
}