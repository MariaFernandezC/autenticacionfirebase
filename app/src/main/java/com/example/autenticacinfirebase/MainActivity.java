package com.example.autenticacinfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText textEmail;
    private EditText TextPassword;
    private Button btnregistrar;
    private ProgressDialog progressDialog;

// declaracion del objecto Firebase
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicializamos el objeto FirebaseAuth
        firebaseAuth =FirebaseAuth.getInstance();

        textEmail=findViewById(R.id.edtUsuario);
        TextPassword = findViewById(R.id.edtPassword);

        btnregistrar= findViewById(R.id.btnLogin);
        progressDialog =new ProgressDialog(this);

        btnregistrar.setOnClickListener((View.OnClickListener) this);
    }

    private void registrarUsuario(){

        //OBTENEMOS EL EMAIL Y LA CONTRASEÑA DESDE LAS CAJAS DE TEXTO
        String email = textEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        //verificamos que las cajas de texto no esten vacias

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"falta ingresar la contrasena",Toast.LENGTH_SHORT).show();
        }

        progressDialog.setMessage("Ralizando registro en linea.... Espero por favor");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Se ha registrado el email",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(MainActivity.this,"No se ha registrado el email",Toast.LENGTH_LONG).show();
            }
        });


    }
    @Override
    public void onClick(View view) {
        //Invocamos al método:
        registrarUsuario();
    }

}
