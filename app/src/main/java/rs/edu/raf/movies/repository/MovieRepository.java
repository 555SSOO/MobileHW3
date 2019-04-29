package rs.edu.raf.movies.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import rs.edu.raf.movies.repository.web.api.MovieApi;
import rs.edu.raf.movies.repository.web.model.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.edu.raf.movies.repository.web.model.Resource;

public class MovieRepository {

    private MovieApi mMovieApi;

    private MutableLiveData<Resource<List<Movie>>> mMovieLiveData;

    public MovieRepository() {
        mMovieApi = new MovieApi();
        mMovieLiveData = new MutableLiveData<>();
    }

    public LiveData <Resource<List<Movie>>> getMovies() {
        fetchMovieDataFromServer();
        return mMovieLiveData;
    }

    private void fetchMovieDataFromServer() {
        mMovieApi.getMovies().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                Resource<List<Movie>> resource = new Resource<>(response.body(), true);
                mMovieLiveData.setValue(resource);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Resource<List<Movie>> resource = new Resource<>(new ArrayList<>(), false);
                mMovieLiveData.setValue(resource);
            }
        });
    }

}
