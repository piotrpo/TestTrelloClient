package pl.com.digita.testtrelloclient.app.dependencies;

import android.content.Context;
import com.squareup.otto.Bus;
import dagger.Module;
import dagger.Provides;
import pl.com.digita.testtrelloclient.app.communication.CommunicationManager;
import pl.com.digita.testtrelloclient.app.datastorage.DataManager;
import pl.com.digita.testtrelloclient.app.ui.activity.MainActivity;
import pl.com.digita.testtrelloclient.app.ui.fragment.TrelloBoardFragment;

import javax.inject.Singleton;

/**
 * Created by Piotr on 2015-04-24.
 *
 * Module provider
 *
 */


@Module(
        complete = false,
        includes = AndroidModule.class,
        library = true,
        injects = {
                MainActivity.class,
                TrelloBoardFragment.class,
                CommunicationManager.class

        }
)
public class AppSpecificModule
{
    private Context mContext;

    public AppSpecificModule(Context pContext)
    {
        mContext = pContext;
    }

    @Provides
    @Singleton
    public Bus providesOttoBus()
    {
        return new Bus();
    }

    @Provides
    @Singleton
    public CommunicationManager provideCommunicationManager()
    {
        return new CommunicationManager();
    }

    @Provides
    @Singleton
    public DataManager provideDataManager()
    {
        return new DataManager(mContext);
    }

}
