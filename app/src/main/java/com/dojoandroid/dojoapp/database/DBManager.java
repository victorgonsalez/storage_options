package com.dojoandroid.dojoapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.dojoandroid.dojoapp.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    /**
     * Database helper instance
     */
    private DBHelper mDatabaseHelper = null;

    /**
     * Database instance
     */
    private SQLiteDatabase mDatabase = null;

    private static DBManager dbManager;

    /**
     * Constructor
     *
     * @param context of the application
     */
    public DBManager(Context context) {
        mDatabaseHelper = new DBHelper(context);
    }

    /**
     * Open the database
     */
    private void open() throws SQLException {
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }

    /**
     * Close the database
     */
    private void close() {
        mDatabaseHelper.close();
    }

    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();

        open();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_PRODUCT, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {

//percorrer do while enquanto tiver produtos e adicionar os produtos a lista de produtos
//Exemplo de como pegar valor das colunas da tabela produto
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
                Float price = cursor.getFloat(cursor.getColumnIndex(DBHelper.COLUMN_PRICE));
                int status = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_STATUS));

                Product product = new Product(price, name, status);
                products.add(product);
            } while (cursor.moveToNext());
            cursor.close();
        }
        close();

        return products;
    }


    public long insert(Product product) {
        open();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, product.name);
        values.put(DBHelper.COLUMN_PRICE, product.price);
        values.put(DBHelper.COLUMN_STATUS, product.status);
        long newItem = mDatabase.insert(DBHelper.TABLE_PRODUCT, null, values);
        close();
        return newItem;
    }
}
