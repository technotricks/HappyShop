package com.sephora.happyshop.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sephora.happyshop.R;
import com.sephora.happyshop.constants.ICommonConstants;
import com.sephora.happyshop.manager.APIManager;
import com.sephora.happyshop.model.Category;
import com.sephora.happyshop.model.Product;
import com.sephora.happyshop.service.APIService;
import com.sephora.happyshop.ui.activity.ProductListActivity;
import com.sephora.happyshop.ui.adapter.CategoryListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.sephora.happyshop.model.Category;
/**
 * Created by Kishore on 21/8/2017.
 */
public class CategoryFragment extends BaseFragment {

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        return fragment;
    }


    @BindView(R.id.recycler_viewCategory)
    RecyclerView recycler_viewCategory;

    CategoryListAdapter categoryListAdapter;
    APIService apiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);

        baseInitialization();
        intializeUI(view);
        return view;
    }

    private void intializeUI(final View view) {

        ButterKnife.bind(this, view);
        apiService = APIManager.getRetrofit().create(APIService.class);

        List<Category> categorys =new ArrayList<>();
        categorys.add(new Category("Makeup"));
        categorys.add(new Category("Tools"));
        categorys.add(new Category("Skincare"));
        categorys.add(new Category("Nails"));
        categorys.add(new Category("Men"));



        categoryListAdapter = new CategoryListAdapter(context,categorys);
        recycler_viewCategory.setLayoutManager(new LinearLayoutManager(context));
        recycler_viewCategory.setAdapter(categoryListAdapter);
        categoryListAdapter.setClickListner(new CategoryListAdapter.ClickListner() {
            @Override
            public void onClick(Category category) {
                intent =new Intent(context, ProductListActivity.class);

                intent.putExtra(ICommonConstants.CATEGORY,category);

                startActivity(intent);
            }
        });

    }

}
