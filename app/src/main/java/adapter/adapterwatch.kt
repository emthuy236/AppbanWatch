package adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.appbanwatch.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_dong_watch.view.*
import model.Sanpham
import org.w3c.dom.Text
import java.text.DecimalFormat

class adapterwatch(var context: Context,var mangwatch: ArrayList<Sanpham>): BaseAdapter() {
    class ViewHolder(row: View){
        var tenwatch:TextView
        var giawatch:TextView
        var motawatch:TextView
        var hinhwatch:ImageView
        init {
            tenwatch = row.findViewById(R.id.txtenwatch)
            giawatch = row.findViewById(R.id.txgiawatch)
            motawatch = row.findViewById(R.id.txmotawatch)
            hinhwatch = row.findViewById(R.id.imgwatch)
        }
    }
    override fun getView(position: Int, convertview: View?, parent: ViewGroup?): View {
        var view: View?
        var viewholder: ViewHolder
        if (convertview == null){
            var layout: LayoutInflater = LayoutInflater.from(context)
            view = layout.inflate(R.layout.custom_dong_watch,null)
            viewholder = ViewHolder(view)
            view.tag = viewholder
        }else{
            view = convertview
            viewholder = convertview.tag as ViewHolder
        }
        var sp:Sanpham = getItem(position) as Sanpham
        viewholder.tenwatch.setText(sp.tenSp)
        var decimal: DecimalFormat = DecimalFormat("###,###,###")
        viewholder.giawatch.setText("Giá: " + decimal.format(sp.giaSp).replace(",",".") + "Đ")
        viewholder.motawatch.maxLines = 2
        viewholder.motawatch.ellipsize = TextUtils.TruncateAt.END
        viewholder.motawatch.setText(sp.motaSp)
        Picasso.with(context).load(sp.hinhSp)
            .placeholder(R.drawable.load)
            .error(R.drawable.error)
            .into(viewholder.hinhwatch)
        return view as View

    }

    override fun getItem(position: Int): Any {
        return mangwatch.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mangwatch.size
    }
}