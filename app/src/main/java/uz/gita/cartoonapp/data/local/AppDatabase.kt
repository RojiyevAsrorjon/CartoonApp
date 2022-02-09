package uz.gita.cartoonapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.gita.cartoonapp.converter.StringTypeConvertor
import uz.gita.cartoonapp.data.local.daos.CartoonDao
import uz.gita.cartoonapp.data.remote.responses.ResultsItem

@Database(entities = [ResultsItem::class], version = 1)
@TypeConverters(StringTypeConvertor::class)
abstract class AppDatabase :RoomDatabase() {
    abstract fun dao() : CartoonDao
    companion object{
        private lateinit var instance : AppDatabase
        fun init(context: Context) : AppDatabase {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "room_database").build()
            }
            return instance
        }
    }
}