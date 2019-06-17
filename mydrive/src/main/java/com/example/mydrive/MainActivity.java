package com.example.mydrive;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // data 준비
        ArrayList<Drive> datas = new ArrayList<>();
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM tb_drive";
        Cursor cursor =  db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            Drive drive = new Drive();
            drive.id = cursor.getInt(0);
            drive.type = cursor.getString(3);
            drive.title = cursor.getString(1);
            drive.date = cursor.getString(2);
            datas.add(drive);
        }
        db.close();

        // 레코드 수동입력 코드
//        datas.add(new Drive("doc", "2학년 4반 파일", "수정된 날짜 : 6월 3일"));
//        datas.add(new Drive("file", "2학년 4반 파일", "수정된 날짜 : 6월 3일"));
//        datas.add(new Drive("image", "2학년 4반 파일", "수정된 날짜 : 6월 3일"));

        DriveAdapter adapter = new DriveAdapter(this, R.layout.item, datas);

        listView.setAdapter(adapter);
    }
}
