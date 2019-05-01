package rs.edu.raf.movies.adapter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import rs.edu.raf.movies.R;

import rs.edu.raf.movies.repository.web.model.Movie;
import rs.edu.raf.movies.util.MovieDiffCallback;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<Movie> mDataSet;
    private OnImageClickCallback mOnImageClickCallback;
    private OnItemRemoveCallback mOnItemRemoveCallback;

    public MovieAdapter() {
        mDataSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = mDataSet.get(position);

        holder.mTitleTv.setText(movie.getmTitle());
        holder.mScoreTv.setText(movie.getmScore());
        holder.mYearTv.setText(movie.getmYear());
        holder.mDirectorTv.setText(movie.getmDirector());

        int score = Integer.valueOf(movie.getmScore());
        if(score > 90) {
            holder.mScoreBackgroundIv.setImageResource(R.drawable.circle_green);
        }
        else if(score > 66) {
            holder.mScoreBackgroundIv.setImageResource(R.drawable.circle_yellow);
        }
        else {
            holder.mScoreBackgroundIv.setImageResource(R.drawable.circle_red);
        }

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setData(List<Movie> movies){
        MovieDiffCallback callback = new MovieDiffCallback(mDataSet, movies);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        mDataSet.clear();
        mDataSet.addAll(movies);
        result.dispatchUpdatesTo(this);
    }

    public class MovieHolder extends RecyclerView.ViewHolder {

        ImageView mScoreBackgroundIv;
        TextView mTitleTv;
        TextView mScoreTv;
        TextView mYearTv;
        TextView mDirectorTv;


        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            mTitleTv = itemView.findViewById(R.id.tv_list_item_name);
            mYearTv = itemView.findViewById(R.id.tv_list_item_year);
            mDirectorTv = itemView.findViewById(R.id.tv_list_item_director);
            mScoreTv = itemView.findViewById(R.id.tv_list_item_score);
            mScoreBackgroundIv = itemView.findViewById(R.id.iv_list_item_circle);

//            mAvatarIv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION){
//                        if (mOnImageClickCallback != null) {
//                            mOnImageClickCallback.onImageClick();
//                        }
//                    }
//                }
//            });
        }
    }

    public void setOnImageClickCallback(OnImageClickCallback onImageClickCallback){
        mOnImageClickCallback = onImageClickCallback;
    }

    public void setOnItemRemoveCallback (OnItemRemoveCallback onItemRemoveCallback) {
        mOnItemRemoveCallback = onItemRemoveCallback;
    }

    // Callback we use when user clicks on remove
    public interface OnItemRemoveCallback {
        void onItemRemove(Movie movie);
    }

    //Callback we use when user click on avatar mAvatarIv
    public interface OnImageClickCallback {
        void onImageClick();
    }
}
