/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadatatest;

/**
 * Common interface for the data structures implemented in this package.
 * 
 * @author Scott
 */
public interface DataInterface {
    public DataAddress insert(DataAddress data);
    public DataAddress delete(String firstName, String lastName);
    public DataAddress lookUp(String firstName, String lastName);
    public void traverse();
}
