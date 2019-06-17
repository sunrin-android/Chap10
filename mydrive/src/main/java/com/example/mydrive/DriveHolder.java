package com.example.mydrive;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DriveHolder {
    ImageView typeImage;
    TextView titleView, dateView;
    ImageView menuImage;

    // 생성자
    DriveHolder(View convertView) {
        menuImage = convertView.findViewById(R.id.menu_image);
        typeImage = convertView.findViewById(R.id.type_image);
        titleView = convertView.findViewById(R.id.title);
        dateView = convertView.findViewById(R.id.date);
    }
}
