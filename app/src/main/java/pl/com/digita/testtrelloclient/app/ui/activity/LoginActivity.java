package pl.com.digita.testtrelloclient.app.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TrelloApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;
import pl.com.digita.testtrelloclient.app.Constants;
import pl.com.digita.testtrelloclient.app.R;

import java.util.concurrent.ExecutionException;


public class LoginActivity extends ActionBarActivity
{

    private final OAuthService s = new ServiceBuilder()
            .provider(TrelloApi.class)
            .apiKey(Constants.API_KEY)
            .apiSecret(Constants.API_SECRET)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.button_login)
    public void onClickLoginButton()
    {

        //TODO check it!!!
        Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_LONG).show();
        String authUrl = null;
        try {
            authUrl = new AuthUrl().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(authUrl)));

        Log.d("trello", "url: "+ authUrl);
    }


    private class AuthUrl extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params) {
            Token requestToken = s.getRequestToken();
            return s.getAuthorizationUrl(requestToken);
        }

    }
}