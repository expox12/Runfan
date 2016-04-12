package mycompany.runfan.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mycompany.runfan.R;

/**
 * Created by macmini02 on 12.04.16.
 */
public class FragmentOne extends Fragment {

    public static Fragment newIntance(Context context) {
        return new FragmentOne();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, null);
    }
}
