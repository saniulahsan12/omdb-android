package info.saniulahsan.omdbapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdaptar extends RecyclerView.Adapter<MovieAdaptar.ViewHolder>{

    private List<MovieItem> movieItems;
    private Context context;

    private String movieTitle;
    private String imdbId;

    private OnItemClickListener mListener;

    public MovieAdaptar(Context context, List<MovieItem> movieItems) {
        this.movieItems = movieItems;
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MovieItem movieItem = movieItems.get(position);

        holder.movieTitle.setText(movieItem.getTitle());
        holder.movieYear.setText(movieItem.getYear());
        holder.movieType.setText(movieItem.getType());
        holder.imdbID.setText(movieItem.getImdbID());
        Picasso.get()
                .load(movieItem.getImageUrl())
                .resize(150, 225)
                .into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView movieTitle;
        public TextView movieYear;
        public ImageView moviePoster;
        public TextView movieType;
        public TextView imdbID;

        public ViewHolder(View itemView) {
            super(itemView);

            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            movieYear = (TextView) itemView.findViewById(R.id.movieYear);
            moviePoster = (ImageView) itemView.findViewById(R.id.moviePoster);
            movieType = (TextView) itemView.findViewById(R.id.movieType);
            imdbID = (TextView) itemView.findViewById(R.id.imdbID);

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(mListener != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            } );
        }
    }
}
