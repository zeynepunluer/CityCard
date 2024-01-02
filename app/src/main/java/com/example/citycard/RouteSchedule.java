    package com.example.citycard;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.cardview.widget.CardView;

    import android.os.Bundle;
    import android.view.View;

    public class RouteSchedule extends AppCompatActivity implements CustomFragmentListener {
        public void onCustomFragmentClose() {
            getSupportFragmentManager().popBackStack();
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_route_schedule);

            // Find the CardView by its ID
            CardView toolBarCardView = findViewById(R.id.toolBar);

            // Set an OnClickListener for the CardView
            toolBarCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle the click event here
                    // In this example, navigate back to the home screen
                    onBackPressed();
                }
            });
            setButtonClickListeners();


        }

        private void setButtonClickListeners() {
            findViewById(R.id.cardView2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCustomFragment("Rota Bilgisi 1");
                    setAllCardViewsInvisible();
                }
            });

            findViewById(R.id.cardView3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCustomFragment("Rota Bilgisi 2");
                    setAllCardViewsInvisible();
                }
            });

            findViewById(R.id.cardView4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCustomFragment("Rota Bilgisi 3");
                    setAllCardViewsInvisible();
                }
            });

            findViewById(R.id.cardView5).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCustomFragment("Rota Bilgisi 4");
                    setAllCardViewsInvisible();
                }
            });

            findViewById(R.id.cardView6).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCustomFragment("Rota Bilgisi 5");
                    setAllCardViewsInvisible();
                }
            });

            findViewById(R.id.cardView7).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCustomFragment("Rota Bilgisi 6");
                    setAllCardViewsInvisible();
                }
            });

        }

        private void openCustomFragment(String routeInfo) {
            CustomFragment customFragment = CustomFragment.newInstance(routeInfo);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, customFragment)  // R.id.fragmentContainer, fragment'in yerleştirileceği bir layout id'sidir.
                    .addToBackStack(null)  // Bu, geri tuşu ile fragment geçmişini yönetmek için kullanılır.
                    .commit();
        }

        // Tüm CardView'ları görünmez yap
        private void setAllCardViewsInvisible() {
            findViewById(R.id.cardView2).setVisibility(View.INVISIBLE);
            findViewById(R.id.cardView3).setVisibility(View.INVISIBLE);
            findViewById(R.id.cardView4).setVisibility(View.INVISIBLE);
            findViewById(R.id.cardView5).setVisibility(View.INVISIBLE);
            findViewById(R.id.cardView6).setVisibility(View.INVISIBLE);
            findViewById(R.id.cardView7).setVisibility(View.INVISIBLE);
        }
        public void setAllCardViewsVisible() {
            findViewById(R.id.cardView2).setVisibility(View.VISIBLE);
            findViewById(R.id.cardView3).setVisibility(View.VISIBLE);
            findViewById(R.id.cardView4).setVisibility(View.VISIBLE);
            findViewById(R.id.cardView5).setVisibility(View.VISIBLE);
            findViewById(R.id.cardView6).setVisibility(View.VISIBLE);
            findViewById(R.id.cardView7).setVisibility(View.VISIBLE);
        }
    }