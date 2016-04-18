package mycompany.runfan.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mycompany.runfan.R;

/**
 * Created by macmini02 on 18.04.16.
 */
public class FragmentTwo extends Fragment {

    public static Fragment newIntance(Context context) {
        FragmentTwo f = new FragmentTwo();

        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_two, null);
        return root;
    }
}
