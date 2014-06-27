import java.util.Iterator;

public class LevelTraverse
{
  public static void main(String [] args)
  {
    // set up the test case binary tree
    LinkedBinaryTree<String> greatgrandchild1 = new LinkedBinaryTree<String>("Greatgrandchild1");
    LinkedBinaryTree<String> greatgrandchild2 = new LinkedBinaryTree<String>("Greatgrandchild2");
    LinkedBinaryTree<String> grandchild1 = new LinkedBinaryTree<String>("Grandchild1");
    LinkedBinaryTree<String> grandchild2 = new LinkedBinaryTree<String>("Grandchild2",
                                           greatgrandchild1, null);
    LinkedBinaryTree<String> grandchild3 = new LinkedBinaryTree<String>("Grandchild3");
    LinkedBinaryTree<String> grandchild4 = new LinkedBinaryTree<String>("Grandchild4",
                                           greatgrandchild2, null);
    LinkedBinaryTree<String> child1 = new LinkedBinaryTree<String>("Child1", grandchild1, grandchild2);
    LinkedBinaryTree<String> child2 = new LinkedBinaryTree<String>("Child2", grandchild3, grandchild4);
    LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>("Root",child1, child2);
    
    Iterator<String> itr = tree.iteratorLevelOrder();
    
    while (itr.hasNext())
      System.out.println(itr.next());
  }
}