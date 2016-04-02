

package com.example.java.generatepdf;

/*
*   This is an app android, created with android studio.
*   App's name is GeneratePDF.
*   This app create the folder that contain a pdf and the pdf. In the end open this pdf.
*   @author Daniela Fiocca
*   @version 1.0
*   @since 02/04/2016
*   */

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.lang.String;



public class MainActivity extends AppCompatActivity {

    public Button button1;
    public Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1= (Button)findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                createPDF();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                openPDF();

            }
        });
    }

    //this function open the PDF when click on the button2
    //@return nothing
    public void openPDF(){

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/generatePDF"+"/demo.pdf");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent); //open application with pdf file

        Context context1 = getApplicationContext();
        CharSequence text1 = "Open the file";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context1, text1, duration);
        toast.show(); //show a message when the file open

    }


    /*this function create a pdf when click button1
    * @return nothing
    * @exception IOException
    * @exception DocumentException
    */
    public void createPDF(){

        com.lowagie.text.Document doc = new com.lowagie.text.Document(); //create a object doc

        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/generatePDF"; //create the path

            File dir = new File(path); //Constructs a new file using the specified directory and name.

            //@return boolean;
            if(dir.mkdir()) { //return true or false, if the directory is created or not.

                //show a message
                Context context3 = getApplicationContext();
                CharSequence text3 = "Create pdf file";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context3, text3, duration);
                toast.show();

            }
            else{
                Context context2 = getApplicationContext();
                CharSequence text2 = "directory not create";
                int duration = Toast.LENGTH_SHORT;
                Toast toast2 = Toast.makeText(context2, text2, duration);
                toast2.show();
            }

            File file = new File(dir, "demo.pdf"); //create a object of file type
            FileOutputStream fOut = new FileOutputStream(file); //Constructs a new FileOutputStream that writes to file.

            /*
            * Method getInstance() is called factory method. It is used for singleton class creation.
            * That means only one instance of that class will be created and others will get reference of that class.*/
            PdfWriter.getInstance(doc, fOut);

            //open the document
            doc.open();

       // Create Paragraph and Set Font
            Paragraph p1 = new Paragraph("Hi! I am Generating my first PDF");

       // Create Set Font and its Size
            Font paraFont= new Font(Font.HELVETICA);
            paraFont.setSize(16);
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            p1.setFont(paraFont);

            //add paragraph to document
            doc.add(p1);


            Paragraph p2 = new Paragraph("This is an example of a simple paragraph");

            p2.setAlignment(Paragraph.ALIGN_CENTER);
            p2.setFont(paraFont);

            doc.add(p2);

       /* Inserting Image in PDF */
            ByteArrayOutputStream stream = new ByteArrayOutputStream(); //Constructs a new ByteArrayOutputStream with a default size of 32 bytes.

            //Creates Bitmap objects from various sources, including files, streams, and byte-arrays
            //decodeResource: The resources object containing the image data, The resource id of the image data
            Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.android);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream); //Write a compressed version of the bitmap to the specified outputstream.
            Image myImg = Image.getInstance(stream.toByteArray());
            myImg.setAlignment(Image.MIDDLE);

            //add image to document
            doc.add(myImg);

            Toast.makeText(getApplicationContext(), "Created...", Toast.LENGTH_LONG).show();

        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        }
        finally
        {
            doc.close();
        }

        openPDF();
    }
}
