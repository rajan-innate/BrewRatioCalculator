package com.example.e6420.brewratiocalculator;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layoutMethod;
    EditText editTextMethods;
    TextView textViewCoffeeQuantity,textViewWaterQuantity;
    TextView textViewCoffeeGrams,textViewWaterGrams;
    CardView cardViewCoffee, cardViewWater;
    private DroppyMenuPopup.Builder droppyBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutMethod = (RelativeLayout) findViewById(R.id.layoutMethod);
        editTextMethods = (EditText) findViewById(R.id.editTextMethods);
        textViewCoffeeGrams = (TextView) findViewById(R.id.textViewCoffeeGrams);
        textViewWaterGrams = (TextView) findViewById(R.id.textViewWaterGrams);
        cardViewCoffee = (CardView) findViewById(R.id.cardViewCoffee);
        cardViewWater = (CardView) findViewById(R.id.cardViewWater);
        textViewCoffeeQuantity = (TextView) findViewById(R.id.textViewCoffeeQuantity);
        textViewWaterQuantity = (TextView) findViewById(R.id.textViewWaterQuantity);
        final CrystalSeekbar rangeSeekbar = (CrystalSeekbar)findViewById(R.id.rangeSeekbar1);

        // set listener
        rangeSeekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue) {
                textViewCoffeeQuantity.setText(String.valueOf(minValue));
            }
        });

// set final value listener
        rangeSeekbar.setOnSeekbarFinalValueListener(new OnSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number value) {
                textViewCoffeeQuantity.setText(String.valueOf(value));
            }
        });

        setupSortDroppyLayout();

        cardViewCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCoffeePopUp();
            }
        });

        cardViewWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWaterPopUp();
            }
        });

        layoutMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                droppyBuilder.build().show();
            }
        });
        editTextMethods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                droppyBuilder.build().show();
            }
        });

        textViewCoffeeGrams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(textViewCoffeeGrams.getText().toString().equals("GRAMS")){
                  textViewCoffeeGrams.setText("TABLESPOONS");
              }else {
                  textViewCoffeeGrams.setText("GRAMS");
              }
            }
        });

        textViewWaterGrams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textViewWaterGrams.getText().toString().equals("GRAMS")){
                    textViewWaterGrams.setText("FLUID OUNCES");
                }
                else   if(textViewWaterGrams.getText().toString().equals("FLUID OUNCES")){
                    textViewWaterGrams.setText("CUPS");
                }
                else {
                    textViewWaterGrams.setText("GRAMS");
                }
            }
        });

    }

    private void setupSortDroppyLayout() {

        droppyBuilder = new DroppyMenuPopup.Builder(this, layoutMethod);
        droppyBuilder.addMenuItem(new DroppyMenuItem("French Press"))
                .addMenuItem(new DroppyMenuItem("Chemex"))
                .addMenuItem(new DroppyMenuItem("Drip"))
                .addMenuItem(new DroppyMenuItem("Aeropress"))
                .addMenuItem(new DroppyMenuItem("Moka Pot"))
                .addMenuItem(new DroppyMenuItem("Siphon"))
                .setPopupAnimation(new DroppyFadeInAnimation())
                .triggerOnAnchorClick(false);

        droppyBuilder.setOnClick(new DroppyClickCallbackInterface() {
            @Override
            public void call(View v, int id) {
                String stringInterval = String.valueOf(id);

                switch (stringInterval) {
                    case "0":
                        editTextMethods.setText("FRENCH PRESS");
                        break;
                    case "1":
                        editTextMethods.setText("CHEMEX");
                        break;
                    case "2":
                        editTextMethods.setText("DRIP");
                        break;
                    case "3":
                        editTextMethods.setText("AEROPRESS");
                        break;
                    case "4":
                        editTextMethods.setText("MOKA POT");
                        break;
                    case "5":
                        editTextMethods.setText("SIPHON");
                        break;

                }

            }
        });
    }

    private void showCoffeePopUp() {
        final Dialog dialogJoinNow = new Dialog(this);
        dialogJoinNow.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogJoinNow.setContentView(R.layout.coffee_dialog);
        dialogJoinNow.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogJoinNow.show();
        final EditText editTextCoffeeQuantity = (EditText) dialogJoinNow.findViewById(R.id.editTextCoffeeQuantity);
        TextView textViewType = (TextView) dialogJoinNow.findViewById(R.id.textViewType);
        TextView textViewCancel = (TextView) dialogJoinNow.findViewById(R.id.textViewCancel);
        TextView textViewOk = (TextView) dialogJoinNow.findViewById(R.id.textViewOK);

        textViewType.setText(textViewCoffeeGrams.getText().toString());
        textViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogJoinNow.dismiss();
            }
        });
        textViewOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogJoinNow.dismiss();
                textViewCoffeeQuantity.setText(editTextCoffeeQuantity.getText().toString());
            }
        });

    }


    private void showWaterPopUp() {
        final Dialog dialogJoinNow = new Dialog(this);
        dialogJoinNow.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogJoinNow.setContentView(R.layout.water_dialog);
        dialogJoinNow.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogJoinNow.show();
        final EditText editTextWaterQuantity = (EditText) dialogJoinNow.findViewById(R.id.editTextWaterQuantity);
        TextView textViewType = (TextView) dialogJoinNow.findViewById(R.id.textViewType);
        TextView textViewCancel = (TextView) dialogJoinNow.findViewById(R.id.textViewCancel);
        TextView textViewOk = (TextView) dialogJoinNow.findViewById(R.id.textViewOK);
        textViewType.setText(textViewWaterGrams.getText().toString());
        textViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogJoinNow.dismiss();
            }
        });
        textViewOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogJoinNow.dismiss();
                textViewWaterQuantity.setText(editTextWaterQuantity.getText().toString());
            }
        });

    }
}
