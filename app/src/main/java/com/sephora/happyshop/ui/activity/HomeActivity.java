package com.sephora.happyshop.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.sephora.happyshop.R;
import com.sephora.happyshop.manager.AppPreferenceManager;
import com.sephora.happyshop.model.Cart;
import com.sephora.happyshop.model.Product;
import com.sephora.happyshop.ui.fragment.CartFragment;
import com.sephora.happyshop.ui.fragment.CategoryFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by Kishore on 21/8/2017.
 */
public class HomeActivity extends BaseActivity {


    @BindView(R.id.navigation)
    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        baseInitialization();
        intializeUI();
    }

    private void intializeUI() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Category", R.drawable.ic_action_category, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Cart", R.drawable.ic_action_cart, R.color.colorPrimary);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);// Set background color
        bottomNavigation.setCurrentItem(0);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                Fragment selectedFragment = null;
                switch (position) {
                    case 0:

                        selectedFragment = CategoryFragment.newInstance();
                        break;
                    case 1:


                        selectedFragment = CartFragment.newInstance();
                        break;

                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new CategoryFragment());
        transaction.commit();
    }


    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Cart> cartList = AppPreferenceManager.getCardItems(context);

        int count = cartList.size();

        if (count > 0) {

            int productCount = 0;
            for (Cart cart: cartList) {
                productCount += cart.getProducts().size();
            }

            AHNotification notification = new AHNotification.Builder()
                    .setText(""+productCount)
                    .setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
                    .setTextColor(ContextCompat.getColor(context, R.color.cardview_dark_background))
                    .build();
            bottomNavigation.setNotification(notification, 1);

        } else {

            bottomNavigation.setNotification("", 1);

        }
    }

    public void refresh(){
        onResume();
    }


}
