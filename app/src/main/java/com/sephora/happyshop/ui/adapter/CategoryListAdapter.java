package com.sephora.happyshop.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sephora.happyshop.R;
import com.sephora.happyshop.model.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kishore on 21/8/2017.
 */

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CustomViewHolder> {
    private List<Category> categorys;
    private Context mContext;

    private ClickListner clickListner;

    public CategoryListAdapter(Context context, List<Category> categorys) {
        this.categorys = categorys;
        this.mContext = context;
    }

    public interface ClickListner {


        void onClick(Category category);


    }

    public void setClickListner(ClickListner clickListner){
        this.clickListner = clickListner;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_category, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final Category category = categorys.get(i);



        customViewHolder.txtCategory.setText(category.getCategory());

        customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListner.onClick(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != categorys ? categorys.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        @BindView(R.id.txtCategory)
        TextView txtCategory;

        public CustomViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

        }
    }
}