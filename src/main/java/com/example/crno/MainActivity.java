package com.example.crno;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Chronometer chrono;
    private boolean runn;
    private long pauseoff;
    ImageView playbt,pausebt,lapbt,stopbt;
    LinearLayout conteneur;
    TextView txtContent;
    ScrollView row;
    int countLaps=0;
    long timeWhenStopped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conteneur= (LinearLayout) findViewById(R.id.conteneur);
        chrono =findViewById(R.id.chronometer);
        txtContent= (TextView) findViewById(R.id.txtContent);
        playbt=findViewById(R.id.start);
        pausebt=findViewById(R.id.pause);
        lapbt=findViewById(R.id.lap);
        stopbt=findViewById(R.id.stop);
        row=findViewById(R.id.row);




    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        timeWhenStopped =  chrono.getBase() - SystemClock.elapsedRealtime();
        savedInstanceState.putLong("alo",timeWhenStopped);
        savedInstanceState.putBoolean("run",runn);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        long time=savedInstanceState.getLong("alo");
        chrono.setBase(SystemClock.elapsedRealtime() +time   );

        boolean r=savedInstanceState.getBoolean("run");
        if(r = true){
        chrono.start();

    }
    }




    public void startChronometre (View view){

if(runn = true){
    chrono.setBase(SystemClock.elapsedRealtime() -pauseoff   );
    chrono.start();
    runn=true;
}
        Log.i("TAG","onStart");
        playbt.setVisibility(View.GONE);
        pausebt.setVisibility(View.VISIBLE);
        lapbt.setVisibility(View.VISIBLE);
        stopbt.setVisibility(View.GONE);
    }

    public void pauseChronometre(View view){
        if(runn = true){
        chrono.stop();
        pauseoff = SystemClock.elapsedRealtime() - chrono.getBase();
            runn=false;
        }
        playbt.setVisibility(View.VISIBLE);
        pausebt.setVisibility(View.GONE);
        lapbt.setVisibility(View.GONE);
        stopbt.setVisibility(View.VISIBLE);
    }

    public void stopChronometre (View view){
        chrono.setBase(SystemClock.elapsedRealtime());
        pauseoff = 0;
        pauseoff = chrono.getBase() - SystemClock.elapsedRealtime();
        playbt.setVisibility(View.VISIBLE);
        pausebt.setVisibility(View.GONE);
        lapbt.setVisibility(View.VISIBLE);
        stopbt.setVisibility(View.GONE);
    }

    public void getLaps(View v){
        String a=chrono.getText().toString();
        countLaps++;
        LayoutInflater inflater= (LayoutInflater)
                getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View addView=inflater.inflate(R.layout.row,null);
        TextView textValue=(TextView) addView.findViewById(R.id.txtContent);

        textValue.setText("Lap" +countLaps+" : " +a);
        textValue.setTextSize(Float.parseFloat("16"));
        textValue.setPadding(30,20,0,0);
        conteneur.addView(addView);
    }

    @Override
    public void onClick(View view) {
    }
}