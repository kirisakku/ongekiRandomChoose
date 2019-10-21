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

fun getList(thisArg:MainActivity): MutableList<Map<String, String>> {
    val musicList = readFile(thisArg);
    var objectList = mutableListOf<Map<String, String>>();
    musicList.forEach{
        val difficulty = it[0];
        val category = it[1];
        val title = it[2];
        val rate = it[3];
        val map: Map<String, String> =
            mapOf("difficulty" to difficulty, "category" to category, "title" to title, "rate" to rate);
        objectList.add(map);
    }

    return objectList;
}
