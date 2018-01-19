package api;




import java.util.List;
import model.UserResponse;
import model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by akaash on 12/1/18.
 */

public interface ApiInterface {
    @GET("users")
    Call<List<User>> getGitHubUsers();

    @GET("/search/users")
    Call<UserResponse> getSearchResult(@Query("q") String keyword);

}
