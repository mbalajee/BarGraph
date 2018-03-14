package in.learn.bargraph;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

/**
 * Created by balajim on 3/12/18.
 */

public class BarGraphAdapter extends RecyclerView.Adapter<BarGraphAdapter.ViewHolder> {

    private Context context;
    private List<BarData> listData;
    static private int MAX_BAR_HEIGHT = 0;


    public BarGraphAdapter(Context context, List<BarData> listData, int maxBarHeight) {
        this.listData = listData;
        this.context = context;
        MAX_BAR_HEIGHT = maxBarHeight;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private View emptyView;
        private TextView index, hours, date;

        ViewHolder(View itemView) {
            super(itemView);

            emptyView = itemView.findViewById(R.id.barEmptyView);
            index = itemView.findViewById(R.id.textViewIndex);
            hours = itemView.findViewById(R.id.textViewHours);
            date = itemView.findViewById(R.id.textViewDate);
        }

        void bind(BarData data, int heightBar) {

            index.setText(String.format("%d", data.getIndex()));
            hours.setText(data.getFormattedHours());
            date.setText(data.getDate());

            // Set bar height
            ViewGroup.LayoutParams layoutParams = emptyView.getLayoutParams();
            layoutParams.height = data.getHeight(heightBar) - (index.getMeasuredHeight() + date.getMeasuredHeight() + 20);
            emptyView.setLayoutParams(layoutParams);

            // Set bar color
            hours.setBackgroundColor(data.getColor());

        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bar_list_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BarData data = listData.get(position);
        holder.bind(data, data.getHeight(MAX_BAR_HEIGHT));
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    public BarData getItem(int position) {
        return listData.get(position);
    }

    public void setBarData(List<BarData> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    private void setAnimation(View viewToAnimate) {
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
        viewToAnimate.startAnimation(animation);
    }
}
