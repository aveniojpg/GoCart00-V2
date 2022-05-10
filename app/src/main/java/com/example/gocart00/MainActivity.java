package com.example.gocart00;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    DecimalFormat df = new DecimalFormat("#.##");


    int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, count7 = 0, count8 = 0, count9 = 0, count10 = 0, count11 = 0, count12 = 0, count13 = 0;
    int check1 = 0, check2 = 0, check3 = 0, check4 = 0, check5 = 0, check6 = 0, check7 = 0, check8 = 0, check9 = 0, check10 = 0, check11 = 0, check12 = 0, check13 = 0;

    double tempfp;



    Button add;
    ScrollView scrollview;
    LinearLayout linearlayout;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // binding = ActivityMainBinding.inflate(getLayoutInflater());

        readfromfilename("names.txt");  // da implementare, possibile solo scrittura.
        readfromfileprice("prices.txt");
        setContentView(R.layout.activity_main);





        // Replace Fragments, tolto per evitare errori

        /*  replaceFragment(new list1_frag());

        binding.bottomnav.setOnItemSelectedListener(item -> {

           switch (item.getItemId()) {

                case R.id.list_menu:
                    replaceFragment(new list1_frag());
                    break;


                case R.id.profile_menu:
                    replaceFragment(new profile_frag());
                    break;

            }
            return true;
        });


           */
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.statusbar));
        }

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mint1)));

        linearlayout = findViewById(R.id.container1);
        add = findViewById(R.id.savespesa);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText nameview = findViewById(R.id.receiptname);
                TextView priceview = findViewById(R.id.finalprice);

                String name = nameview.getText().toString();
                String price = priceview.getText().toString();

                calendar = Calendar.getInstance();
                dateFormat = new SimpleDateFormat("mm-dd-yyyy HH:mm");
                String date = SimpleDateFormat.getDateInstance().format(calendar.getTime());

                writetofilename("names.txt",name);
                writetofileprice("prices.txt",price);

                addCard(name,price,date);

            }
        });


    }

    private void writetofilename(String filename, String name) {
        try {
            FileOutputStream writer = new FileOutputStream("names.txt",true);
            writer.write(name.getBytes());
            writer.write("\n".getBytes());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readfromfilename(String filename) {
        byte[]content = new byte[(int)"names.txt".length()];
        try {
            FileInputStream stream = new FileInputStream("names.txt");
            stream.read(content);
            return new String(content);
        }

        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }

    }

    private void writetofileprice(String filename, String price) {
        try {
            FileOutputStream writer = new FileOutputStream("prices.txt",true);
            writer.write(price.getBytes());
            writer.write("\n".getBytes());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readfromfileprice(String filename) {
        byte[]content = new byte[(int)"prices.txt".length()];
        try {
            FileInputStream stream = new FileInputStream("prices.txt");
            stream.read(content);
            return new String(content);
        }

        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }

    }


          /*  TextView receiptexpense = findViewById(R.id.finalprice);
            String stfinalprice = receiptexpense.getText().toString();

            TextView receiptname = findViewById(R.id.receiptname);
            String streceiptname = receiptname.getText().toString();

           */

       /* dialog = new Dialog(this);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });


        */



    /* private void openDialog() {
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button goback = dialog.findViewById(R.id.goback);
        Button addreceipt = dialog.findViewById(R.id.save);



        dialog.show();

    }

     */


   /* private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_cont, fragment);
        fragmentTransaction.commit();
    }

    */

    public void addCard(String name, String price, String date) {
        View view = getLayoutInflater().inflate(R.layout.expensecard,null);

        TextView nome = view.findViewById(R.id.nome);
        nome.setText(name);

        TextView prezzo = view.findViewById(R.id.spesa);
        prezzo.setText(price);


        TextView dateview = view.findViewById(R.id.date);
        dateview.setText(date);

        linearlayout.addView(view);

       /* TextView nameview = view.findViewById(R.id.receiptname);
        TextView priceview = view.findViewById(R.id.spesa);


        nameview.setText(name);
        priceview.setText(price);


        */

        // bisogno on click listener per rimuovere layout.
    }



    public void NameUpdate(View v) { // modifica il nome nella schermata profilo
        TextView t = findViewById(R.id.input);
        String name = t.getText().toString();

        TextView t1 = findViewById(R.id.user);
        t1.setText(name);

        Button b = findViewById(R.id.namebutton);
        b.setEnabled(false);

        t.setEnabled(false);

    }

    public void plus1(View v) {
        count1++;
        if (count1 > 99) {
            count1 = 99;
            Toast.makeText(this, "Quantità troppo alta", Toast.LENGTH_SHORT).show();
        }
        TextView count = findViewById(R.id.count1);
        count.setText(Integer.toString(count1));


    }

    public void plus2(View v) {
        count2++;
        if (count2 > 99) {
            count2 = 99;
            Toast.makeText(this, "Quantità troppo alta", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count2);
        count.setText(Integer.toString(count2));

    }

    public void plus3(View v) {
        count3++;
        if (count3 > 99) {
            count3 = 99;
            Toast.makeText(this, "Quantità troppo alta", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count3);
        count.setText(Integer.toString(count3));

    }

    public void plus4(View v) {
        count4++;
        if (count4 > 99) {
            count4 = 99;
            Toast.makeText(this, "Quantità troppo alta", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count4);
        count.setText(Integer.toString(count4));

    }

    public void plus5(View v) {
        count5++;
        if (count5 > 99) {
            count5 = 99;
            Toast.makeText(this, "Quantità troppo alta", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count5);
        count.setText(Integer.toString(count5));

    }

    public void plus6(View v) {
        count6++;
        if (count6 > 99) {
            count6 = 99;
            Toast.makeText(this, "Quantità troppo alta", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count6);
        count.setText(Integer.toString(count6));

    }

    public void plus7(View v) {
        count7++;
        if (count7 > 99) {
            count7 = 99;
            Toast.makeText(this, "Quantità troppo alta", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count7);
        count.setText(Integer.toString(count7));

    }

    public void plus8(View v) {
        count8++;
        if (count8 > 99) {
            count8 = 99;
            Toast.makeText(this, "Quantità troppo alta", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count8);
        count.setText(Integer.toString(count8));

    }

    public void plus9(View v) {
        count9++;
        if (count9 > 99) {
            count9 = 99;
            Toast.makeText(this, "Quantità troppo alta", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count9);
        count.setText(Integer.toString(count9));

    }

    public void plus10(View v) {
        count10++;
        if (count10 > 99) {
            count10 = 99;
            Toast.makeText(this, "Quantità troppo alta", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count10);
        count.setText(Integer.toString(count10));

    }

    public void plus11(View v) {
        count11++;
        if (count11 > 99) {
            count11 = 99;
            Toast.makeText(this, "Quantità troppo alta", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count11);
        count.setText(Integer.toString(count11));

    }

    public void plus12(View v) {
        count12++;
        if (count12 > 99) {
            count12 = 99;
            Toast.makeText(this, "Quantità troppo alta", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count12);
        count.setText(Integer.toString(count12));

    }

    public void plus13(View v) {
        count13++;
        if (count13 > 99) {
            count13 = 99;
            Toast.makeText(this, "Quantità troppo alta", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count13);
        count.setText(Integer.toString(count13));

    }

    public void minus1(View v) {
        count1--;
        if (count1 < 0) {
            count1 = 0;
            Toast.makeText(this, "Quantità inferiore a 0", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count1);
        count.setText(Integer.toString(count1));

    }

    public void minus2(View v) {
        count2--;
        if (count2 < 0) {
            count2 = 0;
            Toast.makeText(this, "Quantità inferiore a 0", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count2);
        count.setText(Integer.toString(count2));

    }

    public void minus3(View v) {
        count3--;
        if (count3 < 0) {
            count3 = 0;
            Toast.makeText(this, "Quantità inferiore a 0", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count3);
        count.setText(Integer.toString(count3));

    }


    public void minus4(View v) {
        count4--;
        if (count4 < 0) {
            count4 = 0;
            Toast.makeText(this, "Quantità inferiore a 0", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count4);
        count.setText(Integer.toString(count4));

    }

    public void minus5(View v) {
        count5--;
        if (count5 < 0) {
            count5 = 0;
            Toast.makeText(this, "Quantità inferiore a 0", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count5);
        count.setText(Integer.toString(count5));

    }

    public void minus6(View v) {
        count6--;
        if (count6 < 0) {
            count6 = 0;
            Toast.makeText(this, "Quantità inferiore a 0", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count6);
        count.setText(Integer.toString(count6));

    }

    public void minus7(View v) {
        count7--;
        if (count7 < 0) {
            count7 = 0;
            Toast.makeText(this, "Quantità inferiore a 0", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count7);
        count.setText(Integer.toString(count7));

    }

    public void minus8(View v) {
        count8--;
        if (count8 < 0) {
            count8 = 0;
            Toast.makeText(this, "Quantità inferiore a 0", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count8);
        count.setText(Integer.toString(count8));

    }

    public void minus9(View v) {
        count9--;
        if (count9 < 0) {
            count9 = 0;
            Toast.makeText(this, "Quantità inferiore a 0", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count9);
        count.setText(Integer.toString(count9));

    }

    public void minus10(View v) {
        count10--;
        if (count10 < 0) {
            count10 = 0;
            Toast.makeText(this, "Quantità inferiore a 0", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count10);
        count.setText(Integer.toString(count10));

    }

    public void minus11(View v) {
        count11--;
        if (count11 < 0) {
            count11 = 0;
            Toast.makeText(this, "Quantità inferiore a 0", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count11);
        count.setText(Integer.toString(count11));

    }

    public void minus12(View v) {
        count12--;
        if (count12 < 0) {
            count12 = 0;
            Toast.makeText(this, "Quantità inferiore a 0", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count12);
        count.setText(Integer.toString(count12));

    }

    public void minus13(View v) {
        count13--;
        if (count13 < 0) {
            count13 = 0;
            Toast.makeText(this, "Quantità inferiore a 0", Toast.LENGTH_SHORT).show();

        }
        TextView count = findViewById(R.id.count13);
        count.setText(Integer.toString(count13));

    }


    // Metodi ADD

    public void add1(View view) {

       // Typeface font = Typeface.createFromAsset(getAssets(),"fonts/creatodisplay_light.otf");

        // estrapolazione stringhe
        EditText noteview = findViewById(R.id.note1);
        EditText priceview = findViewById(R.id.price1);

        String notestring = noteview.getText().toString();

        String pricestring = priceview.getText().toString();

        double pricedouble = Double.parseDouble(pricestring);

        BigDecimal bd = new BigDecimal(String.valueOf(pricedouble)); // controllo input
        if (bd.scale()>2) {
            Toast.makeText(getApplicationContext(), "Inserire correttamente il prezzo", Toast.LENGTH_SHORT).show();
            return;
        }

        String qtystring = String.valueOf(count1);

        switch (check1) {

            case 0:


                TextView note1 = findViewById(R.id.note1_1);
               // note1.setTypeface(font);
                note1.setText(notestring);

                TextView price1 = findViewById(R.id.price1_1);

                double finalpriceitem1 = pricedouble * count1;
                String stringfinalpriceitem1 = String.valueOf(finalpriceitem1);
              //  price1.setTypeface(font);
                price1.setText(stringfinalpriceitem1);

                TextView qty1 = findViewById(R.id.qty1_1);
              //  qty1.setTypeface(font);
                qty1.setText(qtystring);

                check1++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                TextView note2 = findViewById(R.id.note1_2);
                note2.setText(notestring);

                TextView price2 = findViewById(R.id.price1_2);

                double finalpriceitem2 = pricedouble * count1;
                String stringfinalpriceitem2 = String.valueOf(finalpriceitem2);
                price2.setText(stringfinalpriceitem2);

                TextView qty2 = findViewById(R.id.qty1_2);
                qty2.setText(qtystring);

                check1++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;


            case 2:
                TextView note3 = findViewById(R.id.note1_3);
                note3.setText(notestring);

                TextView price3 = findViewById(R.id.price1_3);

                double finalpriceitem3 = pricedouble * count1;
                String stringfinalpriceitem3 = String.valueOf(finalpriceitem3);
                price3.setText(stringfinalpriceitem3);

                TextView qty3 = findViewById(R.id.qty1_3);
                qty3.setText(qtystring);

                check1++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getApplicationContext(), "Carrello pieno (Frutta)", Toast.LENGTH_SHORT).show();
        }
    }

    public void add2(View view) {
        // estrapolazione stringhe
        EditText noteview = findViewById(R.id.note2);
        EditText priceview = findViewById(R.id.price2);

        String notestring = noteview.getText().toString();

        String pricestring = priceview.getText().toString();

        double pricedouble = Double.parseDouble(pricestring);

        BigDecimal bd = new BigDecimal(String.valueOf(pricedouble)); // controllo input
        if (bd.scale()>2) {
            Toast.makeText(getApplicationContext(), "Inserire correttamente il prezzo", Toast.LENGTH_SHORT).show();
            return;
        }

        String qtystring = String.valueOf(count2);

        switch (check2) {

            case 0:

                TextView note1 = findViewById(R.id.note2_1);
                note1.setText(notestring);

                TextView price1 = findViewById(R.id.price2_1);

                double finalpriceitem1 = pricedouble * count2;
                String stringfinalpriceitem1 = String.valueOf(finalpriceitem1);
                price1.setText(stringfinalpriceitem1);

                TextView qty1 = findViewById(R.id.qty2_1);
                qty1.setText(qtystring);

                check2++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                TextView note2 = findViewById(R.id.note2_2);
                note2.setText(notestring);

                TextView price2 = findViewById(R.id.price2_2);

                double finalpriceitem2 = pricedouble * count2;
                String stringfinalpriceitem2 = String.valueOf(finalpriceitem2);
                price2.setText(stringfinalpriceitem2);

                TextView qty2 = findViewById(R.id.qty2_2);
                qty2.setText(qtystring);

                check2++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;


            case 2:
                TextView note3 = findViewById(R.id.note2_3);
                note3.setText(notestring);

                TextView price3 = findViewById(R.id.price2_3);

                double finalpriceitem3 = pricedouble * count2;
                String stringfinalpriceitem3 = String.valueOf(finalpriceitem3);
                price3.setText(stringfinalpriceitem3);

                TextView qty3 = findViewById(R.id.qty2_3);
                qty3.setText(qtystring);

                check2++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getApplicationContext(), "Carrello pieno (Carne)", Toast.LENGTH_SHORT).show();
        }
    }

    public void add3(View view) {
        // estrapolazione stringhe
        EditText noteview = findViewById(R.id.note3);
        EditText priceview = findViewById(R.id.price3);

        String notestring = noteview.getText().toString();

        String pricestring = priceview.getText().toString();

        double pricedouble = Double.parseDouble(pricestring);

        BigDecimal bd = new BigDecimal(String.valueOf(pricedouble)); // controllo input
        if (bd.scale()>2) {
            Toast.makeText(getApplicationContext(), "Inserire correttamente il prezzo", Toast.LENGTH_SHORT).show();
            return;
        }

        String qtystring = String.valueOf(count3);

        switch (check3) {

            case 0:

                TextView note1 = findViewById(R.id.note3_1);
                note1.setText(notestring);

                TextView price1 = findViewById(R.id.price3_1);

                double finalpriceitem1 = pricedouble * count3;
                String stringfinalpriceitem1 = String.valueOf(finalpriceitem1);
                price1.setText(stringfinalpriceitem1);

                TextView qty1 = findViewById(R.id.qty3_1);
                qty1.setText(qtystring);

                check3++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                TextView note2 = findViewById(R.id.note3_2);
                note2.setText(notestring);

                TextView price2 = findViewById(R.id.price3_2);

                double finalpriceitem2 = pricedouble * count3;
                String stringfinalpriceitem2 = String.valueOf(finalpriceitem2);
                price2.setText(stringfinalpriceitem2);

                TextView qty2 = findViewById(R.id.qty3_2);
                qty2.setText(qtystring);

                check3++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;


            case 2:
                TextView note3 = findViewById(R.id.note3_3);
                note3.setText(notestring);

                TextView price3 = findViewById(R.id.price3_3);

                double finalpriceitem3 = pricedouble * count3;
                String stringfinalpriceitem3 = String.valueOf(finalpriceitem3);
                price3.setText(stringfinalpriceitem3);

                TextView qty3 = findViewById(R.id.qty3_3);
                qty3.setText(qtystring);

                check3++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getApplicationContext(), "Carrello pieno (Pesce)", Toast.LENGTH_SHORT).show();
        }
    }

    public void add4(View view) {
        // estrapolazione stringhe
        EditText noteview = findViewById(R.id.note4);
        EditText priceview = findViewById(R.id.price4);

        String notestring = noteview.getText().toString();

        String pricestring = priceview.getText().toString();

        double pricedouble = Double.parseDouble(pricestring);

        BigDecimal bd = new BigDecimal(String.valueOf(pricedouble)); // controllo input
        if (bd.scale()>2) {
            Toast.makeText(getApplicationContext(), "Inserire correttamente il prezzo", Toast.LENGTH_SHORT).show();
            return;
        }

        String qtystring = String.valueOf(count4);

        switch (check4) {

            case 0:

                TextView note1 = findViewById(R.id.note4_1);
                note1.setText(notestring);

                TextView price1 = findViewById(R.id.price4_1);

                double finalpriceitem1 = pricedouble * count4;
                String stringfinalpriceitem1 = String.valueOf(finalpriceitem1);
                price1.setText(stringfinalpriceitem1);

                TextView qty1 = findViewById(R.id.qty4_1);
                qty1.setText(qtystring);

                check4++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                TextView note2 = findViewById(R.id.note4_2);
                note2.setText(notestring);

                TextView price2 = findViewById(R.id.price4_2);

                double finalpriceitem2 = pricedouble * count4;
                String stringfinalpriceitem2 = String.valueOf(finalpriceitem2);
                price2.setText(stringfinalpriceitem2);

                TextView qty2 = findViewById(R.id.qty4_2);
                qty2.setText(qtystring);

                check4++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;


            case 2:
                TextView note3 = findViewById(R.id.note4_3);
                note3.setText(notestring);

                TextView price3 = findViewById(R.id.price4_3);

                double finalpriceitem3 = pricedouble * count4;
                df.format(finalpriceitem3);


                String stringfinalpriceitem3 = String.valueOf(finalpriceitem3); // errore?
                price3.setText(stringfinalpriceitem3);

                TextView qty3 = findViewById(R.id.qty4_3);
                qty3.setText(qtystring);

                check4++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getApplicationContext(), "Carrello pieno (Latte e Formaggi)", Toast.LENGTH_SHORT).show();
        }
    }

    public void add5(View view) {
        // estrapolazione stringhe
        EditText noteview = findViewById(R.id.note5);
        EditText priceview = findViewById(R.id.price5);

        String notestring = noteview.getText().toString();

        String pricestring = priceview.getText().toString();

        double pricedouble = Double.parseDouble(pricestring);

        BigDecimal bd = new BigDecimal(String.valueOf(pricedouble)); // controllo input
        if (bd.scale()>2) {
            Toast.makeText(getApplicationContext(), "Inserire correttamente il prezzo", Toast.LENGTH_SHORT).show();
            return;
        }

        String qtystring = String.valueOf(count5);

        switch (check5) {

            case 0:

                TextView note1 = findViewById(R.id.note5_1);
                note1.setText(notestring);

                TextView price1 = findViewById(R.id.price5_1);

                double finalpriceitem1 = pricedouble * count5;
                String stringfinalpriceitem1 = String.valueOf(finalpriceitem1);
                price1.setText(stringfinalpriceitem1);

                TextView qty1 = findViewById(R.id.qty5_1);
                qty1.setText(qtystring);

                check5++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                TextView note2 = findViewById(R.id.note5_2);
                note2.setText(notestring);

                TextView price2 = findViewById(R.id.price5_2);

                double finalpriceitem2 = pricedouble * count5;
                String stringfinalpriceitem2 = String.valueOf(finalpriceitem2);
                price2.setText(stringfinalpriceitem2);

                TextView qty2 = findViewById(R.id.qty5_2);
                qty2.setText(qtystring);

                check5++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;


            case 2:
                TextView note3 = findViewById(R.id.note5_3);
                note3.setText(notestring);

                TextView price3 = findViewById(R.id.price5_3);

                double finalpriceitem3 = pricedouble * count5;
                String stringfinalpriceitem3 = String.valueOf(finalpriceitem3);
                price3.setText(stringfinalpriceitem3);

                TextView qty3 = findViewById(R.id.qty5_3);
                qty3.setText(qtystring);

                check5++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getApplicationContext(), "Carrello pieno (Pane e Dolci)", Toast.LENGTH_SHORT).show();
        }
    }

    public void add6(View view) {
        // estrapolazione stringhe
        EditText noteview = findViewById(R.id.note6);
        EditText priceview = findViewById(R.id.price6);

        String notestring = noteview.getText().toString();

        String pricestring = priceview.getText().toString();

        double pricedouble = Double.parseDouble(pricestring);

        BigDecimal bd = new BigDecimal(String.valueOf(pricedouble)); // controllo input
        if (bd.scale()>2) {
            Toast.makeText(getApplicationContext(), "Inserire correttamente il prezzo", Toast.LENGTH_SHORT).show();
            return;
        }

        String qtystring = String.valueOf(count6);

        switch (check6) {

            case 0:

                TextView note1 = findViewById(R.id.note6_1);
                note1.setText(notestring);

                TextView price1 = findViewById(R.id.price6_1);

                double finalpriceitem1 = pricedouble * count6;
                String stringfinalpriceitem1 = String.valueOf(finalpriceitem1);
                price1.setText(stringfinalpriceitem1);

                TextView qty1 = findViewById(R.id.qty6_1);
                qty1.setText(qtystring);

                check6++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                TextView note2 = findViewById(R.id.note6_2);
                note2.setText(notestring);

                TextView price2 = findViewById(R.id.price6_2);

                double finalpriceitem2 = pricedouble * count4;
                String stringfinalpriceitem2 = String.valueOf(finalpriceitem2);
                price2.setText(stringfinalpriceitem2);

                TextView qty2 = findViewById(R.id.qty6_2);
                qty2.setText(qtystring);

                check6++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;


            case 2:
                TextView note3 = findViewById(R.id.note6_3);
                note3.setText(notestring);

                TextView price3 = findViewById(R.id.price6_3);

                double finalpriceitem3 = pricedouble * count6;
                String stringfinalpriceitem3 = String.valueOf(finalpriceitem3);
                price3.setText(stringfinalpriceitem3);

                TextView qty3 = findViewById(R.id.qty6_3);
                qty3.setText(qtystring);

                check6++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getApplicationContext(), "Carrello pieno (Essenziali)", Toast.LENGTH_SHORT).show();
        }
    }

    public void add7(View view) {
        // estrapolazione stringhe
        EditText noteview = findViewById(R.id.note7);
        EditText priceview = findViewById(R.id.price7);

        String notestring = noteview.getText().toString();

        String pricestring = priceview.getText().toString();

        double pricedouble = Double.parseDouble(pricestring);

        BigDecimal bd = new BigDecimal(String.valueOf(pricedouble)); // controllo input
        if (bd.scale()>2) {
            Toast.makeText(getApplicationContext(), "Inserire correttamente il prezzo", Toast.LENGTH_SHORT).show();
            return;
        }

        String qtystring = String.valueOf(count7);

        switch (check7) {

            case 0:

                TextView note1 = findViewById(R.id.note7_1);
                note1.setText(notestring);

                TextView price1 = findViewById(R.id.price7_1);

                double finalpriceitem1 = pricedouble * count7;
                String stringfinalpriceitem1 = String.valueOf(finalpriceitem1);
                price1.setText(stringfinalpriceitem1);

                TextView qty1 = findViewById(R.id.qty7_1);
                qty1.setText(qtystring);

                check7++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                TextView note2 = findViewById(R.id.note7_2);
                note2.setText(notestring);

                TextView price2 = findViewById(R.id.price7_2);

                double finalpriceitem2 = pricedouble * count7;
                String stringfinalpriceitem2 = String.valueOf(finalpriceitem2);
                price2.setText(stringfinalpriceitem2);

                TextView qty2 = findViewById(R.id.qty7_2);
                qty2.setText(qtystring);

                check7++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;


            case 2:
                TextView note3 = findViewById(R.id.note7_3);
                note3.setText(notestring);

                TextView price3 = findViewById(R.id.price7_3);

                double finalpriceitem3 = pricedouble * count7;
                String stringfinalpriceitem3 = String.valueOf(finalpriceitem3);
                price3.setText(stringfinalpriceitem3);

                TextView qty3 = findViewById(R.id.qty7_3);
                qty3.setText(qtystring);

                check7++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getApplicationContext(), "Carrello pieno (Surgelati)", Toast.LENGTH_SHORT).show();
        }
    }

    public void add8(View view) {
        // estrapolazione stringhe
        EditText noteview = findViewById(R.id.note8);
        EditText priceview = findViewById(R.id.price8);

        String notestring = noteview.getText().toString();

        String pricestring = priceview.getText().toString();

        double pricedouble = Double.parseDouble(pricestring);

        BigDecimal bd = new BigDecimal(String.valueOf(pricedouble)); // controllo input
        if (bd.scale()>2) {
            Toast.makeText(getApplicationContext(), "Inserire correttamente il prezzo", Toast.LENGTH_SHORT).show();
            return;
        }

        String qtystring = String.valueOf(count8);

        switch (check8) {

            case 0:

                TextView note1 = findViewById(R.id.note8_1);
                note1.setText(notestring);

                TextView price1 = findViewById(R.id.price8_1);

                double finalpriceitem1 = pricedouble * count8;
                String stringfinalpriceitem1 = String.valueOf(finalpriceitem1);
                price1.setText(stringfinalpriceitem1);

                TextView qty1 = findViewById(R.id.qty8_1);
                qty1.setText(qtystring);

                check8++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                TextView note2 = findViewById(R.id.note8_2);
                note2.setText(notestring);

                TextView price2 = findViewById(R.id.price8_2);

                double finalpriceitem2 = pricedouble * count8;
                String stringfinalpriceitem2 = String.valueOf(finalpriceitem2);
                price2.setText(stringfinalpriceitem2);

                TextView qty2 = findViewById(R.id.qty8_2);
                qty2.setText(qtystring);

                check8++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;


            case 2:
                TextView note3 = findViewById(R.id.note8_3);
                note3.setText(notestring);

                TextView price3 = findViewById(R.id.price8_3);

                double finalpriceitem3 = pricedouble * count8;
                String stringfinalpriceitem3 = String.valueOf(finalpriceitem3);
                price3.setText(stringfinalpriceitem3);

                TextView qty3 = findViewById(R.id.qty8_3);
                qty3.setText(qtystring);

                check8++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getApplicationContext(), "Carrello pieno (Bevande)", Toast.LENGTH_SHORT).show();
        }
    }

    public void add9(View view) {
        // estrapolazione stringhe
        EditText noteview = findViewById(R.id.note9);
        EditText priceview = findViewById(R.id.price9);

        String notestring = noteview.getText().toString();

        String pricestring = priceview.getText().toString();

        double pricedouble = Double.parseDouble(pricestring);

        BigDecimal bd = new BigDecimal(String.valueOf(pricedouble)); // controllo input
        if (bd.scale()>2) {
            Toast.makeText(getApplicationContext(), "Inserire correttamente il prezzo", Toast.LENGTH_SHORT).show();
            return;
        }

        String qtystring = String.valueOf(count9);

        switch (check9) {

            case 0:

                TextView note1 = findViewById(R.id.note9_1);
                note1.setText(notestring);

                TextView price1 = findViewById(R.id.price9_1);

                double finalpriceitem1 = pricedouble * count9;
                String stringfinalpriceitem1 = String.valueOf(finalpriceitem1);
                price1.setText(stringfinalpriceitem1);

                TextView qty1 = findViewById(R.id.qty9_1);
                qty1.setText(qtystring);

                check9++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                TextView note2 = findViewById(R.id.note9_2);
                note2.setText(notestring);

                TextView price2 = findViewById(R.id.price9_2);

                double finalpriceitem2 = pricedouble * count9;
                String stringfinalpriceitem2 = String.valueOf(finalpriceitem2);
                price2.setText(stringfinalpriceitem2);

                TextView qty2 = findViewById(R.id.qty9_2);
                qty2.setText(qtystring);

                check9++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;


            case 2:
                TextView note3 = findViewById(R.id.note9_3);
                note3.setText(notestring);

                TextView price3 = findViewById(R.id.price9_3);

                double finalpriceitem3 = pricedouble * count9;
                String stringfinalpriceitem3 = String.valueOf(finalpriceitem3);
                price3.setText(stringfinalpriceitem3);

                TextView qty3 = findViewById(R.id.qty9_3);
                qty3.setText(qtystring);

                check9++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getApplicationContext(), "Carrello pieno (Altro)", Toast.LENGTH_SHORT).show();
        }
    }

    public void add10(View view) {
        // estrapolazione stringhe
        EditText noteview = findViewById(R.id.note10);
        EditText priceview = findViewById(R.id.price10);

        String notestring = noteview.getText().toString();

        String pricestring = priceview.getText().toString();

        double pricedouble = Double.parseDouble(pricestring);

        BigDecimal bd = new BigDecimal(String.valueOf(pricedouble)); // controllo input
        if (bd.scale()>2) {
            Toast.makeText(getApplicationContext(), "Inserire correttamente il prezzo", Toast.LENGTH_SHORT).show();
            return;
        }

        String qtystring = String.valueOf(count10);

        switch (check10) {

            case 0:

                TextView note1 = findViewById(R.id.note10_1);
                note1.setText(notestring);

                TextView price1 = findViewById(R.id.price10_1);

                double finalpriceitem1 = pricedouble * count10;
                String stringfinalpriceitem1 = String.valueOf(finalpriceitem1);
                price1.setText(stringfinalpriceitem1);

                TextView qty1 = findViewById(R.id.qty10_1);
                qty1.setText(qtystring);

                check10++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                TextView note2 = findViewById(R.id.note10_2);
                note2.setText(notestring);

                TextView price2 = findViewById(R.id.price10_2);

                double finalpriceitem2 = pricedouble * count10;
                String stringfinalpriceitem2 = String.valueOf(finalpriceitem2);
                price2.setText(stringfinalpriceitem2);

                TextView qty2 = findViewById(R.id.qty10_2);
                qty2.setText(qtystring);

                check10++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;


            case 2:
                TextView note3 = findViewById(R.id.note10_3);
                note3.setText(notestring);

                TextView price3 = findViewById(R.id.price10_3);

                double finalpriceitem3 = pricedouble * count10;
                String stringfinalpriceitem3 = String.valueOf(finalpriceitem3);
                price3.setText(stringfinalpriceitem3);

                TextView qty3 = findViewById(R.id.qty10_3);
                qty3.setText(qtystring);

                check10++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getApplicationContext(), "Carrello pieno (Cucina)", Toast.LENGTH_SHORT).show();
        }
    }

    public void add11(View view) {
        // estrapolazione stringhe
        EditText noteview = findViewById(R.id.note11);
        EditText priceview = findViewById(R.id.price11);

        String notestring = noteview.getText().toString();

        String pricestring = priceview.getText().toString();

        double pricedouble = Double.parseDouble(pricestring);

        BigDecimal bd = new BigDecimal(String.valueOf(pricedouble)); // controllo input
        if (bd.scale()>2) {
            Toast.makeText(getApplicationContext(), "Inserire correttamente il prezzo", Toast.LENGTH_SHORT).show();
            return;
        }

        String qtystring = String.valueOf(count11);

        switch (check11) {

            case 0:

                TextView note1 = findViewById(R.id.note11_1);
                note1.setText(notestring);

                TextView price1 = findViewById(R.id.price11_1);

                double finalpriceitem1 = pricedouble * count11;
                String stringfinalpriceitem1 = String.valueOf(finalpriceitem1);
                price1.setText(stringfinalpriceitem1);

                TextView qty1 = findViewById(R.id.qty11_1);
                qty1.setText(qtystring);

                check11++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                TextView note2 = findViewById(R.id.note11_2);
                note2.setText(notestring);

                TextView price2 = findViewById(R.id.price11_2);

                double finalpriceitem2 = pricedouble * count11;
                String stringfinalpriceitem2 = String.valueOf(finalpriceitem2);
                price2.setText(stringfinalpriceitem2);

                TextView qty2 = findViewById(R.id.qty11_2);
                qty2.setText(qtystring);

                check11++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;


            case 2:
                TextView note3 = findViewById(R.id.note11_3);
                note3.setText(notestring);

                TextView price3 = findViewById(R.id.price11_3);

                double finalpriceitem3 = pricedouble * count11;
                String stringfinalpriceitem3 = String.valueOf(finalpriceitem3);
                price3.setText(stringfinalpriceitem3);

                TextView qty3 = findViewById(R.id.qty11_3);
                qty3.setText(qtystring);

                check11++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getApplicationContext(), "Carrello pieno (Bagno)", Toast.LENGTH_SHORT).show();
        }
    }

    public void add12(View view) {
        // estrapolazione stringhe
        EditText noteview = findViewById(R.id.note12);
        EditText priceview = findViewById(R.id.price12);

        String notestring = noteview.getText().toString();

        String pricestring = priceview.getText().toString();

        double pricedouble = Double.parseDouble(pricestring);

        BigDecimal bd = new BigDecimal(String.valueOf(pricedouble)); // controllo input
        if (bd.scale()>2) {
            Toast.makeText(getApplicationContext(), "Inserire correttamente il prezzo", Toast.LENGTH_SHORT).show();
            return;
        }

        String qtystring = String.valueOf(count12);

        switch (check12) {

            case 0:

                TextView note1 = findViewById(R.id.note12_1);
                note1.setText(notestring);

                TextView price1 = findViewById(R.id.price12_1);

                double finalpriceitem1 = pricedouble * count12;
                String stringfinalpriceitem1 = String.valueOf(finalpriceitem1);
                price1.setText(stringfinalpriceitem1);

                TextView qty1 = findViewById(R.id.qty12_1);
                qty1.setText(qtystring);

                check12++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                TextView note2 = findViewById(R.id.note12_2);
                note2.setText(notestring);

                TextView price2 = findViewById(R.id.price12_2);

                double finalpriceitem2 = pricedouble * count12;
                String stringfinalpriceitem2 = String.valueOf(finalpriceitem2);
                price2.setText(stringfinalpriceitem2);

                TextView qty2 = findViewById(R.id.qty12_2);
                qty2.setText(qtystring);

                check12++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;


            case 2:
                TextView note3 = findViewById(R.id.note12_3);
                note3.setText(notestring);

                TextView price3 = findViewById(R.id.price12_3);

                double finalpriceitem3 = pricedouble * count12;
                String stringfinalpriceitem3 = String.valueOf(finalpriceitem3);
                price3.setText(stringfinalpriceitem3);

                TextView qty3 = findViewById(R.id.qty12_3);
                qty3.setText(qtystring);

                check12++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getApplicationContext(), "Carrello pieno (Igiene)", Toast.LENGTH_SHORT).show();
        }
    }

    public void add13(View view) {
        // estrapolazione stringhe
        EditText noteview = findViewById(R.id.note13);
        EditText priceview = findViewById(R.id.price13);

        String notestring = noteview.getText().toString();

        String pricestring = priceview.getText().toString();

        double pricedouble = Double.parseDouble(pricestring);

        BigDecimal bd = new BigDecimal(String.valueOf(pricedouble)); // controllo input
        if (bd.scale()>2) {
            Toast.makeText(getApplicationContext(), "Inserire correttamente il prezzo", Toast.LENGTH_SHORT).show();
            return;
        }

        String qtystring = String.valueOf(count13);

        switch (check13) {

            case 0:

                TextView note1 = findViewById(R.id.note13_1);
                note1.setText(notestring);

                TextView price1 = findViewById(R.id.price13_1);

                double finalpriceitem1 = pricedouble * count13;
                String stringfinalpriceitem1 = String.valueOf(finalpriceitem1);
                price1.setText(stringfinalpriceitem1);

                TextView qty1 = findViewById(R.id.qty13_1);
                qty1.setText(qtystring);

                check13++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                TextView note2 = findViewById(R.id.note13_2);
                note2.setText(notestring);

                TextView price2 = findViewById(R.id.price13_2);

                double finalpriceitem2 = pricedouble * count13;
                String stringfinalpriceitem2 = String.valueOf(finalpriceitem2);
                price2.setText(stringfinalpriceitem2);

                TextView qty2 = findViewById(R.id.qty13_2);
                qty2.setText(qtystring);

                check13++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;


            case 2:
                TextView note3 = findViewById(R.id.note13_3);
                note3.setText(notestring);

                TextView price3 = findViewById(R.id.price13_3);

                double finalpriceitem3 = pricedouble * count13;
                String stringfinalpriceitem3 = String.valueOf(finalpriceitem3);
                price3.setText(stringfinalpriceitem3);

                TextView qty3 = findViewById(R.id.qty13_3);
                qty3.setText(qtystring);

                check13++;
                Toast.makeText(getApplicationContext(), "Aggiunto al carrello", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getApplicationContext(), "Carrello pieno (Tempo libero)", Toast.LENGTH_SHORT).show();
        }
    }

    public void refreshfp(View view) {

        // associamento view

        TextView price1_1 = findViewById(R.id.price1_1);
        TextView price1_2 = findViewById(R.id.price1_2);
        TextView price1_3 = findViewById(R.id.price1_3);

        TextView price2_1 = findViewById(R.id.price2_1);
        TextView price2_2 = findViewById(R.id.price2_2);
        TextView price2_3 = findViewById(R.id.price2_3);

        TextView price3_1 = findViewById(R.id.price3_1);
        TextView price3_2 = findViewById(R.id.price3_2);
        TextView price3_3 = findViewById(R.id.price3_3);

        TextView price4_1 = findViewById(R.id.price4_1);
        TextView price4_2 = findViewById(R.id.price4_2);
        TextView price4_3 = findViewById(R.id.price4_3);

        TextView price5_1 = findViewById(R.id.price5_1);
        TextView price5_2 = findViewById(R.id.price5_2);
        TextView price5_3 = findViewById(R.id.price5_3);

        TextView price6_1 = findViewById(R.id.price6_1);
        TextView price6_2 = findViewById(R.id.price6_2);
        TextView price6_3 = findViewById(R.id.price6_3);

        TextView price7_1 = findViewById(R.id.price7_1);
        TextView price7_2 = findViewById(R.id.price7_2);
        TextView price7_3 = findViewById(R.id.price7_3);

        TextView price8_1 = findViewById(R.id.price8_1);
        TextView price8_2 = findViewById(R.id.price8_2);
        TextView price8_3 = findViewById(R.id.price8_3);

        TextView price9_1 = findViewById(R.id.price9_1);
        TextView price9_2 = findViewById(R.id.price9_2);
        TextView price9_3 = findViewById(R.id.price9_3);

        TextView price10_1 = findViewById(R.id.price10_1);
        TextView price10_2 = findViewById(R.id.price10_2);
        TextView price10_3 = findViewById(R.id.price10_3);

        TextView price11_1 = findViewById(R.id.price11_1);
        TextView price11_2 = findViewById(R.id.price11_2);
        TextView price11_3 = findViewById(R.id.price11_3);

        TextView price12_1 = findViewById(R.id.price12_1);
        TextView price12_2 = findViewById(R.id.price12_2);
        TextView price12_3 = findViewById(R.id.price12_3);

        TextView price13_1 = findViewById(R.id.price13_1);
        TextView price13_2 = findViewById(R.id.price13_2);
        TextView price13_3 = findViewById(R.id.price13_3);

        // conversione string necessaria per conversione double

        String strprice1_1 = price1_1.getText().toString();
        String strprice1_2 = price1_2.getText().toString();
        String strprice1_3 = price1_3.getText().toString();

        String strprice2_1 = price2_1.getText().toString();
        String strprice2_2 = price2_2.getText().toString();
        String strprice2_3 = price2_3.getText().toString();

        String strprice3_1 = price3_1.getText().toString();
        String strprice3_2 = price3_2.getText().toString();
        String strprice3_3 = price3_3.getText().toString();

        String strprice4_1 = price4_1.getText().toString();
        String strprice4_2 = price4_2.getText().toString();
        String strprice4_3 = price4_3.getText().toString();

        String strprice5_1 = price5_1.getText().toString();
        String strprice5_2 = price5_2.getText().toString();
        String strprice5_3 = price5_3.getText().toString();

        String strprice6_1 = price6_1.getText().toString();
        String strprice6_2 = price6_2.getText().toString();
        String strprice6_3 = price6_3.getText().toString();

        String strprice7_1 = price7_1.getText().toString();
        String strprice7_2 = price7_2.getText().toString();
        String strprice7_3 = price7_3.getText().toString();

        String strprice8_1 = price8_1.getText().toString();
        String strprice8_2 = price8_2.getText().toString();
        String strprice8_3 = price8_3.getText().toString();

        String strprice9_1 = price9_1.getText().toString();
        String strprice9_2 = price9_2.getText().toString();
        String strprice9_3 = price9_3.getText().toString();

        String strprice10_1 = price10_1.getText().toString();
        String strprice10_2 = price10_2.getText().toString();
        String strprice10_3 = price10_3.getText().toString();

        String strprice11_1 = price11_1.getText().toString();
        String strprice11_2 = price11_2.getText().toString();
        String strprice11_3 = price11_3.getText().toString();

        String strprice12_1 = price12_1.getText().toString();
        String strprice12_2 = price12_2.getText().toString();
        String strprice12_3 = price12_3.getText().toString();

        String strprice13_1 = price13_1.getText().toString();
        String strprice13_2 = price13_2.getText().toString();
        String strprice13_3 = price13_3.getText().toString();

        // conversione double
        if (strprice1_1.equals("")) {
            double dbprice1_1 = 0;
            tempfp = dbprice1_1;
        }
        else {
            double dbprice1_1 = Double.parseDouble(strprice1_1);
            tempfp = dbprice1_1;
        }

        if (strprice1_2.equals("")) {
            double dbprice1_2 = 0;
            tempfp = tempfp + dbprice1_2;
        }
        else {
            double dbprice1_2 = Double.parseDouble(strprice1_2);
            tempfp = tempfp + dbprice1_2;
        }

        if (strprice1_3.equals("")) {
            double dbprice1_3 = 0;
            tempfp = tempfp + dbprice1_3;
        }
        else {
            double dbprice1_3 = Double.parseDouble(strprice1_3);
            tempfp = tempfp + dbprice1_3;
        }

        if (strprice2_1.equals("")) {
            double dbprice2_1 = 0;
            tempfp = tempfp + dbprice2_1 ;
        }
        else {
            double dbprice2_1 = Double.parseDouble(strprice2_1);
            tempfp = dbprice2_1 + tempfp;
        }

        if (strprice2_2.equals("")) {
            double dbprice2_2 = 0;
            tempfp = tempfp + dbprice2_2;
        }
        else {
            double dbprice2_2 = Double.parseDouble(strprice2_2);
            tempfp = tempfp + dbprice2_2;
        }

        if (strprice2_3.equals("")) {
            double dbprice2_3 = 0;
            tempfp = tempfp + dbprice2_3;
        }
        else {
            double dbprice2_3 = Double.parseDouble(strprice2_3);
            tempfp = tempfp + dbprice2_3;
        }

        if (strprice3_1.equals("")) {
            double dbprice3_1 = 0;
            tempfp = dbprice3_1 + tempfp;
        }
        else {
            double dbprice3_1 = Double.parseDouble(strprice3_1);
            tempfp = dbprice3_1 + tempfp;
        }

        if (strprice3_2.equals("")) {
            double dbprice3_2 = 0;
            tempfp = tempfp + dbprice3_2;
        }
        else {
            double dbprice3_2 = Double.parseDouble(strprice3_2);
            tempfp = tempfp + dbprice3_2;
        }

        if (strprice3_3.equals("")) {
            double dbprice3_3 = 0;
            tempfp = tempfp + dbprice3_3;
        }
        else {
            double dbprice3_3 = Double.parseDouble(strprice3_3);
            tempfp = tempfp + dbprice3_3;
        }

        if (strprice4_1.equals("")) {
            double dbprice4_1 = 0;
            tempfp = tempfp + dbprice4_1 ;
        }
        else {
            double dbprice4_1 = Double.parseDouble(strprice4_1);
            tempfp = tempfp + dbprice4_1;
        }

        if (strprice4_2.equals("")) {
            double dbprice2_2 = 0;
            tempfp = tempfp + dbprice2_2;
        }
        else {
            double dbprice4_2 = Double.parseDouble(strprice4_2);
            tempfp = tempfp + dbprice4_2;
        }

        if (strprice4_3.equals("")) {
            double dbprice4_3 = 0;
            tempfp = tempfp + dbprice4_3;
        }
        else {
            double dbprice4_3 = Double.parseDouble(strprice4_3);
            tempfp = tempfp + dbprice4_3;
        }

        if (strprice5_1.equals("")) {
            double dbprice5_1 = 0;
            tempfp = dbprice5_1 + tempfp;
        }
        else {
            double dbprice5_1 = Double.parseDouble(strprice5_1);
            tempfp = dbprice5_1 + tempfp;
        }

        if (strprice5_2.equals("")) {
            double dbprice5_2 = 0;
            tempfp = tempfp + dbprice5_2;
        }
        else {
            double dbprice5_2 = Double.parseDouble(strprice5_2);
            tempfp = tempfp + dbprice5_2;
        }

        if (strprice5_3.equals("")) {
            double dbprice5_3 = 0;
            tempfp = tempfp + dbprice5_3;
        }
        else {
            double dbprice5_3 = Double.parseDouble(strprice5_3);
            tempfp = tempfp + dbprice5_3;
        }

        if (strprice6_1.equals("")) {
            double dbprice6_1 = 0;
            tempfp = dbprice6_1 + tempfp;
        }
        else {
            double dbprice6_1 = Double.parseDouble(strprice6_1);
            tempfp = dbprice6_1 + tempfp;
        }

        if (strprice6_2.equals("")) {
            double dbprice6_2 = 0;
            tempfp = tempfp + dbprice6_2;
        }
        else {
            double dbprice6_2 = Double.parseDouble(strprice6_2);
            tempfp = tempfp + dbprice6_2;
        }

        if (strprice6_3.equals("")) {
            double dbprice6_3 = 0;
            tempfp = tempfp + dbprice6_3;
        }
        else {
            double dbprice6_3 = Double.parseDouble(strprice6_3);
            tempfp = tempfp + dbprice6_3;
        }

        if (strprice7_1.equals("")) {
            double dbprice7_1 = 0;
            tempfp = dbprice7_1 + tempfp;
        }
        else {
            double dbprice7_1 = Double.parseDouble(strprice7_1);
            tempfp = dbprice7_1 + tempfp;
        }

        if (strprice7_2.equals("")) {
            double dbprice7_2 = 0;
            tempfp = tempfp + dbprice7_2;
        }
        else {
            double dbprice7_2 = Double.parseDouble(strprice7_2);
            tempfp = tempfp + dbprice7_2;
        }

        if (strprice7_3.equals("")) {
            double dbprice7_3 = 0;
            tempfp = tempfp + dbprice7_3;
        }
        else {
            double dbprice7_3 = Double.parseDouble(strprice7_3);
            tempfp = tempfp + dbprice7_3;
        }

        if (strprice8_1.equals("")) {
            double dbprice8_1 = 0;
            tempfp = dbprice8_1 + tempfp;
        }
        else {
            double dbprice8_1 = Double.parseDouble(strprice8_1);
            tempfp = dbprice8_1 + tempfp;
        }

        if (strprice8_2.equals("")) {
            double dbprice8_2 = 0;
            tempfp = tempfp + dbprice8_2;
        }
        else {
            double dbprice8_2 = Double.parseDouble(strprice8_2);
            tempfp = tempfp + dbprice8_2;
        }

        if (strprice8_3.equals("")) {
            double dbprice8_3 = 0;
            tempfp = tempfp + dbprice8_3;
        }
        else {
            double dbprice8_3 = Double.parseDouble(strprice8_3);
            tempfp = tempfp + dbprice8_3;
        }

        if (strprice9_1.equals("")) {
            double dbprice9_1 = 0;
            tempfp = dbprice9_1 + tempfp;
        }
        else {
            double dbprice9_1 = Double.parseDouble(strprice9_1);
            tempfp = dbprice9_1 + tempfp;
        }

        if (strprice9_2.equals("")) {
            double dbprice9_2 = 0;
            tempfp = tempfp + dbprice9_2;
        }
        else {
            double dbprice9_2 = Double.parseDouble(strprice9_2);
            tempfp = tempfp + dbprice9_2;
        }

        if (strprice9_3.equals("")) {
            double dbprice9_3 = 0;
            tempfp = tempfp + dbprice9_3;
        }
        else {
            double dbprice9_3 = Double.parseDouble(strprice9_3);
            tempfp = tempfp + dbprice9_3;
        }

        if (strprice10_1.equals("")) {
            double dbprice10_1 = 0;
            tempfp = dbprice10_1 + tempfp;
        }
        else {
            double dbprice10_1 = Double.parseDouble(strprice10_1);
            tempfp = dbprice10_1 + tempfp;
        }

        if (strprice10_2.equals("")) {
            double dbprice10_2 = 0;
            tempfp = tempfp + dbprice10_2;
        }
        else {
            double dbprice10_2 = Double.parseDouble(strprice10_2);
            tempfp = tempfp + dbprice10_2;
        }

        if (strprice10_3.equals("")) {
            double dbprice10_3 = 0;
            tempfp = tempfp + dbprice10_3;
        }
        else {
            double dbprice10_3 = Double.parseDouble(strprice10_3);
            tempfp = tempfp + dbprice10_3;
        }

        if (strprice11_1.equals("")) {
            double dbprice11_1 = 0;
            tempfp = dbprice11_1 + tempfp;
        }
        else {
            double dbprice11_1 = Double.parseDouble(strprice11_1);
            tempfp = dbprice11_1 + tempfp;
        }

        if (strprice11_2.equals("")) {
            double dbprice11_2 = 0;
            tempfp = tempfp + dbprice11_2;
        }
        else {
            double dbprice11_2 = Double.parseDouble(strprice11_2);
            tempfp = tempfp + dbprice11_2;
        }

        if (strprice11_3.equals("")) {
            double dbprice11_3 = 0;
            tempfp = tempfp + dbprice11_3;
        }
        else {
            double dbprice11_3 = Double.parseDouble(strprice11_3);
            tempfp = tempfp + dbprice11_3;
        }

        if (strprice12_1.equals("")) {
            double dbprice12_1 = 0;
            tempfp = dbprice12_1 + tempfp;
        }
        else {
            double dbprice12_1 = Double.parseDouble(strprice12_1);
            tempfp = dbprice12_1 + tempfp;
        }

        if (strprice12_2.equals("")) {
            double dbprice12_2 = 0;
            tempfp = tempfp + dbprice12_2;
        }
        else {
            double dbprice12_2 = Double.parseDouble(strprice12_2);
            tempfp = tempfp + dbprice12_2;
        }

        if (strprice12_3.equals("")) {
            double dbprice12_3 = 0;
            tempfp = tempfp + dbprice12_3;
        }
        else {
            double dbprice12_3 = Double.parseDouble(strprice12_3);
            tempfp = tempfp + dbprice12_3;
        }

        if (strprice13_1.equals("")) {
            double dbprice13_1 = 0;
            tempfp = dbprice13_1 + tempfp;
        }
        else {
            double dbprice13_1 = Double.parseDouble(strprice13_1);
            tempfp = dbprice13_1 + tempfp;
        }

        if (strprice13_2.equals("")) {
            double dbprice13_2 = 0;
            tempfp = tempfp + dbprice13_2;
        }
        else {
            double dbprice13_2 = Double.parseDouble(strprice13_2);
            tempfp = tempfp + dbprice13_2;
        }

        if (strprice13_3.equals("")) {
            double dbprice13_3 = 0;
            tempfp = tempfp + dbprice13_3;
        }
        else {
            double dbprice13_3 = Double.parseDouble(strprice13_3);
            tempfp = tempfp + dbprice13_3;
        }

        // operazioni somma / visualizzazione prezzo

        double fp = tempfp;
        String stringfp = String.valueOf(fp);
        TextView finalprice = findViewById(R.id.finalprice);
        finalprice.setText(stringfp);

    }


    public void cleancart (View view) {

        TextView price1_1 = findViewById(R.id.price1_1);
        TextView price1_2 = findViewById(R.id.price1_2);
        TextView price1_3 = findViewById(R.id.price1_3);

        TextView note1_1 = findViewById(R.id.note1_1);
        TextView note1_2 = findViewById(R.id.note1_2);
        TextView note1_3 = findViewById(R.id.note1_3);

        TextView qty1_1 = findViewById(R.id.qty1_1);
        TextView qty1_2 = findViewById(R.id.qty1_2);
        TextView qty1_3 = findViewById(R.id.qty1_3);

        TextView price2_1 = findViewById(R.id.price2_1);
        TextView price2_2 = findViewById(R.id.price2_2);
        TextView price2_3 = findViewById(R.id.price2_3);

        TextView note2_1 = findViewById(R.id.note2_1);
        TextView note2_2 = findViewById(R.id.note2_2);
        TextView note2_3 = findViewById(R.id.note2_3);

        TextView qty2_1 = findViewById(R.id.qty2_1);
        TextView qty2_2 = findViewById(R.id.qty2_2);
        TextView qty2_3 = findViewById(R.id.qty2_3);

        TextView price3_1 = findViewById(R.id.price3_1);
        TextView price3_2 = findViewById(R.id.price3_2);
        TextView price3_3 = findViewById(R.id.price3_3);

        TextView note3_1 = findViewById(R.id.note3_1);
        TextView note3_2 = findViewById(R.id.note3_2);
        TextView note3_3 = findViewById(R.id.note3_3);

        TextView qty3_1 = findViewById(R.id.qty3_1);
        TextView qty3_2 = findViewById(R.id.qty3_2);
        TextView qty3_3 = findViewById(R.id.qty3_3);

        TextView price4_1 = findViewById(R.id.price4_1);
        TextView price4_2 = findViewById(R.id.price4_2);
        TextView price4_3 = findViewById(R.id.price4_3);

        TextView note4_1 = findViewById(R.id.note4_1);
        TextView note4_2 = findViewById(R.id.note4_2);
        TextView note4_3 = findViewById(R.id.note4_3);

        TextView qty4_1 = findViewById(R.id.qty4_1);
        TextView qty4_2 = findViewById(R.id.qty4_2);
        TextView qty4_3 = findViewById(R.id.qty4_3);

        TextView price5_1 = findViewById(R.id.price5_1);
        TextView price5_2 = findViewById(R.id.price5_2);
        TextView price5_3 = findViewById(R.id.price5_3);

        TextView note5_1 = findViewById(R.id.note5_1);
        TextView note5_2 = findViewById(R.id.note5_2);
        TextView note5_3 = findViewById(R.id.note5_3);

        TextView qty5_1 = findViewById(R.id.qty5_1);
        TextView qty5_2 = findViewById(R.id.qty5_2);
        TextView qty5_3 = findViewById(R.id.qty5_3);

        TextView price6_1 = findViewById(R.id.price6_1);
        TextView price6_2 = findViewById(R.id.price6_2);
        TextView price6_3 = findViewById(R.id.price6_3);

        TextView note6_1 = findViewById(R.id.note6_1);
        TextView note6_2 = findViewById(R.id.note6_2);
        TextView note6_3 = findViewById(R.id.note6_3);

        TextView qty6_1 = findViewById(R.id.qty6_1);
        TextView qty6_2 = findViewById(R.id.qty6_2);
        TextView qty6_3 = findViewById(R.id.qty6_3);

        TextView price7_1 = findViewById(R.id.price7_1);
        TextView price7_2 = findViewById(R.id.price7_2);
        TextView price7_3 = findViewById(R.id.price7_3);

        TextView note7_1 = findViewById(R.id.note7_1);
        TextView note7_2 = findViewById(R.id.note7_2);
        TextView note7_3 = findViewById(R.id.note7_3);

        TextView qty7_1 = findViewById(R.id.qty7_1);
        TextView qty7_2 = findViewById(R.id.qty7_2);
        TextView qty7_3 = findViewById(R.id.qty7_3);

        TextView price8_1 = findViewById(R.id.price8_1);
        TextView price8_2 = findViewById(R.id.price8_2);
        TextView price8_3 = findViewById(R.id.price8_3);

        TextView note8_1 = findViewById(R.id.note8_1);
        TextView note8_2 = findViewById(R.id.note8_2);
        TextView note8_3 = findViewById(R.id.note8_3);

        TextView qty8_1 = findViewById(R.id.qty8_1);
        TextView qty8_2 = findViewById(R.id.qty8_2);
        TextView qty8_3 = findViewById(R.id.qty8_3);

        TextView price9_1 = findViewById(R.id.price9_1);
        TextView price9_2 = findViewById(R.id.price9_2);
        TextView price9_3 = findViewById(R.id.price9_3);

        TextView note9_1 = findViewById(R.id.note9_1);
        TextView note9_2 = findViewById(R.id.note9_2);
        TextView note9_3 = findViewById(R.id.note9_3);

        TextView qty9_1 = findViewById(R.id.qty9_1);
        TextView qty9_2 = findViewById(R.id.qty9_2);
        TextView qty9_3 = findViewById(R.id.qty9_3);

        TextView price10_1 = findViewById(R.id.price10_1);
        TextView price10_2 = findViewById(R.id.price10_2);
        TextView price10_3 = findViewById(R.id.price10_3);

        TextView note10_1 = findViewById(R.id.note10_1);
        TextView note10_2 = findViewById(R.id.note10_2);
        TextView note10_3 = findViewById(R.id.note10_3);

        TextView qty10_1 = findViewById(R.id.qty10_1);
        TextView qty10_2 = findViewById(R.id.qty10_2);
        TextView qty10_3 = findViewById(R.id.qty10_3);

        TextView price11_1 = findViewById(R.id.price11_1);
        TextView price11_2 = findViewById(R.id.price11_2);
        TextView price11_3 = findViewById(R.id.price11_3);

        TextView note11_1 = findViewById(R.id.note11_1);
        TextView note11_2 = findViewById(R.id.note11_2);
        TextView note11_3 = findViewById(R.id.note11_3);

        TextView qty11_1 = findViewById(R.id.qty11_1);
        TextView qty11_2 = findViewById(R.id.qty11_2);
        TextView qty11_3 = findViewById(R.id.qty11_3);

        TextView price12_1 = findViewById(R.id.price12_1);
        TextView price12_2 = findViewById(R.id.price12_2);
        TextView price12_3 = findViewById(R.id.price12_3);

        TextView note12_1 = findViewById(R.id.note12_1);
        TextView note12_2 = findViewById(R.id.note12_2);
        TextView note12_3 = findViewById(R.id.note12_3);

        TextView qty12_1 = findViewById(R.id.qty12_1);
        TextView qty12_2 = findViewById(R.id.qty12_2);
        TextView qty12_3 = findViewById(R.id.qty12_3);

        TextView price13_1 = findViewById(R.id.price13_1);
        TextView price13_2 = findViewById(R.id.price13_2);
        TextView price13_3 = findViewById(R.id.price13_3);

        TextView note13_1 = findViewById(R.id.note13_1);
        TextView note13_2 = findViewById(R.id.note13_2);
        TextView note13_3 = findViewById(R.id.note13_3);

        TextView qty13_1 = findViewById(R.id.qty13_1);
        TextView qty13_2 = findViewById(R.id.qty13_2);
        TextView qty13_3 = findViewById(R.id.qty13_3);

        note1_1.setText("");
        qty1_1.setText("");
        price1_1.setText("");

        note1_2.setText("");
        qty1_2.setText("");
        price1_2.setText("");

        note1_3.setText("");
        qty1_3.setText("");
        price1_3.setText("");

        //

        note2_1.setText("");
        qty2_1.setText("");
        price2_1.setText("");

        note2_2.setText("");
        qty2_2.setText("");
        price2_2.setText("");

        note2_3.setText("");
        qty2_3.setText("");
        price2_3.setText("");

        //

        note3_1.setText("");
        qty3_1.setText("");
        price3_1.setText("");

        note3_2.setText("");
        qty3_2.setText("");
        price3_2.setText("");

        note3_3.setText("");
        qty3_3.setText("");
        price3_3.setText("");

        //

        note4_1.setText("");
        qty4_1.setText("");
        price4_1.setText("");

        note4_2.setText("");
        qty4_2.setText("");
        price4_2.setText("");

        note4_3.setText("");
        qty4_3.setText("");
        price4_3.setText("");

        //

        note5_1.setText("");
        qty5_1.setText("");
        price5_1.setText("");

        note5_2.setText("");
        qty5_2.setText("");
        price5_2.setText("");

        note5_3.setText("");
        qty5_3.setText("");
        price5_3.setText("");

        //

        note6_1.setText("");
        qty6_1.setText("");
        price6_1.setText("");

        note6_2.setText("");
        qty6_2.setText("");
        price6_2.setText("");

        note6_3.setText("");
        qty6_3.setText("");
        price6_3.setText("");

        //

        note7_1.setText("");
        qty7_1.setText("");
        price7_1.setText("");

        note7_2.setText("");
        qty7_2.setText("");
        price7_2.setText("");

        note7_3.setText("");
        qty7_3.setText("");
        price7_3.setText("");

        //

        note8_1.setText("");
        qty8_1.setText("");
        price8_1.setText("");

        note8_2.setText("");
        qty8_2.setText("");
        price8_2.setText("");

        note8_3.setText("");
        qty8_3.setText("");
        price8_3.setText("");

        //

        note9_1.setText("");
        qty9_1.setText("");
        price9_1.setText("");

        note9_2.setText("");
        qty9_2.setText("");
        price9_2.setText("");

        note9_3.setText("");
        qty9_3.setText("");
        price9_3.setText("");

        //

        note10_1.setText("");
        qty10_1.setText("");
        price10_1.setText("");

        note10_2.setText("");
        qty10_2.setText("");
        price10_2.setText("");

        note10_3.setText("");
        qty10_3.setText("");
        price10_3.setText("");

        //

        note11_1.setText("");
        qty11_1.setText("");
        price11_1.setText("");

        note11_2.setText("");
        qty11_2.setText("");
        price11_2.setText("");

        note11_3.setText("");
        qty11_3.setText("");
        price11_3.setText("");

        //

        note12_1.setText("");
        qty12_1.setText("");
        price12_1.setText("");

        note12_2.setText("");
        qty12_2.setText("");
        price12_2.setText("");

        note12_3.setText("");
        qty12_3.setText("");
        price12_3.setText("");

        //

        note13_1.setText("");
        qty13_1.setText("");
        price13_1.setText("");

        note13_2.setText("");
        qty13_2.setText("");
        price13_2.setText("");

        note13_3.setText("");
        qty13_3.setText("");
        price13_3.setText("");

        TextView finalprice = findViewById(R.id.finalprice);
        finalprice.setText("0");

        check1=0;
        check2=0;
        check3=0;
        check4=0;
        check5=0;
        check6=0;
        check7=0;
        check8=0;
        check9=0;
        check10=0;
        check11=0;
        check12=0;
        check13=0;

    }

















































    }































































       /* public void gotocart (View view){ // TEST TELEPORT VIEW
            View targetview = findViewById(R.id.count13);
            targetview.getParent().requestChildFocus(targetview, targetview);
        }

        */










