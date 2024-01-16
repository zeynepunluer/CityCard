package com.example.citycard;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.gms.tasks.Tasks;
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

                new FetchBalanceTask().execute(cityCardId);
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
                try {
                    int checkInteger = Integer.parseInt(addedBalance);

                } catch (NumberFormatException e) {
                    // addedBalance bir tamsayıya çevrilemezse kullanıcıya uyarı gönder
                    Toast.makeText(OnlineCharging.this, "Amount must be integer", Toast.LENGTH_SHORT).show();
                    return;
                }

                new TopUpBalanceTask(cityCardId, addedBalance).execute();
            }
        });

        CardView toolBarCardView = findViewById(R.id.toolBar);

        toolBarCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private class FetchBalanceTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            String cityCardId = params[0];
            DocumentReference docRef = firestore.collection("Cards").document(cityCardId);
            try {
                DocumentSnapshot document = Tasks.await(docRef.get());
                if (document.exists()) {
                    Card card = document.toObject(Card.class);
                    if (card != null) {
                        return card.getBalance();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer balance) {
            if (balance != null) {
                showBalance(balance);
            } else {
                Toast.makeText(OnlineCharging.this, "Kart bulunamadı", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class TopUpBalanceTask extends AsyncTask<Void, Void, Integer> {
        private String cityCardId;
        private String addedBalance;

        public TopUpBalanceTask(String cityCardId, String addedBalance) {
            this.cityCardId = cityCardId;
            this.addedBalance = addedBalance;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            DocumentReference docRef = firestore.collection("Cards").document(cityCardId);
            try {
                DocumentSnapshot document = Tasks.await(docRef.get());
                if (document.exists()) {
                    Card card = document.toObject(Card.class);
                    if (card != null) {
                        Integer oldBalance = card.getBalance();
                        Integer newBalance = oldBalance + Integer.parseInt(addedBalance);
                        Tasks.await(docRef.update("balance", newBalance));
                        return newBalance;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer newBalance) {
            if (newBalance != null) {
                Toast.makeText(OnlineCharging.this, "Bakiye Güncellendi", Toast.LENGTH_SHORT).show();
                showTopUp(newBalance - Integer.parseInt(addedBalance), newBalance);
            } else {
                Toast.makeText(OnlineCharging.this, "Kart bulunamadı", Toast.LENGTH_SHORT).show();
            }
        }
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

        txtOldBalance.setText("Eski Bakiye: " + oldBalance);
        txtNewBalance.setText("Yeni Bakiye: " + newBalance);

        btnClosePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topUpDialog.dismiss();
            }
        });

        topUpDialog.show();
    }
}