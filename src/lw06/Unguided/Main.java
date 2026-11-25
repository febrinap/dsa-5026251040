package lw06.Unguided;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        BinarySearchTree bst = new BinarySearchTree();

        if (read.hasNextLine()) {
            String[] numbers = read.nextLine().trim().split("\\s+");
            for (String num : numbers) {
                if (!num.isEmpty()) {
                    bst.insert(Integer.parseInt(num));
                }
            }
        }

        int target = read.nextInt();

        System.out.print("Preorder : ");
        bst.preOrder();
        System.out.print("Inorder : ");
        bst.inOrder();
        System.out.print("Postorder : ");
        bst.postOrder();

        System.out.println("Search " + target + " : " + bst.search(target));
        System.out.println("Nodes : " + bst.countNodes());
        System.out.println("Leaves : " + bst.countLeaves());

        read.close();
    }
}