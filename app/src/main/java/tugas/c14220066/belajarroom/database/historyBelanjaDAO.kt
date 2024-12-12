package tugas.c14220066.belajarroom.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface historyBelanjaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(history: historyBelanja)

    @Query("SELECT * FROM historyBelanja ORDER BY id asc")
    fun selectAll() : MutableList<historyBelanja>

    @Query("SELECT * FROM historyBelanja WHERE id=:isi_id")
    suspend fun getItem(isi_id: Int) : historyBelanja
}