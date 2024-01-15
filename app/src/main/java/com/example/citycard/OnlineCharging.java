package com.example.citycard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class OnlineCharging extends AppCompatActivity {
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_charging);

        firestore = FirebaseFirestore.getInstance();
        Button btnBalanceCheck = findViewById(R.id.btnBalanceCheck);
        Dialog dialog = new Dialog(this);

        btnBalanceCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityCardId = ((EditText) findViewById(R.id.editTextCityCardId)).getText().toString().toUpperCase();
                if (cityCardId.isEmpty()) {
                    Toast.makeText(OnlineCharging.this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Firestore'da ilgili dokümanı sorgula
                DocumentReference docRef = firestore.collection("Cards").document(cityCardId);
                docRef.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // Firestore'dan belgeyi al ve Card nesnesine çevir
                            Card card = document.toObject(Card.class);
                            if (card != null) {
                                Integer balance = card.getBalance();
                                showBalance(balance);
                            }
                        } else {
                            Toast.makeText(OnlineCharging.this, "Kart bulunamadı", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(OnlineCharging.this, "Firestore hatası: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button btnTopUp = findViewById(R.id.btnTopUp);
        btnTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityCardId = ((EditText) findViewById(R.id.editTextCityCardId)).getText().toString().toUpperCase();
                String addedBalance = ((EditText) findViewById(R.id.editTextAmount)).getText().toString();
                String creditCardNumber = ((EditText) findViewById(R.id.editTextCCNum)).getText().toString();
                String expireDate = ((EditText) findViewById(R.id.editTextExpire)).getText().toString();
                String cvv = ((EditText) findViewById(R.id.editTextCVV)).getText().toString();

                // Boşluk kontrolü
                if (cityCardId.isEmpty() || addedBalance.isEmpty() || creditCardNumber.isEmpty() || expireDate.isEmpty() || cvv.isEmpty()) {
                    Toast.makeText(OnlineCharging.this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show();
                    return;
                }


                // Firestore'da ilgili dokümanın bakiye alanını güncelle
                DocumentReference docRef = firestore.collection("Cards").document(cityCardId);
                docRef.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // Firestore'dan belgeyi al ve Card nesnesine çevir
                            Card card = document.toObject(Card.class);
                            if (card != null) {
                                // Önceki bakiyeyi al
                                Integer oldBalance = card.getBalance();

                                // Yeni bakiyeyi hesapla ve Firestore'a geri yaz
                                Integer newBalance = oldBalance + Integer.parseInt(addedBalance);
                                docRef.update("balance", newBalance).addOnCompleteListener(updateTask -> {
                                    if (updateTask.isSuccessful()) {
                                        Toast.makeText(OnlineCharging.this, "Bakiye Güncellendi", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(OnlineCharging.this, "Firestore hatası: " + updateTask.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                                // Top-up pop-up ekranını göster
                                showTopUp(oldBalance, newBalance);
                            }
                        } else {
                            Toast.makeText(OnlineCharging.this, "Kart bulunamadı", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(OnlineCharging.this, "Firestore hatası: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        CardView toolBarCardView = findViewById(R.id.toolBar);


        toolBarCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event here
                // In this example, navigate back to the home screen
                onBackPressed();
            }
        });


    }

    private void showBalance(int balance) {
        Dialog balanceDialog = new Dialog(this);
        balanceDialog.setContentView(R.layout.popup_balancecheck);
        TextView txtBalance = balanceDialog.findViewById(R.id.txtBalance);
        txtBalance.setText(balance + "");

        ImageButton btnClosePopup = balanceDialog.findViewById(R.id.btnClosePopup);
        btnClosePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                balanceDialog.dismiss();
            }
        });

        balanceDialog.show();
    }
    private void showTopUp(int oldBalance, int newBalance) {
        Dialog topUpDialog = new Dialog(this);
        topUpDialog.setContentView(R.layout.popup_topup);

        TextView txtOldBalance = topUpDialog.findViewById(R.id.txtOldBalance);
        TextView txtNewBalance = topUpDialog.findViewById(R.id.txtNewBalance);
        ImageButton btnClosePopup = topUpDialog.findViewById(R.id.btnClosePopup);

        txtOldBalance.setText("Old Balance: " + oldBalance);
        txtNewBalance.setText("New Balance: " + newBalance);

        btnClosePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topUpDialog.dismiss();
            }
        });

        topUpDialog.show();
    }

}