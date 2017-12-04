package se.kth.sda.othello;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import se.kth.sda.othello.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth mAuth;
    EditText editTextUsername, editTextpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        editTextUsername= (EditText) findViewById(R.id.editTextEmail);
        editTextpassword=(EditText) findViewById(R.id.editTextPassword);
        findViewById(R.id.textViewSignUp).setOnClickListener(this);
        findViewById(R.id.buttonSignin).setOnClickListener(this);
    }
private void userLogin(){
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
mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
            Intent intent = new Intent(getBaseContext(), MenuActivity.class);
            startActivityForResult(intent, 0);
        }else{
            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
});
}
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.textViewSignUp:
                startActivity(new Intent(this ,signupActivity.class));
                break;
            case R.id.buttonSignin:
                userLogin();
                break;
        }
    }
}
