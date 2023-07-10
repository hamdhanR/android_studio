package com.example.startdb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText first,last;
Button insert,update,delete,view;
DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first=findViewById(R.id.first);
        last=findViewById(R.id.last);

        insert=findViewById(R.id.insert);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);
        view=findViewById(R.id.view);

        db=new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname=first.getText().toString();
                String lname=last.getText().toString();

                Boolean checkuserdata=db.insertuserdata(fname,lname);
                if(checkuserdata==true){
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    first.setText("");
                    last.setText("");
                    first.requestFocus();
                }
                else{
                    Toast.makeText(MainActivity.this, "Not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname=first.getText().toString();
                String lname=last.getText().toString();

                Boolean checkupdatedata=db.updateuserdata(fname,lname);
                if(checkupdatedata==true){
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    first.setText("");
                    last.setText("");
                    first.requestFocus();
                }
                else{
                    Toast.makeText(MainActivity.this, "Not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fxname=first.getText().toString();
                Boolean checkdeletedata=db.deletedata(fxname);
                if(checkdeletedata==true){
                    Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();
                    first.setText("");
                    last.setText("");
                    first.requestFocus();
                }
                else{
                    Toast.makeText(MainActivity.this, "Not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=db.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No data exists", Toast.LENGTH_SHORT).show();
                    first.setText("");
                    last.setText("");
                    first.requestFocus();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("First name:"+res.getString(0)+"\n");
                    buffer.append("Last name:"+res.getString(1)+"\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("user entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });



    }
}