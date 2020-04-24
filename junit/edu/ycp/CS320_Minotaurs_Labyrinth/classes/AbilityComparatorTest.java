package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class AbilityComparatorTest {
	Ability test = new Ability("test", "test", "test", "test", 1, 1);
	Ability test1 = new Ability("test1", "test1", "test1", "test1", 1, 1);
	ArrayList<Ability> list1 = new ArrayList();
	ArrayList<Ability> list2 = new ArrayList();
	ArrayList<Ability> list3 = new ArrayList();
	ArrayList<Ability> list4 = new ArrayList();
	AbilityComparator AC = new AbilityComparator();
	
	@Before
	public void setUp() {
		list1.add(test);
		list2.add(test);
		list3.add(test1);
		list4.add(test);
		list4.add(test1);
		
	}

	@Test
	public void testCompare() {
		assertEquals(1, AC.compare(list1, list2));
		assertEquals(-1, AC.compare(list1, list3));
		assertEquals(-1, AC.compare(list1, list4));
	}

}
