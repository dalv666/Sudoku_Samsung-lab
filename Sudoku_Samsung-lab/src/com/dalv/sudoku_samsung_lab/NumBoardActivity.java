package com.dalv.sudoku_samsung_lab;

import com.dalv.sudoku_samsung_lab.NumBoardFragment.onNumClickI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class NumBoardActivity extends FragmentActivity implements onNumClickI{

	boolean isEmpty;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		isEmpty = intent.getBooleanExtra("isEmpty", true);
		setContentView(R.layout.num_activity);
		NumBoardFragment fragment = new NumBoardFragment();
		fragment.setEmpty(isEmpty);
        getSupportFragmentManager().beginTransaction().add(R.id.FLnum, fragment).commit();
	}

	@Override
	public void onNumClick(String value) {
		Intent intent = new Intent();
		intent.putExtra("value", value);
		setResult(RESULT_OK, intent);
		finish();
	}
	

}

