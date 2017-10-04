package muserapp.muserapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by oculu on 06/06/2017.
 */

public class MyBroadcast extends BroadcastReceiver {

    public interface OnMyBroadcastListener{
        void antClicked();
        void playClicked();
        void sigClicked();
    }

    private final OnMyBroadcastListener onMyBroadcastListener;

    public MyBroadcast(OnMyBroadcastListener onMyBroadcastListener){
        this.onMyBroadcastListener = onMyBroadcastListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().contentEquals(null)){
            onMyBroadcastListener.antClicked();
        }
        if (intent.getAction().contentEquals(null)){
            onMyBroadcastListener.playClicked();
        }
        if (intent.getAction().contentEquals(null)){
            onMyBroadcastListener.sigClicked();
        }
    }

}

