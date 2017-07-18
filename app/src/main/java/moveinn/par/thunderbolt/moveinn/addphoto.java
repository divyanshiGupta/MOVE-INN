package moveinn.par.thunderbolt.moveinn;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class addphoto extends Fragment {
    View rootview;
    CardView camera, gallery;
    ImageView upload;
    private static int RESULT_LOAD_IMAGE = 1;

    Uri selectedImageUri;
    String  selectedPath;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.addphoto, container, false);

        camera = (CardView) rootview.findViewById(R.id.camera);
        gallery = (CardView) rootview.findViewById(R.id.gallery);
        upload = (ImageView)rootview.findViewById(R.id.uploadgallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);*/
                openGallery(10);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 100);
            }
        });


        return rootview;
    }

    public void openGallery(int req_code){

        Intent intent = new Intent();

        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);

            startActivityForResult(Intent.createChooser(intent,"Select file to upload "), req_code);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);

        /*if (requestCode == RESULT_LOAD_IMAGE && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            CardView upload=(CardView) rootview.findViewById(R.id.uploadgallery);

            ImageView imageView = (ImageView) rootview.findViewById(R.id.gimgView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }*/

        if (resultCode == RESULT_LOAD_IMAGE) {
            if(data.getData() != null){
                selectedImageUri = data.getData();
                Toast.makeText(getActivity(), "Captured", Toast.LENGTH_SHORT);
            }else{
                Log.d("selectedPath1 : ","Came here its null !");
                Toast.makeText(getActivity(), "failed to get Image!", Toast.LENGTH_SHORT).show();
            }

            if (requestCode == 100 && resultCode == RESULT_OK) {
                //Bitmap photo = (Bitmap) data.getExtras().get("data");
                /*selectedPath = getPath(selectedImageUri);
                upload.setImageURI(selectedImageUri);
                Log.d("selectedPath1 : " ,selectedPath);*/

                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                try {
                    thumbnail = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                upload.setImageBitmap(thumbnail);


            }

            if (requestCode == 10)

            {

               /*selectedPath = getPath(selectedImageUri);
                upload.setImageURI(selectedImageUri);
                Log.d("selectedPath1 : " ,selectedPath);*/


                 /*   Uri selectedImage = data.getData();
                    upload.setImageURI(selectedImage);*/

                //Uri selectedImage = data.getData();
                try {
                    upload.setImageBitmap(MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }



    }

    public String getPath(Uri uri) {

        String[] projection = { MediaStore.Images.Media.DATA };

       Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
      //  Cursor cursor = getActivity().getContentResolver().query(selectedImage,filePathColumn, null, null, null);

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(column_index);


    }
}
