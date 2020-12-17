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
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newsapp.utils.Constants;
import com.example.newsapp.utils.NewsLoader;

import java.util.ArrayList;
import java.util.List;

public class BusinessFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>>{
   private static String BUSINESS_REQUEST_URL="https://content.guardianapis.com/search?q=business&show-fields=trailText,thumbnail&api-key=31aaf3ce-31cc-4a14-8c2a-a26c3a136453";

    private static final String LOG_TAG = BusinessFragment.class.getName();

    private static final int LOADER_NUMBER=1;

    private NewsAdapter mAdapter;

    private TextView mEmptyStateTextView;
    //

    private View mLoadingIndicator;

    // */
    private SwipeRefreshLayout mSwipeRefreshLayout;
    //
  //  public static final String NEWS_REQUEST_URL = "https://content.guardianapis.com/search?show-fields=trailText,headline,thumbnail&api-key=31aaf3ce-31cc-4a14-8c2a-a26c3a136453";







    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment,container,false);
//
        RecyclerView mRecyclerView=rootView.findViewById(R.id.recycler_view);
//
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
//


        mRecyclerView.setLayoutManager(layoutManager);


        mSwipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.swipe_color_1),
                getResources().getColor(R.color.swipe_color_2),
                getResources().getColor(R.color.swipe_color_3),
                getResources().getColor(R.color.swipe_color_4));


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

        mLoadingIndicator = rootView.findViewById(R.id.loading_indicator);


        mEmptyStateTextView = rootView.findViewById(R.id.empty_view);



        mAdapter = new NewsAdapter(new ArrayList<News>(),getActivity());

        mRecyclerView.setAdapter(mAdapter);



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

            LoaderManager loaderManager = getLoaderManager();
            // Restart the loader with the NEWS_LOADER_ID
            loaderManager.restartLoader(LOADER_NUMBER, null, this);

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
           for(int i=0;i<newsData.size();i++){
              News current=newsData.get(i);
             current.setSection("Business");
            }

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
        return new NewsLoader(getActivity(),BUSINESS_REQUEST_URL);
    }

}
