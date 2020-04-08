package adapter

import activity.Chitietsanpham
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appbanwatch.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_sanphammoinhat.view.*
import model.Sanpham
import java.text.DecimalFormat

class adapterSanpham(var context:Context,var mangsanpham:ArrayList<Sanpham>): RecyclerView.Adapter<adapterSanpham.ItemHolder>() {
    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var txtenSP: TextView
        var txgiaSP: TextView
        var imgSP: ImageView
        init {
            txtenSP = itemView.findViewById(R.id.txtensp)
            txgiaSP = itemView.findViewById(R.id.txgiasp)
            imgSP = itemView.findViewById(R.id.imgsp)
            itemView.setOnClickListener {
                var intent:Intent = Intent(context,Chitietsanpham::class.java)
                intent.putExtra("thongtinchitet",mangsanpham.get(position))
                context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.custom_sanphammoinhat,null)
        var item:ItemHolder = ItemHolder(view)
        return item
    }

    override fun getItemCount(): Int {
        return mangsanpham.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
          var sanpham:Sanpham = mangsanpham.get(position)
        holder.txtenSP.setText(sanpham.tenSp)
            var decimal: DecimalFormat = DecimalFormat("###,###,###")
        holder.txgiaSP.setText("Giá: " + decimal.format(sanpham.giaSp).replace(",",".") + "Đ")
        Picasso.with(context).load(sanpham.hinhSp)
            .placeholder(R.drawable.load)
            .error(R.drawable.error)
            .into(holder.imgSP)
    }


}