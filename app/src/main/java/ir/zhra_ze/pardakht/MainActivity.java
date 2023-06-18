package ir.zhra_ze.pardakht;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnFixedLine, btnChargeMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFixedLine = findViewById(R.id.btnFixedLine_main);
        btnChargeMobile = findViewById(R.id.btnChargeMobile_main);

        btnFixedLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FixedLineInquiryActivity.class);
                startActivity(intent);
            }
        });
        btnChargeMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ChargeMobileActivity.class);
                startActivity(intent);
            }
        });
    }
}