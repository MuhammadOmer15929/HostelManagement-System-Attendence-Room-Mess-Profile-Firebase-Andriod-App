package com.example.projectdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class StudentSignIn extends AppCompatActivity {

    Button signin;

    EditText EmailL;
    EditText PasswordL;
    Button registration;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    boolean validL = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_in);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Objects.requireNonNull(getSupportActionBar()).hide();
        EmailL = findViewById(R.id.editText);
        PasswordL = findViewById(R.id.editText2);
        signin = findViewById(R.id.Sign_in);
        registration = findViewById(R.id.create_account);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkFieldL(EmailL);
                checkFieldL(PasswordL);

                if (validL) {
                    firebaseAuth.signInWithEmailAndPassword(EmailL.getText().toString(), PasswordL.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            Toast.makeText(StudentSignIn.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Student_Profile.class));


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(StudentSignIn.this, "Incorrect Info Try Again", Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }


        });
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Registration.class));

            }
        });

    }
    public boolean checkFieldL(EditText text)
    {
        if(text.getText().toString().isEmpty())
        {
            text.setError("Fill all Fields");
            validL=false;
        }
        else
        {
            validL=true;
        }
        return  validL;

    }
}