import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CountryDB {
	//main method
	public static void main(String[] args) {
		
		
		ArrayList<Country> countryList = new ArrayList<>();
		

		File file = new File("countries.txt");
		Scanner readFile = null;
		String code="";
		String name="";
		String line;
		Double area;
		
		try {
			readFile = new Scanner(file);
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found.");
			System.exit(0);
		}
		while(readFile.hasNext()) {
			line = readFile.nextLine();
			code = line.split("\\|")[0];
			name = line.split("\\|")[1];
			area = Double.parseDouble(line.split("\\|")[2]);
			
			Country c = new Country(code,name,area);
			countryList.add(c);
		}
		readFile.close();
		
		
		BST<Country> countryBST = new BST<>();
		for(int i=0;i<countryList.size();i++) {
			performance_insert[0][i] = countryBST.insert(countryList.get(i));
		}

		
		System.out.println("Sorted Country List");
		System.out.printf("%-11s\t\t%-25s\t\t\t\t\t\t%-20s\n", "Code", "Name", "Area(sq.Km)");
		countryBST.inorder();
		System.out.println();
		System.out.println("Number of leaf Nodes: " + countryBST.leafNodes());
		System.out.println("Height of the tree: " + countryBST.height());
		System.out.println("Is the tree balanced? " + countryBST.isBalanced());
		System.out.println();
		
		Random r = new Random();
		
		for(int i=0;i<10;i++) {
			int randomIndex = r.nextInt(countryList.size()-1);
			Country randomName = countryList.get(randomIndex);
			performance_search[0][i] = countryBST.searchIterations(randomName);
			performance_delete[0][i] =	countryBST.delete(randomName);
		}
	//	---------------------------------------------------------------------------
		java.util.Collections.shuffle(countryList);
		countryBST.clear();
		
		for(int i=0;i<countryList.size();i++) {
			performance_insert[1][i] = countryBST.insert(countryList.get(i));
		}

		System.out.println("Shuffled Country List");
		System.out.printf("%-11s\t\t%-25s\t\t\t\t\t\t%-20s\n", "Code", "Name", "Area(sq.Km)");
		countryBST.inorder();
		System.out.println();
		System.out.println("Number of leaf Nodes: " + countryBST.leafNodes());
		System.out.println("Height of the tree: " + countryBST.height());
		System.out.println("Is the tree balanced? " + countryBST.isBalanced());
		System.out.println();
		
		for(int i=0;i<10;i++) {
			int randomIndex = r.nextInt(countryList.size()-1);
			Country randomName = countryList.get(randomIndex);
			performance_search[1][i] = countryBST.searchIterations(randomName);
			performance_delete[1][i] =	countryBST.delete(randomName);
		}
		
		System.out.printf("%-20s\t\t\t\t%-20s\n", "Sorted List", "Shuffled List");
		System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\n", "Insert", "Search","Delete","Insert", "Search","Delete");
		for(int i=0;i<10;i++) {
			int randomIndex = r.nextInt(countryList.size()-1);
				System.out.printf("%-10s\t%-10s\t%-10d\t%-10s\t%-10s\t%-10d\n",performance_insert[0][randomIndex], performance_search[0][i], performance_delete[0][i],
						performance_insert[1][randomIndex], performance_search[1][i], performance_delete[1][i]);
			}
		}
	
	
	static int[][] performance_insert = new int[2][228];
	static int[][] performance_search = new int[2][10];
	static int[][] performance_delete = new int[2][10];

	//After step f,  there is only 1 leaf node and the height is 228, or the full length of the countryList. This tree is not balanced because it is a linear arrangement, and a single branch.
	//After step i, there are ~78 leaf nodes, and ~15 branches. These numbers vary from run to run because the BST created for step i is built with a shuffled countryList. The first element
	//inserted into the new BST will vary from run to run depending on the random shuffling. The BST is built out from there with varying dimensions for the height and number of leaf nodes.
	//Both BSTs are unbalanced meaning that the  height  of  the  left  subtree  and  the height  of  the  right  subtree  differ  by  more than 1.
	
	//insert(),search(),and delete() all perform better for the second BST created from the shuffled list. This makes sense considering that the second
	//BST contains many more branches where as the first BST contains only a single branch. In the first subtree, insert(),search(),and delete() have to iterate through the entire BST which explains
	//the large number of iterations present in all of these categories for the first BST. 
	//search()and delete() show no variation from one another in their respective BSTs because the same iterative process is used to search and delete an element. 
}
