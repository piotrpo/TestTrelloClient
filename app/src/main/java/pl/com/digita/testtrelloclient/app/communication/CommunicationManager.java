package pl.com.digita.testtrelloclient.app.communication;

import android.os.AsyncTask;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TrelloApi;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
import pl.com.digita.testtrelloclient.app.model.UserCredentials;

import java.util.Scanner;

/**
 * Created by Piotr on 2015-05-12.
 *
 *
 * Class handles all methods to start communication with backend
 */
public class CommunicationManager
{
    //OAuth constants
    private static final String API_KEY = "609dcfdf0749515b1943f945fbf6f7a4";
    private static final String API_SECRET = "c21b13a13b5475bdb4c09fc6b63cac698d6f829346f43345c91298b237cd977a";
    private static final String PROTECTED_RESOURCE_URL = "https://trello.com/1/members/me";


}
