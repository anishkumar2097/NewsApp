package com.example.newsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newsapp.utils.Constants;
import com.example.newsapp.utils.NewsLoader;

import java.util.ArrayList;
import java.util.List;

public class SportFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {
 //   private static String SPORT_REQUEST_URL="https://newsapi.org/v2/everything?domains=indianexpress.com&q=Sports&apiKey=a1307f33512645af9d6c065510b5ed5a";
    private static String SPORT_REQUEST_URL="https://content.guardianapis.com/search?q=sport&show-fields=thumbnail,trailText&api-key=31aaf3ce-31cc-4a14-8c2a-a26c3a136453";




    private static final String LOG_TAG = SportFragment.class.getName();

    private static final int LOADER_NUMBER=1;
    //https://content.guardianapis.com/search?q=sport&show-fields=thumbnail,trailText&api-key=31aaf3ce-31cc-4a14-8c2a-a26c3a136453
    //  /** Adapter for the list of news */
    private NewsAdapter mAdapter;
    //
    //  /** TextView that is displayed when the recycler view is empty */
    private TextView mEmptyStateTextView;
    //
    //  /** Loading indicator that is displayed before the first load is completed */
    private View mLoadingIndicator;
    //
    //  /** The {@link SwipeRefreshLayout} that detects swipe gestures and
    // * triggers callbacks in the app.
    // */
    private SwipeRefreshLayout mSwipeRefreshLayout;
    //
    public static final String NEWS_REQUEST_URL = "https://content.guardianapis.com/search?show-fields=trailText,headline,thumbnail&api-key=31aaf3ce-31cc-4a14-8c2a-a26c3a136453";




    public SportFragment(){}


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment,container,false);
//
        RecyclerView mRecyclerView=rootView.findViewById(R.id.recycler_view);
//
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
//

        //      // Set the layoutManager on the {@link RecyclerView}
        mRecyclerView.setLayoutManager(layoutManager);

        // Find the SwipeRefreshLayout
        mSwipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout);
        // Set the color scheme of the SwipeRefreshLayout
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.swipe_color_1),
                getResources().getColor(R.color.swipe_color_2),
                getResources().getColor(R.color.swipe_color_3),
                getResources().getColor(R.color.swipe_color_4));

        // Set up OnRefreshListener that is invoked when the user performs a swipe-to-refresh gesture.
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                // restart the loader
                initiateRefresh();
                Toast.makeText(getActivity(), "update just now",
                        Toast.LENGTH_SHORT).show();
            }
        });
        // Find the loading indicator from the layout
        mLoadingIndicator = rootView.findViewById(R.id.loading_indicator);

        // Find the empty view from the layout and set it on the new recycler view
        mEmptyStateTextView = rootView.findViewById(R.id.empty_view);


        initializeLoader(isConnected());
        // Create a new adapter that takes an empty list of news as input
        mAdapter = new NewsAdapter(new ArrayList<News>(),getActivity());

        // Set the adapter on the {@link recyclerView}
        mRecyclerView.setAdapter(mAdapter);

        // Check for network connectivity and initialize the loader


        return rootView;
    }
    private void initiateRefresh() {
        restartLoader(isConnected());
    }

    //Check for network connectivity.
    //       */
    private boolean isConnected() {

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            return true;

        } else {
            return false;
        }

    }
    public  void initializeLoader(boolean isConnected){
        if(isConnected){
            mEmptyStateTextView.setVisibility(View.INVISIBLE);
            mLoadingIndicator.setVisibility(View.GONE);
            LoaderManager loaderManager=LoaderManager.getInstance(this);
            loaderManager.initLoader(LOADER_NUMBER,null,this);
        }
        else{
            mLoadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText("No Internet");
        }
    }
    private void restartLoader(boolean isConnected) {
        if (isConnected) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            // Restart the loader with the NEWS_LOADER_ID
            loaderManager.restartLoader(LOADER_NUMBER, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            mLoadingIndicator.setVisibility(View.GONE);
            // Update empty state with no connection error message and image
            mEmptyStateTextView.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText("NO INTERNET CONNECTION");
            //  mEmptyStateTextView.setCompoundDrawablesWithIntrinsicBounds(Constants.DEFAULT_NUMBER,
            //        R.drawable.ic_network_check,Constants.DEFAULT_NUMBER,Constants.DEFAULT_NUMBER);

            // Hide SwipeRefreshLayout
            mSwipeRefreshLayout.setVisibility(View.GONE);
        }
    }
    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> newsData) {


        mLoadingIndicator.setVisibility(View.GONE);
        mEmptyStateTextView.setVisibility(View.GONE);
        mEmptyStateTextView.setText("no news found");

        // If there is a valid list of {@link News}, then add them to the adapter's
        // data set. This will trigger the recyclerView to update.
        if (newsData != null && !newsData.isEmpty()) {
            mAdapter.addAll(newsData);

        }

        // Hide the swipe icon animation when the loader is done refreshing the data
        mSwipeRefreshLayout.setRefreshing(false);
    }
    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        mAdapter.clearAll();
    }


    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(getActivity(),SPORT_REQUEST_URL);
    }

}
