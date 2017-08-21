package com.sephora.happyshop.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sephora.happyshop.R;
import com.sephora.happyshop.model.Category;
import com.sephora.happyshop.model.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kishore on 21/8/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.CustomViewHolder> {
    private List<Product> products;
    private Context mContext;

    private ClickListner clickListner;

    public ProductListAdapter(Context context, List<Product> products) {
        this.products = products;
        this.mContext = context;
    }

    public interface ClickListner {


        void onClick(Product product);


    }

    public void setClickListner(ClickListner clickListner){
        this.clickListner = clickListner;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_product_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final Product product = products.get(i);


        Glide.with(customViewHolder.itemView.getContext())
                .load(product.getImgUrl())
                .placeholder(R.drawable.empty_image)
                .error(R.drawable.err_image)
                .into(customViewHolder.imgProduct);


        customViewHolder.txtProductName.setText(product.getName());
        customViewHolder.txtPrice.setText("S$ "+product.getPrice());
        customViewHolder.txtCategory.setText(product.getCategory());

        customViewHolder.imgSale.setVisibility(product.getUnderSale()?View.VISIBLE:View.INVISIBLE);

        customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListner!=null)
                clickListner.onClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != products ? products.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.imgProduct)
        ImageView imgProduct;
        @BindView(R.id.imgSale)
        ImageView imgSale;
        @BindView(R.id.txtProductName)
        TextView txtProductName;
        @BindView(R.id.txtPrice)
        TextView txtPrice;
        @BindView(R.id.txtCategory)
        TextView txtCategory;
        @BindView(R.id.txtAvailable)
        TextView txtAvailable;




        public CustomViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

        }
    }
}