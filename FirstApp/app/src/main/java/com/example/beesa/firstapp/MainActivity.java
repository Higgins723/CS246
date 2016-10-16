package com.example.beesa.firstapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

    Button share_button;
    EditText book, chapter, verse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        share_button = (Button) findViewById(R.id.share_button);
        book = (EditText) findViewById(R.id.book);
        chapter = (EditText) findViewById(R.id.chapter);
        verse = (EditText) findViewById(R.id.verse);

        share_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent("com.example.beesa.firstapp.ANOTHER_ACTIVITY");

        // 2. create person object
        Scripture scripture = new Scripture();
        scripture.setBookName(book.getText().toString());
        scripture.setChapterName(chapter.getText().toString());
        scripture.setVerseName(verse.getText().toString());

        // 3. put person in intent data
        intent.putExtra("scripture", scripture);

        // 4. start the activity
        startActivity(intent);
    }

}
