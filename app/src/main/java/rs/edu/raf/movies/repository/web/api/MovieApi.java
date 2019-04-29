package rs.edu.raf.movies.repository.web.api;

import rs.edu.raf.movies.repository.web.model.Movie;
import rs.edu.raf.movies.repository.web.service.MovieService;
import rs.edu.raf.movies.repository.web.service.ServiceGenerator;

import java.util.List;

import retrofit2.Call;

public class MovieApi {

    private MovieService mMovieService;

    public MovieApi() {
        mMovieService = ServiceGenerator.createService(MovieService.class);
    }

    public Call<List<Movie>> getMovies() {
        return mMovieService.getMovies();
    }

}
