package com.minesweeper.program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MineSweeperImpl implements MineSweeper {

	private List<String> mineFieldList = new ArrayList<>();
	private List<Integer> rowLengthList = new ArrayList<>();
	private int n, m;

	@Override
	public void setMineField(String mineField) throws IllegalArgumentException {
		mineFieldList = new ArrayList<>();
		rowLengthList = new ArrayList<>();

		if (StringUtils.isBlank(mineField))
			throw new IllegalArgumentException("Invalid Input: Blank Input.");

		Arrays.stream(StringUtils.splitByWholeSeparator(mineField, "\n")).forEach(rowString -> {
			if (StringUtils.containsOnly(rowString, "*.")
					&& (rowLengthList.isEmpty() || (rowLengthList.get(0) == rowString.length()))) {
				mineFieldList.add(rowString);
				rowLengthList.add(rowString.length());
			} else {
				mineFieldList = new ArrayList<>();
				rowLengthList = new ArrayList<>();
				throw new IllegalArgumentException("Invalid Input: Invalid Characters Exist.");
			}
		});
	}

	@Override
	public String getHintField() throws IllegalStateException {
		if (mineFieldList.isEmpty())
			throw new IllegalStateException("Invalid State: MineField is not set.");

		n = mineFieldList.size();
		m = mineFieldList.get(0).length();

		StringBuilder hintString = new StringBuilder();

		for (int i = 0; i < n; i++) {
			String s = mineFieldList.get(i);
			for (int j = 0; j < m; j++) {
				if ('*' == s.charAt(j)) {
					hintString.append("*");
				} else {
					int adjCount = 1;
					if ((j - 1 >= 0) && ('*' == s.charAt(j - 1))) {
						++adjCount;
						if (((i - 1) >= 0) && ('*' == mineFieldList.get(i - 1).charAt(j - 1))) {
							++adjCount;
						}
						if (((i + 1) < n) && ('*' == mineFieldList.get(i + 1).charAt(j - 1))) {
							++adjCount;
						}
					}
					if ((j + 1 < m) && ('*' == s.charAt(j + 1))) {
						++adjCount;
						if (((i - 1) >= 0) && ('*' == mineFieldList.get(i - 1).charAt(j + 1))) {
							++adjCount;
						}
						if (((i + 1) < n) && ('*' == mineFieldList.get(i + 1).charAt(j + 1))) {
							++adjCount;
						}
					}
					hintString.append(adjCount);
				}
			}
			hintString.append("\n");
		}
		return hintString.toString();
	}

}