package com.sephora.happyshop.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.sephora.happyshop.R;
import com.sephora.happyshop.constants.ICommonConstants;
import com.sephora.happyshop.manager.APIManager;
import com.sephora.happyshop.manager.AlertManager;
import com.sephora.happyshop.manager.Utils;
import com.sephora.happyshop.model.Product;
import com.sephora.happyshop.service.APIService;
import com.sephora.happyshop.ui.adapter.ProductListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.sephora.happyshop.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kishore on 21/8/2017.
 */
public class ProductListActivity extends BaseActivity {

    @BindView(R.id.swipeRefreshLayout)
    SwipyRefreshLayout swipeRefreshLayout;

    @BindView(R.id.listview_products)
    RecyclerView listview_products;
    APIService apiService;
    Category category;
    ProductListAdapter productListAdapter;
    int page = 1;
    List<Product> products;
    private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        baseInitialization();
        intializeUI();
    }

    private void intializeUI() {

        extras 			= getIntent().getExtras();
        if(extras!=null)
        {
            category = (Category) extras.getSerializable(ICommonConstants.CATEGORY);
        }

        ActionBar ab = getSupportActionBar();
        ab.setTitle(category.getCategory());



        apiService = APIManager.getRetrofit().create(APIService.class);

        products = new ArrayList<>();
        productListAdapter = new ProductListAdapter(context,products);

        listview_products.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        listview_products.setLayoutManager(new LinearLayoutManager(context));
        listview_products.setHasFixedSize(true);
        listview_products.setAdapter(productListAdapter);


        pDialog.show();
        loadData(page);

        setData();

    }

    private void setData() {

        swipeRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if (SwipyRefreshLayoutDirection.TOP == direction) {
                    page = 1;
                    loadData(page);

                } else if (SwipyRefreshLayoutDirection.BOTTOM == direction) {
                    page++;
                    loadData(page);
                }
            }
        });
    }

    public void loadData(final int page) {

        if(!Utils.isNetworkAvailable(context)) {
            AlertManager.shorttoastMessage(context,getString(R.string.connection_err));
            swipeRefreshLayout.setRefreshing(false);

            pDialog.dismiss();
            finish();
            return;
        }





        Call<Category> call1 = apiService.getProducts(category.getCategory(), page);


        call1.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                pDialog.dismiss();
                swipeRefreshLayout.setRefreshing(false);

                if (response.body().getProducts() == null) return;

                if (page == 1) {

                    products =new ArrayList<Product>();
                    products = response.body().getProducts();
                    productListAdapter = new ProductListAdapter(context,products);
                    listview_products.setLayoutManager(new LinearLayoutManager(context));
                    listview_products.setAdapter(productListAdapter);

                } else {
                    products.addAll(response.body().getProducts());
                    productListAdapter = new ProductListAdapter(context,products);

                    listview_products.getAdapter().notifyDataSetChanged();

                }

                productListAdapter.setClickListner(new ProductListAdapter.ClickListner() {
                    @Override
                    public void onClick(Product product) {
                        intent =new Intent(context,ProductDetailActivity.class);
                        intent.putExtra(ICommonConstants.PRODUCT,product);
                        startActivity(intent);
                    }
                });




            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                pDialog.dismiss();
                AlertManager.shorttoastMessage(context,getString(R.string.common_err));

            }
        });
    }

}
