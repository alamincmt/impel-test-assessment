package com.alamincmt.news.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alamincmt.news.R;
import com.alamincmt.news.utils.Utils;
import com.bumptech.glide.Glide;

import java.text.ParseException;

public class DetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView source;
    TextView title;
    TextView authorAndPublishedDate;
    TextView description;

    String authorID;

    private TextView tvBookmark;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = findViewById(R.id.imgv_top);
        source = findViewById(R.id.tv_source_name);
        title = findViewById(R.id.tv_title);
        authorAndPublishedDate = findViewById(R.id.tv_author_and_published_date);
        description = findViewById(R.id.tv_description);

        authorID = getIntent().getExtras().getString("authorID");

        Glide.with(this).load(getIntent().getExtras().getString("imageUri")).into(imageView);
        source.setText(getIntent().getExtras().getString("sourceName"));
        title.setText(getIntent().getExtras().getString("title"));

        if(getIntent().getExtras().getString("author") == null || getIntent().getExtras().getString("author").equals("null")){
            authorAndPublishedDate.setText(Utils.getInstance(getApplicationContext()).convertDate(getIntent().getExtras().getString("publishedDate")));
        }else{
            authorAndPublishedDate.setText(getIntent().getExtras().getString("author")+", "+Utils.getInstance(getApplicationContext()).convertDate(getIntent().getExtras().getString("publishedDate")));
        }

        description.setText(getIntent().getExtras().getString("description"));

        tvBookmark = findViewById(R.id.tvBookmark);
        tvBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.getInstance(getApplicationContext()).showToast("Bookmark Clicked...Need to implement functionality.");
            }
        });
    }
}