package muserapp.muserapp.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import muserapp.muserapp.Adapters.SongsAdapter;
import muserapp.muserapp.Models.Song;
import muserapp.muserapp.R;

import static com.facebook.FacebookSdk.getApplicationContext;


public class ListFragment extends Fragment {
    ArrayList<Song> dataset;
    ArrayList<Song> songAux = new ArrayList<Song>();

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String URL = "https://script.google.com/macros/s/AKfycbzTsG0NDsBVAk96PP3fEpqzko-zk8qdh56Ri2acHEJwr6v8uJQ/exec";

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),"Cargando","Lista de reproducción");

        JsonArrayRequest req = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("mirespuesta", response.toString());

                dataset = new ArrayList<Song>();
                dataset = parser(response);

                progressDialog.cancel();

                RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_list);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(new SongsAdapter(dataset, R.layout.row_songs));
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.cancel();
            }
        });
        queue.add(req);
        }

    public ArrayList<Song> parser (JSONArray response){
        int i;

        for(i =0; i<response.length();i++){
            Song song = new Song();
            try {
                JSONObject jsonObject = (JSONObject) response.get(i);
                song.setNombre(jsonObject.getString("nombre"));
                song.setAutor(jsonObject.getString("autor"));
                song.setDisco(jsonObject.getString("disco"));
                song.setImagen(jsonObject.getString("imagen"));
                song.setLink(jsonObject.getString("link"));

                Log.e("dato", song.getNombre().toString());
                Log.e("dato2", song.getAutor().toString());
                Log.e("dato3", song.getDisco().toString());
                Log.e("dato3", song.getImagen().toString());
                Log.e("dato4", song.getLink().toString());

                songAux.add(song);
                Log.e("veamos",songAux.toString());
            }catch(JSONException e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Error de Conección",Toast.LENGTH_LONG).show();
            }
        }
        return songAux;

    }

}


