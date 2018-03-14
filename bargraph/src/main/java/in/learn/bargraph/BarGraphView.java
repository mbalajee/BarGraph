package in.learn.bargraph;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.List;


public class BarGraphView extends RecyclerView {

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

        setHasFixedSize(true);

        // Layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(layoutManager);

        // Spacing between items
        HorizontalSpacing spacing = new HorizontalSpacing(10);
        addItemDecoration(spacing);

    }

    public void setBarData(final List<BarData> listData) {

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                // Adapter
                final BarGraphAdapter adapter = new BarGraphAdapter(getContext(), listData, getMeasuredHeight());
                setAdapter(adapter);

                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private class HorizontalSpacing extends RecyclerView.ItemDecoration {

        private final int horizontalSpacing;

        public HorizontalSpacing(int verticalSpaceHeight) {
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
