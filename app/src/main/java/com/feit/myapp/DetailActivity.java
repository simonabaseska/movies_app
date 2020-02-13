package com.feit.myapp;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;



public class DetailActivity extends AppCompatActivity {
    /**
     * Initializes the activity, filling in the data from the Intent.
     * @param savedInstanceState Contains information about the saved state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Initialize the views
        TextView moviesTitle = (TextView)findViewById(R.id.titleDetail);
        ImageView moviesImage = (ImageView)findViewById(R.id.moviesImageDetail);
        TextView  moviesText=(TextView) findViewById(R.id.subTitleDetail);

        //Get the drawable
        Drawable drawable = ContextCompat.getDrawable
                (this,getIntent().getIntExtra(Movies.IMAGE_KEY, 0));

        //Create a placeholder gray scrim while the image loads
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.GRAY);

        //Make it the same size as the image
        if(drawable!=null) {
            gradientDrawable.setSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }

        //Set the text from the Intent extra
        moviesTitle.setText(getIntent().getStringExtra(Movies.TITLE_KEY));
        moviesText.setText(getIntent().getStringExtra((Movies.TEXT_KEY)));


        //Load the image using the glide library and the Intent extra
        Glide.with(this).load(getIntent().getIntExtra(Movies.IMAGE_KEY,0))
                .placeholder(gradientDrawable).into(moviesImage);
    }

}
