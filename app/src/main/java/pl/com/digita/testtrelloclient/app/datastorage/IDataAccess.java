package pl.com.digita.testtrelloclient.app.datastorage;

import java.io.FileNotFoundException;

/**
 * Created by Piotr on 2015-05-02.
 *
 * Interface for data access layer
 */
public interface IDataAccess
{
    void saveData(ApplicationData pApplicationData);

    ApplicationData readData() throws FileNotFoundException;
}
