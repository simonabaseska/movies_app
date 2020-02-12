package com.feit.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<Movies> mMoviesData;
    private MoviesAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the RecyclerView
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        //Get the appropriate column count
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);

        //Set the Layout Manager
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        //Initialize the ArrayList that will contain the data
        mMoviesData = new ArrayList<>();

        //Initialize the adapter and set it ot the RecyclerView
        mAdapter = new MoviesAdapter(this, mMoviesData);
        mRecyclerView.setAdapter(mAdapter);

        //Get the data
        initializeData();

        // If there is more than one column, disable swipe to dismiss
        int swipeDirs;
        if(gridColumnCount > 1){
            swipeDirs = 0;
        } else {
            swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }


        //Helper class for creating swipe to dismiss and drag and drop functionality
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
                (ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN
                        | ItemTouchHelper.UP, swipeDirs) {

            /**
             * Method that defines the drag and drop functionality
             * @param recyclerView The RecyclerView that contains the list items
             * @param viewHolder The SportsViewHolder that is being moved
             * @param target The SportsViewHolder that you are switching the original one with.
             * @return returns true if the item was moved, false otherwise
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {

                //Get the from and to position
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                //Swap the items and notify the adapter
                Collections.swap(mMoviesData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            /**
             * Method that defines the swipe to dismiss functionality
             * @param viewHolder The viewholder being swiped
             * @param direction The direction it is swiped in
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                //Remove the item from the dataset
                mMoviesData.remove(viewHolder.getAdapterPosition());

                //Notify the adapter
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        //Attach the helper to the RecyclerView
        helper.attachToRecyclerView(mRecyclerView);
    }


    /**
     * Method for initializing the sports data from resources.
     */
    private void initializeData() {
        //Get the resources from the XML file
        String[] moviesList = getResources().getStringArray(R.array.movies_titles);
        String[] moviesInfo = getResources().getStringArray(R.array.movies_info);
        String [] moviesListInfo=getResources().getStringArray(R.array.filler_text);
        TypedArray moviesImageResources = getResources().obtainTypedArray(R.array.movies_images);
        //Clear the existing data (to avoid duplication)
        mMoviesData.clear();


        //Create the ArrayList of Sports objects containing the titles,
        // images and information about each sport
        for(int i=0; i<moviesList.length; i++){
            mMoviesData.add(new Movies(moviesList[i], moviesInfo[i],
                    moviesImageResources.getResourceId(i,0),moviesListInfo[i]));
        }

        //Recycle the typed array
        moviesImageResources.recycle();

        //Notify the adapter of the change
        mAdapter.notifyDataSetChanged();
    }

    /**
     * onClick method for th FAB that resets the data
     * @param view The button view that was clicked
     */

    public void resetMovies(View view) {
        initializeData();
    }
}
