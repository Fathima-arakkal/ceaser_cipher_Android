package com.example.ceaser_cipher;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextText;
    private EditText editTextShift;
    private Button buttonEncrypt;
    private Button buttonDecrypt;
    private TextView textViewEncryptedText;
    private TextView textViewDecryptedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextText = findViewById(R.id.editTextText);
        editTextShift = findViewById(R.id.editTextShift);
        buttonEncrypt = findViewById(R.id.buttonEncrypt);
        buttonDecrypt = findViewById(R.id.buttonDecrypt);
        textViewEncryptedText = findViewById(R.id.textViewEncryptedText);
        textViewDecryptedText = findViewById(R.id.textViewDecryptedText);

        buttonEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editTextText.getText().toString();
                int shift = Integer.parseInt(editTextShift.getText().toString());

                String encryptedText = encrypt(text, shift);
                textViewEncryptedText.setText(encryptedText);
            }
        });

        buttonDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String encryptedText = textViewEncryptedText.getText().toString();
                int shift = Integer.parseInt(editTextShift.getText().toString());

                String decryptedText = decrypt(encryptedText, shift);
                textViewDecryptedText.setText(decryptedText);
            }
        });
    }

    // Encryption method
    private String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch)) {
                    ch = (char) (((int) ch + shift - 65) % 26 + 65);
                } else {
                    ch = (char) (((int) ch + shift - 97) % 26 + 97);
                }
            }

            result.append(ch);
        }

        return result.toString();
    }

    // Decryption method
    private String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }
}