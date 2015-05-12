package pl.com.digita.testtrelloclient.app.dependencies;

import android.app.Activity;
import android.content.Context;
import dagger.ObjectGraph;

/**
 * Created by Piotr on 2015-04-25.
 *
 * Singleton style dagger injector
 *
 */
public enum  DaggerGraphManager
{
    INSTANCE;

    private Context mApplicationContext;
    private AndroidModule mAndroidModule;
    private AppSpecificModule mAppSpecificDaggerModule;
    private ObjectGraph mObjectGraph;
    private Context activityContext;

    public void initModules(Context applicationContext)
    {
        mApplicationContext = applicationContext;
        mObjectGraph = ObjectGraph.create(getModules());
    }

    private Object[] getModules()
    {
        mAndroidModule = new AndroidModule(mApplicationContext);
        mAppSpecificDaggerModule = new AppSpecificModule(mApplicationContext);
        return new Object[]{mAndroidModule, mAppSpecificDaggerModule};
    }

    public ObjectGraph getObjectGraph()
    {
        return mObjectGraph;
    }

    public ObjectGraph getObjectGraph(Activity pContext)
    {
        mAndroidModule.setLastActivityContext(pContext);
        return mObjectGraph;
    }
}
