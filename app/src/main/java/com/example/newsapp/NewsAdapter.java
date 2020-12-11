package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.ims.ImsManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.utils.NewsLoader;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder>{
    private Context mContext;
    private List<News> mNewsList;
   // private SharedPreferences sharedPrefs;

private String TAG=NewsAdapter.class.getSimpleName();
    public NewsAdapter(List<News>newsList,Context context) {

        mContext = context;
        mNewsList = newsList;
    }
    @Override
    public NewsAdapter.NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item, parent, false);
        return new NewsHolder(v);
    }



    @Override
    public int getItemCount() {
        return mNewsList.size();
    }


    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
       final News currentNews=mNewsList.get(position);

       String s=currentNews.getTitle();
       Log.v(TAG, "TITLE IS "+ s);
        holder.mTitleTextView.setText(currentNews.getTitle());

      //  holder.mAuthorTextView.setText(currentNews.getAuthor());
        holder.mSectionView.setText(currentNews.getSection());
        String section=currentNews.getSection();
        holder.mDateTextView.setText(currentNews.getDate());
      //  holder.SetTitle(News.getTitle());
        if(currentNews.getAuthor()==null){
            holder.mAuthorTextView.setVisibility(View.GONE);
        }
        else{
            holder.mAuthorTextView.setVisibility(View.VISIBLE);
            holder.mAuthorTextView.setText(currentNews.getAuthor());
        }
        // Get string of the trailTextHTML and convert Html text to plain text
        // and set the plain text on the textView
        if(currentNews.getTrailTextHtml()!=null){
        holder.trailTextView.setText(Html.fromHtml(Html.fromHtml(currentNews.getTrailTextHtml()).toString()));}

        if (currentNews.getThumbnail() == null) {
            holder.mthumbnailView.setVisibility(View.GONE);
        } else {
            holder.mthumbnailView.setVisibility(View.VISIBLE);
            // Load thumbnail with glide
            Glide.with(mContext.getApplicationContext())
                    .load(currentNews.getThumbnail())
                    .into(holder.mthumbnailView);
        }

        holder.mShareImageView.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                      }
                                                  });
                // holder.SetAuthor(News.getAuthor());
                // You can set click listeners to individual items in the viewHolder here
                // make sure you pass down the listener or make the Data members of the viewHolder public

                // Set an OnClickListener to open a website with more information about the selected article

    }
    /**
     *  Clear all data (a list of {@link News} objects)
     */
    public void clearAll() {
        mNewsList.clear();
        notifyDataSetChanged();
    }

    /**
     * Add  a list of {@link News}
     * @param newsList is the list of news, which is the data source of the adapter
     */
    public void addAll(List<News> newsList) {
        mNewsList.clear();
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }

    // This is your ViewHolder class that helps to populate data to the view
  public  class NewsHolder extends RecyclerView.ViewHolder {
           private TextView mTitleTextView;
           private TextView  mSectionView;
           private TextView mAuthorTextView;
           private TextView mDateTextView;
           private CardView mCardView;
           private TextView trailTextView;
           private ImageView mthumbnailView;
           private ImageView mShareImageView;
        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            this.mTitleTextView= itemView.findViewById(R.id.title_card);
            this.mAuthorTextView=itemView.findViewById(R.id.author_card);
            this.mSectionView=itemView.findViewById(R.id.section_card);
            this.mDateTextView=itemView.findViewById(R.id.date_card);
            this.mCardView=itemView.findViewById(R.id.card_view);
            this.trailTextView=itemView.findViewById(R.id.trail_text_card);
            this.mthumbnailView=itemView.findViewById(R.id.thumbnail_image_card);
            this.mShareImageView=itemView.findViewById(R.id.share_image_card);
        }

  //   public  void bind(String title,String section,String author,String date,String) //public void SetTitle(String title){
        //    mTitleTextView.setText(News.getTitle());
        //}
        //public void SetSection(String section){
          //  mSectionView.setText(News.getSection());
        //}
        //public void SetAuthor(String author) {
          //  if (author!= null) {
            //    mAuthorTextView.setText(News.getAuthor());}
            //else{
              //    mAuthorTextView.setVisibility(View.GONE);
                //}

            //}

        //public void SetDate(String Date){
          //  mDateTextView.setText(News.getDate());
        //}



    }
}
