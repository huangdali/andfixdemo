package com.hdl.andfixdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvContent.setText("--------------content----------------\n");
        showData();
    }

    private void showData() {
        for (int i = 1; i <= 10; i++) {
            tvContent.append(i + "--------------content\n");
        }
    }

    public void onRefresh(View view) {
        tvContent.setText("");
        showData();
        int text = 0;
        tvContent.append("\ntext=" + text);
    }
}
