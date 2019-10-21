package com.example.ongekirandomchoose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import MusicList.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        random.setOnClickListener{
            val musicList = getList(this);
            val randomNumber = Random.nextInt(musicList.size);
            val targetData = musicList[randomNumber];
            val difficulty = targetData.get("difficulty");
            val category = targetData.get("category");
            val title = targetData.get("title");
            val rate = targetData.get("rate");

            chooseResult.text = """
                | 難易度: $difficulty
                | カテゴリ：$category
                | 曲名：$title
                | レート値：$rate
            """.trimMargin()
        }
    }
}
