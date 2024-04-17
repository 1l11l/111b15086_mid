package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}




public class MainActivity extends AppCompatActivity {

    private final String[] items = {"原味蛋塔", "卡拉脆雞", "義式香草紙包機", "雙色轉轉QQ球", "香酥脆薯"};
    private final boolean[] checkedItems = {false, false, false, false, false};
    private final ImageView[] mealImages = new ImageView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < mealImages.length; i++) {
            String imageViewId = "meal_image" + (i + 1);
            int resId = getResources().getIdentifier(imageViewId, "id", getPackageName());
            mealImages[i] = findViewById(resId);
        }

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showMenuDialog();
            }
        });
    }


    private void showMenuDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("選擇餐點")
                .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedItems[which] = isChecked;
                    }
                })
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        StringBuilder selectedItems = new StringBuilder();
                        for (int i = 0; i < checkedItems.length; i++) {
                            if (checkedItems[i]) {
                                selectedItems.append(items[i]).append("\n");
                            }
                        }
                        TextView textView = (TextView) findViewById(R.id.textView2);
                        textView.setText("您的餐點有：\n" + selectedItems.toString());
                    }
                })
                .setNegativeButton("離開", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // 取消選擇
                    }
                });

        builder.create().show();

    }
}