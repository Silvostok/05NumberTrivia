package com.example.miguelangelrubiocaballero.a05numbertrivia;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


/**
 * Retrofit userguide at
 * https://square.github.io/retrofit/
 * More usage information at
 * https://guides.codepath.com/android/consuming-apis-with-retrofit
 */


public interface ApiService {

    String BASE_URL = "http://numbersapi.com/";

    /**
     * Create a retrofit client.
     */

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    /**
     * The string in the GET annotation is added to the BASE_URL.
     * It simply represents the designed layout of the URLs of the numbersapi.com website,
     * refer to it in a browser and try. This request will deliver a json stream based on number and
     * quote. It will be put in a NumberJson object by Retrofit.
     */

    /**
     * "NumberJson" is the name of the helper class just defined, defining the datamodel, and given as argument.
     * "getRandomQuote" is the name of the symbol get method. It can be chosen at wish, as long as it is invoked
     * with the same name.
     */
    @GET("/random/?json&max=99")
    Call<NumberJson> getRandomQuote();
}
