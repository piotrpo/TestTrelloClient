package pl.com.digita.testtrelloclient.app.ui.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.squareup.otto.Bus;
import pl.com.digita.testtrelloclient.app.R;
import pl.com.digita.testtrelloclient.app.communication.CommunicationManager;
import pl.com.digita.testtrelloclient.app.dependencies.DaggerGraphManager;
import pl.com.digita.testtrelloclient.app.model.UserCredentials;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment
{
    @Inject
    CommunicationManager mCommunicationManager;

    @Inject
    Bus mOttoBus;

    /**
     * Use this factory method to create a new instance of
     *
     * @return A new instance of fragment LoginFragment.
     */

    public static LoginFragment newInstance()
    {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    {
        DaggerGraphManager.INSTANCE.getObjectGraph().inject(this);
    }

    public LoginFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.inject(this, view);
        return view;
    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.button_login)
    public void onLoginClicked()
    {
        //mCommunicationManager.doLogin();
    }
}
