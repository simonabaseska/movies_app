package com.feit.myapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    //Member variables
    private GradientDrawable mGradientDrawable;
    private ArrayList<Movies> mMoviesData;
    private Context mContext;


    MoviesAdapter(Context context, ArrayList<Movies> moviesData) {
        this.mMoviesData = moviesData;
        this.mContext = context;

        //Prepare gray placeholder
        mGradientDrawable = new GradientDrawable();
        mGradientDrawable.setColor(Color.GRAY);

        //Make the placeholder same size as the images
        Drawable drawable = ContextCompat.getDrawable
                (mContext,R.drawable.a_star_is_born);
        if(drawable != null) {
            mGradientDrawable.setSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }


    /**
     * Required method for creating the viewholder objects.
     * @param parent The ViewGroup into which the new View is added after it is
     *               bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly create SportsViewHolder.
     */
    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoviesViewHolder(mContext, LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false), mGradientDrawable);
    }

    /**
     * Required method that binds the data to the viewholder.
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {

        //Get the current sport
        Movies currentMovies = mMoviesData.get(position);

        //Bind the data to the views
        holder.bindTo(currentMovies);

    }


    /**
     * Required method for determining the size of the data set.
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mMoviesData.size();
    }


    /**
     * SportsViewHolder class that represents each row of data in the RecyclerView
     */
    static class MoviesViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        //Member Variables for the holder data
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mMoviesImage;
        private Context mContext;
        private TextView mText;
        private Movies mCurrentMovies;
        private GradientDrawable mGradientDrawable;

        /**
         * Constructor for the SportsViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        MoviesViewHolder(Context context, View itemView, GradientDrawable gradientDrawable) {
            super(itemView);

            //Initialize the views
            mTitleText = (TextView)itemView.findViewById(R.id.title);
            mInfoText = (TextView)itemView.findViewById(R.id.subTitle);
            mMoviesImage = (ImageView)itemView.findViewById(R.id.moviesImage);
            mText=(TextView)itemView.findViewById(R.id.subTitleDetail);

            mContext = context;
            mGradientDrawable = gradientDrawable;

            //Set the OnClickListener to the whole view
            itemView.setOnClickListener(this);
        }

        void bindTo(Movies currentMovies){
            //Populate the textviews with data
            mTitleText.setText(currentMovies.getTitle());
            mInfoText.setText(currentMovies.getInfo());
            mText.setText(currentMovies.getText());

            //Get the current sport
            mCurrentMovies = currentMovies;



            //Load the images into the ImageView using the Glide library
            Glide.with(mContext).load(currentMovies.
                    getImageResource()).placeholder(mGradientDrawable).into(mMoviesImage);
        }

        @Override
        public void onClick(View view) {

            //Set up the detail intent
            Intent detailIntent = Movies.starter(mContext, mCurrentMovies.getTitle(),
                    mCurrentMovies.getImageResource(),(String) mText.getText());


            //Start the detail activity
            mContext.startActivity(detailIntent);
        }
    }

}
