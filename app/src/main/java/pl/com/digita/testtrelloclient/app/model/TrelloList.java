package pl.com.digita.testtrelloclient.app.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Piotr on 2015-05-13.
 *
 */
public class TrelloList
{
    @SerializedName("name")
    String mName;

    @SerializedName("id")
    String mId;


    @SerializedName("cards")
    public ArrayList<TrelloCard> mCards;

    @Override
    public String toString()
    {
        return mName;
    }
}


