//*******************************************************************
//  BinaryTreeNode.java                Authors:  Lewis/Chase
//
//  Represents a node in a binary tree with a left and right child.
//  Modified by Bob Wilson for CS210 Lab 9
//  11/04/2006
//*******************************************************************

public class BinaryTreeNode<T>
{
   protected T element;
   protected BinaryTreeNode<T> left, right;

   /*****************************************************************
     Creates a new tree node with the specified data.
   *****************************************************************/
   BinaryTreeNode (T obj) 
   {
      element = obj;
      left = null;
      right = null;
   }

   /*****************************************************************
     Returns the number of non-null children of this node.
     This method may be able to be written more efficiently.
   *****************************************************************/
   public int numChildren() 
   {
      int children = 0;

      if (left != null)
         children = 1 + left.numChildren();

      if (right != null)
         children = children + 1 + right.numChildren();

      return children;
   }
   
   public void setLeftChild(BinaryTreeNode<T> left)
   {
     this.left = left;
   }

   public BinaryTreeNode<T> getLeftChild()
   {
     return left;
   }
   
   public void setRightChild(BinaryTreeNode<T> right)
   {
     this.right = right;
   }
   
   public BinaryTreeNode<T> getRightChild()
   {
     return right;
   }
   
   public T getElement()
   {
     return element;
   }
}
