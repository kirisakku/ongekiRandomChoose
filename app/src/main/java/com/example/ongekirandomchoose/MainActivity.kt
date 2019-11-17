package com.example.ongekirandomchoose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import MusicList.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    fun getFileteredRate():MutableList<String> {
        val filteredRate:MutableList<String> = mutableListOf();
        // チェックが外れていたらフィルタ
        if (checkBox14plus.isChecked() == false) {
            filteredRate.add("14+");
        }
        if (checkBox14.isChecked() == false) {
            filteredRate.add("14");
        }
        if (checkBox13plus.isChecked() == false) {
            filteredRate.add("13+");
        }
        if (checkBox13.isChecked() == false) {
            filteredRate.add("13");
        }
        if (checkBox12plus.isChecked() == false) {
            filteredRate.add("12+");
        }
        if (checkBox12.isChecked() == false) {
            filteredRate.add("12");
        }
        if (checkBox11plus.isChecked() == false) {
            filteredRate.add("11+");
        }
        if (checkBox11.isChecked() == false) {
            filteredRate.add("11");
        }
        if (checkBox10plus.isChecked() == false) {
            filteredRate.add("10+");
        }
        if (checkBox10.isChecked() == false) {
            filteredRate.add("10");
        }

        return filteredRate;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        random.setOnClickListener {
            val filteredRate = this.getFileteredRate();
            val musicList = getList(this, filteredRate);

            if (musicList.size == 0) {
                chooseResult.text = "対象曲がありません";
            } else {
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
}
