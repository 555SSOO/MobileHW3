package rs.edu.raf.movies.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import rs.edu.raf.movies.R;
import rs.edu.raf.movies.repository.web.model.Movie;
import rs.edu.raf.movies.viewmodel.MainViewModel;

public class FirstFragment extends Fragment {

    private MainViewModel mViewModel;
    private TextView mContentTv;


    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        mContentTv = view.findViewById(R.id.tv_fragment_first_content);
        EditText editText = view.findViewById(R.id.et_fragment_first_add);
        Button button = view.findViewById(R.id.btn_fragmnet_first_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                Movie movie = new Movie(name);
        }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

    }
}
