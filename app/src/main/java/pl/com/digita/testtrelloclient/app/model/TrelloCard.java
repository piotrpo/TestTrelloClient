package pl.com.digita.testtrelloclient.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Piotr on 2015-05-13.
 *
 * Card model
 */
public class TrelloCard
{
    @SerializedName("id")
    public String mId;

    @SerializedName("name")
    public String mName;

    @SerializedName("description")
    public String mDescription;
}
