package com.target;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class BarrenLandAnalyzerTest {
	
	@Test
	public void testSampleInput1() {
		BarrenLandAnalyzer a = new BarrenLandAnalyzer(Arrays.asList(new int[] {0, 292, 399, 307}));
		List<Integer> fLandAreas = a.findFertileLands();
		assertEquals(2, fLandAreas.size());
		assertEquals(116800, fLandAreas.get(0));
		assertEquals(116800, fLandAreas.get(1));
	}
	
	@Test
	public void testSampleInput2() {
		BarrenLandAnalyzer a = new BarrenLandAnalyzer(Arrays.asList(
				new int[] {48, 192, 351, 207},
				new int[] {48, 392, 351, 407},
				new int[] {120, 52, 135, 547},
				new int[] {260, 52, 275, 547}));
		List<Integer> fLandAreas = a.findFertileLands();
		assertEquals(2, fLandAreas.size());
		assertEquals(22816, fLandAreas.get(0));
		assertEquals(192608, fLandAreas.get(1));
	}
	
	@Test
	public void testWholeAreaBarren() {
		BarrenLandAnalyzer a = new BarrenLandAnalyzer(Arrays.asList(new int[] {0, 0, 399, 599}));
		List<Integer> fLandAreas = a.findFertileLands();
		assertEquals(0, fLandAreas.size());
	}
	
	@Test
	public void testTopRightInputOutsideBoundary() {
		assertThrows(IllegalArgumentException.class, () -> new BarrenLandAnalyzer(Arrays.asList(new int[] {0, 0, 400, 600})));
	}
	
	@Test
	public void testBottomLeftInputOutsideBoundary() {
		assertThrows(IllegalArgumentException.class, () -> new BarrenLandAnalyzer(Arrays.asList(new int[] {-1, -1, 200, 300})));
	}
	
	@Test
	public void testInvalidBottomLeftCoordinateWrtTopRight() {
		assertThrows(IllegalArgumentException.class, () -> new BarrenLandAnalyzer(Arrays.asList(new int[] {200, 300, 100, 200})));
	}

	@Test
	public void testNoInput() {
		assertThrows(IllegalArgumentException.class, () -> new BarrenLandAnalyzer(null));
		assertThrows(IllegalArgumentException.class, () -> new BarrenLandAnalyzer(null));
	}
}
