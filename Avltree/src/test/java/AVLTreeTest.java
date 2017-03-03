import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;


public class AVLTreeTest {
	
	/*
	 * In order to show the test process to you, here I print most information.
	 */
	
	@Test
	public void stringTest() {
		AVLTree<String> list = new AVLTree<>();
		String[] strings = {"zero","one", "two", "three", "four", "five"};
		for (String string : strings) {
			list.add(string);
		}
		assertEquals((String)list.get(3), "three");
		list.inorder();
		System.out.println("");
	}
	
	@Test
	public void balancedTest1() {
		AVLTree<Integer> list = new AVLTree<>(); // This list is actually an AVL tree. 
		Random rd = new Random();
		int num = 10000000;
		for (int i = 0; i < 10000000; i++) {
			list.add(rd.nextInt(1000));
		}
		
		assertTrue(list.height() < 30);
		System.out.println("the number of elements:" + num);
		System.out.println("height of root:" + list.height());
		System.out.println("height of root.left:" + list.root.left.height);
		System.out.println("height of root.right:" + list.root.right.height);
		System.out.println("");
	}
	
	@Test
	public void balancedTest2() {
		AVLTree<Integer> list = new AVLTree<>(); // This list is actually an AVL tree. 
		Random rd = new Random();
		int num = 1000000;
		for (int i = 0; i < 1000000; i++) {
			list.add(rd.nextInt(1000));
		}
		
		assertTrue(list.height() < 25);
		System.out.println("the number of elements:" + num);
		System.out.println("height of root:" + list.height());
		System.out.println("height of root.left:" + list.root.left.height);
		System.out.println("height of root.right:" + list.root.right.height);
		System.out.println("");
	}
	
	@Test
	public void addTest() {
		AVLTree<Integer> list = new AVLTree<>();  
		Random rd = new Random();
		int[] array = new int[1000];
		for (int i = 0; i < 1000; i++) {
			array[i] = rd.nextInt(100); 
		}
		array[100] = 200; // all elements are in [0, 100] except for the 100th elements is 200
		
		for (int num : array) {
			list.add(num); // add 1000 elements into the list
		}
		
		System.out.println("The element on index 100 is:" + list.get(100));
		assertEquals(200, (int)list.get(100));
		
		System.out.println("");
	}
	
	@Test
	public void addTestWithIndex() {
		AVLTree<Integer> list = new AVLTree<>();  
		Random rd = new Random();
		
		for (int i = 0; i < 1000; i++) {
			list.add(rd.nextInt(1000)); 
		}
		
		int addNum = 100;
		
		for (int i = 0; i < 5; i++) {
			int index = rd.nextInt(1000);
			list.add(index, addNum);
			System.out.println("The size of the whole list is: " + list.size());
			System.out.println("The " + index + "th number is: " + list.get(index));
			assertEquals(addNum, (int)list.get(index));
		}
		
		System.out.println("");
	}
	
	@Test
	public void setTest() {
		AVLTree<Integer> list = new AVLTree<>();  
		Random rd = new Random();
		
		for (int i = 0; i < 1000; i++) {
			list.add(rd.nextInt(1000)); 
		}
		
		int setNum = 100;
		
		for (int i = 0; i < 5; i++) {
			int index = rd.nextInt(1000);
			list.set(index, setNum);
			System.out.println("The " + index + "th number is: " + list.get(index));
			assertEquals(setNum, (int)list.get(index));
		}
		
		System.out.println("");
	}
	
	@Test
	public void removeTest() {
		AVLTree<Integer> list = new AVLTree<>();  
		Random rd = new Random();
		
		for (int i = 0; i < 1000; i++) {
			list.add(rd.nextInt(1000)); 
		}
		
		for (int i = 0; i < 3; i++) {
			int index = rd.nextInt(995);
			int num1 = list.get(index + 1);
			System.out.println("Before remove(), the size of the whole list: " + list.size());
			for (int j = index - 1; j <= index + 1; j++) {
				System.out.println(j + "th element: " + list.get(j));
			}
			
			list.remove(index);
			
			int num2= list.get(index);
			assertEquals(num1, num2);
			System.out.println("After remove "+index+ "th element, the size of the whole list: " + list.size());
			for (int j = index - 1; j <= index + 1; j++) {
				System.out.println(j + "th element: " + list.get(j));
			}
			
		}
		
		System.out.println("");
	}
	
	

}
















