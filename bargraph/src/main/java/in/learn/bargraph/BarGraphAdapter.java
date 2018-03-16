package in.learn.bargraph;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import java.util.List;

/**
 * Created by balajim on 3/12/18.
 */

public class BarGraphAdapter extends RecyclerView.Adapter<BarGraphAdapter.ViewHolder> {

    private Context context;
    private List<BarData> listData;
    static private int ANIMATABLE_HEIGHT = 0;

    BarGraphAdapter(Context context, List<BarData> listData, int maxBarHeight) {
        this.listData = listData;
        this.context = context;
        ANIMATABLE_HEIGHT = (maxBarHeight - context.getResources().getDimensionPixelOffset(R.dimen.dp30) * 2) - 15;
        // +15 -> to avoid hr text from truncation
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

        @SuppressLint("DefaultLocale")
        void bind(BarData data) {

            index.setText(String.format("%d", data.getIndex()));
            hours.setText(data.getFormattedHours());
            date.setText(data.getDate());

            // Set bar height
            int barHeight = data.getHeight(ANIMATABLE_HEIGHT);
            ViewGroup.LayoutParams layoutParams = emptyView.getLayoutParams();
            layoutParams.height = barHeight;
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
        BarData data = listData.get(holder.getLayoutPosition());
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    BarData getItem(int position) {
        return listData.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }
}
