package com.example.chap10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Spinner spinner;
    GridView gridView;
    String[] datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        spinner = findViewById(R.id.spinner);
        gridView = findViewById(R.id.gridView);

        // data
        datas = new String[]{"노홍림", "강동훈","강준하","김도훈","김수빈"};
        // adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.item, R.id.text1, datas);

        // listView에 adapter 세팅
        listView.setAdapter(adapter1);
        spinner.setAdapter(adapter);
        gridView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, datas[position], Toast.LENGTH_SHORT).show(); // 둘 다 사용 가능
//                Toast.makeText(getApplicationContext(), datas[position], Toast.LENGTH_SHORT).show(); // 둘 다 사용 가능
            }
        });
    }
}
