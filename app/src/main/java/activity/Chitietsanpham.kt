package activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.appbanwatch.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_chitietsanpham.*
import kotlinx.android.synthetic.main.activity_phone.*
import model.Sanpham
import model.giohang
import java.text.DecimalFormat

class Chitietsanpham : AppCompatActivity() {
   var spinn:Spinner? = null
    var id:Int = 0
    var tennew:String = ""
    var gianew:Int = 0
    var hinhnew:String = ""
    var motanew:String = ""
    var idnew:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chitietsanpham)
        Actiontol()
        Getinformation()
        Catch()
        Catchevent()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_giohang,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.giohang1 -> {var intent:Intent = Intent(this,Giohang::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun Catchevent() {
        btnthem.setOnClickListener {
            var intent:Intent = Intent(this,Giohang::class.java)
            startActivity(intent)
            if (MainActivity.manggiohang?.size!! > 0){
                var exit:Boolean = false
                var sl: Int = spin.selectedItem.toString().toInt()
                for (i in 0..(MainActivity.manggiohang?.size!! -1)){
                    if (MainActivity.manggiohang?.get(i)!!.idSP == id ){
                        MainActivity.manggiohang?.get(i)!!.soluongsp = sl
                        if (MainActivity.manggiohang?.get(i)!!.soluongsp >=10){
                            MainActivity.manggiohang?.get(i)!!.soluongsp = 10
                        }
                        MainActivity.manggiohang?.get(i)!!.giaSP = (MainActivity.manggiohang?.get(i)!!.soluongsp * gianew).toLong()
                        exit =true
                    }
                }
                if (exit == false){
                    var soluong: Int = spin.selectedItem.toString().toInt()
                    var giamoi:Long = (soluong * gianew).toLong()
                    MainActivity.manggiohang?.add(giohang(id,tennew,giamoi,hinhnew,soluong))
                }
            }
            else{
                var soluong: Int = spin.selectedItem.toString().toInt()
                var giamoi:Long = (soluong * gianew).toLong()
                MainActivity.manggiohang?.add(giohang(id,tennew,giamoi,hinhnew,soluong))
            }
        }
    }

    private fun Catch() {
        spinn = findViewById(R.id.spin)
        var soluong: Array<Int> = arrayOf(1,2,3,4,5,6,7,8,9,10)
        var aray:ArrayAdapter<Int> = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,soluong)
       spin.adapter = aray
    }

    private fun Getinformation() {
        var spnew:Sanpham = intent.getSerializableExtra("thongtinchitet") as Sanpham
         id = spnew.id
         tennew= spnew.tenSp
         gianew = spnew.giaSp
         hinhnew = spnew.hinhSp
         motanew = spnew.motaSp
        idnew = spnew.idSp
        txtenchitiet.setText(tennew)
        var decimal: DecimalFormat = DecimalFormat("###,###,###")
        txgiachitiet.setText("Giá: " + decimal.format(gianew).replace(",",".") + "Đ")
        txtmotachitiet.setText(motanew)
        Picasso.with(this).load(hinhnew)
            .placeholder(R.drawable.load)
            .error(R.drawable.error)
            .into(imgchitiet)
    }

    private fun Actiontol() {
        setSupportActionBar(findViewById(R.id.toolchitiet))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolchitiet.setNavigationOnClickListener {
            finish()
        }
    }
}
