package com.example.moneymanager20logic.Database.Daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.moneymanager20logic.Database.table.Entities
import kotlinx.coroutines.flow.Flow

@Dao
interface Saving {

    @Upsert
    suspend fun add(saving:Entities)

    @Delete
    suspend fun delete(saving: Entities)

    @Query("SELECT * FROM saveEntity ORDER BY dateAdded")
    fun getSavingByDateAdded(): Flow<List<Entities>>

    @Query("SELECT * FROM saveEntity ORDER BY title")
    fun getSavingByTitle(): Flow<List<Entities>>

    @Query("UPDATE saveEntity  SET amount = :newAmount WHERE title= :title")
    suspend fun updateAmount(newAmount:Double?,title:String)

    @Query("SELECT SUM(amount) as sum FROM saveEntity")
     fun sumOftheAmount():Flow<Double>

}