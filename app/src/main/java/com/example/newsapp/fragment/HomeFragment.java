package com.example.newsapp.fragment;

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

import com.example.newsapp.News;
import com.example.newsapp.NewsAdapter;
import com.example.newsapp.R;
import com.example.newsapp.utils.NewsLoader;

import java.util.List;

public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {
    //private static String BUSINESS_REQUEST_URL="https://content.guardianapis.com/sections?q=business&show-fields=trailText&api-key=31aaf3ce-31cc-4a14-8c2a-a26c3a136453";

    private static final String LOG_TAG = HomeFragment.class.getName();
    private static final int LOADER_NUMBER = 1;
    private NewsAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private View mLoadingIndicator;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    public static final String NEWS_REQUEST_URL = "https://content.guardianapis.com/search?show-fields=trailText,headline,thumbnail&api-key=31aaf3ce-31cc-4a14-8c2a-a26c3a136453";


    public HomeFragment() {
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment, container, false);

        RecyclerView mRecyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);

        // Find the SwipeRefreshLayout
        mSwipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                // restart the loader

                initiateRefresh();

            }
        });

        mLoadingIndicator = rootView.findViewById(R.id.loading_indicator);

        mEmptyStateTextView = rootView.findViewById(R.id.empty_view);
        initializeLoader(isConnected());

        mAdapter = new NewsAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);


        return rootView;
    }

    private void initiateRefresh() {
        restartLoader(isConnected());
    }


    public boolean isConnected() {

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            return true;

        } else {
            return false;
        }

    }

    public void initializeLoader(boolean isConnected) {
        if (isConnected) {
            mEmptyStateTextView.setVisibility(View.INVISIBLE);
            mLoadingIndicator.setVisibility(View.VISIBLE);
            LoaderManager loaderManager = LoaderManager.getInstance(this);
            loaderManager.initLoader(LOADER_NUMBER, null, this);
            Log.v(LOG_TAG, "LOADER NUMBER IS" + LOADER_NUMBER);
        } else {
            mLoadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText("Internet Not Available");
        }
    }

    private void restartLoader(boolean isConnected) {

        if (isConnected) {
            mLoadingIndicator.setVisibility(View.VISIBLE);

            LoaderManager loaderManager = LoaderManager.getInstance(this);
            loaderManager.restartLoader(LOADER_NUMBER, null, this);
            Toast.makeText(getActivity(), "updated just now",
                    Toast.LENGTH_SHORT).show();

        } else {

            mLoadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText("NO INTERNET CONNECTION");


        }

    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> newsData) {


        mLoadingIndicator.setVisibility(View.GONE);
        if (newsData != null && !newsData.isEmpty()) {

            mAdapter.addAll(newsData);


        } else {
            mEmptyStateTextView.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText("no news found");

        }

        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        mAdapter.clearAll();
    }


    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {

        return new NewsLoader(getActivity(), NEWS_REQUEST_URL);
    }

}

