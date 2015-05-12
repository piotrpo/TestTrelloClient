package pl.com.digita.testtrelloclient.app.dependencies;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Piotr on 2015-04-24.
 *
 * Daggerk tag annotation
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForApplication
{
}
