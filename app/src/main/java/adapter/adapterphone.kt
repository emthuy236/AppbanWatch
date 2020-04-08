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
import model.Sanpham
import java.text.DecimalFormat

class adapterphone(var context: Context,var mangphone:ArrayList<Sanpham>): BaseAdapter() {
    class ViewHolder(row: View) {
        var tenphone: TextView
        var giaphone: TextView
        var motaphone: TextView
        var hinhphone: ImageView

        init {
            tenphone = row.findViewById(R.id.txtenphone)
            giaphone = row.findViewById(R.id.txgiaphone)
            motaphone = row.findViewById(R.id.txmotaphone)
            hinhphone = row.findViewById(R.id.imgphone)
        }
    }

        override fun getView(position: Int, convertview: View?, parent: ViewGroup?): View {
            var view: View?
            var viewholder: ViewHolder
            if (convertview == null){
                var layout: LayoutInflater = LayoutInflater.from(context)
                view = layout.inflate(R.layout.custom_phone,null)
                viewholder = ViewHolder(view)
                view.tag = viewholder
            }else{
                view = convertview
                viewholder = convertview.tag as ViewHolder
            }
            var sp:Sanpham = getItem(position) as Sanpham
            viewholder.tenphone.setText(sp.tenSp)
            var decimal: DecimalFormat = DecimalFormat("###,###,###")
            viewholder.giaphone.setText("Giá: " + decimal.format(sp.giaSp).replace(",",".") + "Đ")
            viewholder.motaphone.maxLines = 2
            viewholder.motaphone.ellipsize = TextUtils.TruncateAt.END
            viewholder.motaphone.setText(sp.motaSp)
            Picasso.with(context).load(sp.hinhSp)
                .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(viewholder.hinhphone)
            return view as View
        }

        override fun getItem(position: Int): Any {
            return mangphone.get(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return mangphone.size
        }
    }
