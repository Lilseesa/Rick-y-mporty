package cr.ac.ucr.rickmorty.api;
import cr.ac.ucr.rickmorty.models.LocationResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface LocationService {

    @Headers("Content-Type: application/json")
    @GET("location")
    Call<LocationResponse> getLocations(@Query("page") int page);

}
