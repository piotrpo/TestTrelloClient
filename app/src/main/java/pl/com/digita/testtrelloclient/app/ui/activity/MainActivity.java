package pl.com.digita.testtrelloclient.app.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import pl.com.digita.testtrelloclient.app.Constants;
import pl.com.digita.testtrelloclient.app.R;
import pl.com.digita.testtrelloclient.app.communication.CommunicationManager;
import pl.com.digita.testtrelloclient.app.dependencies.DaggerGraphManager;
import pl.com.digita.testtrelloclient.app.ui.fragment.TrelloBoardFragment;

import javax.inject.Inject;


public class MainActivity extends ActionBarActivity
{

    @Inject
    CommunicationManager mCommunicationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerGraphManager.INSTANCE.getObjectGraph().inject(this);

        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, TrelloBoardFragment.newInstance())
                    .commit();
        }
    }


    @Override
    protected void onResume()
    {
        super.onResume();

        // the intent filter defined in AndroidManifest will handle the return from ACTION_VIEW intent
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(Constants.REDIRECT_URL))
        {
            String[] strings = uri.toString().split("=");
            String token = strings[strings.length-1];

            if (token != null)
            {
                mCommunicationManager.setAuthToken(token);
            }
            else
            {
                //something went wrong
                Log.w(Constants.TAG, "Token not found in redirected URL: " + uri);
            }
        }
    }
}
