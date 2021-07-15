package com.example.day3task;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import www.sanju.motiontoast.MotionToast;

public class Blood_Bank_Admin_AddProfiles extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7;
    String[] s=new String[8];
    RadioButton r1;
    RadioGroup rg1;
    Button b1;
    CheckBox c1;
    ImageView i1;
    final Calendar myCalendar = Calendar.getInstance();
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    Bitmap cropbitmap;
    Blood_Bank_Database blood_bank_database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_admin_add_profiles);

        e1=findViewById(R.id.bbgnupname2);
        e2=findViewById(R.id.bbsgnupno2);
        e3=findViewById(R.id.bbsgnupmail2);
        e4=findViewById(R.id.bbsgnupadd2);
        e5=findViewById(R.id.bbsgnupbgrp2);
        e6=findViewById(R.id.bbsgnuplastblooddate2);
        e7=findViewById(R.id.bbsgnupdob2);
        b1=findViewById(R.id.bbsgnsubmit2);
        rg1=findViewById(R.id.bbrg2);
        c1=findViewById(R.id.bbcheckBox2);
        i1=findViewById(R.id.bbsgnimgvw2);
        blood_bank_database=new Blood_Bank_Database(this);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        e7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Blood_Bank_Admin_AddProfiles.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    public void bbsgnup(View view) {
        if(c1.isChecked()) {

            stcon();

//            if(s[0].equals("")||s[1].length()<10||!(s[2].matches(emailPattern))||s[3].equals("")||s[4].equals("")||s[5].equals("")||s[6].equals(""))
//            {
//                MotionToast.Companion.darkToast(Blood_Bank_Sign_up.this,
//                        "Error!!!!!!","Please fill all the details Right!",
//                        MotionToast.TOAST_WARNING,
//                        MotionToast.GRAVITY_BOTTOM,
//                        MotionToast.LONG_DURATION,
//                        ResourcesCompat.getFont(this, R.font.helvetica_regular));
//            }
//            else {

            blood_bank_database.svedata(s[0],s[1],s[2],s[3],s[4],s[5],s[6],s[7]);
            MotionToast.Companion.darkToast(Blood_Bank_Admin_AddProfiles.this,
                    "Success!!!!!!","Save Successfull",
                    MotionToast.TOAST_SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    ResourcesCompat.getFont(this, R.font.helvetica_regular));
//            }
        }
        else
        {
            new AestheticDialog.Builder(this, DialogStyle.EMOTION, DialogType.WARNING).setTitle("Alert!!!!").setMessage("Check the Checkbox").setAnimation(DialogAnimation.SPLIT).setGravity(5).setCancelable(true).show();
        }
    }
    public void stcon()
    {

        s[0]=e1.getText().toString();
        s[1]=e2.getText().toString();
        s[2]=e3.getText().toString().trim();
        s[3]=e4.getText().toString();
        s[4]=e5.getText().toString();
        s[5]=e6.getText().toString();
        s[6]=e7.getText().toString();
        int selectedId = rg1.getCheckedRadioButtonId();

        if (selectedId <= 0) {
            Toast.makeText(Blood_Bank_Admin_AddProfiles.this, "Select Gender", Toast.LENGTH_LONG).show();
        }
        else {
            r1 = (RadioButton)findViewById(selectedId);
            s[7] = r1.getText().toString();
        }

    }

    public void bbsgnupimg(View view) {
        selectImage(this);
    }
    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        cropbitmap=getCroppedBitmap(selectedImage);
                        i1.setImageBitmap(cropbitmap);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage =  data.getData();

                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Bitmap resized = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
                        cropbitmap=getCroppedBitmap(resized);
                        i1.setImageBitmap(cropbitmap);


                    }
                    break;
            }
        }
    }
    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return output;
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        e7.setText(sdf.format(myCalendar.getTime()));
    }
    public void bbsgnupanother(View view) {
        MotionToast.Companion.darkToast(Blood_Bank_Admin_AddProfiles.this,
                "Success!!!!!!","Register Another Candidate",
                MotionToast.TOAST_INFO,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.SHORT_DURATION,
                ResourcesCompat.getFont(this, R.font.helvetica_regular));
        startActivity(new Intent(this,Blood_Bank_Admin_AddProfiles.class));
    }

}