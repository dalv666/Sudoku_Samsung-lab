package com.dalv.sudoku_samsung_lab.sudokuSolver;
import java.util.ArrayList;
import java.util.List;

public class SudokuSolver {
	private int mBoard[][];
	public int[][] getmBoard() {
		return mBoard;
	}

	private int mBoardSize;
	private int mBoxSize;
	private boolean mRowSubset[][];
	private boolean mColSubset[][];

	private boolean mBoxSubset[][];

	public SudokuSolver(int board[][]) {
		this.mBoard = new int[board.length][board.length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				mBoard[i][j] = board[i][j];
			}
		}
		mBoardSize = mBoard.length;
		mBoxSize = (int) Math.sqrt(mBoardSize);
	}

	public void initSubsets() {
		mRowSubset = new boolean[mBoardSize][mBoardSize];
		mColSubset = new boolean[mBoardSize][mBoardSize];
		mBoxSubset = new boolean[mBoardSize][mBoardSize];
		for (int i = 0; i < mBoard.length; i++) {
			for (int j = 0; j < mBoard.length; j++) {
				int value = mBoard[i][j];
				if (value != 0) {
					setSubsetValue(i, j, value, true);
				}
			}
		}
	}

	private void setSubsetValue(int i, int j, int value, boolean present) {
		mRowSubset[i][value - 1] = present;
		mColSubset[j][value - 1] = present;
		mBoxSubset[computeBoxNo(i, j)][value - 1] = present;
	}

	public boolean solve() {
		return solve(0, 0);
	}

	public boolean solve(int i, int j) {
		if (i == mBoardSize) {
			i = 0;
			if (++j == mBoardSize) {
				return true;
			}
		}
		if (mBoard[i][j] != 0) {
			return solve(i + 1, j);
		}
		for (int value = 1; value <= mBoardSize; value++) {
			if (isValid(i, j, value)) {
				mBoard[i][j] = value;
				setSubsetValue(i, j, value, true);
				if (solve(i + 1, j)) {
					return true;
				}
				setSubsetValue(i, j, value, false);
			}
		}

		mBoard[i][j] = 0;
		return false;
	}

	private boolean isValid(int i, int j, int val) {
		val--;
		boolean isPresent = mRowSubset[i][val] || mColSubset[j][val]
				|| mBoxSubset[computeBoxNo(i, j)][val];
		return !isPresent;
	}

	private int computeBoxNo(int i, int j) {
		int boxRow = i / mBoxSize;
		int boxCol = j / mBoxSize;
		return boxRow * mBoxSize + boxCol;
	}

	public void print() {
		System.out.println(mBoard[0][0]);
	}

	public static void main(String args[]) {
		/*
		 * SudokuSolver ss = new SudokuSolver(gridTable); ss.print();
		 * ss.initSubsets(); System.out.println(ss.solve()); ss.print();
		 */
	}
}