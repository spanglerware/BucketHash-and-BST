/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadatatest;

/**
 * Class for testing bucket hash and binary tree structures.
 * 
 * @author Scott
 */
public class JavaDataTest {

    public static void main(String[] args) {
        //SDS load test data
        DataAddress[] testData = loadTestCases();
        
        //SDS test DataBST
        runTestCases(testData, true);
        
        //SDS test DataHashBucket
        runTestCases(testData, false);
    }
    
    public static void runTestCases(DataAddress[] testData, boolean tree) {
        DataInterface runObject;
        DataAddress result = null;
        
        //SDS set the run object based on class we want to test
        if (tree) {
            runObject = new DataBST(testData[0]);
        } else {
            runObject = new DataBucketHash();
            runObject.insert(testData[0]);
        }
        
        //SDS insert first data addresses
        for (int i = 1; i < 10; i++) {
            result = runObject.insert(testData[i]);
            System.out.println("result of " + i + " is " + result);
        }

        //runObject.traverse();
        
        //SDS perform lookup and delete functions
        result = runObject.lookUp("Pat", "Jones");
        System.out.println("result of Pat Jones lookup is " + result);
        result = runObject.lookUp("Billy", "Kidd");
        System.out.println("result of Billy Kidd lookup is " + result);
        result = runObject.delete("John", "Doe");
        System.out.println("result of John Doe delete is " + result);
        
        //SDS insert additional data
        for (int i = 10; i < 16; i++) {
            result = runObject.insert(testData[i]);
            System.out.println("result of " + i + " is " + result);
        }
        
        //runObject.traverse();
        
        //SDS lookup and delete tests
        result = runObject.lookUp("Jack", "Jones");
        System.out.println("result of Jack Jones lookup is " + result);
        result = runObject.lookUp("Nadezhda", "Kanachekhovskaya");
        System.out.println("result of Nadezhda lookup is " + result);
        result = runObject.delete("Jill", "Jones");
        System.out.println("result of Jill Jones delete is " + result);
        result = runObject.delete("John", "Doe");
        System.out.println("result of John Doe delete is " + result);
        result = runObject.lookUp("Jill", "Jones"); //should not be found
        System.out.println("result of Jill Jones lookup is " + result);
        result = runObject.lookUp("John", "Doe"); //should not be found
        System.out.println("result of John Doe lookup is " + result);
        
        //SDS display all remaining data objects in the structure
        runObject.traverse();      

        
        //SDS additional tests to check all possible use cases
/*
        result = runObject.delete("Pat", "Jones");
        System.out.println("result of Pat Jones delete is " + result);
        runObject.traverse();
        
        result = runObject.delete("Jane", "Williams");
        System.out.println("result of Jane Williams delete is " + result);        
        runObject.traverse();
        
        result = runObject.delete("Bob", "Smith");
        System.out.println("result of Bob Smith (root) delete is " + result);        
        runObject.traverse();
  */
    } //SDS end of runTestCases method
    
    
    public static DataAddress[] loadTestCases() {
        //SDS set up array containing the provided test cases
        DataAddress[] dataArray = new DataAddress[16];
        dataArray[0] = new DataAddress("Bob", "Smith", "555-235-1111", 
                "bsmith@somewhere.com");
        dataArray[1] = new DataAddress("Jane", "Williams", "555-235-1112",
                "jw@something.com");
        dataArray[2] = new DataAddress("Mohammed", "al-Salam", "555-235-1113",
                "mas@someplace.com");
        dataArray[3] = new DataAddress("Pat", "Jones", "555-235-1114",
                "pjones@homesweethome.com");
        dataArray[4] = new DataAddress("Billy", "Kidd", "555-235-1115",
                "billy_the_kid@nowhere.com");
        dataArray[5] = new DataAddress("H.", "Houdini", "555-235-1116",
                "houdini@noplace.com");
        dataArray[6] = new DataAddress("Jack", "Jones", "555-235-1117",
                "jjones@hill.com");
        dataArray[7] = new DataAddress("Jill", "Jones", "555-235-1118",
                "jillj@hill.com");
        dataArray[8] = new DataAddress("John", "Doe", "555-235-1119",
                "jdoe@somedomain.com");
        dataArray[9] = new DataAddress("Jane", "Doe", "555-235-1120",
                "jdoe@somedomain.com");
        dataArray[10] = new DataAddress("Test", "Case", "555-235-1121",
                "Test_Case@testcase.com");
        dataArray[11] = new DataAddress("Nadezhda", "Kanachekhovskaya",
                "555-235-1122", 
                "dr.nadezhda.kanacheckovskaya@somehospital.moscow.ci.ru");
        dataArray[12] = new DataAddress("Jo", "Wu", "555-235-1123", "wu@h.com");
        dataArray[13] = new DataAddress("Millard", "Fillmore", "555-235-1124",
                "millard@theactualwhitehouse.us");
        dataArray[14] = new DataAddress("Bob", "vanDyke", "555-235-1125",
                "vandyke@nodomain.com");
        dataArray[15] = new DataAddress("Upside", "Down", "555-235-1126",
                "upsidedown@rightsideup.com");
        
        return dataArray;
    }
    
} //SDS end of JavaDataTest class
