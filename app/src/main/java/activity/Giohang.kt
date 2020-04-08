package activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.appbanwatch.R
import kotlinx.android.synthetic.main.activity_giohang.*
import kotlinx.android.synthetic.main.activity_phone.*
import model.adaptergiohang
import model.giohang
import java.text.DecimalFormat
import kotlin.math.E

class Giohang : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giohang)
        Actionn()
        Checkdata()
        listgiohang.adapter = adaptergiohang(this,MainActivity.manggiohang!!)
        txtongtien = findViewById(R.id.txttongtien)
        Evenutil()
       Catchonxoa()
        EventButton()

    }

    private fun EventButton() {
        tieptucmua.setOnClickListener {
            var intent:Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        thanhtoan.setOnClickListener {
            if (MainActivity.manggiohang?.size!! > 0){
                var intent:Intent = Intent(this,Thongtinkhachhang::class.java)
                startActivity(intent)
            }else{
                Log.d("AAA","Giỏ hàng của bạn đang trống!")
            }
        }
    }


    private fun Catchonxoa() {

        listgiohang.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { parent, view, position, id ->
                val builder =
                    AlertDialog.Builder(this)
                builder.setTitle("Xác nhận xóa sản phẩm")
                builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này?")
                builder.setIcon(R.mipmap.ic_launcher)
                builder.setPositiveButton("có"){dialog: DialogInterface?, which: Int ->
                    if (MainActivity.manggiohang?.size!! <=0){
                        txthongbao.visibility = View.VISIBLE
                    }else{
                        MainActivity.manggiohang?.removeAt(position)
                        Evenutil()
                        if (MainActivity.manggiohang?.size!! <= 0){
                            txthongbao.visibility = View.VISIBLE
                        }else{
                            txthongbao.visibility = View.INVISIBLE
                            Evenutil()
                        }
                    }
                }

                builder.setNegativeButton("không"){dialog: DialogInterface?, which: Int ->
                    Evenutil()
                }
                builder.show()
                return@OnItemLongClickListener true
            }
    }


    companion object {
        var txtongtien:TextView? = null
        fun Evenutil() {
            var tongtien:Long = 0
            for (i in 0..(MainActivity.manggiohang?.size!! -1)){
                tongtien += MainActivity.manggiohang?.get(i)!!.giaSP
            }
            var decimal: DecimalFormat = DecimalFormat("###,###,###")
            txtongtien!!.setText("Giá: " + decimal.format(tongtien).replace(",",".") + "Đ")
        }
    }

//    fun Evenutil() {
//        var tongtien:Long = 0
//        for (i in 0..(MainActivity.manggiohang?.size!! -1)){
//            tongtien += MainActivity.manggiohang?.get(i)!!.giaSP
//        }
//        var decimal: DecimalFormat = DecimalFormat("###,###,###")
//            txttongtien.setText("Giá: " + decimal.format(tongtien).replace(",",".") + "Đ")
//    }

    private fun Checkdata() {
        if (MainActivity.manggiohang?.size!! <= 0){
            txthongbao.visibility = View.VISIBLE
            listgiohang.visibility = View.INVISIBLE
        }else{
            txthongbao.visibility = View.INVISIBLE
            listgiohang.visibility =  View.VISIBLE
        }
    }

    private fun Actionn() {
        setSupportActionBar(findViewById(R.id.toolgiohang))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolgiohang.setNavigationOnClickListener {
            finish()
        }
    }
}
