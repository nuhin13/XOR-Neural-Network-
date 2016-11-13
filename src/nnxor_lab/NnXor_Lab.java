/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nnxor_lab;

/**
 *
 * @author nuhin13
 */
public class NnXor_Lab {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       
       double WeightX1 = Math.random();
       double WeightX2 = Math.random();
       double WeightX3 = Math.random();
       double WeightX4 = Math.random();
       
       double WeightH1 = Math.random();
       double WeightH2 = Math.random();
       
        double weights1[] = {WeightX1 , WeightX2 ,WeightX3 , WeightX4};
        double weights2[] = {WeightH1, WeightH2};
        
        int trainingData[][] = {
                        {0,0} ,
                        {0,1} ,
                        {1,0} ,
                        {1,1} ,
        };
        
        int output[]= {0,1,1,0};
        
        
       Xor op = new Xor();
       op.XOR(trainingData, output , weights1 ,weights2);

    }
    
}
