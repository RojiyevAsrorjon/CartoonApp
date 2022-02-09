package uz.gita.cartoonapp.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringTypeConvertor {

    @TypeConverter
    fun saveStringList(list : List<String>) : String?{
        return Gson().toJson(list)
    }

    @TypeConverter
    fun getStringList(list : String) : List<String>{
        return Gson().fromJson(list, object : TypeToken<List<String?>?>(){}.type)
    }
}