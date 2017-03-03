import java.util.LinkedList;
import java.util.Queue;

/* Basically I use AVL tree to achieve O(log(n)) time complexity of 4 operations.
 * Although the problem asked to implement a List, here I just name the structure as AVLTree. */
 public class AVLTree<T> {
    public AVLTreeNode<T> root = null;    
    
    public int size() {
      return size(root);
    }
    
    public int height() {
    	return height(root);
    }
    
    class AVLTreeNode<T> {           
        T val;
        int size = 1;		 // maintaining size for looking for a certain index
        int height = 1;      // maintaining height to keep balance  
        
        AVLTreeNode<T> left = null;   
        AVLTreeNode<T> right = null;  

        public AVLTreeNode(T val) {
            this.val = val;
        }
    }
    
    public void layerTraverse() {    // traverse layer by layer
      Queue<AVLTreeNode<T>> queue = new LinkedList<>();
      queue.add(root);
      System.out.println(" -- start layer traversal--");
      while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
          AVLTreeNode<T> temp = queue.poll();
          System.out.print("" + temp.val+" ");
          if (temp.left != null) {
            queue.add(temp.left);
          }
          if (temp.right != null) {
            queue.add(temp.right);
          }
        }
        System.out.println("");
      }
      System.out.println(" -- end layer traversal--");
    }
    
    public void inorder() {  // print inorder traversal
      inorderHelper(root);
    }
    
    private void inorderHelper(AVLTreeNode<T> tree) {
      if (tree == null) { 
        return;
      }
      
      inorderHelper(tree.left);
      System.out.print("" + tree.val + " ");
      inorderHelper(tree.right);
    }
    
    private int height(AVLTreeNode<T> tree) {
        if (tree != null)
            return tree.height;

        return 0;
    }
    
    private int size(AVLTreeNode<T> tree) {
      if (tree != null)
          return tree.size;

      return 0;
    }
    
    private AVLTreeNode<T> ll(AVLTreeNode<T> node2) { // left left rotation
      AVLTreeNode<T> node1 = node2.left;
      
      node2.left = node1.right;
      node1.right = node2;
      
      update(node2);
      
      node1.height = Math.max(height(node1.left), node2.height) + 1;
      node1.size = size(node1.left) + size(node2) + 1;
      
      return node1;
  }
    
    private AVLTreeNode<T> rr(AVLTreeNode<T> node1) { // right right rotation
      AVLTreeNode<T> node2 = node1.right;
      node1.right = node2.left;
      node2.left = node1;

      update(node1);
      
      node2.height = Math.max(height(node2.right), node1.height) + 1;
      node2.size = size(node1) + size(node2.right) + 1;
      
      return node2;
  }
    
    private AVLTreeNode<T> lr(AVLTreeNode<T> node3) { // left right rotation
      node3.left = rr(node3.left);
      return ll(node3);
  }
    
    private AVLTreeNode<T> rl(AVLTreeNode<T> node1) {  // right left rotation
      node1.right = ll(node1.right);
      return rr(node1);
  }
    
    void add(int index, T val) {  // The second required API: add(int index, Object obj)
      root = insert(root, index, val);
    }
    
    void add(T val) {   // The first required API: add(Object obj)
      root = insert(root, size(root), val);
    }
    
    private AVLTreeNode<T> insert(AVLTreeNode<T> tree, int index, T val) {
    	if (tree == null) {
    		tree = new AVLTreeNode<T>(val);
            return tree;
        } 
        
        if (index <= size(tree.left)) {    
        	tree.left = insert(tree.left, index, val);

            if (height(tree.left) - height(tree.right) == 2) {
            	if (height(tree.left.left) > height(tree.left.right)) {
            		tree = ll(tree);
            	} else {
                    tree = lr(tree);
            	}
             }
        } else {    
        	tree.right = insert(tree.right, index - size(tree.left) - 1, val);
                
            if (height(tree.right) - height(tree.left) == 2) {
            	if (height(tree.right.right) > height(tree.right.left)) {
            		tree = rr(tree);
            	} else {
            		tree = rl(tree);
            	}
            }
        }

        update(tree);

        return tree;
    }
    
    public T get(int index) {  // same as List.get()
    	AVLTreeNode<T> node = getNode(index);
    	return node.val;
    }
    
    private AVLTreeNode<T> getNode(int index) {
      AVLTreeNode<T> curt = root;
      
      while (index != size(curt.left)) {
        if (index < size(curt.left)){ 
          curt = curt.left;
        } else {
          index = index - size(curt.left) - 1;
          curt = curt.right;
        }
      }
      
      return curt;
    }
    
    void set(int index, T val) {  // The fourth required API: set(int index, Object obj)
      AVLTreeNode<T> node = getNode(index);
      node.val = val;
    }
    
    void remove(int index) {  // The third required API: remove(int index)
      root = remove(root, index);
    }
    
    private AVLTreeNode<T> remove(AVLTreeNode<T> tree, int index) {

    	if (index < size(tree.left)) {        
            tree.left = remove(tree.left, index);
            
            update(tree);
            
            if (height(tree.right) - height(tree.left) == 2) {
                if (height(tree.right.left) > height(tree.right.right)) {
                    tree = rl(tree);
                } else {
                	tree = rr(tree);
                }
            }
      } else if (index > size(tree.left)) {    
          tree.right = remove(tree.right, index - size(tree.left) - 1);
          
          update(tree);
          
          if (height(tree.left) - height(tree.right) == 2) {
              if (height(tree.left.right) > height(tree.left.left)) {
                  tree = lr(tree);
              } else {
                  tree = ll(tree);
              }
          }
      } else if ((tree.left != null) && (tree.right != null)) {
    	  if (height(tree.left) > height(tree.right)) {
    		  AVLTreeNode<T> leftMax = tree.left;
              while (leftMax.right != null) {
            	  leftMax = leftMax.right;
              }
              tree.val = leftMax.val;
                  
              if (leftMax == tree.left) {
            	  tree.left = null;
              } else {
            	  removeMax(tree.left, leftMax);
              }
                  
              update(tree);
                  
          } else {
        	  AVLTreeNode<T> rightMin = tree.right;
              while (rightMin.left != null) {
            	  rightMin = rightMin.left;
              }
              tree.val = rightMin.val;
                
              if (rightMin == tree.right) {
            	  tree.right = null;
              } else {
            	  removeMin(tree.right, rightMin);
              }
                
              update(tree);
          }
      } else {
    	  tree = (tree.left != null) ? tree.left : tree.right;
      }
      
      return tree;
  }
    
    private void update(AVLTreeNode<T> tree) {  // update the height attribute and size attribute of a node
      tree.height = Math.max(height(tree.left), height(tree.right)) + 1;
      tree.size = size(tree.left) + size(tree.right) + 1;
    }
      
     private void removeMax(AVLTreeNode<T> tree, AVLTreeNode<T> leftMax) {  // helper function for remove()
       if (tree.right == leftMax) {
         tree.right = null;
       } else {
         removeMax(tree.right, leftMax);
       }
       
       update(tree);
     }
     
     private void removeMin(AVLTreeNode<T> tree, AVLTreeNode<T> rightMin) { // helper function for remove()
       if (tree.left == rightMin) {
         tree.left = null;
       } else {
         removeMin(tree.left, rightMin);
       }
       
       update(tree);
     }
    
    
 }