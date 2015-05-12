package pl.com.digita.testtrelloclient.app.datastorage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * Created by Piotr on 2015-04-24.
 *
 * Stores application data object as json file
 */
public class JsonDataAccess implements IDataAccess
{

    private final Gson gson;
    File dataDirectory;

    public JsonDataAccess(File dataDirectory)
    {
        this.dataDirectory = dataDirectory;
        gson = new GsonBuilder().create();
    }


    @Override
    public void saveData(ApplicationData pApplicationData)
    {
        FileWriter writer;

        try
        {
            writer = getWriter();
            gson.toJson(pApplicationData, writer);
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private FileWriter getWriter() throws IOException
    {

        File dataFile = getDataFile();
        if (!dataFile.exists())
        {
            //noinspection ResultOfMethodCallIgnored
            dataFile.createNewFile();
        }
        return new FileWriter(dataFile);
    }

    private FileReader getReader() throws FileNotFoundException
    {
        return new FileReader(getDataFile());
    }


    private File getDataFile()
    {
        return new File(this.dataDirectory, "data.json");
    }


    @Override
    public ApplicationData readData() throws FileNotFoundException
    {
        return gson.fromJson(getReader(), ApplicationData.class);
    }

}
