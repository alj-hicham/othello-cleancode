package se.kth.sda.othello;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class signupActivity extends AppCompatActivity implements View.OnClickListener  {
EditText editTextUsername, editTextpassword;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
editTextUsername= (EditText) findViewById(R.id.editTextEmail);
editTextpassword=(EditText) findViewById(R.id.editTextPassword);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.buttonSignup).setOnClickListener(this);
        findViewById(R.id.textViewSignin).setOnClickListener(this);
    }

private void registerUser(){
        String email= editTextUsername.getText().toString().trim();
        String password=editTextpassword.getText().toString().trim();
    if(email.isEmpty()){
        editTextUsername.setError("Email is required");
        editTextUsername.requestFocus();
        return;
    }
    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        editTextUsername.setError("please enter a valid email");
        editTextpassword.requestFocus();
        return;
    }
    if(password.length()<6){
        editTextpassword.setError("minimum lenght of password should be 6");
        editTextpassword.requestFocus();
        return;
    }

    if(password.isEmpty()){
        editTextpassword.setError("password is required");
        editTextpassword.requestFocus();
        return;
    }
    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
if(task.isSuccessful()){
    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
    startActivityForResult(intent, 0);
    Toast.makeText(getApplicationContext(), "user registred succefull", Toast.LENGTH_LONG).show();
}else{
if(task.getException()instanceof FirebaseAuthUserCollisionException){
Toast.makeText(getApplicationContext() , "you are already registred " , Toast.LENGTH_LONG).show();
}else{
    Toast.makeText(getApplicationContext(), task.getException().getMessage(),Toast.LENGTH_SHORT).show();
}
}}
    });
    }
public void onClick(View view){
        switch(view.getId()){
            case R.id.buttonSignup:
            registerUser();
                break;

            case R.id.textViewSignin:
            startActivity(new Intent(this, LoginActivity.class));
            break;
        }
}
}