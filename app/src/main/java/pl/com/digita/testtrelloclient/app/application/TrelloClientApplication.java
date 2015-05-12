package pl.com.digita.testtrelloclient.app.application;

import android.app.Application;
import pl.com.digita.testtrelloclient.app.dependencies.DaggerGraphManager;

/**
 * Created by Piotr on 2015-05-12.
 *
 * Overrriden application object
 */
public class TrelloClientApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        //just to be sure that application context is passed to dagger modules
        DaggerGraphManager.INSTANCE.initModules(this);
    }
}
