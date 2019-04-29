package rs.edu.raf.movies.repository.web.service;


import rs.edu.raf.movies.repository.web.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {

    @GET("films")
    public Call<List<Movie>> getMovies();

}
