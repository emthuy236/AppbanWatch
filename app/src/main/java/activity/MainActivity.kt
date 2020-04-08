package activity

import adapter.adapterLoaisp
import adapter.adapterSanpham
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ViewFlipper
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appbanwatch.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import model.Sanpham
import model.giohang
import model.loaisp
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
  var viewFlipe: ViewFlipper? = null
    var arrayloaisp: ArrayList<loaisp> = ArrayList()
    var arraysanpham:ArrayList<Sanpham> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycleMain.setHasFixedSize(true)
        recycleMain.layoutManager = GridLayoutManager(this,2) as RecyclerView.LayoutManager?
      //  var arrayloaisp: ArrayList<loaisp> = ArrayList()
        Actiontoolbar()
        Viewflipper()
        Getdataloaisp()
        Getdataspmoinhat()
        Chuyendata()
        if (manggiohang !=null){

        }else{
            manggiohang = ArrayList()
        }

      //  listMain.adapter = adapterLoaisp(this,arrayloaisp)
    }
    companion object {
        var manggiohang: ArrayList<giohang>? = null
    }

    private fun Chuyendata() {
        listMain.setOnItemClickListener{
            parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            if (position == 0){
                var intent:Intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                drawer.closeDrawer(GravityCompat.START)
            }
            if (position == 1){
                var intent:Intent = Intent(this,Watch::class.java)
                intent.putExtra("idloaisanpham",arrayloaisp.get(position).id)
                startActivity(intent)
                drawer.closeDrawer(GravityCompat.START)
            }
            if (position ==2){
                var intent:Intent = Intent(this,Phone::class.java)
                intent.putExtra("idloaisanpham",arrayloaisp.get(position).id)
                startActivity(intent)
                drawer.closeDrawer(GravityCompat.START)
            }
            if (position ==3){
                var intent:Intent = Intent(this,ThongTin::class.java)
                startActivity(intent)
                drawer.closeDrawer(GravityCompat.START)
            }
            if (position ==4){
                var intent:Intent = Intent(this,Lienhe::class.java)
                startActivity(intent)
                drawer.closeDrawer(GravityCompat.START)
            }
        }

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

    private fun Getdataspmoinhat() {
        var url = "http://192.168.1.6/service/getsanphammoinhat.php"
        var queue1: RequestQueue = Volley.newRequestQueue(this)
        var stringrequest1 = StringRequest(Request.Method.GET,url,Response.Listener { response ->
            var jsonarraysp: JSONArray = JSONArray(response)
            if (response != null){
                for (i in 0..(jsonarraysp.length()-1)){
                    var jsonobject1: JSONObject = jsonarraysp.getJSONObject(i)
                    var id:Int = jsonobject1.getInt("id")
                    var tenSP: String = jsonobject1.getString("tensp")
                    var giaSP: Int = jsonobject1.getInt("giasp")
                    var hinhSP: String = jsonobject1.getString("hinhsp")
                    var motaSP: String = jsonobject1.getString("motasp")
                    var idSP: Int = jsonobject1.getInt("idsp")
                    arraysanpham.add(Sanpham(id,tenSP,giaSP,hinhSP,motaSP,idSP))
                }

            }
            recycleMain.adapter = adapterSanpham(this,arraysanpham)
        },Response.ErrorListener {  })
        queue1.add(stringrequest1)

    }

    private fun Getdataloaisp() {

       var url: String = "http://192.168.1.6/service/getloaisp.php"
        var queue: RequestQueue = Volley.newRequestQueue(this)
        var stringrequest = StringRequest(Request.Method.GET,url,Response.Listener { response ->
            var jsonarrayloaisp: JSONArray = JSONArray(response)

            arrayloaisp.add(0,loaisp(0,"Trang chính","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTHhdk1GkaH3exeJh7I79N4cdyl_cmj7t-M-SMjCwrMzaEJkEh1"))
                if (response != null) {
                    for (i in 0..(jsonarrayloaisp.length() -1) ) {
                      //  var jsonarrayloaisp: JSONArray = JSONArray(response)
                        var jsonobject: JSONObject = jsonarrayloaisp.getJSONObject(i)
                        var id: Int = jsonobject.getInt("id")
                        var tenLoaisp: String = jsonobject.getString("tenloaisp")
                        var hinhLoaisp: String = jsonobject.getString("hinhloaisp")
                        arrayloaisp.add(loaisp(id, tenLoaisp, hinhLoaisp))

                    }
                    arrayloaisp.add(3,

                        loaisp(
                            0,
                            "Thông tin cá nhân",
                            "https://static.vecteezy.com/system/resources/thumbnails/000/550/731/small/user_icon_004.jpg"
                        )
                    )
                    arrayloaisp.add(4,

                        loaisp(
                            0,
                            "Liên hệ",
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRhfLodb3NG0U-mWT1aG8OwCM1iC_4A4bo77RzzrRLEUWyVPCur"
                        )
                    )
                   // listMain.adapter = adapterLoaisp(this,arrayloaisp)
                }
           listMain.adapter = adapterLoaisp(this,arrayloaisp)
        },
            Response.ErrorListener {
                print("error")
            })

        queue.add(stringrequest)

    }

    private fun Viewflipper() {
        viewFlipe = findViewById(R.id.viewFlip)
        var arrayquangcao: ArrayList<String> = ArrayList()
        arrayquangcao.add("https://adsplus.vn/wp-content/uploads/2019/02/8-1.jpg")
        arrayquangcao.add("https://cdn3.dhht.vn/wp-content/uploads/2017/09/chung-nhan-dai-ly-orient-xuat-sac-nhat-2016-2017-banner.png")
        arrayquangcao.add("https://genk.mediacdn.vn/2018/8/5/386146239667656201610143863448190854365184n-1533442997389894652550.jpg")
        arrayquangcao.add("https://media.cungcau.vn/files/baochau/2019/09/12/apple-watch-series-5-ra-mat-2-0916.jpg")
        for (i in 0..(arrayquangcao.size -1)){
            var imgae: ImageView = ImageView(applicationContext)
            Picasso.with(applicationContext).load(arrayquangcao.get(i)).into(imgae)
            imgae.scaleType = ImageView.ScaleType.FIT_XY
            viewFlip.addView(imgae)
        }
        viewFlip.isAutoStart = true
        viewFlip.flipInterval = 5000
       var anim_slide_in: Animation = AnimationUtils.loadAnimation(applicationContext,
           R.anim.anim_in
       )
        var anim_slide_out: Animation = AnimationUtils.loadAnimation(applicationContext,
            R.anim.anim_out
        )
        viewFlip.setInAnimation(anim_slide_in)
        viewFlip.setOutAnimation(anim_slide_out)

    }

    private fun Actiontoolbar() {
        setSupportActionBar(findViewById(R.id.toolbarMain))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbarMain.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size)
        toolbarMain.setNavigationOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }
    }
}
