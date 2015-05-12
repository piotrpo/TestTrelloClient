package pl.com.digita.testtrelloclient.app.dependencies;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Piotr on 2015-04-24.
 *
 * General android utilities module
 */
@Module(library = true)
public class AndroidModule {

    private final Context application;

    private Context mLastActivityContext;

    public AndroidModule(Context application) {
        this.application = application;
    }


    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext()
    {
        return application;
    }

    @Provides
    Context provideContext()
    {
        return mLastActivityContext;
    }

    @Provides
    Resources providesResources() {
        return application.getResources();
    }

    @Provides
    LayoutInflater provideLayoutInflater() {
        return (LayoutInflater) mLastActivityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setLastActivityContext(Context pLastActivityContext)
    {
        mLastActivityContext = pLastActivityContext;
    }
}
