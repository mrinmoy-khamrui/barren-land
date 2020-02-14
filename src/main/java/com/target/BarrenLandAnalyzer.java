package com.target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Here the assumption is as per the problem description each block in the grid is of 1 sqm
 * 
 * @author mrinmoy
 *
 */
public class BarrenLandAnalyzer {

	private int height = 400;
	private int width = 600;
	
	private boolean[][] grid = new boolean[height][width]; // all fertile
	
	/**
	 * 
	 * @param barrenRectangleCoordinates - one or more barren area. the array to have exactly 4 elements
	 * bounding bottom left corner with first 2 members and top right corner with last 2 members
	 */
	public BarrenLandAnalyzer(List<int[]> barrenRectangleCoordinates) {
		if(barrenRectangleCoordinates == null || barrenRectangleCoordinates.isEmpty()) {
			throw new IllegalArgumentException("Please provide at least one barren area coordinate");
		}
		for(int[] barrenAreaCoord : barrenRectangleCoordinates) {
			if(isNotIn(barrenAreaCoord[0], barrenAreaCoord[1]) || isNotIn(barrenAreaCoord[2], barrenAreaCoord[3])) {
				throw new IllegalArgumentException("bottom left and top right coordinates must be within [0, 0] and ["+height+", " + width + "]");
			}
			if(barrenAreaCoord[2] < barrenAreaCoord[0] || barrenAreaCoord[3] < barrenAreaCoord[1]) {
				throw new IllegalArgumentException("Invalid coordinates " + Arrays.toString(barrenAreaCoord));
			}
			markBarrenArea(barrenAreaCoord[0], barrenAreaCoord[1], barrenAreaCoord[2], barrenAreaCoord[3]);
		}
	}
	
	public static void main(String[] args) {
		try (Scanner inputScanner = new Scanner(System.in)) {
			System.out.println("Please input barren land rectangle coordinates:");
			String input = null;
			do {
				input = inputScanner.nextLine();
			} while(input == null || input.isBlank());
			
			String[] inputRectangles = input.replaceAll("\"", "").split(",");
			ArrayList<int[]> barrenRectangleCoordinates = new ArrayList<>();
			for(String rectangle : inputRectangles) {
				String[] members = rectangle.split(" ");
				if(members.length != 4) {
					throw new IllegalArgumentException("invalid coordinates for " + rectangle);
				}
				int[] intCoordinates = new int[4];
				for(int i = 0; i < members.length; i++) {
					intCoordinates[i] = Integer.valueOf(members[i]);
				}
				barrenRectangleCoordinates.add(intCoordinates);
			}
				
			BarrenLandAnalyzer b = new BarrenLandAnalyzer(barrenRectangleCoordinates);
			List<Integer> fertileLands = b.findFertileLands();
			System.out.println("Fertile land areas (in sqm) in ascending order:");
			String output = fertileLands
					.stream()
					.map(String::valueOf)
					.collect(Collectors.joining(" "));
			System.out.println(output);
		}
	}
	
	private void markBarrenArea(int cHB, int cWL, int cHT, int cWR) {
		for(int cH = cHB; cH <= cHT; cH++) {
			for(int cW = cWL; cW <= cWR; cW++) {
				grid[cH][cW] = true;
			}
		}
	}
	
	/**
	 * 
	 * @return contiguous fertile land areas in ascending order
	 */
	public List<Integer> findFertileLands() {
		ArrayList<Integer> fertileLands = new ArrayList<>();
		for(int cH = 0; cH < height; cH++) {
			for(int cW = 0; cW < width; cW++) {
				if(!grid[cH][cW]) { // coordinate is fertile
					fertileLands.add(findFertileLandAreaBfs(cH, cW));
				}
			}
		}
		Collections.sort(fertileLands);
		return fertileLands;
	}
	
	private int findFertileLandAreaBfs(int cH, int cW) {
		LinkedList<int[]> queue = new LinkedList<>();
		queue.add(new int[] {cH, cW});
		grid[cH][cW] = true;
		int area = 0;
		while(!queue.isEmpty()) {
			int[] c = queue.remove();
			area++;
			findNeighboringFertileCoordinates(c[0] + 1, c[1], queue);
			findNeighboringFertileCoordinates(c[0] - 1, c[1], queue);
			findNeighboringFertileCoordinates(c[0], c[1] + 1, queue);
			findNeighboringFertileCoordinates(c[0], c[1] - 1, queue);
		}
		return area;
	}
	
	private void findNeighboringFertileCoordinates(int cH, int cW, LinkedList<int[]> queue) {
		if(!isNotIn(cH, cW) && !grid[cH][cW]) {
			queue.add(new int[] {cH, cW});
			grid[cH][cW] = true;
		}
	}

	boolean isNotIn(int cH, int cW) {
		return cH < 0 || cW < 0 || cH >= height || cW >= width;
	}
}
