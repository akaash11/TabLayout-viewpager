package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.akaash.testproject20.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.User;

/**
 * Created by akaash on 17/1/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    private List<User> userList;
    private Context context;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        UserViewHolder userViewHolder = new UserViewHolder(inflater.inflate(R.layout.list_item_user, parent, false));
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User items = userList.get(position);

        holder.tvUserName.setText(context.getString(R.string.name) + items.getLogin());
        holder.tvUserUrl.setText(context.getString(R.string.url) + items.getUrl());
        holder.tvDataOne.setText(context.getString(R.string.id)+ items.getId());
        holder.tvDataTwo.setText(context.getString(R.string.score) + items.getScore());
        if (items.getAvatarUrl() != null)
            Picasso.with(context).load(items.getAvatarUrl()).into(holder.ivUserIcon);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (userList != null) {
            count = userList.size();
        }
        return count;
    }

    public void updateList(List<User> newList) {
        if (null != userList && null != newList) {
            userList.clear();
            userList.addAll(newList);
            notifyDataSetChanged();
        }
    }
    public class UserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvUserName)
        TextView tvUserName;

        @BindView(R.id.tvUserURL)
        TextView tvUserUrl;

        @BindView(R.id.tvDataone)
        TextView tvDataOne;

        @BindView(R.id.tvDatatwo)
        TextView tvDataTwo;

        @BindView(R.id.ivProfileIcon)
        ImageView ivUserIcon;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
