package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.akaash.testproject20.R;

import java.util.List;

import adapter.UserAdapter;
import api.ApiClient;
import api.ApiInterface;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserFragment extends Fragment {

    @BindView(R.id.rvUsers)
    RecyclerView rvUserParent;

    private UserAdapter userAdapter;
    private View view;
    private List<User> userList;
    private ApiInterface apiService;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);
        rvUserParent.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressBar.setVisibility(View.VISIBLE);
        rvUserParent.setVisibility(View.GONE);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<User>> call = apiService.getGitHubUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response != null) {
                    userList = response.body();
                    if (userList != null && userList.size() > 0) {
                        userAdapter = new UserAdapter(userList);
                        rvUserParent.setAdapter(userAdapter);
                        progressBar.setVisibility(View.GONE);
                        rvUserParent.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("", "");
            }
        });


        return view;
    }


}
