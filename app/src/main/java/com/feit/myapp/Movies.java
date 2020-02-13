package com.feit.myapp;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.DrawableRes;

public class Movies {
    //Member variables representing the title, image and information about the movie
    private final String title;
    private final String info;
    private final int imageResource;
    private final String text;
    static final String TITLE_KEY = "Title";
    static final String IMAGE_KEY = "Image Resource";
    static final String TEXT_KEY= "Text";

    /**
     * Constructor for the Mvoies class data model
     * @param title The name if the movie.
     * @param info Information about the movie.
     * @param imageResource The resource for the movie image
     */
    Movies(String title, String info, int imageResource,String text) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
        this.text=text;
    }

    /**
     * Gets the title of the movie
     * @return The title of the movie.
     */
    String getTitle() {
        return title;
    }
    /**
     * Gets the info about the movie
     * @return The info about the movie.
     */
    String getInfo() {
        return info;
    }

    /**
     * Gets the resource for the image
     * @return The resource for the image
     */
    int getImageResource() {
        return imageResource;
    }
     String getText(){
        return text;
    }
    /**
     * Method for creating  the Detail Intent
     * @param context Application context, used for launching the Intent
     * @param title The title of the current Movies
     * @param imageResId The image resource associated with the current movies
     * @return The Intent containing the extras about the movie, launches Detail activity
     */
    static Intent starter(Context context, String title, @DrawableRes int imageResId, String text) {
        Intent detailIntent = new Intent(context, DetailActivity.class);
        detailIntent.putExtra(TITLE_KEY, title);
        detailIntent.putExtra(IMAGE_KEY, imageResId);
        detailIntent.putExtra(TEXT_KEY,text);
        return detailIntent;
    }

}
