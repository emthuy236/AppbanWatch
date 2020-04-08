package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.appbanwatch.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_loaisp.view.*
import model.loaisp

class adapterLoaisp(var context:Context,var mangloaisp:ArrayList<loaisp>): BaseAdapter() {
    class ViewHolder(row: View){
        var txttenLoaisp: TextView
        var imgLoaisp: ImageView
        init {
            txttenLoaisp = row.findViewById(R.id.tenloaisp)
            imgLoaisp = row.findViewById(R.id.imgeloaisp)
        }
    }
    override fun getView(position: Int, convertview: View?, parent: ViewGroup?): View {
        var view: View?
        var viewholder: ViewHolder
        if (convertview == null){
            var layout: LayoutInflater = LayoutInflater.from(context)
            view = layout.inflate(R.layout.custom_loaisp,null)
            viewholder = ViewHolder(view)
            view.tag = viewholder
        }else{
            view = convertview
            viewholder = convertview.tag as ViewHolder
        }
        var loaiSP: loaisp = getItem(position) as loaisp
        viewholder.txttenLoaisp.text = loaiSP.tenloaisp
        Picasso.with(context)
            .load(loaiSP.hinhloaisp)
            .placeholder(R.drawable.load)
            .error(R.drawable.error)
            .into(viewholder.imgLoaisp)
        return view as View
    }

    override fun getItem(position: Int): Any {
        return mangloaisp.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
       return mangloaisp.size
    }
}