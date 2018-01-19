package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by akaash on 12/1/18.
 */

public class UserResponse {

    @SerializedName("total_count")
    private Integer totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<User> users;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
