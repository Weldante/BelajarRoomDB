package tugas.c14220066.belajarroom

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tugas.c14220066.belajarroom.database.daftarBelanja

class adapterDaftar (private val daftarBelanja: MutableList<daftarBelanja>):
    RecyclerView.Adapter<adapterDaftar.ListViewHolder>(){

    private lateinit var onItemClickCallback : OnItemClickCallback

    interface OnItemClickCallback {
        fun delData(dtBelanja: daftarBelanja)
        fun selesai(dtBelanja: daftarBelanja)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
            var _tvItemBarang = itemView.findViewById<TextView>(R.id.tvItemBarang)
            var _tvJumlahBarang = itemView.findViewById<TextView>(R.id.tvJumlahBarang)
            var _tvTanggal = itemView.findViewById<TextView>(R.id.tvTanggal)

            var _ivEdit = itemView.findViewById<ImageView>(R.id.ivEdit)
            var _ivDelete = itemView.findViewById<ImageView>(R.id.ivDelete)
            var _ivSelesai = itemView.findViewById<ImageView>(R.id.ivSelesai)
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adapterDaftar.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list, parent,
            false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return daftarBelanja.size
    }

    override fun onBindViewHolder(holder: adapterDaftar.ListViewHolder, position: Int) {
        val daftar = daftarBelanja[position]

        holder._tvTanggal.setText(daftar.tanggal)
        holder._tvItemBarang.setText(daftar.item)
        holder._tvJumlahBarang.setText(daftar.jumlah)

        holder._ivEdit.setOnClickListener {
            val intent = Intent(it.context, TambahDaftar::class.java)
            intent.putExtra("id", daftar.id)
            intent.putExtra("addEdit", 1)
            it.context.startActivity(intent)
        }

        holder._ivDelete.setOnClickListener {
            onItemClickCallback.delData(daftar)
        }

        holder._ivSelesai.setOnClickListener {
            onItemClickCallback.selesai(daftar)
        }
    }

    fun isiData(daftar: List<daftarBelanja>) {
        daftarBelanja.clear()
        daftarBelanja.addAll(daftar)
        notifyDataSetChanged()
    }

}