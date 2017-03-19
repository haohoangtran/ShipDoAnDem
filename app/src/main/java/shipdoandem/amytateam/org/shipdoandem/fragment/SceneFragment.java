package shipdoandem.amytateam.org.shipdoandem.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by DUC THANG on 3/19/2017.
 */

public class SceneFragment {
    private FragmentManager fragmentManager;
    private int id_Fragment;

    public SceneFragment(FragmentManager fragmentManager, int id_Fragment) {
        this.fragmentManager = fragmentManager;
        this.id_Fragment = id_Fragment;
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id_Fragment,fragment);
        if(addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
