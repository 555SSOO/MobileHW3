package rs.edu.raf.movies.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import rs.edu.raf.movies.repository.web.model.Movie;
import rs.edu.raf.movies.repository.MovieRepository;
import rs.edu.raf.movies.repository.web.model.Resource;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private MediatorLiveData<Resource<List<Movie>>> mMovieMediatorLiveData;
    private LiveData<Resource<List<Movie>>> mMovieLiveData;

    private MovieRepository mMovieRepository;

    public MainViewModel(Application application) {
        super(application);
        mMovieRepository = new MovieRepository();
        mMovieMediatorLiveData = new MediatorLiveData<>();
        mMovieLiveData = new MutableLiveData<>();
    }

    public LiveData<Resource<List<Movie>>> getMovies() {
        return mMovieMediatorLiveData;
    }

    // We have to call this from second fragment's onResume
    // because when we start the app, fragment is  observing mediatorLiveData
    // which still doesn't have any liveData source attached to it
    public void refreshMovies() {
        mMovieMediatorLiveData.removeSource(mMovieLiveData);
        mMovieLiveData = mMovieRepository.getMovies();
        mMovieMediatorLiveData.addSource(mMovieLiveData,
                listResource -> mMovieMediatorLiveData.setValue(listResource));
    }

    public void setFilter(String filter, String filter_param) {
        filter = filter.toLowerCase();
        List<Movie> filteredList = new ArrayList<>();
        for (Movie movie: mMovieRepository.getMovies().getValue().getData()) {

            if(filter_param.equalsIgnoreCase("title")){
                if(movie.getmTitle().toLowerCase().startsWith(filter)){
                    filteredList.add(movie);
                }
            }
            else if (filter_param.equalsIgnoreCase("year")){
                if(movie.getmYear().toLowerCase().startsWith(filter)){
                    filteredList.add(movie);
                }
            }
            else if (filter_param.equalsIgnoreCase("score")){
                if(movie.getmScore().toLowerCase().startsWith(filter)){
                    filteredList.add(movie);
                }
            }


        }

        mMovieLiveData.getValue();

//        mMovieLiveData.setValue(new Resource<>(filteredList, true));
    }

}
