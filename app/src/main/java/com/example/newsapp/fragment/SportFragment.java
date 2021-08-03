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

import java.util.ArrayList;
import java.util.List;

public class SportFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {

    private static String SPORT_REQUEST_URL = "https://content.guardianapis.com/search?q=sport&show-fields=thumbnail,trailText&api-key=31aaf3ce-31cc-4a14-8c2a-a26c3a136453";
    private static final String LOG_TAG = SportFragment.class.getName();
    private static final int LOADER_NUMBER = 1;

    private NewsAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private View mLoadingIndicator;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public SportFragment() {
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment, container, false);

        RecyclerView mRecyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);

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


    private boolean isConnected() {

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
            mLoadingIndicator.setVisibility(View.GONE);
            LoaderManager loaderManager = LoaderManager.getInstance(this);
            loaderManager.initLoader(LOADER_NUMBER, null, this);
        } else {
            mLoadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText("No Internet");
        }
    }

    private void restartLoader(boolean isConnected) {
        if (isConnected) {
            LoaderManager loaderManager = LoaderManager.getInstance(this);
            loaderManager.restartLoader(LOADER_NUMBER, null, this);
            Toast.makeText(getActivity(), "update just now",
                    Toast.LENGTH_SHORT).show();
        } else {

            mLoadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText("NO INTERNET CONNECTION");
            mSwipeRefreshLayout.setVisibility(View.GONE);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> newsData) {


        mLoadingIndicator.setVisibility(View.GONE);
        if (newsData != null && !newsData.isEmpty()) {
            List<News> newsList = new ArrayList<>();
            for (int i = 0; i < newsData.size(); i++) {
                if (newsData.get(i).getSection().contains("Sport")) {
                    newsList.add(newsData.get(i));
                }
            }
            mAdapter.addAll(newsList);
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
        return new NewsLoader(getActivity(), SPORT_REQUEST_URL);
    }

}
