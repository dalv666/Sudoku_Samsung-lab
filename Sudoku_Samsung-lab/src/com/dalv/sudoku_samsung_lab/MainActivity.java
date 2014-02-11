package com.dalv.sudoku_samsung_lab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class MainActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SudokuFragment firstFragment = new SudokuFragment(); 
		setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
        .add(R.id.FLgame, firstFragment).commit();
	}
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("dalvv", data.getStringExtra("value")+" Activity");
	    if (data == null || data.getStringExtra("value").equals(getResources().getString(R.string.back))) {return;}
	    String name = data.getStringExtra("value");
	    SudokuFragment frag = (SudokuFragment) (getSupportFragmentManager().findFragmentById(R.id.FLgame));
	    frag.changeValueCell(name);
		super.onActivityResult(requestCode, resultCode, data);
	}
}
