package uz.gita.cartoonapp.data.remote.responses

import androidx.room.*
import uz.gita.cartoonapp.converter.StringTypeConvertor

data class CharacterResponse(
	val results: List<ResultsItem>,
	val info: Info
)

data class Info(
	val next: String,
	val pages: Int,
	val prev: Any,
	val count: Int
)

data class Location(
	@ColumnInfo(name = "last_place")
	val name: String,
	@ColumnInfo(name="last_url")
	val url: String

)

data class Origin(
	@ColumnInfo(name = "first_place")
	val name: String,
	@ColumnInfo(name="first_url")
	val url: String
)

@Entity(tableName = "cartoon_table")
data class ResultsItem(
	val image: String,
	val gender: String,
	val species: String,
	val created: String,
	@Embedded
	val origin: Origin,
	@ColumnInfo(name="cartoon_name")
	val name: String,
	@Embedded
	val location: Location,
	@field:TypeConverters(StringTypeConvertor::class)
	val episode: List<String> = emptyList(),
	@PrimaryKey
	val id: Int,
	val type: String,
	@ColumnInfo(name="cartoon_url")
	val url: String,
	val status: String
)

