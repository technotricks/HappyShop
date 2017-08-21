package com.sephora.happyshop.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sephora.happyshop.R;
import com.sephora.happyshop.manager.AlertManager;
import com.sephora.happyshop.manager.AppPreferenceManager;
import com.sephora.happyshop.model.Cart;
import com.sephora.happyshop.model.Product;
import com.sephora.happyshop.ui.activity.HomeActivity;
import com.sephora.happyshop.ui.activity.ProductListActivity;
import com.sephora.happyshop.ui.adapter.CartListAdapter;
import com.sephora.happyshop.ui.adapter.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kishore on 21/8/2017.
 */
public class CartFragment extends BaseFragment {

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @BindView(R.id.recycler_viewCart)
    RecyclerView recycler_viewCart;

    @BindView(R.id.txtItems)
    TextView txtItems;

    @BindView(R.id.txtTotal)
    TextView txtTotal;

    @BindView(R.id.btnPlaceOrder)
    Button btnPlaceOrder;

    List<Product> products;
    CartListAdapter cartListAdapter;
    ArrayList<Cart> cartList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        baseInitialization();
        intializeUI(view);
        setData();

        return view;
    }

    private void intializeUI(final View view) {
        ButterKnife.bind(this, view);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartList.size()==0){
                    AlertManager.shorttoastMessage(context, "Cart is empty");
                    return;
                }
                AppPreferenceManager.removeAllFromCart(context);

                ((HomeActivity) getActivity()).refresh();
                AlertManager.shorttoastMessage(context, getString(R.string.items_placed));
                setData();

            }
        });

    }

    private void setData() {

        cartList = new ArrayList<>();
        cartList = AppPreferenceManager.getCardItems(context);


        int productCount = 0;
        int totalPrice = 0;
        for (Cart cart : cartList) {

            int count = cart.getProducts().size();
            productCount += count;
            totalPrice += cart.getProducts().get(0).getPrice() * count;
        }

        txtItems.setText("Items: " + productCount);
        txtTotal.setText("Total S$: " + totalPrice);

        cartListAdapter = new CartListAdapter(context, cartList);
        recycler_viewCart.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        recycler_viewCart.setLayoutManager(new LinearLayoutManager(context));
        recycler_viewCart.setAdapter(cartListAdapter);
    }


}
