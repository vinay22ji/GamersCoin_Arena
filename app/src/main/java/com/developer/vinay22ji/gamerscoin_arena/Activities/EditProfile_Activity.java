package com.developer.vinay22ji.gamerscoin_arena.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.vinay22ji.gamerscoin_arena.DarkModePrefManager;
import com.developer.vinay22ji.gamerscoin_arena.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditProfile_Activity extends AppCompatActivity {

    ImageView editprofileback_btn;
    TextInputEditText firstname,email,mobileNumber;
    Button saveBtn;
    TextView messageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_);

        if(new DarkModePrefManager(EditProfile_Activity.this).isNightMode()){
            MainActivity.darkmode(EditProfile_Activity.this);
        }
        MainActivity.setupMode(this.getWindow(), EditProfile_Activity.this);

        init();
        getuserData();
    }

    private void getuserData()
    {
        FirebaseDatabase.getInstance().getReference("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        firstname.setText(snapshot.child("name").getValue().toString());
                        email.setText(snapshot.child("email").getValue().toString());
                        mobileNumber.setText(snapshot.child("number").getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void init()
    {
        editprofileback_btn=findViewById(R.id.editprofileback_btn);
        firstname=findViewById(R.id.firstname);
        email=findViewById(R.id.email);
        mobileNumber=findViewById(R.id.mobileNumber);
        saveBtn=findViewById(R.id.saveBtn);
        messageView=findViewById(R.id.messageView);



        editprofileback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageView.setVisibility(View.GONE);
                String name=firstname.getText().toString();
                String emai=email.getText().toString();
                String phone=mobileNumber.getText().toString();

                if(name.equals(""))
                {
                    Toast.makeText(EditProfile_Activity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }
                else if(emai.equals(""))
                {
                    Toast.makeText(EditProfile_Activity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Map<String,Object> userdata=new HashMap<>();
                    userdata.put("name",name);
                    userdata.put("email",emai);
                    userdata.put("number",phone);

                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .updateChildren(userdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                messageView.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                Toast.makeText(EditProfile_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}