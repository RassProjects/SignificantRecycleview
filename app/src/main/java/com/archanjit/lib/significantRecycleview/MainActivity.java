package com.archanjit.lib.significantRecycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.archanjit.lib.significantrecycleview.main.SignificantAdapter;
import com.archanjit.lib.significantrecycleview.main.SignificantRecycleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] myList = new String[]
            {"Item1", "Item2", "Item3", "Item4", "Item5", "Item6", "Item7", "Item8", "Item9", "Item10",
                    "Item11", "Item12", "Item13", "Item14", "Item15"};

    private List<String> listToUse = new ArrayList<>();
    private List<String> listToUseTemp = new ArrayList<>();
    private SignificantRecycleView recycleView;
    private MyCustomAdapter adapter;
    private Button enableDisableButton, removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listToUse = Arrays.asList(myList);
        listToUseTemp.addAll(listToUse);

        removeButton = (Button) findViewById(R.id.removeButton);
        recycleView = (SignificantRecycleView) findViewById(R.id.significantRecycleView);
        recycleView.setEmptyTextMessage("Its Empty RecycliView");
        recycleView.setLinearLayoutManagerToView(this);
        adapter = new MyCustomAdapter(listToUseTemp);
        adapter.setOnItemClickListener(new SignificantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showToast("OnItemClick Called @ Position" + position);
            }
        });
        adapter.setOnItemLongClickListener(new SignificantAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                showToast("onItemLongClick Called @ Position" + position);
                return false;
            }
        });
        recycleView.setAdapterToView(adapter);
        enableDisableButton = (Button) findViewById(R.id.enableDisableButton);
        enableDisableButton.setOnClickListener(onClickListener);
        enableDisableButton.setText("Disable Scroll");
        removeButton.setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.removeButton:
                    removeOnItemFromList();
                    break;
                case R.id.enableDisableButton:
                    enableDisableRecycleView();
                    break;
            }
        }

    };

    private void removeOnItemFromList() {
        runOnUiThread(new Runnable() {
            public void run() {
                if (listToUseTemp.size() > 0)
                    listToUseTemp.remove(0);
                if (recycleView != null && recycleView.getRecycleView() != null && recycleView.getRecycleView().getAdapter() != null)
                    recycleView.getRecycleView().getAdapter().notifyDataSetChanged();
            }
        });

    }

    private void enableDisableRecycleView() {
        if (recycleView != null) {
            if (recycleView.isEnableScroll()) {
                recycleView.setEnableScroll(false);
                enableDisableButton.setText("Enable Scroll");
            }else {
                recycleView.setEnableScroll(true);
                enableDisableButton.setText("Disable Scroll");
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private class MyCustomAdapter extends SignificantAdapter<MyCustomAdapter.ViewHolder> {
        @Override
        public int getRootViewId() {
            return R.id.rootLayout;
        }

        @Override
        public String getTag() {
            return MyCustomAdapter.class.getName();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public MyCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_recycleview, parent, false);
            return new MyCustomAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            MyCustomAdapter.ViewHolder viewHolder = (MyCustomAdapter.ViewHolder) holder;
            viewHolder.textureViewItemName.setText(list.get(position));
        }

        private List<String> list;

        public MyCustomAdapter(List<String> list) {
            this.list = list;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textureViewItemName;
            Button removeButton;
            LinearLayout linearLayout;

            public ViewHolder(View itemView) {
                super(itemView);
                linearLayout = (LinearLayout) itemView.findViewById(R.id.rootLayout);
                // removeButton = (Button) itemView.findViewById(R.id.removeButton);
                textureViewItemName = (TextView) itemView.findViewById(R.id.itemNameTextView);
            }
        }
    }
}
