package muserapp.muserapp.Adapters;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import muserapp.muserapp.MainActivity;
import muserapp.muserapp.Models.Song;
import muserapp.muserapp.R;

import static com.facebook.FacebookSdk.getApplicationContext;
import static muserapp.muserapp.R.id.parent;

/**
 * Created by oculu on 04/06/2017.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {

    int l = 0;
    private ArrayList<Song> songs; //matriz obtenida por JSON
    private int itemLayout; //objeto que contiene el model de tarjeta
    private Context context;

    public SongsAdapter(ArrayList<Song> songs, int itemLayout) {
        //aqui recibe la lista JSON y el CARD
        this.songs = songs;//asigna las variables
        this.itemLayout = itemLayout;
    }

    @Override
    public SongsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //infla la vista en una lista
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        //****************doy un contexto al adapter
        context=parent.getContext();

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //declara la ubicacion en la matriz
        Song song=songs.get(position);
        //asigna el texto para cada parametro de la tarjeta
        holder.nombreTeam.setText(song.getNombre());
        holder.autorTeam.setText(song.getAutor());
        holder.discoTeam.setText(song.getDisco());
        Picasso.with(context).load(song.getImagen())
                .error(R.mipmap.ic_launcher)
                .fit()
                .centerInside()
                .into(holder.icon);

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //declaramos publicos los elementos de la tarjeta
        public TextView nombreTeam;
        public TextView autorTeam;
        public TextView discoTeam;
        public ImageView icon;

        //public ImageView portadaList;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //enlaza los elementos con el recurso que se muestra en pantalla
            nombreTeam=(TextView) itemView.findViewById(R.id.nombreteam);
            autorTeam=(TextView) itemView.findViewById(R.id.autorteam);
            discoTeam=(TextView) itemView.findViewById(R.id.discoteam);
            icon = (ImageView) itemView.findViewById(R.id.icon);

        }
        private static final int MY_NOTIFY=1000;
        @Override
        public void onClick(View v) {
            String name, band;
            int k = getPosition();
            Song song=songs.get(k);
            String url = song.getLink();
            MainActivity.Reproducion reproduccion = new MainActivity.Reproducion(songs, k);

            /**
            Bitmap mIcon1=null;
            String urlS = MainActivity.Reproducion.ImageBand();
            URL urlN = null;
            try {
                urlN = new URL(urlS);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                mIcon1 = BitmapFactory.decodeStream(urlN.openConnection().getInputStream()) ;
            } catch (IOException e) {
                //mIcon1=R.mipmap.ic_launcher;
                e.printStackTrace();
            }
            MainActivity ma = new MainActivity();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(ma.getApplicationContext());
            builder.setContentTitle(MainActivity.Reproducion.nameSong());
            builder.setContentText(MainActivity.Reproducion.nameBand());
            builder.setSmallIcon(R.drawable.ic_notif);
            builder.setLargeIcon(mIcon1);
            builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            //notificacion(builder);

            NotificationManager notificationManager = (NotificationManager) ma.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(MY_NOTIFY, builder.build());
             */
        }
    }
}
