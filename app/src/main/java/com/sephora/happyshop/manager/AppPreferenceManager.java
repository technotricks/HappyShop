package com.sephora.happyshop.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sephora.happyshop.model.Cart;
import com.sephora.happyshop.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.reflect.Type;
import java.util.List;


/**
 * Created by Kishore on 21/8/2017.
 */

public class AppPreferenceManager {

    public static void addToCart(Context ctx, Product item) {
        SharedPreferences prefs = ctx.getSharedPreferences("HappyShop",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        ArrayList<Cart> listItems = new ArrayList<>();
        listItems = getCardItems(ctx);
        int index = -1;

        for (int i = 0; i < listItems.size(); i++) {

            if (listItems.get(i).getProducts().get(0).getId().equals(item.getId())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            listItems.get(index).getProducts().add(item);
        } else {
            Cart cart = new Cart();
            cart.getProducts().add(item);
            listItems.add(cart);
        }

        Gson gson = new Gson();
        Type listOfTestObject = new TypeToken<List<Cart>>() {
        }.getType();
        String data = gson.toJson(listItems, listOfTestObject);
        editor.putString("HappyShopCart", data);
        editor.commit();
    }


    public static void removeAllFromCart(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("HappyShop",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        ArrayList<Cart> listItems = new ArrayList<>();


        Gson gson = new Gson();
        Type listOfTestObject = new TypeToken<List<Cart>>() {
        }.getType();
        String data = gson.toJson(listItems, listOfTestObject);
        editor.putString("HappyShopCart", data);
        editor.commit();
    }

    public static ArrayList<Cart> getCardItems(Context ctx) {
        ArrayList<Cart> listItems = null;
        SharedPreferences prefs = ctx.getSharedPreferences("HappyShop",
                Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type listOfTestObject = new TypeToken<List<Cart>>() {
        }.getType();

        try {
            String data = prefs.getString("HappyShopCart", gson.toJson(new ArrayList<Cart>(), listOfTestObject));

            listItems = gson.fromJson(data, listOfTestObject);
        } catch (Exception e) {
            Log.e("", "Error" + e.getMessage());
            e.printStackTrace();
        }
        return listItems;
    }
}
