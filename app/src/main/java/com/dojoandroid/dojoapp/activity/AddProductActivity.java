package com.dojoandroid.dojoapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.dojoandroid.dojoapp.R;
import com.dojoandroid.dojoapp.database.DBManager;
import com.dojoandroid.dojoapp.entity.Product;

public class AddProductActivity extends Activity {
    private EditText mEditTextProductName;
    private EditText mEditTextProductPrice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        loadUI();
    }

    private void loadUI() {

        mEditTextProductName = (EditText) findViewById(R.id.editText_name);
        mEditTextProductPrice = (EditText) findViewById(R.id.editText_price);

        Button saveButton = (Button) findViewById(R.id.button);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox_status);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int status = (checkBox.isChecked()? 1 : 0);
                final float price = Float.valueOf(mEditTextProductPrice.getText().toString());
                final String name = mEditTextProductName.getText().toString();

                Product product = new Product(price, name, status);
                DBManager dbManager = new DBManager(AddProductActivity.this);

                dbManager.insert(product);

                Intent intent = new Intent(AddProductActivity.this, ProductActivity.class); intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); startActivity(intent);
            }
        });

    }
}
