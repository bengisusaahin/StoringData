package com.bengisu.storingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//küçük verileri kaydetmek için mantıklı ama büyük veriler için habire key üretmek sıkıntı
    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextNumber);
        textView = findViewById(R.id.textView);

        sharedPreferences = this.getSharedPreferences("com.bengisu.storingdata", Context.MODE_PRIVATE);//sadece benim uygulamamdan ulasilsin
        int storedAge =sharedPreferences.getInt("storedAge",0); //kaydettigim seyi geri aliyorum save methodundan
        //eger buluıncak bi deger yoksa 0 degerini alacak
        textView.setText("Your age: "+storedAge);
        if (storedAge ==0){
            textView.setText("Your age: ");
        }else
            textView.setText("Your age: "+storedAge);
    }
    public void save(View view){
        if(!editText.getText().toString().matches("")){//eger bosluk varsa kullanici hicbir sey vermediyse,ünlem koyarak verdiyse
            int userAge = Integer.parseInt(editText.getText().toString());
            textView.setText("Your age: "+userAge);

            sharedPreferences.edit().putInt("storedAge",userAge).apply();//apply demezsen kaydedemezsin
        }
    }
    public void delete(View view){
        int storedData = sharedPreferences.getInt("storedAge",0);
        if (storedData!=0){
            sharedPreferences.edit().remove("storedAge").apply();//verecegim keyle yani storedAgela kayıtlı bir deger varsa artık o değeri sil
            textView.setText("Your age:");
        }
    }
}
