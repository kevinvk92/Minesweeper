package com.minesweeper.program;

public interface MineSweeper {

	void setMineField(String mineField) throws IllegalArgumentException;

	String getHintField() throws IllegalStateException;
}