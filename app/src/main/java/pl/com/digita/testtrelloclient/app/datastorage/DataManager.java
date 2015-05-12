package pl.com.digita.testtrelloclient.app.datastorage;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Piotr on 2015-05-02.
 *
 *
 * Data storage manager
 */
public class DataManager
{
    private JsonDataAccess mJsonDataAccess;
    private ApplicationData mAppData;


    public DataManager(Context pContext)
    {
        File dataDir  = new File(pContext.getApplicationInfo().dataDir);

        mJsonDataAccess = new JsonDataAccess(dataDir);
        try
        {
            mAppData = mJsonDataAccess.readData();
        } catch (FileNotFoundException e)
        {
            mAppData = new ApplicationData();
        }
    }

    public void saveData()
    {
        mJsonDataAccess.saveData(mAppData);
    }

    public ApplicationData getAppData()
    {
        return mAppData;
    }
}
