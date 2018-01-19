package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akaash.testproject20.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.History;

/**
 * Created by akaash on 18/1/18.
 */

public class HistoryAdapter  extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{

    private List<History> historyList;
    private Context context;

    public HistoryAdapter(List<History> historyList, Context context) {
        this.historyList = historyList;
        this.context = context;
    }

    public HistoryAdapter(List<History> historyList) {
        this.historyList = historyList;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        HistoryViewHolder historyViewHolder=new HistoryViewHolder(layoutInflater.inflate(R.layout.list_item_history,parent,false));
        return historyViewHolder;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        History items= historyList.get(position);
        holder.tvKeyword.setText(items.getKeyword());
        holder.tvCount.setText(String.valueOf(items.getCount()));
    }

    @Override
    public int getItemCount() {
        int count=0;
        if(historyList !=null){
            count= historyList.size();
        }
        return count;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvKeyword)
        TextView tvKeyword;

        @BindView(R.id.tvCount)
        TextView tvCount;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
