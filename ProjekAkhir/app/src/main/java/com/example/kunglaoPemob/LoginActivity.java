package com.example.kunglaoPemob;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button masuk, register;
    private EditText email, pass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        masuk = (Button) findViewById((R.id.btn_peminjaman));
        email = (EditText) findViewById(R.id.txt_mail);
        pass = (EditText) findViewById(R.id.txt_pass);

        mAuth = FirebaseAuth.getInstance();

        masuk.setOnClickListener((v) -> {
            String mail = email.getText().toString().trim();
            String password = pass.getText().toString().trim();

            if (TextUtils.isEmpty(mail)) {
                email.setError("E-mail is required");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                pass.setError("Password is required");
                return;
            }

            mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener((task) -> {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Login was successful",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Index.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Email or Password is incorrect",
                            Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
