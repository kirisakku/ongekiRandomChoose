package MusicList

import android.content.res.Resources
import android.widget.TextView
import android.widget.Toast
import com.example.ongekirandomchoose.MainActivity
import com.example.ongekirandomchoose.R
import java.util.*
import java.io.*
import java.lang.StringBuilder

fun readFile(thisArg: MainActivity): List<List<String>> {
    val res: Resources = thisArg.getResources();
    var bufferReader: BufferedReader? = null;
    var separatedList = mutableListOf<List<String>>();
    try {
        try {
            val inputStream = res.openRawResource(R.raw.musiclist);
            bufferReader = BufferedReader(InputStreamReader(inputStream));
            var str = bufferReader.readLine();
            while(str != null) {
                separatedList.add(str.split(','))
                str = bufferReader.readLine();
            }
        } finally {
            if (bufferReader != null) {
                bufferReader.close();
            }
        }
    } catch (e: IOException) {
        Toast.makeText(thisArg, "読み込み失敗", Toast.LENGTH_SHORT).show();
    }

    return separatedList;
}

fun isFileteredRate(rate:String, filteredDifficulty: MutableList<String>): Boolean {
    var flag = false;
    val numRate = rate.toDouble();
    filteredDifficulty.forEach{
        when(it) {
            "14+" -> flag = (14.7 <= numRate && numRate <= 14.9)
            "14" -> flag = (14.0 <= numRate && numRate <= 14.6)
            "13+" -> flag = (13.7 <= numRate && numRate <= 13.9)
            "13" -> flag = (13.0 <= numRate && numRate <= 13.6)
            "12+" -> flag = (12.7 <= numRate && numRate <= 12.9)
            "12" -> flag = (12.0 <= numRate && numRate <= 12.6)
            "11+" -> flag = (11.7 <= numRate && numRate <= 11.9)
            "11" -> flag = (11.0 <= numRate && numRate <= 11.6)
            "10+" -> flag = (10.7 <= numRate && numRate <= 10.9)
            "10" -> flag = (10.0 <= numRate && numRate <= 10.6)
        }

        if (flag) {
            return true;
        }
    }

    return false;
}

fun getList(thisArg:MainActivity, filteredRate: MutableList<String>): MutableList<Map<String, String>> {
    print("getList")
    val musicList = readFile(thisArg);
    var objectList = mutableListOf<Map<String, String>>();
    musicList.forEach{
        val difficulty = it[0];
        val category = it[1];
        val title = it[2];
        val rate = it[3];
        val map: Map<String, String> =
            mapOf("difficulty" to difficulty, "category" to category, "title" to title, "rate" to rate);

        // フィルタ対象でなければ追加
        if (isFileteredRate(rate, filteredRate) == false) {
            objectList.add(map);
        }
    }

    return objectList;
}
