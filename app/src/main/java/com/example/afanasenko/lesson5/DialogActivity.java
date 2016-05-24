package com.example.afanasenko.lesson5;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DialogActivity extends Activity {

    private String app_pn;
    private String app_name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Intent intent=getIntent();

        app_name=intent.getStringExtra("APP_NAME");
        app_pn = intent.getStringExtra("APP_PN");

        TextView textView=(TextView)findViewById(R.id.app_description_text);
        textView.setText(app_name);
    }

    public void onClickDellButtonDialog(View view) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:"+app_pn));
        startActivity(intent);
    }

    public void onClickCloseButtonDialog(View view) {
        finish();
    }
}
