package pl.com.digita.testtrelloclient.app.communication;

import android.util.Base64;
import com.squareup.okhttp.OkHttpClient;
import pl.com.digita.testtrelloclient.app.model.AccessToken;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

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


    private static RestAdapter.Builder builder = new RestAdapter.Builder().setClient(new OkClient(new OkHttpClient()));


    public static <S> S createService(Class<S> serviceClass, String baseUrl, String username, String password) {
        // set endpoint url
        builder.setEndpoint(baseUrl);

        if (username != null && password != null) {
            // concatenate username and password with colon for authentication
            final String credentials = username + ":" + password;

            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    // create Base64 encodet string
                    String string = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    request.addHeader("Accept", "application/json");
                    request.addHeader("Authorization", string);
                }
            });
        }

        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }


    public static <S> S createService(Class<S> serviceClass, String baseUrl, final AccessToken accessToken) {
        // set endpoint url
        builder.setEndpoint(baseUrl);

        if (accessToken != null) {
            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Accept", "application/json");
                    request.addHeader("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
                }
            });
        }

        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }


}
