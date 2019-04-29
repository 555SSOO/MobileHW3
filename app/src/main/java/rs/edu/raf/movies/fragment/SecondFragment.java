package rs.edu.raf.movies.fragment;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rs.edu.raf.movies.R;
import rs.edu.raf.movies.adapter.MovieAdapter;
import rs.edu.raf.movies.repository.web.model.Movie;
import rs.edu.raf.movies.repository.web.model.Resource;
import rs.edu.raf.movies.viewmodel.MainViewModel;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static android.content.ContentValues.TAG;

public class SecondFragment extends Fragment {

    private MainViewModel mMainViewModel;
    private MovieAdapter mMovieAdapter;
    private TextView mInfoTv;
    RecyclerView recyclerView;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        mInfoTv = view.findViewById(R.id.tv_fragment_second_info);
//        EditText editText = view.findViewById(R.id.et_fragment_second_filter);
//        Button button = view.findViewById(R.id.btn_fragment_second);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String filter = editText.getText().toString();
//                Toast.makeText(SecondFragment.this.getContext(), "Filter not implemented...", Toast.LENGTH_SHORT).show();
//            }
//        });
        FloatingActionButton fab = view.findViewById(R.id.fab_fragment_second_refresh);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainViewModel.refreshMovies();
            }
        });

        recyclerView = view.findViewById(R.id.rv_fragment_second);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        mMovieAdapter = new MovieAdapter();

        mMovieAdapter.setOnImageClickCallback(new MovieAdapter.OnImageClickCallback() {
            @Override
            public void onImageClick() {
                Toast.makeText(getContext(), getString(R.string.not_implemented), Toast.LENGTH_SHORT).show();
            }
        });

        mMovieAdapter.setOnItemRemoveCallback(new MovieAdapter.OnItemRemoveCallback() {
            @Override
            public void onItemRemove(Movie movie) {
                Toast.makeText(SecondFragment.this.getContext(), getString(R.string.not_implemented), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(mMovieAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mMainViewModel.getMovies().observe(getViewLifecycleOwner(),
                new Observer<Resource<List<Movie>>>() {
                    @Override
                    public void onChanged(Resource<List<Movie>> resource) {
                        if(resource.isSuccessful()){
                            Toast.makeText(SecondFragment.this.getContext(), "Data fetched from the server!", Toast.LENGTH_LONG).show();
                            recyclerView.setVisibility(View.VISIBLE);
                            mInfoTv.setVisibility(View.GONE);

                        } else {
                            Toast.makeText(SecondFragment.this.getContext(), "Data fetch failed!", Toast.LENGTH_LONG).show();
                            recyclerView.setVisibility(View.GONE);
                            mInfoTv.setVisibility(View.VISIBLE);
                            Log.e(TAG, "Something went terribly wrong, check your connection!");
                        }
                        mMovieAdapter.setData(resource.getData());
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMainViewModel.refreshMovies();
    }
}
