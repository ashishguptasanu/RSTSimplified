package international.rst.com.rstsimplified;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by ashish on 13/1/17.
 */

public class Fragment extends android.support.v4.app.Fragment{

    public Fragment() {
    }
    public static Fragment newInstance(String title){
        Fragment fragment1 = new Fragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment1.setArguments(args);
        return fragment1;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
