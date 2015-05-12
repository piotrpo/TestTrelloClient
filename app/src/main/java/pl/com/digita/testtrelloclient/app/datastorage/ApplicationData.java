package pl.com.digita.testtrelloclient.app.datastorage;

import com.google.gson.annotations.SerializedName;
import pl.com.digita.testtrelloclient.app.model.AccessToken;
import pl.com.digita.testtrelloclient.app.model.UserCredentials;

/**
 * Created by Piotr on 2015-05-12.
 *
 * Application setting model
 *
 *
 */
public class ApplicationData
{
    @SerializedName("UserCredentials")
    public UserCredentials mUserCredentials;
    @SerializedName("AccessToken")
    public AccessToken mAccessToken;
}
