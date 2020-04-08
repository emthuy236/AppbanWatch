package activity

import adapter.adapterSanpham
import adapter.adapterwatch
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.core.view.GravityCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appbanwatch.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_watch.*
import model.Sanpham
import org.json.JSONArray
import org.json.JSONObject

class Watch : AppCompatActivity() {
   var iddh:Int = 0
    var page:Int = 1
    var arrayWatch: ArrayList<Sanpham> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch)
        Getdata()
        Getwatch(page)
        Actiontool()
        Load()

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
    private fun Load() {
        listwatch.setOnItemClickListener{
            parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            var intent: Intent = Intent(this,Chitietsanpham::class.java)
            intent.putExtra("thongtinchitet",arrayWatch.get(position))
            startActivity(intent)
        }
    }

    private fun Actiontool() {
        setSupportActionBar(findViewById(R.id.toolwatch))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolwatch.setNavigationOnClickListener {
            finish()
        }
    }

    private fun Getwatch(Page: Int) {
        var url = "http://192.168.1.6/service/getsanphamwatch.php?page="
        var duongdan:String = url + Page.toString()
        var queue1: RequestQueue = Volley.newRequestQueue(this)
        var stringrequest1 = StringRequest(Request.Method.GET,duongdan, Response.Listener { response ->
            var jsonarraysp: JSONArray = JSONArray(response)
            if (response != null){
                for (i in 0..(jsonarraysp.length()-1)){
                    var jsonobject1: JSONObject = jsonarraysp.getJSONObject(i)
                    var id:Int = jsonobject1.getInt("id")
                    var tendongho: String = jsonobject1.getString("tensp")
                    var giadongho: Int = jsonobject1.getInt("giasp")
                    var hinhdongho: String = jsonobject1.getString("hinhsp")
                    var motadongho: String = jsonobject1.getString("motasp")
                    var iddongho: Int = jsonobject1.getInt("idsp")
                    arrayWatch.add(Sanpham(id,tendongho,giadongho,hinhdongho,motadongho,iddongho))
                }
              //  listwatch.adapter = adapterwatch(this,arrayWatch)
            }
            listwatch.adapter = adapterwatch(this,arrayWatch)
        }, Response.ErrorListener {  })

        var hashMap
                = HashMap<String, String> ()
        hashMap.put("idsanpham",iddh.toString())

        queue1.add(stringrequest1)

    }

    private fun Getdata() {
        iddh = intent.getIntExtra("idloaisanpham",-1)

        Log.d("AAA",iddh.toString() + "")
    }
}
