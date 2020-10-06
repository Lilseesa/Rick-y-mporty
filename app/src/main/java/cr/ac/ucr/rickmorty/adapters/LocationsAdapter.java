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
import cr.ac.ucr.rickmorty.models.Location;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.ViewHolder> implements LocationItemClickListener{

    private Context context;
    private ArrayList<Location> locations;

    public LocationsAdapter(Context context, ArrayList<Location> locations) {
        this.context = context;
        this.locations = locations;
    }

    public LocationsAdapter(Context context){
        this.context = context;
        this.locations = new ArrayList<>();
    }

    public void addLocations(ArrayList<Location> locations) {
        this.locations.addAll(locations);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull LocationsAdapter.ViewHolder holder, int position) {
        Location location = locations.get(position);

        holder.tvName.setText(location.getName());
        holder.tvType.setText(location.getType());
        holder.tvDimension.setText(location.getDimension());
    }

    @Override
    public int getItemCount() {
        return locations != null ? locations.size() : 0;
    }

    @NonNull
    @Override
    public LocationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onClick(View view, int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CardView cvEpisodeCard;

        private final TextView tvName;
        private final TextView tvType;
        private final TextView tvDimension;

        private ItemClickListener listener;

        public ViewHolder(@NonNull View view) {
            super(view);
            cvEpisodeCard = view.findViewById(R.id.cv_location_card);
            tvName = view.findViewById(R.id.tv_name);
            tvType = view.findViewById(R.id.tv_type);
            tvDimension = view.findViewById(R.id.tv_dimension);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getLayoutPosition());
        }
    }
}

interface LocationItemClickListener{
    void onClick(View view, int position);
}