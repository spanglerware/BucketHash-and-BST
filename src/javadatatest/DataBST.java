/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadatatest;

/**
 * Binary Search Tree data structure with insert, delete, lookup, 
 * and traverse methods.
 * 
 * @author Scott
 */
public class DataBST implements DataInterface {
    private DataNode rootNode;
    
    //SDS constructor sets up root node of the tree
    public DataBST(DataAddress data) {
        rootNode = new DataNode(data);
    }

    //SDS insert will search the tree for where to add the new data value
    //using the name key of the data object. If an existing key matches the
    //new key value the old object is replaced and returned. Otherwise a 
    //null value is returned.
    public DataAddress insert(DataAddress data) {
        DataAddress result = null;
        DataNode current = rootNode;
        DataNode newNode = null;
        boolean found = false;
        int compare = 0;
        
        if (rootNode == null) {
            rootNode = new DataNode(data);
        } else {
            //SDS find where to insert
            while (!found) {
                compare = data.compareTo(current.getData().getNameKey());
                if (compare == 0) { //SDS replace and return old data
                    result = current.getData();
                    found = true;
                    current.setData(data);
                } else if (compare < 0) { //look into left path
                    if (current.getLeftChild() == null) {
                        found = true;
                        newNode = new DataNode(data);
                        current.setLeftChild(newNode);
                        newNode.setParent(current);
                    } else {
                        current = current.getLeftChild();
                    }
                } else { //SDS if compare > 0, look into right path
                    if (current.getRightChild() == null) {
                        found = true;
                        newNode = new DataNode(data);
                        current.setRightChild(newNode);
                        newNode.setParent(current);
                    } else {
                        current = current.getRightChild();
                    }
                }
            } //SDS end while loop    
        } //SDS end if (rootNode == null)
        
        return result;
    }
    
    //SDS delete searches the tree looking for a match to the data key.
    //If a match is found the node must be removed using different methods 
    //depending on how many children the node has. Once the node is removed 
    //the data value is returned. If no match is found a null value is returned.
    public DataAddress delete(String firstName, String lastName) {
        DataAddress result = null;
        DataNode findNode = null;
        DataNode parent = null;
        DataNode leftChild = null;
        DataNode rightChild = null;
        boolean root = false;
        
        if (rootNode == null) return null; //SDS tree is empty
        String key = (firstName + lastName).toUpperCase();
        
        findNode = locateNode(rootNode, key);
        if (findNode == rootNode) root = true; //SDS this is to prevent use of parent variable
        
        if (findNode != null) {
            System.out.println("findNode value = " + findNode.getData().getNameKey());
            result = findNode.getData();
            parent = findNode.getParent();
            leftChild = findNode.getLeftChild();
            rightChild = findNode.getRightChild();
            
            //SDS if node has no children remove node
            if (leftChild == null && rightChild == null) {
                if (!root && parent.getLeftChild() == findNode) {
                    parent.setLeftChild(null);
                } else if (!root && parent.getRightChild() == findNode) {
                    parent.setRightChild(null);
                }
                findNode = null;
            
            //SDS if node has two children, search for replacement, find inorder predecessor
            } else if (leftChild != null && rightChild!= null) {
                findNode.setData(findPredecessor(findNode));
            
            //SDS if node has one child, remove node and replace with child
            } else {
                if (!root && parent.getLeftChild() == findNode) {
                    if (leftChild != null) {
                        parent.setLeftChild(leftChild);
                        leftChild.setParent(parent);
                    } else {
                        parent.setLeftChild(rightChild);
                        rightChild.setParent(parent);
                    }
                } else if (!root && parent.getRightChild() == findNode) {
                    if (leftChild != null) {
                        parent.setRightChild(leftChild);
                        leftChild.setParent(parent);
                    } else {
                        parent.setRightChild(rightChild);
                        rightChild.setParent(parent);
                    }
                } else { //SDS if findNode is the root, replace with only child
                    if (leftChild != null) {
                        findNode = leftChild;
                    } else {
                        findNode = rightChild;
                    }
                    findNode.setParent(null);
                }
            }
        } //SDS end if (findNode != null)
        
        return result;
    }
    
