package cr.ac.ucr.rickmorty.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cr.ac.ucr.rickmorty.R;
import cr.ac.ucr.rickmorty.models.Episode;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.ViewHolder> implements ItemClickListener{

    private Context context;
    private ArrayList<Episode> episodes;

    public EpisodesAdapter(Context context, ArrayList<Episode> episodes) {
        this.context = context;
        this.episodes = episodes;
    }

    public EpisodesAdapter(Context context){
        this.context = context;
        this.episodes = new ArrayList<>();
    }

    @NonNull
    @Override
    public EpisodesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_episode, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodesAdapter.ViewHolder holder, int position) {
        Episode episode = episodes.get(position);

        holder.tvName.setText(episode.getName());
        holder.tvEpisode.setText(episode.getEpisode());
        holder.tvAirDate.setText(episode.getAirDate());

    }

    @Override
    public int getItemCount() {
        return episodes != null ? episodes.size() : 0;
    }

    public void addEpisodes(ArrayList<Episode> episodes) {
        this.episodes.addAll(episodes);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view, int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CardView cvEpisodeCard;

        private final TextView tvName;
        private final TextView tvEpisode;
        private final TextView tvAirDate;

        private ItemClickListener listener;

        public ViewHolder(@NonNull View view) {
            super(view);
            cvEpisodeCard = view.findViewById(R.id.cv_episode_card);
            tvName = view.findViewById(R.id.tv_name);
            tvEpisode = view.findViewById(R.id.tv_episode);
            tvAirDate = view.findViewById(R.id.tv_air_date);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getLayoutPosition());
        }
    }
}
