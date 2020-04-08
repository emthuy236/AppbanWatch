package activity

import adapter.adapterphone
import adapter.adapterwatch
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appbanwatch.R
import kotlinx.android.synthetic.main.activity_phone.*
import kotlinx.android.synthetic.main.activity_watch.*
import model.Sanpham
import org.json.JSONArray
import org.json.JSONObject

class Phone : AppCompatActivity() {
    var iddt:Int = 0
    var page:Int = 2
 var arrayphone:ArrayList<Sanpham> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)
        Actiontoolphone()
        Getdataphone(page)
        Getdulieumanhinh()
        load()
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

    private fun load() {
        listphone.setOnItemClickListener{
                parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            var intent: Intent = Intent(this,Chitietsanpham::class.java)
            intent.putExtra("thongtinchitet",arrayphone.get(position))
            startActivity(intent)
        }
    }

    private fun Getdulieumanhinh() {
        iddt = intent.getIntExtra("idloaisanpham",-1)
        Log.d("AAA",iddt.toString() + "")
    }

    private fun Getdataphone(Page:Int) {
        var url = "http://192.168.1.6/service/getsanphamphone.php?page="
        var duongdan:String = url + Page.toString()
        var queue1: RequestQueue = Volley.newRequestQueue(this)
        var stringrequest1 = StringRequest(Request.Method.GET,duongdan, Response.Listener { response ->
            var jsonarraysp: JSONArray = JSONArray(response)
            if (response != null){
                for (i in 0..(jsonarraysp.length()-1)){
                    var jsonobject1: JSONObject = jsonarraysp.getJSONObject(i)
                    var id:Int = jsonobject1.getInt("id")
                    var tendienthoai: String = jsonobject1.getString("tensp")
                    var giadienthoai: Int = jsonobject1.getInt("giasp")
                    var hinhdienthoai: String = jsonobject1.getString("hinhsp")
                    var motadienthoai: String = jsonobject1.getString("motasp")
                    var iddienthoai: Int = jsonobject1.getInt("idsp")
                    arrayphone.add(Sanpham(id,tendienthoai,giadienthoai,hinhdienthoai,motadienthoai,iddienthoai))

                }
                //  listwatch.adapter = adapterwatch(this,arrayWatch)
            }
            listphone.adapter = adapterphone(this,arrayphone)
        }, Response.ErrorListener {  })

        var hashMap
                = HashMap<String, String> ()
        hashMap.put("idsanpham",iddt.toString())

        queue1.add(stringrequest1)
    }

    private fun Actiontoolphone() {
        setSupportActionBar(findViewById(R.id.toolphone))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolphone.setNavigationOnClickListener {
            finish()
        }
    }
}
