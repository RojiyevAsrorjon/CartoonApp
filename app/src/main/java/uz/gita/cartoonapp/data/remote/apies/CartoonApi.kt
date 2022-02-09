package uz.gita.cartoonapp.data.remote.apies

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.cartoonapp.data.remote.responses.CharacterResponse

interface CartoonApi {
    @GET("character")
    suspend fun getCharacterContents(
        @Query("page") page : Int
    ) : Response<CharacterResponse>
}