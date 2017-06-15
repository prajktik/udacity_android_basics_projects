package com.example.anroid.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private static final int PRICE_COFFEE = 5;
    private static final int PRICE_WHIPPED_CREAM = 1;
    private static final int PRICE_CHOCOLATE = 2;
    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText etName = (EditText) findViewById(R.id.et_name);
        String name = etName.getText().toString();

        CheckBox cbCream = (CheckBox) findViewById(R.id.cb_whipped_cream);
        boolean hasWhippedCream = cbCream.isChecked();

        CheckBox cbChocolate = (CheckBox) findViewById(R.id.cb_chocolate);
        boolean hasChocolate = cbChocolate.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);

        String summary = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject,name));
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate){

        int basePrice = PRICE_COFFEE;
        if(hasWhippedCream)
            basePrice += PRICE_WHIPPED_CREAM;
        if(hasChocolate)
            basePrice += PRICE_CHOCOLATE;

        int price = quantity * basePrice;

        return price;
    }

    public void increment(View view) {
        if(quantity == 100){
            Toast.makeText(this,getString(R.string.toast_max_coffees),Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        if(quantity == 1){
            Toast.makeText(this,R.string.toast_min_coffees,Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.tv_value);
        quantityTextView.setText("" + number);
    }


    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate) {
        StringBuilder summary = new StringBuilder();
        summary.append(getString(R.string.order_summary_name, name));
        summary.append(getString(R.string.add_whipped_cream)+hasWhippedCream+"\n");
        summary.append(getString(R.string.add_chocolate)+hasChocolate+"\n");
        summary.append(getString(R.string.summary_quantity, quantity)+"\n");
        summary.append(getString(R.string.summary_total)+price+"\n");
        summary.append(getString(R.string.summary_thank_you));
        return summary.toString();
    }
}