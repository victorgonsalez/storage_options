package com.dojoandroid.dojoapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.dojoandroid.dojoapp.R;
import com.dojoandroid.dojoapp.adapters.ProductsAdapter;
import com.dojoandroid.dojoapp.database.DBManager;
import com.dojoandroid.dojoapp.entity.Product;

import java.util.List;


/**
 * Created by vgonsalez on 6/13/16.
 */
public class ProductActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ListView listView = (ListView) findViewById(R.id.list_product);
        Button addProduct = (Button) findViewById(R.id.button_addProduct); addProduct.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { Intent intent = new Intent(ProductActivity.this, AddProductActivity.class); startActivity(intent); } });

        DBManager dbManager = new DBManager(this);
        List<Product> productList = dbManager.getAllProducts();

        ProductsAdapter productsAdapter = new ProductsAdapter(this, productList);
        listView.setAdapter(productsAdapter);
    }
}

