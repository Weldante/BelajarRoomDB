package tugas.c14220066.belajarroom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import tugas.c14220066.belajarroom.database.daftarBelanjaDB
import tugas.c14220066.belajarroom.database.historyBelanja

class History : AppCompatActivity() {

    private lateinit var DB : daftarBelanjaDB

    private lateinit var adapterHistory: adapterHistory

    private var arHistory : MutableList<historyBelanja> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        DB = daftarBelanjaDB.getDatabase(this)

        adapterHistory = adapterHistory(arHistory)
        val _rvHisBlanja = findViewById<RecyclerView>(R.id.rvHisBlanja)
        _rvHisBlanja.layoutManager = LinearLayoutManager(this)
        _rvHisBlanja.adapter = adapterHistory

        val _btnDaftar = findViewById<Button>(R.id.btnDaftar)
        _btnDaftar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.Main).async {
            val historyBelanja = DB.funhistoryBelanjaDAO().selectAll()
            Log.d("data ROOM", historyBelanja.toString())
            adapterHistory.isiData(historyBelanja)
        }
    }
}