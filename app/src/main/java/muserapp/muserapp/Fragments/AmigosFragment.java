package muserapp.muserapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import muserapp.muserapp.MainActivity;
import muserapp.muserapp.R;

public class AmigosFragment extends Fragment {

    private ImageView imageBand;
    private String UrlImage;

    public AmigosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_amigos, container, false);

        imageBand = (ImageView) view.findViewById(R.id.imageBand);
        UrlImage = MainActivity.Reproducion.ImageBand();

        Picasso.with(getContext()).load(UrlImage)
                .error(R.mipmap.ic_launcher)
                .fit()
                .centerInside()
                .into(imageBand);

        return view;
    }
}
