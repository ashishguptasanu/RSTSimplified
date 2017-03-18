package international.rst.com.rstsimplified.Fragments;

import android.os.Bundle;

/**
 * Created by Ashish on 18-03-2017.
 */

public class FragmentUSAForm extends android.support.v4.app.Fragment {
    public static FragmentUSAForm newFormInstance( String title) {
        FragmentUSAForm fragmentUsaForm = new FragmentUSAForm();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragmentUsaForm.setArguments(args);
        return fragmentUsaForm;
    }
}
