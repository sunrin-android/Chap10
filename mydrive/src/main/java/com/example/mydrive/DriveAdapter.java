package com.example.mydrive;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DriveAdapter extends ArrayAdapter<Drive> {
    // 멤버 변수
    Context context;
    int layoutId; // item 1개에 대한 layout
    ArrayList<Drive> datas; // 보여줄 data

    // 생성자
    public DriveAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Drive> objects) {
        super(context, resource, objects);
        this.context = context;
        layoutId = resource;
        datas = objects;
    }

    // 오버라이드 메소드 getCount, getView
    @Override
    public int getCount() {
        // data 개수
        return datas.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // R.layout.item 인플레이션(메모리에 객체화)
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutId, null);

            // 객체 찾아오기
            DriveHolder driveHolder = new DriveHolder(convertView);
            convertView.setTag(driveHolder);
        }

        ImageView menuImage = convertView.findViewById(R.id.menu_image);
        ImageView typeImage = convertView.findViewById(R.id.type_image);
        TextView titleView = convertView.findViewById(R.id.title);
        TextView dateView = convertView.findViewById(R.id.date);

        // 현재 위치에 해당하는 data 확보
        final Drive drive  = datas.get(position);
        DriveHolder holder = (DriveHolder)convertView.getTag();

        // view에 data 넣기
        holder.titleView.setText(drive.title);
        holder.dateView.setText(drive.date);

        if (drive.type.equals("doc"))
            holder.typeImage.setImageResource(R.drawable.ic_type_doc);
        else if(drive.type.equals("file"))
            holder.typeImage.setImageResource(R.drawable.ic_type_file);
        else if(drive.type.equals("img"))
            holder.typeImage.setImageResource(R.drawable.ic_type_image);

        // 파일 제목을 클릭했을 때 이벤트 처리
        holder.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, drive.title, Toast.LENGTH_SHORT).show();
            }
        });

        // 메뉴이미지를 클릭했을 때 이벤트 처리
        holder.menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 추가
//                datas.add(new Drive("doc", "파일 추가","최종수정날짜 : 6월 10일"));
                // 삭제 - db 삭제, liveview 삭제
                DBHelper helper = new DBHelper(context);
                SQLiteDatabase db = helper.getWritableDatabase();
                String sql = "DELETE FROM tb_drive WHERE _id=" + drive.id;
                db.execSQL(sql);
                db.close();

                datas.remove(position);
                DriveAdapter.this.notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
