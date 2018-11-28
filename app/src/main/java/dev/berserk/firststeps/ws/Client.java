package dev.berserk.firststeps.ws;

import dev.berserk.firststeps.ws.wsmodels.SWCharacter;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Client {

    @GET(ClientEndPoints.getPeople)
    Call<SWCharacter> getCharacter(@Path("id") String id);
}
