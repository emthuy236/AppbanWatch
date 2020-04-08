package activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appbanwatch.R
import kotlinx.android.synthetic.main.activity_lienhe.*
import kotlinx.android.synthetic.main.activity_thong_tin.*

class Lienhe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lienhe)
        Actionbar()
    }

    private fun Actionbar() {
        setSupportActionBar(findViewById(R.id.toollienhe))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toollienhe.setNavigationOnClickListener {
            finish()
        }
    }
}
