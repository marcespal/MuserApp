package muserapp.muserapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import muserapp.muserapp.Fragments.AmigosFragment;
import muserapp.muserapp.Fragments.InicioFragment;
import muserapp.muserapp.Fragments.ListFragment;
import muserapp.muserapp.Models.Song;

import static muserapp.muserapp.MainActivity.Reproducion.antPlayer;
import static muserapp.muserapp.MainActivity.Reproducion.boton;
import static muserapp.muserapp.MainActivity.Reproducion.nameBand;
import static muserapp.muserapp.MainActivity.Reproducion.nameSong;
import static muserapp.muserapp.MainActivity.Reproducion.pausePlayer;
import static muserapp.muserapp.MainActivity.Reproducion.sigPlayer;
import static muserapp.muserapp.MainActivity.Reproducion.startPlayer;
import static muserapp.muserapp.R.id.imageBand;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MyBroadcast.OnMyBroadcastListener {
    int l=0;
    int pl=0;
    static TextView textName, textBand;
    static ImageButton pause, sig, ant, port;
    static ProgressBar progressBar;
    private static final int MY_NOTIFY=1000;
    /**
     * variables del broadcast NOTIFICACION

    private MyBroadcast broadcast;
    protected static final String BUTTON_PLAY= "buttonplay";
    protected static final String BUTTON_SIG= "buttonsig";
    protected static final String BUTTON_ANT= "buttonant";

    /**
     * variables del broadcast NOTIFICACION
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * ****************************************
         * ****************************************
         * ************ Aumnentado ****************
         * ****************************************
         * ****************************************
        */
        pause = (ImageButton) findViewById(R.id.pause);
        sig = (ImageButton) findViewById(R.id.sig);
        ant = (ImageButton) findViewById(R.id.ant);
        port= (ImageButton) findViewById(R.id.portada);
        textName = (TextView) findViewById(R.id.textName);
        textBand = (TextView) findViewById(R.id.textBand);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Fragment fragment = new ListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor, fragment)
                .commit();

        /**
         * NOTOFICACION

        broadcast=new MyBroadcast(this);
        IntentFilter filter1 = new IntentFilter(BUTTON_PLAY);
        IntentFilter filter2 = new IntentFilter(BUTTON_SIG);
        IntentFilter filter3 = new IntentFilter(BUTTON_ANT);
        registerReceiver(broadcast, filter1);
        registerReceiver(broadcast, filter2);
        registerReceiver(broadcast, filter3);

        /**
         * NOTOFICACION
         */

        port.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int siboton= boton();
                if (siboton==0){
                    Toast.makeText(getApplicationContext(),"Seleciona primero una canción de la lista", Toast.LENGTH_SHORT).show();
                }else {

                    if (pl == 0) {
                        Fragment fragment = new AmigosFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contenedor, fragment)
                                .commit();
                        pl = 1;
                        port.setImageResource(R.drawable.com_facebook_tooltip_black_bottomnub);
                    } else {
                        Fragment fragment = new ListFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contenedor, fragment)
                                .commit();
                        pl = 0;
                        port.setImageResource(R.drawable.com_facebook_tooltip_black_topnub);
                    }
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int siboton= boton();
                if (siboton==0){
                    Toast.makeText(getApplicationContext(),"Seleciona primero una canción de la lista", Toast.LENGTH_SHORT).show();
                }else {

                    if (l == 0) {
                        pause.setImageResource(R.drawable.play_ab);
                        md.pause();
                        Toast.makeText(getApplicationContext(),"Pause", Toast.LENGTH_SHORT).show();
                        l = 1;
                    } else {
                        pause.setImageResource(R.drawable.pausa_ab);
                        md.start();
                        Toast.makeText(getApplicationContext(),"Start", Toast.LENGTH_SHORT).show();
                        l = 0;
                    }
                }
            }
        });

        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int siboton= boton();
                if (siboton==0){
                    Toast.makeText(getApplicationContext(),"Seleciona primero una canción de la lista", Toast.LENGTH_SHORT).show();
                }else {
                    Reproducion.sigPlayer();
                }
            }
        });

        ant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int siboton= boton();
                if (siboton==0){
                    Toast.makeText(getApplicationContext(),"Seleciona primero una canción de la lista", Toast.LENGTH_SHORT).show();
                }else {
                    Reproducion.antPlayer();
                }
            }
        });

        /**
         * ****************************************
         * ****************************************
         * ********** Aumnentado ******************
         * ****************************************
         * ****************************************
         */

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * CONSTRUCCION DE LA NOTIFICACION
     */
    public void notificacion(NotificationCompat.Builder builder) {
        /**
        Bitmap mIcon1 = null;
        String urlS = im;
        URL url = null;
        try {
            url = new URL(urlS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            mIcon1 = BitmapFactory.decodeStream(url.openConnection().getInputStream()) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setContentTitle(na);
        builder.setContentText(so);
        builder.setSmallIcon(R.drawable.ic_notif);
        builder.setLargeIcon(mIcon1);
/**
        Intent intent1 = new Intent(BUTTON_PLAY);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(this,0,intent1,0);
        builder.addAction(R.drawable.com_facebook_auth_dialog_background,"play",pendingIntent1);

        Intent intent2 = new Intent(BUTTON_SIG);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(getApplicationContext(),0,intent2,0);
        builder.addAction(R.drawable.com_facebook_auth_dialog_background,"sig",pendingIntent2);

        Intent intent3 = new Intent(BUTTON_ANT);
        PendingIntent pendingIntent3 = PendingIntent.getBroadcast(getApplicationContext(),0,intent3,0);
        builder.addAction(R.drawable.com_facebook_auth_dialog_background,"ant",pendingIntent3);
*/
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(MY_NOTIFY, builder.build());
    }

    private NotificationManager notificationManager;
    //*****Setea la barra con nombre de la cancion******
    public void banner(){
        textName.setText(Reproducion.nameSong());
        textBand.setText(Reproducion.nameBand());
        pause.setImageResource(R.drawable.pausa_ab);
        sig.setImageResource(R.drawable.sig_ab);
        ant.setImageResource(R.drawable.ant_ab);
        progressBar.setMax(total);

        /** **NOTIFICACION

        Bitmap mIcon1=null;
        String urlS = Reproducion.ImageBand();
        URL url = null;
        try {
            url = new URL(urlS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            mIcon1 = BitmapFactory.decodeStream(url.openConnection().getInputStream()) ;
        } catch (IOException e) {
            //mIcon1=R.mipmap.ic_launcher;
            e.printStackTrace();
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(Reproducion.nameSong());
        builder.setContentText(Reproducion.nameBand());
        builder.setSmallIcon(R.drawable.ic_notif);
        builder.setLargeIcon(mIcon1);
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        */
        //notificacion(builder);

        //notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //notificationManager.notify(MY_NOTIFY, builder.build());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = new InicioFragment();
        Boolean FragmentSelect=true;

        if (id == R.id.nav_inicio) {
            fragment = new InicioFragment();
            FragmentSelect=true;

        } else if (id == R.id.nav_list) {
            fragment = new ListFragment();
            FragmentSelect=true;

        } else if (id == R.id.nav_amigos) {
            int siboton= boton();
            if (siboton==0){
                Toast.makeText(getApplicationContext(),"Seleciona primero una canción de la lista", Toast.LENGTH_SHORT).show();
            }else {
                fragment = new AmigosFragment();
                FragmentSelect = true;
            }

        } else if (id == R.id.nav_manage) {
            Toast.makeText(getApplicationContext(),"Lo sentimos, esta función esta en desarrollo", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_share) {
            Toast.makeText(getApplicationContext(),"Lo sentimos, esta función esta en desarrollo", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText(getApplicationContext(),"Lo sentimos, esta función esta en desarrollo", Toast.LENGTH_SHORT).show();

        }

        if (FragmentSelect){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, fragment)
                    .commit();
            item.setChecked(true);
            //getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void antClicked() {
        antPlayer();
    }

    @Override
    public void playClicked() {
        pausePlayer();
    }

    @Override
    public void sigClicked() {
        sigPlayer();
    }

    /**
     * ***********************************************************************
     * ***********************************************************************
     * ******CLASE REPRODUCCION - CONTIENE CONTROLES DE REPRODUCCION**********
     * ***********************************************************************
     * ***********************************************************************
     */
    private static ArrayList<Song>songs;
    static MediaPlayer md; //variable de tipo mediaplayer
    private static String url, image, descripcion;
    private static int kr, lr=0, j=0;
    private static int posicion=0, total=1, pruebAsync;
    private static MainActivity ma;
    private static pasaCancion d;



    public static class Reproducion{

        public Reproducion(ArrayList<Song>song, int k) {
            //aqui recibe link de pista
            songs=song;//asigna las variables
            kr = k;
            Song song1=songs.get(k);
            url = song1.getLink();

            if (lr==1){
                md.stop();
            }
            pruebAsync=1;
            inicializaPlayer();
            playPlayer(url);
        }

        public static void inicializaPlayer(){
            md = new MediaPlayer();
            md.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }

        public static void playPlayer(String url){
            try {
                md.setDataSource(url);
                md.prepare();
            } catch (IOException e) {
                ma = new MainActivity();
                Toast.makeText(ma.getApplicationContext(), "la cancion no esta en el servidor", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            md.start();
            lr=1;
            total=md.getDuration();
            posicion=md.getCurrentPosition();
            //SystemClock.sleep(100);
            if (j==0) {
                asincrona();
                j=1;
            }
            ma=new MainActivity();
            ma.banner();
            //progressBar.setMax(total);
            //Toast.makeText(getApplicationContext(), "Total "+md.getDuration()+"inicial"+md.getCurrentPosition(), Toast.LENGTH_LONG).show();
        }

        public static void pausePlayer(){
            md.pause();
            ma = new MainActivity();
            Toast.makeText(ma.getApplicationContext(),"Pause", Toast.LENGTH_SHORT).show();
            Log.e("AsyncTask","position ="+posicion);
        }

        public static void startPlayer(){
            md.start();
            ma = new MainActivity();
            Toast.makeText(ma.getApplicationContext(),"Start", Toast.LENGTH_SHORT).show();
        }

        public static void sigPlayer(){
            if (kr==songs.size()-1){
                kr=0;
            }else {
                kr=kr+1;
            }
            Song song1=songs.get(kr);
            url = song1.getLink();
            pruebAsync=1;
            if (lr==1){
                md.stop();
            }
            inicializaPlayer();
            playPlayer(url);

        }

        public static void antPlayer(){
            if (kr==0){
                kr=songs.size()-1;
            }else {
                kr=kr-1;
            }
            Song song1=songs.get(kr);
            url = song1.getLink();
            pruebAsync=1;
            if (lr==1){
                md.stop();
            }
            inicializaPlayer();
            playPlayer(url);
        }

        public static void asincrona(){
            d = new pasaCancion();
            d.execute();
            //Toast.makeText(getApplicationContext(),"AsyncTask "+d.isCancelled(), Toast.LENGTH_LONG).show();
        }

        public static void random(){
            kr = (int) (Math.random()*songs.size());
            Song song1=songs.get(kr);
            url = song1.getLink();
            pruebAsync=1;
            if (lr==1){
                md.stop();
            }
            inicializaPlayer();
            playPlayer(url);
        }
        public static int boton(){
            return lr;
        }

        public static String ImageBand (){
            Song song1=songs.get(kr);
            image = song1.getImagen();
            return (image);
        }
        public static String nameSong (){
            String Nombre;
            Song song1=songs.get(kr);
            Nombre = song1.getNombre();
            return (Nombre);
        }

        public static String nameBand() {
            String Band;
            Song song1 = songs.get(kr);
            Band = song1.getAutor();
            return (Band);
        }
    }// cierra la clase reproduccion



    /**
     * *********************************************************************************
     * ***********COMIENZA ASYNCTASK (cambio de cancion y avance de barra)**************
     * *********************************************************************************
     */

    private static class pasaCancion extends AsyncTask<Void, Integer, Void>{

        @Override
        protected void onPreExecute() {
            //progressBar.setMax(total); // ***************setea MAX del progressBar

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //Tarea al terminar el doInBaground
            switch (pruebAsync){
                case 1:
                    Reproducion.asincrona();
                    pruebAsync=3;
                    break;
                default:
                    Reproducion.random();
                    Reproducion.asincrona();
            }
            Log.e("Termina","AsyncTASK");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onCancelled() {
            Log.e("Cancela","AsyncTASK");
        }

        @Override
        protected Void doInBackground(Void... params) {

            while (posicion+200<total){
                total=md.getDuration();
                posicion=md.getCurrentPosition();
                if (total<0 || posicion<0){
                    total=201;
                    posicion = 0;
                }
                publishProgress(posicion);
                Log.e("AsyncTask","position "+posicion+" < "+total);
                if (isCancelled()){
                    d.cancel(false);
                    break;
                }
            }
            return null;
        }
    }// cierra clase pasaCancion (AsyncTask)
}
