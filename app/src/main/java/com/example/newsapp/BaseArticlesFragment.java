package com.example.newsapp;
import com.example.newsapp.utils.Constants;
import com.example.newsapp.utils.NewsLoader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
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
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newsapp.utils.NewsLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static androidx.core.content.ContextCompat.getSystemService;


//public class BaseArticlesFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {

  // private static final String LOG_TAG = BaseArticlesFragment.class.getName();

  // private static final int LOADER_NUMBER=1;
//
  //  /** Adapter for the list of news */
  //  private NewsAdapter mAdapter;
//
  //  /** TextView that is displayed when the recycler view is empty */
  //  private TextView mEmptyStateTextView;
//
  //  /** Loading indicator that is displayed before the first load is completed */
  //  private View mLoadingIndicator;
//
  //  /** The {@link SwipeRefreshLayout} that detects swipe gestures and
    // * triggers callbacks in the app.
    // */
 // private SwipeRefreshLayout mSwipeRefreshLayout;
//
  //  @Nullable
    //@Override
 // public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      //      View rootView=inflater.inflate(R.layout.fragment,container,false);
//
      //          RecyclerView  mRecyclerView=rootView.findViewById(R.id.recycler_view);
//
   //    LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
     /// mRecyclerView.setHasFixedSize(true);
//

  //      // Set the layoutManager on the {@link RecyclerView}
     //  mRecyclerView.setLayoutManager(layoutManager);

        // Find the SwipeRefreshLayout
       // mSwipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout);
        // Set the color scheme of the SwipeRefreshLayout
       // mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.swipe_color_1),
         //       getResources().getColor(R.color.swipe_color_2),
           //     getResources().getColor(R.color.swipe_color_3),
             //   getResources().getColor(R.color.swipe_color_4));
//
  //      // Set up OnRefreshListener that is invoked when the user performs a swipe-to-refresh gesture.
    ///    mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
       //     @Override
         //   public void onRefresh() {
           //     Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
             //   // restart the loader
               // initiateRefresh();
                //T//oast.makeText(getActivity(), "update just now",
                  //      Toast.LENGTH_SHORT).show();
            //}/
        //});
        // Find the loading indicator from the layout
        //mLoadingIndicator = rootView.findViewById(R.id.loading_indicator);

        // Find the empty view from the layout and set it on the new recycler view
        ///mEmptyStateTextView = rootView.findViewById(R.id.empty_view);


      //initializeLoader(isConnected());
        // Create a new adapter that takes an empty list of news as input
       // mAdapter = new NewsAdapter(new ArrayList<News>(),getActivity());

        // Set the adapter on the {@link recyclerView}
        //mRecyclerView.setAdapter(mAdapter);

        // Check for network connectivity and initialize the loader


        //return rootView;
  //}

    //private void initiateRefresh() {
      //restartLoader(isConnected());
    //}

    //Check for network connectivity.
     //       */
    //private boolean isConnected() {

      //  ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
///
 ///       if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
   //         return true;
//
  //      } else {
    //        return false;
      //  }

//    }
   


  // public  void initializeLoader(boolean isConnected){
    //  if(isConnected){
      ///    mEmptyStateTextView.setVisibility(View.INVISIBLE);
         // mLoadingIndicator.setVisibility(View.GONE);
          //LoaderManager loaderManager=LoaderManager.getInstance(this);
          //lo/aderManager.initLoader(LOADER_NUMBER,null,this);
     // }
      //else{
        //  mLoadingIndicator.setVisibility(View.GONE);
          //mEmptyStateTextView.setText("No Internet");
      //}
  // }
    //private void restartLoader(boolean isConnected) {
      //  if (isConnected) {
        //    // Get a reference to the LoaderManager, in order to interact with loaders.
          //  LoaderManager loaderManager = getLoaderManager();
            //// Restart the loader with the NEWS_LOADER_ID
            //l/o//aderManager.restartLoader(LOADER_NUMBER, null, this);
        //} else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
         //   mLoadingIndicator.setVisibility(View.GONE);
          //  // Update empty state with no connection error message and image
           // mEmptyStateTextView.setVisibility(View.VISIBLE);
            //m/EmptyStateTextView.setText("NO INTERNET CONNECTION");
          //  mEmptyStateTextView.setCompoundDrawablesWithIntrinsicBounds(Constants.DEFAULT_NUMBER,
            //        R.drawable.ic_network_check,Constants.DEFAULT_NUMBER,Constants.DEFAULT_NUMBER);

            // Hide SwipeRefreshLayout
         //mSwipeRefreshLayout.setVisibility(View.GONE);
        //}
    //}





// Create a new loader for the given URL
;

    //@/NonNull
    //@Override
    //public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {
      //  return new NewsLoader(getContext(),Constants.NEWS_REQUEST_URL);}
//
  //  @Override
    //public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> newsData) {
//

  //    mLoadingIndicator.setVisibility(View.GONE);
    //  mEmptyStateTextView.setVisibility(View.GONE);
      //mEmptyStateTextView.setText("no news found");

        // If there is a valid list of {@link News}, then add them to the adapter's
        // data set. This will trigger the recyclerView to update.
        //if (newsData != null && !newsData.isEmpty()) {
          //  mAdapter.addAll(newsData);
//
  //      }

        // Hide the swipe icon animation when the loader is done refreshing the data
    ///  //  mSwipeRefreshLayout.setRefreshing(false);
  //}//''
    //@Override
    //p/ublic void onLoaderReset(@NonNull Loader<List<News>> loader) {
      //     mAdapter.clearAll();
    //}


//}
