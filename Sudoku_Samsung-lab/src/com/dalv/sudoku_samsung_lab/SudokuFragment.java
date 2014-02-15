package com.dalv.sudoku_samsung_lab;

import java.util.BitSet;

import com.dalv.sudoku_samsung_lab.sudokuSolver.SudokuSolver;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SudokuFragment extends Fragment implements OnClickListener {
	private TextView result;
	private LinearLayout llMain;
	private Button btnNew;
	private int[][] grid;
	private Button[][] gridButton;
	private int coordinates[] = new int[2];

	public void changeValueCell(String value) {
		if (value.equals(getResources().getString(R.string.clean))) {
			value = "";
		} else {
			grid[coordinates[0]][coordinates[1]] = Integer.valueOf(value);
		}
		gridButton[coordinates[0]][coordinates[1]].setText(value);

	}

	onClickbutton onClick;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public interface onClickbutton {
		public void onButtonClick(String value);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.grid = new int[9][9];
		gridButton = new Button[9][9];
		LayoutInflater inflator = getLayoutInflater(getArguments());
		View v = inflator.inflate(R.layout.fragment_game, null);
		result = (TextView) v.findViewById(R.id.tvResult);
		Button check = (Button) v.findViewById(R.id.btSolve);
		check.setOnClickListener(this);
		llMain = (LinearLayout) v.findViewById(R.id.llMain);
		LayoutParams linLayoutParam = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		for (int j = 0; j < 9; j++) {
			LinearLayout linLayout = new LinearLayout(getActivity());
			linLayout.setOrientation(LinearLayout.HORIZONTAL);
			for (int i = 0; i < 9; i++) {
				btnNew = new Button(getActivity());
				btnNew.setTextSize(14);
				btnNew.setTag(j + "" + i);
				btnNew.setOnClickListener(this);
				gridButton[j][i] = btnNew;
				btnNew.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1.0f));
				if (grid[j][i] != 0) {
					btnNew.setText(grid[j][i] + "");
					btnNew.setClickable(false);
				}
				linLayout.setLayoutParams(linLayoutParam);
				linLayout.addView(btnNew);
			}
			llMain.addView(linLayout);
		}
		return v;
	}

	public static boolean isValid(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			BitSet bsRow = new BitSet(9);
			BitSet bsColumn = new BitSet(9);
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0 || board[j][i] == 0)
					continue;
				if (bsRow.get(board[i][j] - 1) || bsColumn.get(board[j][i] - 1))
					return false;
				else {
					bsRow.set(board[i][j] - 1);
					bsColumn.set(board[j][i] - 1);
				}
			}
		}
		for (int rowOffset = 0; rowOffset < 9; rowOffset += 3) {
			for (int columnOffset = 0; columnOffset < 9; columnOffset += 3) {
				BitSet threeByThree = new BitSet(9);
				for (int i = rowOffset; i < rowOffset + 3; i++) {
					for (int j = columnOffset; j < columnOffset + 3; j++) {
						if (board[i][j] == 0)
							continue;
						if (threeByThree.get(board[i][j] - 1))
							return false;
						else
							threeByThree.set(board[i][j] - 1);
					}
				}
			}
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		if ((((Button) v).getText()).equals(getResources().getString(R.string.solve))) {
			if (!isValid(grid)) {
				result.setTextColor(Color.RED);
				result.setText(getResources().getString(R.string.incorrect));
			}else{
				SudokuSolver ss = new SudokuSolver(this.grid); 
				ss.initSubsets();
				ss.solve(); 
				this.grid = ss.getmBoard();
				refill();
			}
		} else {
			result.setText("");
			char x = ((String) v.getTag()).charAt(0);
			char y = ((String) v.getTag()).charAt(1);
			coordinates[0] = Character.getNumericValue(x);
			coordinates[1] = Character.getNumericValue(y);
			Intent intent = new Intent(getActivity(), NumBoardActivity.class);
			intent.putExtra("isEmpty",
					gridButton[coordinates[0]][coordinates[1]].getText()
							.equals(""));
			startActivityForResult(intent, 1);
		}
	}

	private void refill() {
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				gridButton[j][i].setText(grid[j][i]+"");
			}
		}
	}

}
