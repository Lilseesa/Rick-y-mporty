package cr.ac.ucr.rickmorty.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import cr.ac.ucr.rickmorty.R;
import cr.ac.ucr.rickmorty.adapters.EpisodesAdapter;
import cr.ac.ucr.rickmorty.api.EpisodeService;
import cr.ac.ucr.rickmorty.api.RetrofitBuilder;
import cr.ac.ucr.rickmorty.models.Episode;
import cr.ac.ucr.rickmorty.models.EpisodeResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeFragment extends Fragment {

    private final String TAG = "EpisodeFragment";
    private AppCompatActivity activity;
    private ArrayList<Episode> episodes;
    private EpisodesAdapter episodesAdapter;
    private ProgressBar pbLoading;
    private  RecyclerView rvEpisodes;

    boolean canLoad = true;
    int limit = 0;
    int page = 1;

    public EpisodeFragment() {}

    public static EpisodeFragment newInstance() {
        EpisodeFragment fragment = new EpisodeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        episodes = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_episode, container, false);

        pbLoading = view.findViewById(R.id.pb_loading);

        rvEpisodes = view.findViewById(R.id.rv_episodes);

        episodesAdapter = new EpisodesAdapter(activity);

        rvEpisodes.setAdapter(episodesAdapter);
        rvEpisodes.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);

        rvEpisodes.setLayoutManager(linearLayoutManager);

        episodesAdapter.addEpisodes(episodes);
        setupRVScrollListener(rvEpisodes, linearLayoutManager);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getEpisodesInfo(page);
    }

    private void getEpisodesInfo(int page) {
        EpisodeService episodeService = RetrofitBuilder.createService(EpisodeService.class);

        Call<EpisodeResponse> response = episodeService.getEpisodes(page);

        response.enqueue(new Callback<EpisodeResponse>() {

            @Override
            public void onResponse(Call<EpisodeResponse> call, Response<EpisodeResponse> response) {
                if(response.isSuccessful()){

                    EpisodeResponse episodeResponse = response.body();

                    ArrayList<Episode> episodes = episodeResponse.getResults();

                    episodesAdapter.addEpisodes(episodes);

                    showEpisodes(true);
                } else{
                    Log.e(TAG, "onError: " + response.errorBody());
                }

                canLoad = true;
            }

            @Override
            public void onFailure(Call<EpisodeResponse> call, Throwable t) {
                canLoad = true;
                throw new RuntimeException(t);
            }
        });
    }

    private void setupRVScrollListener(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0){

                    int totalItems= linearLayoutManager.getItemCount();
                    int pastItems = linearLayoutManager.findFirstVisibleItemPosition();
                    int visibleItems = linearLayoutManager.getChildCount();

                    if(canLoad){
                        if(pastItems + visibleItems >= totalItems){
                            page++;
                            pbLoading.setVisibility(View.VISIBLE);
                            getEpisodesInfo(page);
                        }
                    }
                }
            }
        });
    }

    public void showEpisodes(boolean setVisible){
        rvEpisodes.setVisibility(setVisible ? View.VISIBLE : View.GONE);
        pbLoading.setVisibility(!setVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }
}