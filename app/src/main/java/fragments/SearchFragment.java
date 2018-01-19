package fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.akaash.testproject20.R;

import adapter.UserAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import database.DBHelper;
import model.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.App;
import utils.CommonUtil;
import utils.ToastUtils;


public class SearchFragment extends Fragment {

    @BindView(R.id.etSearchFrame)
    EditText etSearchFrame;

    @BindView(R.id.rvSearchParent)
    RecyclerView rvSearchParent;

    private View view;
    private UserAdapter userAdapter;

    private DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvSearchParent.setLayoutManager(new LinearLayoutManager(getActivity()));
        etSearchFrame.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String keyword=etSearchFrame.getText().toString();
                    getUserByKeyword(keyword);
                    dbHelper=new DBHelper(getContext());
                    dbHelper.insertOrUpdateKeyword(keyword);
                    return true;
                }else {
                    return false;
                }
            }
        });
    }

    private void getUserByKeyword(String keyword) {
        CommonUtil.hideKeyboardFrom(getContext(), view);
        if (CommonUtil.isConnectedToInternet()) {
            if (null != keyword && !TextUtils.isEmpty(keyword)) {
                Call<UserResponse> call = App.getAPIInterface().getSearchResult(keyword);
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        UserResponse data = response.body();
                        if (data != null && data.getUsers().size() > 0) {
                            userAdapter = new UserAdapter(data.getUsers());
                            rvSearchParent.setAdapter(userAdapter);
                        } else {
                            ToastUtils.showToast(getActivity(), "No record found for this keyword");
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        ToastUtils.showToast(getActivity(), "Server error");
                    }
                });
            } else {
                ToastUtils.showToast(getActivity(), "Pease enter keyword");
            }
        } else {
            ToastUtils.showToast(getActivity(), "Pease connect to the working internet connection.");
        }
    }
/*
    private void searchResult() {
        rvSearchParent.setLayoutManager(new LinearLayoutManager(getActivity()));
        apiService = ApiClient.getClient().create(ApiInterface.class);
        etSearchFrame.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    CommonUtil.hideKeyboardFrom(getContext(), view);
                    String keyword = etSearchFrame.getText().toString();
                    Call<UserResponse> call = apiService.getSearchResult(keyword);
                    call.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            UserResponse data = response.body();
                            if (data != null && data.getUsers().size() > 0) {
                                userAdapter = new UserAdapter(data.getUsers());
                                rvSearchParent.setAdapter(userAdapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {

                        }
                    });

                    return true;
                }
                return false;
            }
        });
    }*/

}
