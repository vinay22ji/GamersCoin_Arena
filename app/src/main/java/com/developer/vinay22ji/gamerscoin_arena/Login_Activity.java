package com.developer.vinay22ji.gamerscoin_arena;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.security.AccessController.getContext;

public class Login_Activity extends AppCompatActivity {

    Button Get_otp_btn,Register_btn;
    EditText Number_Edittext,Otp_Edittext;
    TextView ShowNumber_text,changeNumber_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        MainActivity.setupMode(this.getWindow(), Login_Activity.this);

        Get_otp_btn=findViewById(R.id.Get_otp_btn);
        Number_Edittext=findViewById(R.id.Number_Edittext);
        ShowNumber_text=findViewById(R.id.ShowNumber_text);
        Otp_Edittext=findViewById(R.id.Otp_Edittext);
        Register_btn=findViewById(R.id.Register_btn);
        changeNumber_text=findViewById(R.id.changeNumber_text);

        Get_otp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String mobileNum = Number_Edittext.getText().toString();


                if(mobileNum.length()!=10)
                {
                    Toast.makeText(Login_Activity.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final ProgressDialog dialog = new ProgressDialog(Login_Activity.this);
                    dialog.setMessage("Checking data....");
                    dialog.setTitle("Please wait");
                    dialog.setCancelable(false);
                    dialog.show();


                    dialog.dismiss();
                    ShowNumber_text.setText("We Sent One Time Password to +91 "+mobileNum);
                    sendotp("+91"+mobileNum);

                }

            }
        });


        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String otptex=Otp_Edittext.getText().toString();
                if(!otptex.isEmpty())
                {
                    PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(token,otptex);
                    matchotp(phoneAuthCredential);
                }
                else
                {
                    Toast.makeText(Login_Activity.this, "enter otp", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    String token;
    private void sendotp(String ph)
    {
        final ProgressDialog dialog = new ProgressDialog(Login_Activity.this);
        dialog.setMessage("loading.... if number is in your device you login automatically ");
        dialog.setCancelable(false);
        dialog.setTitle("Please wait");
        dialog.show();

        findViewById(R.id.layout_number).setVisibility(View.GONE);
        findViewById(R.id.layout_otp).setVisibility(View.VISIBLE);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(ph, 60, TimeUnit.SECONDS, Login_Activity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                matchotp(phoneAuthCredential);
                dialog.dismiss();

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                new CountDownTimer(60000, 1000) { // adjust the milli seconds here

                    public void onTick(long millisUntilFinished) {
//                        resendcode_text.setText("Code is Valid only for "+String.format("%d:%d sec",
//                                TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
//                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
//                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    }

                    public void onFinish() {
//                        resendcode_text.setVisibility(View.GONE);
//                        verifivation_text.setVisibility(View.VISIBLE);
//                        verifivation_text.setText("Resend Code");
//                        resendcode_text.setClickable(true);
//                        mobileNumber.setEnabled(true);
                    }
                }.start();

                token=s;
                dialog.dismiss();
                Toast.makeText(Login_Activity.this, "OTP Sent!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(Login_Activity.this, e.toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    boolean notreg=false;

    private void matchotp(PhoneAuthCredential phoneAuthCredential)
    {
        final ProgressDialog dialog = new ProgressDialog(Login_Activity.this);
        dialog.setMessage("loading.... Matching Otp ");
        dialog.setTitle("Please wait");
        dialog.setCancelable(false);
        dialog.show();

        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if(task.isSuccessful())
                            {

                                FirebaseDatabase.getInstance().getReference().child("users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        if(snapshot.exists())
                                        {
                                            Toast.makeText(Login_Activity.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(Login_Activity.this,MainActivity.class));
                                        }
                                        else
                                        {
                                            final String mobileNum = Number_Edittext.getText().toString();
                                            final Map<String, Object> user = new HashMap<>();
                                            user.put("name", "");
                                            user.put("email", "");
                                            user.put("number", "+91 "+mobileNum);
                                            user.put("wallet", 0);
                                            user.put("verified", false);
                                            user.put("status","active");
//                                            user.put("joinDate", FieldValue.serverTimestamp());
                                            user.put("userId", FirebaseAuth.getInstance().getCurrentUser().getUid());

                                            FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if(task.isSuccessful())
                                                    {
                                                        Toast.makeText(Login_Activity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(Login_Activity.this,MainActivity.class));
                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(Login_Activity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                                    }

                                                }
                                            });

                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                            else
                            {
                                dialog.dismiss();
                                Toast.makeText(Login_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    dialog.dismiss();
                    Toast.makeText(Login_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}