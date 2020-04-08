package model

import activity.Giohang
import activity.MainActivity
import adapter.adapterwatch
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.appbanwatch.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_giohang.*
import java.text.DecimalFormat

class adaptergiohang(var context: Context,var manggio:ArrayList<giohang>): BaseAdapter() {
    class ViewHolder(row: View){
        var tensp: TextView
        var giasp: TextView
        var hinhsp: ImageView
        var bttru:Button
        var btgiatri:Button
        var btcong:Button
        init {
            tensp = row.findViewById(R.id.txtengiohang)
            giasp = row.findViewById(R.id.txgiagiohang)
            hinhsp = row.findViewById(R.id.imggiohang)
            bttru = row.findViewById(R.id.btntru)
            btcong = row.findViewById(R.id.btncong)
            btgiatri = row.findViewById(R.id.btnvalue)
        }
    }
    override fun getView(position: Int, convertview: View?, parent: ViewGroup?): View {
        var view: View?
        var viewholder: ViewHolder
        if (convertview == null){
            var layout: LayoutInflater = LayoutInflater.from(context)
            view = layout.inflate(R.layout.custom_giohang,null)
            viewholder = ViewHolder(view)
            view.tag = viewholder
        }else{
            view = convertview
            viewholder = convertview.tag as ViewHolder
        }
        var sp:giohang = getItem(position) as giohang
        viewholder.tensp.setText(sp.tenSP)
        var decimal: DecimalFormat = DecimalFormat("###,###,###")
        viewholder.giasp.setText("Giá: " + decimal.format(sp.giaSP).replace(",",".") + "Đ")
        viewholder.btgiatri.setText(sp.soluongsp.toString())

        Picasso.with(context).load(sp.hinhSP)
            .placeholder(R.drawable.load)
            .error(R.drawable.error)
            .into(viewholder.hinhsp)
        var sl:Int = viewholder.btgiatri.text.toString().toInt()
        if (sl >=10){
            viewholder.btcong.visibility = View.INVISIBLE
            viewholder.bttru.visibility = View.VISIBLE
        }else if (sl >=1){
            viewholder.btcong.visibility = View.VISIBLE
        }else if (sl <=1){
            viewholder.btcong.visibility = View.VISIBLE
            viewholder.bttru.visibility = View.INVISIBLE
        }
        viewholder.btcong.setOnClickListener {
            var slmoi:Int = viewholder.btgiatri.text.toString().toInt() + 1
            var slht:Int = MainActivity.manggiohang?.get(position)!!.soluongsp
            var giaht: Long = MainActivity.manggiohang?.get(position)!!.giaSP
            MainActivity.manggiohang?.get(position)!!.soluongsp = slmoi
            var giamoi:Long = (slmoi * giaht) /slht
            MainActivity.manggiohang?.get(position)!!.giaSP = giamoi
            var decimal: DecimalFormat = DecimalFormat("###,###,###")
            viewholder.giasp.setText("Giá: " + decimal.format(giamoi).replace(",",".") + " Đ ")
            Giohang.Evenutil()
            if (slmoi > 9){
                viewholder.btcong.visibility = View.INVISIBLE
                viewholder.bttru.visibility = View.VISIBLE
                viewholder.btgiatri.setText(slmoi.toString())
            }else{
                viewholder.btcong.visibility = View.VISIBLE
                viewholder.bttru.visibility = View.VISIBLE
                viewholder.btgiatri.setText(slmoi.toString())
            }


        }
        viewholder.bttru.setOnClickListener {
            var slmoi:Int = viewholder.btgiatri.text.toString().toInt() - 1
            var slht:Int = MainActivity.manggiohang?.get(position)!!.soluongsp
            var giaht: Long = MainActivity.manggiohang?.get(position)!!.giaSP
            MainActivity.manggiohang?.get(position)!!.soluongsp = slmoi
            var giamoi:Long = (slmoi * giaht) /slht
            MainActivity.manggiohang?.get(position)!!.giaSP = giamoi
            var decimal: DecimalFormat = DecimalFormat("###,###,###")
            viewholder.giasp.setText("Giá: " + decimal.format(giamoi).replace(",",".") + " Đ ")
            Giohang.Evenutil()
            if (slmoi < 2){
                viewholder.btcong.visibility = View.VISIBLE
                viewholder.bttru.visibility = View.INVISIBLE
                viewholder.btgiatri.setText(slmoi.toString())
            }else{
                viewholder.btcong.visibility = View.VISIBLE
                viewholder.bttru.visibility = View.VISIBLE
                viewholder.btgiatri.setText(slmoi.toString())
            }
        }
        return view as View
    }

    override fun getItem(position: Int): Any {
        return manggio.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
       return manggio.size
    }
}