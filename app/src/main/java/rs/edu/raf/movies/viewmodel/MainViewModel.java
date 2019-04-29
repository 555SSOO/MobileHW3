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
}
