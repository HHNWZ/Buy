package com.example.user.myapplication;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by 888888888 on 2017/9/9.
 */

public class AddActivity2 extends AppCompatActivity {

    private EditText edProductName;
    private EditText edProductClass;
    private EditText edProductPrice;
    private EditText edProductAmount;
    private MyDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sell);
        findViews();
        helper = new MyDBHelper(this, "product3.db", null, 1);
    }

    private void findViews() {
        edProductName=(EditText)findViewById(R.id.editText);
        edProductClass=(EditText)findViewById(R.id.editText2);
        edProductPrice=(EditText)findViewById(R.id.editText3);
        edProductAmount=(EditText)findViewById(R.id.editText4);

    }

    public void add1(View v){
        String ProductName=edProductName.getText().toString();
        String ProductClass=edProductClass.getText().toString();
        int ProductPrice=Integer.parseInt(edProductPrice.getText().toString());
        int ProductAmount=Integer.parseInt(edProductAmount.getText().toString());

        ContentValues values = new ContentValues();
        values.put("productname", ProductName);
        values.put("productclass", ProductClass);
        values.put("productprice", ProductPrice);
        values.put("productamount", ProductAmount);
        long id = helper.getWritableDatabase().insert("product", null, values);
        Log.d("ADD", id+"");
    }
}
