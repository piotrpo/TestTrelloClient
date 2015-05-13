package pl.com.digita.testtrelloclient.app.communication;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Bus;
import pl.com.digita.testtrelloclient.app.dependencies.DaggerGraphManager;
import pl.com.digita.testtrelloclient.app.model.Board;
import pl.com.digita.testtrelloclient.app.model.TrelloList;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by Piotr on 2015-05-12.
 *
 *
 * Class handles all methods to start communication with backend
 */
public class CommunicationManager
{
    private String mAuthToken;
    private ITrelloService mTrelloService;
    private String mMyBoardId;

    @Inject
    Bus mOttoBus;

    {
        DaggerGraphManager.INSTANCE.getObjectGraph().inject(this);
        RestAdapter restAdapter = new RestAdapter.Builder().setClient(new OkClient(new OkHttpClient()))
                .setEndpoint(ITrelloService.BASE_URL)
               // .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        mTrelloService = restAdapter.create(ITrelloService.class);

    }

    public void setAuthToken(String pAuthToken)
    {
        mAuthToken = pAuthToken;

        requestBoards();
    }


    public void requestBoards()
    {
        mTrelloService.requestMyBoards(mAuthToken, ITrelloService.API_KEY, "name", new Callback<ArrayList<Board>>()
        {
            @Override
            public void success(ArrayList<Board> pBoards, Response response)
            {
                //we just need it to check what is My_Board id, as it's the only one we're interested in

                for (Board board : pBoards)
                {
                    if (board.mName.equalsIgnoreCase("my_board"))
                    {
                        mMyBoardId = board.mId;
                        break;
                    }
                }

                //right after getting id ask for cards
                requestBoardContent(mMyBoardId);
            }

            @Override
            public void failure(RetrofitError error)
            {
                mOttoBus.post(error);
            }
        });
    }

    public void requestBoardContent(String pBoardId)
    {
        mTrelloService.requestBoardContent(mAuthToken, ITrelloService.API_KEY, "name", "all", "name, content", pBoardId, new Callback<ArrayList<TrelloList>>()
        {
            @Override
            public void success(ArrayList<TrelloList> pTrelloLists, Response response)
            {
                mOttoBus.post(pTrelloLists);
            }

            @Override
            public void failure(RetrofitError error)
            {
                mOttoBus.post(error);
            }
        });
    }

}
