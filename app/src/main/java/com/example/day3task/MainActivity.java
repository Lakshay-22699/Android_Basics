package com.example.day3task;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.media.MediaPlayer;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7;
    String[] s=new String[7];
    RadioButton r1;
    RadioGroup rg1;
    Button b1;
    CheckBox c1;
    ImageView i1;
    final Calendar myCalendar = Calendar.getInstance();
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    MediaPlayer mediaPlayer;
    Bitmap cropbitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.editTextTextPersonName2);
        e2=findViewById(R.id.editTextPhone);
        e3=findViewById(R.id.editTextTextEmailAddress);
        e4=findViewById(R.id.location);
        e5=findViewById(R.id.editTextTextPersonName4);
        e6=findViewById(R.id.editTextTextPersonName6);
        e7=findViewById(R.id.dob);
        b1=findViewById(R.id.button);
        rg1=findViewById(R.id.rg);
        c1=findViewById(R.id.checkBox);
        i1=findViewById(R.id.imageView2);
        sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        mediaPlayer=MediaPlayer.create(this,R.raw.popalert);

        //Date Picker Dialog start


        //EditText edittext= (EditText) findViewById(R.id.dob);
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
                new DatePickerDialog(MainActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        // Date Picker Dialog End

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(c1.isChecked()) {

                    String fin= stcon();

                    editor.putString("alldata",fin);
                    editor.commit();

                    if(s[0].equals("")||s[1].length()<10||!(s[2].matches(emailPattern))||s[3].equals("")||s[4].equals("")||s[5].equals("")||s[5].equals(""))
                    {
                        Toast.makeText(MainActivity.this, "Enter the Credentials Right", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent obj = new Intent(MainActivity.this, MainActivity2.class);
                        //Toast.makeText(MainActivity.this,fin, Toast.LENGTH_SHORT).show();
                        obj.putExtra("send1", fin);
                        obj.putExtra("number", e2.getText().toString());
                        obj.putExtra("location", e4.getText().toString());
//                        obj.putExtra("image",cropbitmap);
                        startActivity(obj);
                    }
                }
                else
                {
                    mediaPlayer.start();
                    AlertDialog.Builder n=new AlertDialog.Builder(MainActivity.this);
                    n.setTitle("Alert!!!");
                    n.setMessage("Check the Checkbox");
                    n.setPositiveButton("Yes",null);
                    n.setNegativeButton("No",null);
                    n.setNeutralButton("Cancel",null);
                    n.show();
                }
            }
        });


    }
    public String stcon()
    {
        String fin;
        s[0]=e1.getText().toString();
        s[1]=e2.getText().toString();
        s[2]=e3.getText().toString().trim();
        s[3]=e4.getText().toString();
        s[4]=e5.getText().toString();
        s[5]=e6.getText().toString();
        s[6]=e7.getText().toString();
        fin="Name : "+s[0]+"\n"+"Phone No. : "+s[1]+"\n"+"Email : "+s[2]+"\n"+"Location : "+s[3]+"\n"+"Blood Group : "+s[4]+"\n"+"Qualifications : "+s[5]+"\n"+"DOB : "+s[6]+"\n";
        int selectedId = rg1.getCheckedRadioButtonId();

        // find the radiobutton by returned id

        if (selectedId <= 0) {
            Toast.makeText(MainActivity.this, "Select Gender", Toast.LENGTH_LONG).show();
        }
        else {
            r1 = (RadioButton)findViewById(selectedId);
            String radiovalue = r1.getText().toString();
            fin=fin+"Gender : "+radiovalue;
        }

        return  fin;
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        e7.setText(sdf.format(myCalendar.getTime()));
    }

    public void opncam(View view) {

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


}
