package activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appbanwatch.R
import com.google.android.gms.common.api.Response
import kotlinx.android.synthetic.main.activity_thongtinkhachhang.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Thongtinkhachhang : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thongtinkhachhang)
        btnBack.setOnClickListener {
            finish()
        }
        Evenbutton()

    }


    private fun Evenbutton() {
        btnxacnhan.setOnClickListener {

            var ten: String = edtname.text.toString().trim()
            var email: String = edtemail.text.toString().trim()
            var sdt: String = edtSdt.text.toString().trim()

            if (ten.length > 0 && email.length > 0 && sdt.length > 0) {
                var url:String = "http://192.168.1.6/service/thongtinkhachhang.php"
                var queue: RequestQueue = Volley.newRequestQueue(this)
                var string = object : StringRequest(Request.Method.POST,url, com.android.volley.Response.Listener{response ->
                    Log.d("madonhang",response)
                    if (response != null){
                        var url1 = "http://192.168.1.6/service/chitietdonhang.php"
                        var queue1:RequestQueue = Volley.newRequestQueue(this)
                        var string1 = object : StringRequest(Request.Method.POST,url1,com.android.volley.Response.Listener { response1 ->
                            if (response1.equals("1")){
                               MainActivity.manggiohang?.clear()
                                Toast.makeText(this,"Lưu dữ liệu thành công",Toast.LENGTH_SHORT).show()
                                var intent:Intent = Intent(this,MainActivity::class.java)
                                startActivity(intent)

                            }else{
                                Toast.makeText(this,"dữ liệu của bạn bị lỗi",Toast.LENGTH_SHORT).show()
                            }

                        },com.android.volley.Response.ErrorListener {

                        }){ @Override
                            override fun getParams(): MutableMap<String, String> {
                                var jsonarray:JSONArray = JSONArray()
                                for (i in 0..(MainActivity.manggiohang?.size!! -1)){
                                    var jsonobject:JSONObject = JSONObject()
                                    try {
                                        jsonobject.put("madonhang",response)
                                        jsonobject.put(
                                            "masanpham",
                                            MainActivity.manggiohang?.get(i)!!.idSP
                                        )
                                        jsonobject.put(
                                            "tensanpham",
                                            MainActivity.manggiohang?.get(i)!!.tenSP
                                        )
                                        jsonobject.put(
                                            "giasanpham",
                                            MainActivity.manggiohang?.get(i)!!.giaSP
                                        )
                                        jsonobject.put(
                                            "soluongsanpham",
                                            MainActivity.manggiohang?.get(i)!!.soluongsp
                                        )
                                    }catch (e: JSONException){
                                        e.printStackTrace()
                                    }
                                    jsonarray.put(jsonobject)
                                }
                                var hasmap = HashMap<String,String>()
                                hasmap.put("json",jsonarray.toString())
                                return hasmap
                            }
                        }
                        queue1.add(string1)
                    }

                }, com.android.volley.Response.ErrorListener {

                }){
                    @Override
                    override fun getParams(): MutableMap<String, String> {
                        var hashMap = HashMap<String, String> ()
                        hashMap.put("tenkhachang",ten)
                        hashMap.put("email1",email)
                        hashMap.put("sodienthoai1",sdt)
                        return hashMap
                    }
                }


                queue.add(string)

            }
        }
    }
}