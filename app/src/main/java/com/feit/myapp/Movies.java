package com.feit.myapp;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.DrawableRes;

public class Movies {
    //Member variables representing the title, image and information about the sport
    private final String title;
    private final String info;
    private final int imageResource;
    public final String text;
    static final String TITLE_KEY = "Title";
    static final String IMAGE_KEY = "Image Resource";
    public static String TEXT_KEY= "Text";

    /**
     * Constructor for the Sport class data model
     * @param title The name if the sport.
     * @param info Information about the sport.
     * @param imageResource The resource for the sport image
     */
    Movies(String title, String info, int imageResource,String text) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
        this.text=text;
    }

    /**
     * Gets the title of the sport
     * @return The title of the sport.
     */
    String getTitle() {
        return title;
    }
    /**
     * Gets the info about the sport
     * @return The info about the sport.
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
     * @param title The title of the current Sport
     * @param imageResId The image resource associated with the current sport
     * @return The Intent containing the extras about the sport, launches Detail activity
     */
    static Intent starter(Context context, String title, @DrawableRes int imageResId, String text) {
        Intent detailIntent = new Intent(context, DetailActivity.class);
        detailIntent.putExtra(TITLE_KEY, title);
        detailIntent.putExtra(IMAGE_KEY, imageResId);
        detailIntent.putExtra(TEXT_KEY,text);
        return detailIntent;
    }

}
