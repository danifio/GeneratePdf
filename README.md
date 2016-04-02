# GeneratePdf
This is an app that generate a pdf and open it

This is an app android, created with android studio.
App's name is GeneratePDF.
This app create the folder that contain a pdf and the pdf. In the end open this pdf.

1.

I used droidText. I add droidText in a folder, called libs, in the principal directory of the app.

Then i added in the file build.gradle(module:app) inside the dependencies this line of code: compile files ('libs/droidTest.0.2.jar')

In this step we added finally the droidText in the project.

2.
In the second step, i created the activity_main.xml and the MainActivity.java files.

Because we want read and write in the external storage of the android smartphone, add this line of code in the AndroidManifest.xml :

<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

3.
in the third step create the activity_main.xml, inserting the TextView, the Button, the Image, in an LinearLayout.

4.
Then i created the MainActivity.java.
In this file there are two important function: createPDF and openPDF.

In the function openPDF, i created a directory, where will be the new file, called "demo.pdf", in the folder called "generatePDF".
Then with an intent, i defined the operation for the application like a pdf.
At the end start the activity.

The function createPDF is more difficult.
	1) create the path : Environment.getExternalStorageDirectory().getAbsolutePath()
	2) File dir : Constructs a new file using the specified directory and name.
	3) dir.mkdir() : return true or false, if the directory is created or not.
	4) File file = new File(dir, "demo.pdf") : create a object of file type
	5) FileOutputStream : Constructs a new FileOutputStream that writes to file.
	6) PdfWriter.getInstance : Method getInstance() is called factory method. It is used for singleton class creation.
            That means only one instance of that class will be created and others will get reference of that class.
    7)  doc.open() : open the document
    8) Add paragraph, choose the font, the style and the size.
    9) Add image.
    9.1) ByteArrayOutputStream stream = new ByteArrayOutputStream() : Constructs a new ByteArrayOutputStream with a default size of 32 bytes.
    9.2) BitmapFactory.decodeResource : The resources object containing the image data and The resource id of the image data
    9.3) bitmap.compress : Write a compressed version of the bitmap to the specified outputstream.
    10) doc close.
    11) open pdf.
    
	
