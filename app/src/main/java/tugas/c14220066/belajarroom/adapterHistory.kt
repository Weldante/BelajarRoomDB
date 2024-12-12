package tugas.c14220066.belajarroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tugas.c14220066.belajarroom.database.historyBelanja

class adapterHistory (private val historyBelanja: MutableList<historyBelanja>):
    RecyclerView.Adapter<adapterHistory.ListViewHolder>() {

    inner class ListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var _tvItemBarang = itemView.findViewById<TextView>(R.id.tvItemBarang)
        var _tvJumlahBarang = itemView.findViewById<TextView>(R.id.tvJumlahBarang)
        var _tvTanggal = itemView.findViewById<TextView>(R.id.tvTanggal)

        var _ivEdit = itemView.findViewById<ImageView>(R.id.ivEdit)
        var _ivDelete = itemView.findViewById<ImageView>(R.id.ivDelete)
        var _ivSelesai = itemView.findViewById<ImageView>(R.id.ivSelesai)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            adapterHistory.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list, parent,
            false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyBelanja.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val daftar = historyBelanja[position]

        holder._tvTanggal.setText(daftar.tanggal)
        holder._tvItemBarang.setText(daftar.item)
        holder._tvJumlahBarang.setText(daftar.jumlah)

        holder._ivEdit.visibility = View.GONE
        holder._ivSelesai.visibility = View.GONE
        holder._ivDelete.visibility = View.GONE
    }

    fun isiData(history: List<historyBelanja>) {
        historyBelanja.clear()
        historyBelanja.addAll(history)
        notifyDataSetChanged()
    }

}