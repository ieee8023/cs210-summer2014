//*******************************************************************
//  LinkedBinaryTree.java               Authors:  Lewis/Chase
//
//  Implements the BinaryTreeADT interface
//  Modified by Bob Wilson for CS210 Lab 9
//  11/04/2006
//*******************************************************************
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T>
{
   protected int count;
   protected BinaryTreeNode<T> root; 

   /*****************************************************************
     Creates an empty binary tree.
   *****************************************************************/
   public LinkedBinaryTree() 
   {
      count = 0;
      root = null;
   }

   /*****************************************************************
     Creates a binary tree with the specified element as its root.
   *****************************************************************/
   public LinkedBinaryTree (T element) 
   {
      count = 1;
      root = new BinaryTreeNode<T> (element);
   }

   /*****************************************************************
     Constructs a binary tree from the two specified binary trees.
   *****************************************************************/
   public LinkedBinaryTree (T element, LinkedBinaryTree<T> leftSubtree,
                            LinkedBinaryTree<T> rightSubtree) 
   {
      root = new BinaryTreeNode<T> (element);
      count = 1;
      
      if (leftSubtree != null)
      {
         count = count + leftSubtree.size();
         root.left = leftSubtree.root;
      }
      else
         root.left = null;
      
      if (rightSubtree !=null)
      {
         count = count + rightSubtree.size();
         root.right = rightSubtree.root;
      }
      else
         root.right = null;
   }
   
   /*****************************************************************
     Removes the left subtree of this binary tree.
   *****************************************************************/
   public void removeLeftSubtree() 
   {
      if (root.left != null)
         count = count - root.left.numChildren() - 1;
      root.left = null;
   }
   
   /*****************************************************************
     Removes the right subtree of this binary tree.
   *****************************************************************/
   public void removeRightSubtree() 
   {

   }
   
   /*****************************************************************
     Deletes all nodes from this binary tree.
   *****************************************************************/
   public void removeAllElements() 
   {
   }
   
   /*****************************************************************
     Returns true if this binary tree is empty and false otherwise.
   *****************************************************************/
   public boolean isEmpty() 
   {
     return count != 0;
   }

   /*****************************************************************
     Returns true if this binary tree is empty and false otherwise.
   *****************************************************************/
   public int size() 
   {
     return count;
   }
   
   /*****************************************************************
     Returns true if this tree contains an element that matches the
     specified target element and false otherwise.
   *****************************************************************/
   public boolean contains (T targetElement) 
   {
     return false;
   }
   
   /*****************************************************************
     Returns a reference to the specified target element if it is
     found in this binary tree.  Returns a null reference value if
     the specified target element is not found in the binary tree.
   *****************************************************************/
   public T find(T targetElement)
   {
      BinaryTreeNode<T> current = findAgain( targetElement, root );
      if (current != null)
        return (current.element);
      return null;
   }

   /*****************************************************************
     Returns a reference to the specified target element if it is
     found in this binary tree.
   *****************************************************************/
   private BinaryTreeNode<T> findAgain(T targetElement, 
                                       BinaryTreeNode<T> next)
   {
      if (next == null)
         return null;
      
      if (next.element.equals(targetElement))
         return next;
      
      BinaryTreeNode<T> temp = findAgain(targetElement, next.left);
      
      if (temp == null)
         temp = findAgain(targetElement, next.right);
      
      return temp;
   }
   
   /*****************************************************************
     Returns a string representation of this binary tree.
   *****************************************************************/
   public String toString() 
   {
     return "NYI";
   }

   /*****************************************************************
     Performs an inorder traversal on this binary tree by calling an
     overloaded, recursive inorder method that starts with
     the root.
   *****************************************************************/
   public Iterator<T> iteratorInOrder()
   {
     return null;
   }


   /*****************************************************************
     Performs an preorder traversal on this binary tree by calling 
     an overloaded, recursive preorder method that starts with
     the root.
   *****************************************************************/
   public Iterator<T> iteratorPreOrder() 
   {
     return null;
   }

   /*****************************************************************
     Performs an postorder traversal on this binary tree by calling
     an overloaded, recursive postorder method that starts
     with the root.
   *****************************************************************/
   public Iterator<T> iteratorPostOrder() 
   {
     return null;
   }

   /*****************************************************************
     Performs a levelorder traversal on this binary tree, using a
     java.util.LinkedList templist.
   *****************************************************************/
   public Iterator<T> iteratorLevelOrder() 
   {
     // write your code here
   }
   
   public void rotateRight()
   {
     // write your code here
   }
   
   public void rotateLeft()
   {
     // write your code here
   }
}
