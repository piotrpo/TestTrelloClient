package pl.com.digita.testtrelloclient.app.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.com.digita.testtrelloclient.app.R;
import pl.com.digita.testtrelloclient.app.communication.ITrelloService;


public class LoginActivity extends ActionBarActivity
{



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

        //Serious troubles with standard OAuth calls, using some workaround...


        Uri uri = Uri.parse(ITrelloService.BASE_URL + "authorize?key="
                + ITrelloService.API_KEY
                + "&name=My+Application&expiration=1day&response_type=code&scope=read,write&&return_url=http%3A%2F%2Ftrello.digita.com.pl");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        Log.i("trello", "called url" + uri.toString());
        startActivity(intent);


    }



}