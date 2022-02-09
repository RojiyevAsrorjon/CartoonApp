package uz.gita.cartoonapp.data.local.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.gita.cartoonapp.data.remote.responses.ResultsItem

@Dao
interface CartoonDao {
    @Query("SELECT * FROM cartoon_table")
    fun getSavedData() : PagingSource<Int, ResultsItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list : List<ResultsItem>)
}