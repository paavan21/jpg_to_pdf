package com.example.jpgtopdf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/mycamaras";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
    }
    public void convertButton(View view){

        String file = directory + "2.jpg";

        Bitmap bitmap = BitmapFactory.decodeFile(file);

        PdfDocument pdfDocument = new PdfDocument();
        pdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(960,1280,1).create();
        PdfDocument.Page page = pdfDocument.startPage(myPageInfo);


        page.getCanvas().drawBitmap(bitmap, 0, 0,null);
        pdfDocument.finishPage(page);

        String pdffile = directory + "/myPDFFile.pdf";
        File myPDFFile = new File(pdffile);

        pdfDocument.writeTo(new FileOutputStream(myPDFFile));

        try {
            {
                pdfDocument.writeTo(new FileOutputStream(myPDFFile));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        pdfDocument.close();

    }
}