    //SDS findPredecessor method used to remove node with two children. It 
    //locates the predecessor of the node to be removed, removes the predecessor 
    //node, and replaces data to be removed with data from predecessor.
    private DataAddress findPredecessor(DataNode startNode) {
        boolean found = false;
        DataAddress result = null;
        DataNode parent = null;
        DataNode current = startNode.getLeftChild();
        
        while (!found) {
            parent = current.getParent();
            if (current.getRightChild() != null) {
                current = current.getRightChild();
            } else if (current.getLeftChild() != null) {
                //SDS node only has one child, replace data from child then remove child
                result = current.getData();
                if (parent.getLeftChild() == current) {
                    current = current.getLeftChild();
                    parent.setLeftChild(current);
                } else {
                    current = current.getLeftChild();
                    parent.setRightChild(current);
                }                
                current.setParent(parent);
                found = true;
            } else { //no children, leaf node
                result = current.getData();
                found = true;
                if (parent.getLeftChild() == current) {
                    parent.setLeftChild(null);
                } else {
                    parent.setRightChild(null);
                }
            }
        } //SDS end while loop
        
        return result;
    }
    
    //SDS locateNode uses the data key to find a match in the tree structure. 
    //If a match is found the node is returned, otherwise a null value is returned.
    private DataNode locateNode(DataNode startNode, String key) {
        DataNode current = startNode;
        DataNode result = null;
        boolean found = false;
        int compare = 0;
        
        while (!found) {
            compare = current.getData().compareTo(key);
            if (compare == 0) { //SDS match found
                result = current;
                found = true;
            } else if (compare > 0) { //SDS search left path
                if (current.getLeftChild() == null) {
                    found = true; //SDS result not found but exit loop
                } else {
                    current = current.getLeftChild();
                }
            } else { //SDS if compare < 0, search right path
                if (current.getRightChild() == null) {
                    found = true; //SDS result not found but exit loop
                } else {
                    current = current.getRightChild();
                }
            }
        } //SDS end while
        
        return result;
    }
    
    //SDS lookUp method searches through the tree for a matching value to the
    //key provided. If a match is found the data object for that key is returned.
    //If no match is found a null value is returned.
    public DataAddress lookUp(String firstName, String lastName) {
        //SDS search using key
        DataAddress result = null;
        DataNode findNode = null;

        if (rootNode == null) return null; //SDS tree is empty
        String key = (firstName + lastName).toUpperCase();
        
        findNode = locateNode(rootNode, key);
        if (findNode != null) result = findNode.getData();
                
        return result;
    }
    
    //SDS traverse is used display all the values in the tree structure
    public void traverse() {
        traverse(rootNode, true, 0);
    }
    
    //SDS public traverse method calls the private method which is then
    //called recursively.
    private void traverse(DataNode node, boolean left, int height) {
        String path;
        
        if (left) {
            path = "left";
        } else {
            path = "right";
        }
        if (node != null) {
            System.out.println("current node is " + node.getData().getNameKey() + 
                " from " + path + " path" + " and height = " + height);
            traverse(node.getLeftChild(), true, height + 1);
            traverse(node.getRightChild(), false, height + 1);
        }
    }
    
    //SDS DataNode class defines the nodes used in the tree structure. Contains 
    //references to both children and parent.
    private class DataNode {
        private DataAddress data;
        private DataNode leftChild;
        private DataNode rightChild;
        private DataNode parent;
        
        public DataNode(DataAddress data) {
            this(data, null, null, null);
        }
        
        public DataNode(DataAddress data, DataNode leftNode, DataNode rightNode, 
                DataNode parent) {
            this.data = data;
            leftChild = leftNode;
            rightChild = rightNode;
            this.parent = parent;
        }

        public void setData(DataAddress data) {
            this.data = data;
        }
        
        public DataAddress getData() {
            return data;
        }

        public DataNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(DataNode leftChild) {
            this.leftChild = leftChild;
        }

        public DataNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(DataNode rightChild) {
            this.rightChild = rightChild;
        }

        public DataNode getParent() {
            return parent;
        }

        public void setParent(DataNode parent) {
            this.parent = parent;
        }
        
    } //SDS end of DataNode class
    
} //SDS end of DataBST class
