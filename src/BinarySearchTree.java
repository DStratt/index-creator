import java.io.PrintWriter;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 * 
 * Additional methods added by DStratt: printTree(), FindMatchingNode(),
 * and writeTree()
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{

  /** The tree root. */
	private BinaryNode<AnyType> root;
  
	/**
   * Construct the tree.
   */
  public BinarySearchTree() { root = null; }

  /**
   * Insert into the tree; duplicates are ignored.
   * @param x the item to insert.
   */
  public void insert(AnyType x) { root = insert( x, root ); }

  /**
   * Internal method to insert into a subtree.
   * @param x the item to insert.
   * @param t the node that roots the subtree.
   * @return the new root of the subtree.
   */
  private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t)
  {
      if(t == null)
          return new BinaryNode<>(x, null, null);
        
      int compareResult = x.compareTo(t.element);
            
      if(compareResult < 0) // value in CURRENT root 't' > new value
          t.left = insert(x, t.left);
      else if(compareResult > 0) // value in CURRENT root 't' < new value
          t.right = insert(x, t.right);
      else
          ;  // Duplicate; do nothing
      return t;
  }
    
  /**
   * Remove from the tree. Nothing is done if x is not found.
   * @param x the item to remove.
   */
  public void remove(AnyType x)
  {
      root = remove(x, root);
  }

  /**
   * Internal method to remove from a subtree.
   * @param x the item to remove.
   * @param t the node that roots the subtree.
   * @return the new root of the subtree.
   */
  private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t)
  {
      if(t == null)
          return t;   // Item not found; do nothing
            
      int compareResult = x.compareTo(t.element);
            
      if(compareResult < 0)
          t.left = remove(x, t.left);
      else if(compareResult > 0)
          t.right = remove(x, t.right);
      else if(t.left != null && t.right != null) // Two children
      {
        	// move smallest value into this place!
        	t.element = findMin(t.right).element; 
        	// now delete this node
          t.right = remove(t.element, t.right); 
      }
      else
          t = (t.left != null) ? t.left : t.right;
      return t;
  }

  /**
   * Find the smallest item in the tree.
   * @return smallest item or null if empty.
   */
  public AnyType findMin()
  {
      if(isEmpty( ))
          throw new UnderflowException("Cound not findMin");
      return findMin(root).element;
  }

  /**
   * Internal method to find the smallest item in a subtree
   * @param t the node that roots the subtree.
   * @return node containing the smallest item.
   */
  private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t)
  {
      if(t == null)
          return null;
      else if(t.left == null)
          return t;
      return findMin(t.left);
  }
        
  /**
   * Find an item in the tree.
   * @param x the item to search for.
   * @return true if not found.
   */
  public boolean contains(AnyType x) { return contains(x, root); }

  /**
   * Internal method to find an item in a subtree.
   * @param x is item to search for.
   * @param t the node that roots the subtree.
   * @return node containing the matched item.
   */
  private boolean contains(AnyType x, BinaryNode<AnyType> t)
  {
      if(t == null)
          return false;
            
      int compareResult = x.compareTo(t.element);
            
      if(compareResult < 0)
          return contains(x, t.left);
      else if(compareResult > 0)
          return contains(x, t.right);
      else
          return true;    // Match
  }

  /**
   * Make the tree logically empty.
   */
  public void makeEmpty() { root = null; }

  /**
   * Test if the tree is logically empty.
   * @return true if empty, false otherwise.
   */
  public boolean isEmpty() { return root == null; }
 
  /**
   * Internal method to compute height of a subtree.
   * @param t the node that roots the subtree.
   */
  private int height(BinaryNode<AnyType> t)
  {
      if(t == null)
          return -1;
      else
          return 1 + Math.max(height(t.left), height(t.right));    
  }
    
  //START of additional methods added
  /**
   * Print the elements in the tree in order. If tree is 
   * empty, print message.
   * Bootstrap method added by DStratt
   */
  public void printTree()
  {
    	if(isEmpty())
    		System.out.println("BST is empty");
    	else
        printTree(root);
  }
    
  /**
   * Internal method to print the elements of the tree in order.
   * @param t the node that roots the subtree.
   * Worker method added by DStratt
   */
  private void printTree(BinaryNode<AnyType> t)
  {
    	if(t != null)
    	{
    		printTree(t.left);
    		System.out.println(t.element.toString());
    		printTree(t.right);
      }
  }    
    
  /**
   * Write the elements in the tree, in order, to an output
   * file. If tree is empty, write appropriate message to file.
   * @param output
   * 		PrintWriter object to receive output
   * Bootstrap method added by DStratt
   */
  public void writeTree(PrintWriter output)
  {
    	if(isEmpty())
    		output.println("Tree is empty");
    	else
    	 	writeTree(root, output);	
  }
    
  /**
   * Internal method to write the elements in the tree, in order,
   * to an output file.
   * @param t the node that roots the subtree
   * @param output
   * 		PrintWriter object to receive output
   * Worker method added by DStratt
   */
  private void writeTree(BinaryNode<AnyType> t, PrintWriter output)
  {
    	if(t != null)
    	{
    		writeTree(t.left, output);
    		output.println(t.element.toString());
    		writeTree(t.right, output);
    	}
  }
    
  /**
   * Find a node matching the given parameter.
   * @param x is the item to search for.
   * @return node containing the matching element.
   * Bootstrap method added by DStratt
   */
  public AnyType findMatchingNode(AnyType x)
  { 
    	if(isEmpty())
    		throw new UnderflowException("Could not getMatchingNode");
    	return findMatchingNode(x, root).element;	
  }
    
  /**
   * Internal method to find a node that matches the given parameter.
   * @param x is the item to search for.
   * @param t the node that roots the subtree.
   * @return node containing the matching element.
   * Worker method added by DStratt
   */
  private BinaryNode<AnyType> findMatchingNode(AnyType x, BinaryNode<AnyType> t)
  {
    	if(t == null)
    		return null;
    	
    	int compareResult = x.compareTo(t.element);
    	
    	if(compareResult == 0) //match
    		return t;
    	else if(compareResult < 0) //need to search left
        	return findMatchingNode(x, t.left);
        else //need to search right
        	return findMatchingNode(x, t.right);
  }
    
  //END of additional methods added    
    
  // Basic node stored in unbalanced binary search trees
  static class BinaryNode<AnyType>
  {
        // Constructors
        BinaryNode(AnyType theElement)
        {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt)
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        
        public AnyType getElement()
        { return this.element; }
        
        public void setElement(AnyType theElement)
        { this.element = theElement; }                
  }

  // Test program
  public static void main(String [ ] args)
  {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println("Checking... (no more output means success)");

        for(int i = GAP; i != 0; i = (i + GAP) % NUMS)
            t.insert(i);
  }
  
}
