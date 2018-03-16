package in.learn.bargraph;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.List;


public class BarGraphView extends RecyclerView {

    private LinearLayoutManager layoutManager;
    private BarGraphAdapter adapter;
    private int animateDuration = 700;

    public BarGraphView(Context context) {
        super(context);
        setupRecyclerView();
    }

    public BarGraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupRecyclerView();
    }

    public BarGraphView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupRecyclerView();
    }

    private void setupRecyclerView() {

//        getRecycledViewPool().setMaxRecycledViews(1, 0);

        setHasFixedSize(true);

        // Layout manager
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(layoutManager);

        // Spacing between items
        HorizontalSpacing spacing = new HorizontalSpacing(10);
        addItemDecoration(spacing);

        setupScrollListener();
    }

    private void setupScrollListener() {

        addOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int hv = layoutManager.findFirstVisibleItemPosition();

                if (hv != NO_POSITION) {

                    BarData data = adapter.getItem(hv);
                    View view = findViewHolderForAdapterPosition(hv).itemView;
//                    BarGraphAdapter.ViewHolder holder = (BarGraphAdapter.ViewHolder) findViewHolderForAdapterPosition(hv);
//                    View view = holder.getEmptyView();

                    if (view.getX() <= -(view.getMeasuredWidth() / 2.5))
                    {
                        Log.d("CPOS",  "POS " + hv + "\tX " + view.getX() + "\tPX " + view.getPivotX());

                        if (dx >= 0) { // Scroll left

//                             Check if already animated
                            if (data.animate != 0) {
                                //view.animate().scaleY(1).setDuration(100);
                                data.animate = 0;
                                //adapter.notifyItemChanged(hv);

                                view.animate().scaleY(0).setDuration(animateDuration);
                            }

                        } else { // Scroll right

                            // Check if already animated
                            if (data.animate != 1) {
                                //view.animate().scaleY(0).setDuration(100);
                                data.animate = 1;
                                //adapter.notifyItemChanged(hv);

                                view.setScaleY(0);
                                view.animate().scaleY(1).setDuration(animateDuration);
                            }
                        }
                    }
                }

            }
        });
    }


    public void setBarData(final List<BarData> listData) {

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                // Adapter
                adapter = new BarGraphAdapter(getContext(), listData, getMeasuredHeight());
                setAdapter(adapter);

                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public void setAnimateDuration(int duration) {
        animateDuration = duration;
    }


    private class HorizontalSpacing extends RecyclerView.ItemDecoration {

        private final int horizontalSpacing;

        HorizontalSpacing(int verticalSpaceHeight) {
            this.horizontalSpacing = verticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
                outRect.right = horizontalSpacing;
            }
        }

    }
}
