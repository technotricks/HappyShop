package com.sephora.happyshop.ui.activity;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sephora.happyshop.R;
import com.sephora.happyshop.constants.ICommonConstants;
import com.sephora.happyshop.manager.APIManager;
import com.sephora.happyshop.manager.AlertManager;
import com.sephora.happyshop.manager.AppPreferenceManager;
import com.sephora.happyshop.manager.Utils;
import com.sephora.happyshop.model.Category;
import com.sephora.happyshop.model.Product;
import com.sephora.happyshop.service.APIService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kishore on 21/8/2017.
 */
public class ProductDetailActivity extends BaseActivity {

    @BindView(R.id.imgProduct)
    ImageView imgProduct;

    @BindView(R.id.txtProductName)
    TextView txtProductName;

    @BindView(R.id.txtProductDesc)
    TextView txtProductDesc;

    @BindView(R.id.txtProductPrice)
    TextView txtProductPrice;

    @BindView(R.id.buttonAddToCart)
    Button buttonAddToCart;
    @BindView(R.id.imgSale)
    ImageView imgSale;

    private Bundle extras;
    private Product product;
    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        baseInitialization();
        intializeUI();
        setData();
        loadData();
    }


    private void intializeUI() {


        extras = getIntent().getExtras();
        if (extras != null) {
            product = (Product) extras.getSerializable(ICommonConstants.PRODUCT);
        }

        apiService = APIManager.getRetrofit().create(APIService.class);


    }

    private void setData() {

        ActionBar ab = getSupportActionBar();
        ab.setTitle(getString(R.string.app_name));
        ab.setSubtitle(product.getCategory());

        txtProductName.setText(product.getName());

        txtProductDesc.setText(product.getDescription());
        txtProductPrice.setText("S$ " + product.getPrice());
        imgSale.setVisibility(product.getUnderSale() ? View.VISIBLE : View.INVISIBLE);

        Glide.with(this)
                .load(product.getImgUrl())
                .placeholder(R.drawable.empty_image)
                .error(R.drawable.err_image)
                .into(imgProduct);


        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppPreferenceManager.addToCart(context, product);

                AlertManager.shorttoastMessage(context, getString(R.string.item_added));


            }
        });

    }

    public void loadData() {
        pDialog.show();

        if (!Utils.isNetworkAvailable(context)) {
            AlertManager.shorttoastMessage(context, getString(R.string.connection_err));
            pDialog.dismiss();
            finish();
            return;
        }

        Call<Category> call1 = apiService.getProduct("" + product.getId());


        call1.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                pDialog.dismiss();

                product = response.body().getProduct();
                setData();


            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                pDialog.dismiss();
                System.out.println("ERR");
            }
        });

    }

}