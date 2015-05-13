package pl.com.digita.testtrelloclient.app.communication;

import pl.com.digita.testtrelloclient.app.model.Board;
import pl.com.digita.testtrelloclient.app.model.TrelloCard;
import pl.com.digita.testtrelloclient.app.model.TrelloList;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import java.util.ArrayList;

/**
 * Created by Piotr on 2015-05-12.
 *
 * Stub for backend methods create retrofit client
 */
public interface ITrelloService
{
    String BASE_URL = "https://trello.com/1/";
    String API_KEY = "609dcfdf0749515b1943f945fbf6f7a4";
    String API_SECRET = "c21b13a13b5475bdb4c09fc6b63cac698d6f829346f43345c91298b237cd977a";


    //example query from doc:
    //https://api.trello.com/1/boards/4eea4ffc91e31d1746000046?lists=open&list_fields=name&fields=name,desc&key=[application_key]&token=[optional_auth_token]

    @GET("/boards/{board_id}")
    void requestCardOnList(@Query("token") String pAuthToken, @Query("key") String pAppKey, @Path("board_id") String pBoardId, Callback<ArrayList<TrelloCard>> pCallback);


    //https://api.trello.com/1/boards/4eea4ffc91e31d1746000046/lists?cards=open&card_fields=name&fields=name&key=[application_key]&token=[optional_auth_token]
    @GET("/boards/{board_id}/lists")
    void requestBoardContent(@Query("token")String pAuthToken, @Query("key")String pAppKey, @Query("fields") String pListFields, @Query("cards") String pCardFilter, @Query("card_fields") String pCardFields ,@Path("board_id")String pBoardId, Callback<ArrayList<TrelloList>> pCallback);

    //https://trello.com/1/members/me/boards?fields=name
    @GET("/members/me/boards")
    void requestMyBoards(@Query("token")String pAuthToken, @Query("key")String pAppKey, @Query("fields") String pFields, Callback<ArrayList<Board>> pCallback);
}
