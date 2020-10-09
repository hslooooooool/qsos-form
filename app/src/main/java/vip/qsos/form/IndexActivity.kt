package vip.qsos.form

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.index_activity.*

/**
 * @author : 华清松
 */
class IndexActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.index_activity)

        form_item_1.setOnClickListener {
            start("1")
        }
        form_item_2.setOnClickListener {
            start("2")
        }
        form_item_3.setOnClickListener {
            start("3")
        }
        form_item_4.setOnClickListener {
            start("4")
        }

    }

    private fun start(v: String) {
        val sIntent = Intent(this, FormActivity::class.java).apply {
            putExtra("sceneType", v)
        }
        startActivity(sIntent)
    }
}