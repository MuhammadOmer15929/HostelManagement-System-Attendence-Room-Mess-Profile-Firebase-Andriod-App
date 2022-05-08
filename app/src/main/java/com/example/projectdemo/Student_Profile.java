package com.example.projectdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.Objects;

public class Student_Profile extends AppCompatActivity {


    TextView Name;
    TextView Email;
    TextView Mobile;
    TextView BloodGroup;
    TextView Category;
    TextView Gender;
    TextView RollNo;
    TextView CNIC;
    TextView PermAdd;
    TextView LocalGuardian;
    TextView Class1;
    TextView Year;
    TextView Branch;
    String userID;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Name=findViewById(R.id.tv_username);
        Email=findViewById(R.id.tv_emailaddress);
        Mobile=findViewById(R.id.tv_mobile_umber);
        BloodGroup=findViewById(R.id.tv_blood_group);
        Category=findViewById(R.id.tv_cast);
        Gender=findViewById(R.id.tv_gender);
        RollNo=findViewById(R.id.tv_enrollment_no);
        CNIC=findViewById(R.id.tv_aadhar_no);
        PermAdd=findViewById(R.id.tv_perma_address);
        LocalGuardian=findViewById(R.id.tv_guardian_name);
        Class1=findViewById(R.id.sp_class);
        Year=findViewById(R.id.sp_year);
        Branch=findViewById(R.id.sp_branch);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        userID=firebaseAuth.getCurrentUser().getUid();

        DocumentReference documentReference=firebaseFirestore.collection("Users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Name.setText(value.getString("FullName"));
                Email.setText(value.getString("Email"));
                Mobile.setText(value.getString("Phone"));
                BloodGroup.setText(value.getString("BloodGroup"));
                Category.setText(value.getString("Category"));
                Gender.setText(value.getString("Gender"));
                RollNo.setText(value.getString("RollNo"));
                CNIC.setText(value.getString("CNIC"));
                PermAdd.setText(value.getString("PermeAdd"));
                LocalGuardian.setText(value.getString("localGuardian"));
                Class1.setText(value.getString("Class"));
                Year.setText(value.getString("Year"));
                Branch.setText(value.getString("Branch"));



                CNIC.setText(value.getString("CNIC"));
            }

        });

    }
}