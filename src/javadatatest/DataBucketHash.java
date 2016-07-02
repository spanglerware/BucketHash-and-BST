/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadatatest;

/**
 * Bucket Hash data structure with insert, delete, lookup, 
 * and traverse methods.
 * 
 * @author Scott
 */
public class DataBucketHash implements DataInterface {
    private HashNode[] hashTable;
    private static final int NUMBER_BUCKETS = 13;
    
    public DataBucketHash() {
        hashTable = new HashNode[NUMBER_BUCKETS];
    }
    
    //SDS insert will hash the key from the data object to find the index
    //for the hash table. Then it will insert a new node into the corresponding
    //bucket of the array. Since duplicates are allowed, all return values
    //will be null.
    public DataAddress insert(DataAddress data) {
        HashNode current = null;
        HashNode newNode =  new HashNode(data);
        DataAddress result = null;
        String key = data.getNameKey();
        int hash = hashIndex(key);
        System.out.println("hash key = " + hash);
        
        if (hashTable[hash] == null) {
            hashTable[hash] = newNode;
        } else {
            //SDS insert new node in front of current node
            current = hashTable[hash];
            newNode.setNextNode(current);
            hashTable[hash] = newNode;
            current.setPreviousNode(newNode);
        }
        
        return result;
    }
    
    //SDS delete will first locate the corresponding hash bucket using a hash
    //of the data key. Then it will search the nodes in the bucket for a match.
    //If a match is found the node is removed and returned as a result.
    //If no match is found, a null value is returned.
    public DataAddress delete(String firstName, String lastName) {
        String key = (firstName + lastName).toUpperCase();
        int hash = hashIndex(key);
        HashNode current = hashTable[hash];
        HashNode nodeBefore = null;
        HashNode nodeAfter = null;
        boolean found = false;
        DataAddress result = null;
        int compare = 0;
        
        while(!found) {
            if (current == null) return null;
            compare = current.getData().compareTo(key);
            if (compare == 0) {  //SDS a match has been found
                result = current.getData();
                nodeAfter = current.getNextNode();
                if (nodeBefore != null) {
                    nodeBefore.setNextNode(nodeAfter);
                } else {
                    hashTable[hash] = nodeAfter;
                }
                if (nodeAfter != null) nodeAfter.setPreviousNode(nodeBefore);
                current = null;
                found = true;
            } else { //SDS no match found, go to next node
                nodeBefore = current;
                current = current.getNextNode();
            }
        }
        
        return result;
    }

    //SDS lookup will locate the hash bucket using a hash of the data key.
    //The method then searches the nodes of the bucket for a match. If a 
    //match is found the data object is returned. If no match is found a 
    //null value is returned.
    public DataAddress lookUp(String firstName, String lastName) {
        DataAddress result = null;
        String key = (firstName + lastName).toUpperCase();
        int hash = hashIndex(key);
        HashNode current = hashTable[hash];
        boolean found = false;
        int compare = 0;
        
        while(!found) {
            if (current == null) return null;
            compare = current.getData().compareTo(key);
            if (compare == 0) { //SDS a match has been found
                result = current.getData();
                found = true;
            } else { //SDS no match, go to next node
                current = current.getNextNode();
            }
        }
        
        return result;
    }
    
    //SDS traverse loops through the hash table and all the nodes in each 
    //bucket to display the current contents.
    public void traverse() {
        HashNode current = null;
        boolean done = false;
        
        for (int i = 0; i < NUMBER_BUCKETS; i++) {
            current = hashTable[i];
            done = false;
            if (current != null) {
                while (!done) {
                    if (current == null) {
                        done = true;
                    } else {
                        System.out.println("index = " + i + ", key = " + current.getData().getNameKey());
                        current = current.getNextNode();
                    }
                }
            }
        } //SDS end of for loop
    }
    
    //SDS hashIndex function ensures the index is positive and within the 
    //limits of the hash table.
    private int hashIndex(String key) {
        int index = key.hashCode() % NUMBER_BUCKETS;
        if (index < 0) index = index + NUMBER_BUCKETS;
        return index;
    }
    
    //SDS HashNode class defines the nodes used in the hash buckets. Set up
    //as double linked nodes.
    private class HashNode {
        private DataAddress data;
        private HashNode nextNode;
        private HashNode previousNode;

        public HashNode(DataAddress data) {
            this.data = data;
            nextNode = null;
            previousNode = null;
        }
        
        public DataAddress getData() {
            return data;
        }

        public void setData(DataAddress data) {
            this.data = data;
        }

        public HashNode getNextNode() {
            return nextNode;
        }

        public void setNextNode(HashNode nextNode) {
            this.nextNode = nextNode;
        }

        public HashNode getPreviousNode() {
            return previousNode;
        }

        public void setPreviousNode(HashNode previousNode) {
            this.previousNode = previousNode;
        }
    } //SDS end of HashNode class
    
} //SDS end of DataBucketHash class
