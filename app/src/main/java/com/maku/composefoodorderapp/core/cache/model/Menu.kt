package com.maku.composefoodorderapp.core.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu")
data class Menu(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val name: String,
    val description: String,
    val url: String,
    val added: Boolean,
    val price: Int
) {
    companion object {
        val DEFAULT_MENU_ITEMS = listOf(
            Menu(
                1,
                "Fish Salad",
                "Delicious fish salad",
                "https://img.freepik.com/free-photo/baked-chicken-wings-asian-style-tomatoes-sauce-plate_2829-10657.jpg?w=1380&t=st=1666364197~exp=1666364797~hmac=2ddac09d38682462ae3b8504900ea55bc08cc15e680f4ff50fc4f65200d17e4b",
                added = false,
                price = 200
            ),
            Menu(
                2,
                "Fish Salad",
                "Delicious fish salad",
                "https://img.freepik.com/free-photo/baked-chicken-wings-asian-style-tomatoes-sauce-plate_2829-10657.jpg?w=1380&t=st=1666364197~exp=1666364797~hmac=2ddac09d38682462ae3b8504900ea55bc08cc15e680f4ff50fc4f65200d17e4b",
                added = false,
                price = 400
            ),
            Menu(
                3,
                "Fish Salad",
                "Delicious fish salad",
                "https://img.freepik.com/free-photo/baked-chicken-wings-asian-style-tomatoes-sauce-plate_2829-10657.jpg?w=1380&t=st=1666364197~exp=1666364797~hmac=2ddac09d38682462ae3b8504900ea55bc08cc15e680f4ff50fc4f65200d17e4b",
                added = false,
                price = 900
            ),
            Menu(
                4,
                "Fish Salad",
                "Delicious fish salad",
                "https://img.freepik.com/free-photo/baked-chicken-wings-asian-style-tomatoes-sauce-plate_2829-10657.jpg?w=1380&t=st=1666364197~exp=1666364797~hmac=2ddac09d38682462ae3b8504900ea55bc08cc15e680f4ff50fc4f65200d17e4b",
                added = false,
                price = 1200
            ),
            Menu(
                5,
                "Fish Salad",
                "Delicious fish salad",
                "https://img.freepik.com/free-photo/baked-chicken-wings-asian-style-tomatoes-sauce-plate_2829-10657.jpg?w=1380&t=st=1666364197~exp=1666364797~hmac=2ddac09d38682462ae3b8504900ea55bc08cc15e680f4ff50fc4f65200d17e4b",
                added = false,
                price = 4500
            ),
            Menu(
                6,
                "Fish Salad",
                "Delicious fish salad",
                "https://img.freepik.com/free-photo/baked-chicken-wings-asian-style-tomatoes-sauce-plate_2829-10657.jpg?w=1380&t=st=1666364197~exp=1666364797~hmac=2ddac09d38682462ae3b8504900ea55bc08cc15e680f4ff50fc4f65200d17e4b",
                added = false,
                price = 200
            ),
            Menu(
                7,
                "Fish Salad",
                "Delicious fish salad",
                "https://img.freepik.com/free-photo/baked-chicken-wings-asian-style-tomatoes-sauce-plate_2829-10657.jpg?w=1380&t=st=1666364197~exp=1666364797~hmac=2ddac09d38682462ae3b8504900ea55bc08cc15e680f4ff50fc4f65200d17e4b",
                added = false,
                price = 200
            ),
            Menu(
                8,
                "Fish Salad",
                "Delicious fish salad",
                "https://img.freepik.com/free-photo/baked-chicken-wings-asian-style-tomatoes-sauce-plate_2829-10657.jpg?w=1380&t=st=1666364197~exp=1666364797~hmac=2ddac09d38682462ae3b8504900ea55bc08cc15e680f4ff50fc4f65200d17e4b",
                added = false,
                price = 200
            ),
            Menu(
                9,
                "Fish Salad",
                "Delicious fish salad",
                "https://img.freepik.com/free-photo/baked-chicken-wings-asian-style-tomatoes-sauce-plate_2829-10657.jpg?w=1380&t=st=1666364197~exp=1666364797~hmac=2ddac09d38682462ae3b8504900ea55bc08cc15e680f4ff50fc4f65200d17e4b",
                added = false,
                price = 200
            ),
            Menu(
                10,
                "Fish Salad",
                "Delicious fish salad",
                "https://img.freepik.com/free-photo/baked-chicken-wings-asian-style-tomatoes-sauce-plate_2829-10657.jpg?w=1380&t=st=1666364197~exp=1666364797~hmac=2ddac09d38682462ae3b8504900ea55bc08cc15e680f4ff50fc4f65200d17e4b",
                added = false,
                price = 200
            ),
            Menu(
                11,
                "Fish Salad",
                "Delicious fish salad",
                "https://img.freepik.com/free-photo/baked-chicken-wings-asian-style-tomatoes-sauce-plate_2829-10657.jpg?w=1380&t=st=1666364197~exp=1666364797~hmac=2ddac09d38682462ae3b8504900ea55bc08cc15e680f4ff50fc4f65200d17e4b",
                added = false,
                price = 200
            ),
            Menu(
                12,
                "Fish Salad",
                "Delicious fish salad",
                "https://img.freepik.com/free-photo/baked-chicken-wings-asian-style-tomatoes-sauce-plate_2829-10657.jpg?w=1380&t=st=1666364197~exp=1666364797~hmac=2ddac09d38682462ae3b8504900ea55bc08cc15e680f4ff50fc4f65200d17e4b",
                added = false,
                price = 200
            ),
        )
    }
}
