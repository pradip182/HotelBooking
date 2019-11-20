package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    Spinner spinlocation, spinroom;
    TextView tvindate, tvoutdate;
    Button btncalc;
    TextView tvroom, tvadult, tvkids;
    int year2, year3;
    int month2, month3;
    int day2, day3;
    Button simpledatepicker1, simpledatepicker2;
    int no_of_room;
    TextView tvdayofstay, totalsuite, tvtotal, tvVat, tvroomno, tverr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinlocation = findViewById(R.id.spinlocation);
        spinroom = findViewById(R.id.spinroom);
        totalsuite = findViewById(R.id.totalsuite);
        tvtotal = findViewById(R.id.tvtotal);
        tvVat = findViewById(R.id.tvVat);
        tvroomno = findViewById(R.id.tvroomno);
        tverr = findViewById(R.id.tverr);
        tvindate = findViewById(R.id.tvindate);
        tvdayofstay = findViewById(R.id.tvdayofstay);
        tvoutdate = findViewById(R.id.tvoutdate);
        simpledatepicker1 = findViewById(R.id.simpledatepicker1);
        simpledatepicker2 = findViewById(R.id.simpledatepicker2);
        tvadult = findViewById(R.id.tvadult);
        tvkids = findViewById(R.id.tvkids);
        tvroom = findViewById(R.id.tvroom);
        btncalc = findViewById(R.id.btncalc);
        String location[] = {"Bhaktapur", "Chitwan", "Kathmandu"};
        String room[] = {"Deluxe", "AC", "Platinum"};


        ArrayAdapter adapter = new ArrayAdapter<>
                (
                        this,
                        android.R.layout.simple_list_item_1, location
                );
        ArrayAdapter adapter1 = new ArrayAdapter<>
                (
                        this,
                        android.R.layout.simple_list_item_1, room
                );
        spinlocation.setAdapter(adapter);
        spinroom.setAdapter(adapter1);
        simpledatepicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCalendar1();
            }
        });


        simpledatepicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCalendar2();

            }
        });

    }

    private void loadCalendar2() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "Checked Out At::" + month + "/" + dayOfMonth + "/" + year;
                month3 = month;
                day3 = dayOfMonth;
                year3 = year;
                tvoutdate.setText(date);
            }

        }, year, month, day);
        datePickerDialog.show();
    }


    private void loadCalendar1() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "Checked In At::" + month + "/" + dayOfMonth + "/" + year;
                month2 = month;
                day2 = dayOfMonth;
                year2 = year;
                tvindate.setText(date);
            }

        }, year, month, day);
        datePickerDialog.show();


        btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(tvindate.getText())) {
                    tverr.setText("Please enter Checked in Date ");
                    return;
                } else if (TextUtils.isEmpty(tvkids.getText())) {
                    tverr.setText("Please enter number of Kids ");
                    return;
                } else if (TextUtils.isEmpty(tvadult.getText())) {
                    tverr.setText("Please enter number of adults ");
                    return;
                } else if (TextUtils.isEmpty(tvoutdate.getText())) {
                    tverr.setText("Please enter Checked out Date ");
                    return;
                } else if (TextUtils.isEmpty(tvroom.getText())) {
                    tverr.setText("Please enter Number of Rooms ");
                    return;
                }

                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal1.set(year2, month2, day2);
                cal2.set(year3, month3, day3);
                long millis1 = cal1.getTimeInMillis();
                long millis2 = cal2.getTimeInMillis();
                long diff = millis2 - millis1;
                long diffDays = (diff / (86400000));
                no_of_room = Integer.parseInt(tvroom.getText().toString());
                double price;
                double Total_Price;
                double Grand_Total;


                String suite = spinroom.getSelectedItem().toString();

                if (suite == "Deluxe") {

                    price = 2000;
                    Total_Price = price * no_of_room * diffDays;
                    Grand_Total = (0.13) * Total_Price + Total_Price;

                    tvdayofstay.setText("Total Days:" + diffDays);
                    tvroomno.setText(("Number of Room(s):" + no_of_room));
                    totalsuite.setText("Room Price Per Night:" + "2000");
                    tvtotal.setText("Total:" + Total_Price);
                    tvVat.setText("Total price including 13% VAT and 10% SC:" + Grand_Total);
                    Toast.makeText(MainActivity.this, "Total price is" + Grand_Total, Toast.LENGTH_SHORT).show();


                } else if (suite == "AC") {

                    price = 3000;
                    Total_Price = price * no_of_room * diffDays;
                    Grand_Total = (0.13) * Total_Price + Total_Price;
                    tvdayofstay.setText("Total Days:" + diffDays);
                    tvroomno.setText(("Number of Room(s):" + no_of_room));
                    totalsuite.setText("Room Price Per Night:" + "3000");
                    tvtotal.setText("Total:" + Total_Price);
                    tvVat.setText("Grand Total:" + Grand_Total);
                    Toast.makeText(MainActivity.this, "Total price is" + Grand_Total, Toast.LENGTH_SHORT).show();

                } else if (suite == "Platinum") {
                    price = 4000;
                    Total_Price = price * no_of_room * diffDays;
                    Grand_Total = (0.13) * Total_Price + Total_Price;
                    tvdayofstay.setText("Total Days:" + diffDays);
                    tvroomno.setText(("Number of Room(s):" + no_of_room));
                    totalsuite.setText("Room Price Per Night:" + "4000");
                    tvtotal.setText("Total:" + Total_Price);
                    tvVat.setText("Grand Total:" + Grand_Total);
                    Toast.makeText(MainActivity.this, "Total price is" + Grand_Total, Toast.LENGTH_SHORT).show();

                }

            }
        });

    }


}
