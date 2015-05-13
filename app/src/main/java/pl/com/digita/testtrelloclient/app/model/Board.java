package pl.com.digita.testtrelloclient.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Piotr on 2015-05-13.
 *
 * Model class for boards
 */
public class Board
{
    @SerializedName("name")
    public String mName;

    @SerializedName("id")
    public String mId;
}
