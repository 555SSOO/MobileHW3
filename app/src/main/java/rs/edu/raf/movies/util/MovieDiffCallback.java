package rs.edu.raf.movies.util;

import androidx.recyclerview.widget.DiffUtil;

import rs.edu.raf.movies.repository.web.model.Movie;

import java.util.List;


public class MovieDiffCallback extends DiffUtil.Callback {

    private List<Movie> mOldList;
    private List<Movie> mNewList;

    public MovieDiffCallback(List<Movie> oldList, List<Movie> newList){
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Movie oldMovie = mOldList.get(oldItemPosition);
        Movie newMovie = mNewList.get(newItemPosition);
        return oldMovie.getmId() == newMovie.getmId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Movie oldMovie = mOldList.get(oldItemPosition);
        Movie newMovie = mNewList.get(newItemPosition);
        return oldMovie.getmTitle().equals(newMovie.getmTitle()) &&
                oldMovie.getmScore().equals(newMovie.getmScore()) &&
                oldMovie.getmYear().equals(newMovie.getmYear());
    }
}
