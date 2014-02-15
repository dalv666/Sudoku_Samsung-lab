package com.dalv.sudoku_samsung_lab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class NumBoardFragment extends Fragment implements OnClickListener {

	private boolean isEmpty;
	
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public interface onNumClickI{
		public void onNumClick(String value);
	}
	
	onNumClickI numList;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			numList = (onNumClickI) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement onSomeEventListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.num_board_fragment, null);
		((Button) v.findViewById(R.id.btNum1)).setOnClickListener(this);
		((Button) v.findViewById(R.id.btNum2)).setOnClickListener(this);
		((Button) v.findViewById(R.id.btNum3)).setOnClickListener(this);
		((Button) v.findViewById(R.id.btNum4)).setOnClickListener(this);
		((Button) v.findViewById(R.id.btNum5)).setOnClickListener(this);
		((Button) v.findViewById(R.id.btNum6)).setOnClickListener(this);
		((Button) v.findViewById(R.id.btNum7)).setOnClickListener(this);
		((Button) v.findViewById(R.id.btNum8)).setOnClickListener(this);
		((Button) v.findViewById(R.id.btNum9)).setOnClickListener(this);
		((Button) v.findViewById(R.id.btBuck)).setOnClickListener(this);
		if(!isEmpty){
			Button clean = ((Button) v.findViewById(R.id.btClean));
			clean.setOnClickListener(this);
			clean.setVisibility(0);
		}
		
		return v;
	}

	@Override
	public void onClick(View arg0) {
		Button b = (Button) arg0;
		String value = (String) b.getText();
		numList.onNumClick(value);
	}


}

