/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nnxor_lab;

import javax.print.attribute.standard.OutputDeviceAssigned;

/**
 *
 * @author nuhin13
 */
public class Xor {
    
    double threshold = 0.9999;
     double learnigRate = 1.5;
     double finalResult;
     
     int iteration = 0 ; 
     
     int bias = 1;
     
     
     public double function(double x)
     {
         double result = (1/(1 + Math.exp(-x)));
         return result;
     }
     
     public double derivative(double x)
     {
         double result = (1/(1 + Math.exp(-x)));
         double derivative = result * (1 - result);
         return derivative;
     }
     
     public void XOR( int trainingData[][] ,int trainOutput[] , double weights[] ,double weights_L2[])
     {
        while(true)
        {
            iteration = iteration +1;
            boolean existError = false;
            
            double weights1[] = new double[(weights.length/2)];
            double weights2[] = new double[(weights.length/2)];
            
            double L1_result1 ;
            double L1_result2 ;
            
            // Training Data Loop
            for( int i=0 ; i<trainingData.length ; i++)
            {
                System.out.println( "----------------------------------------------------------------");
                
                System.out.println( "Starting Weights : " );
                
                for( int k=0 ; k<weights1.length ; k++)
                {
                    weights1[k] = weights[k];
                    System.out.print( "Weight ["+k+"]: "+ weights1[k]+" ");
                    System.out.print( " -*- ");
                }
                
                for( int k=0 ; k<weights2.length ; k++)
                {
                    weights2[k] = weights[(weights.length/2)+k];                            
                    System.out.print( "Weight ["+((weights.length/2)+k)+"]: "+ weights2[k]+" ");
                    System.out.print( " -*- ");
                }
                
                
                double weightedSum_0 = 0; 
                double weightedSum_1 = 0;
                double weightedSum_L2 = 0;
            
                // Calculate weighted sum for X1
                for(int j=0 ; j<2 ; j++)
                {
                     weightedSum_0 = weightedSum_0 + (trainingData[i][j] * weights1[j]);
                                         
                }
                
                weightedSum_0 = weightedSum_0 + (bias*Math.random());
                
                L1_result1 = function( weightedSum_0);
                
                            
                // Calculate weighted sum for X2
                for(int j=0 ; j<2 ; j++)
                {
                     weightedSum_1  = weightedSum_1 +(trainingData[i][j] * weights2[j]);                  
                }
                
                weightedSum_1 = weightedSum_1 + (bias*Math.random());
                
                L1_result2 = function( weightedSum_1);
                
                double L2_input[] ={L1_result1 , L1_result2};
                             
                
                for(int j=0 ; j<L2_input.length ; j++)
                {
                     weightedSum_L2  = weightedSum_L2  + (L2_input[j] * weights_L2[j]);
                     
                }
                
                weightedSum_L2 = weightedSum_L2 + (bias*Math.random());
                
                finalResult = function(weightedSum_L2);
                
                System.out.println("\n Final Result: "+ finalResult +" ");
                
                int output = 0 ; 
                
                if(threshold < finalResult)
                {
                    output = 1; 
                }
                
                System.out.println( "\n Actual output: " + trainOutput[i] +"  ->  " + "Gained output: " +output);
                
                //error claculation 
                int error = trainOutput[i] - output;
           
                if(error !=0)
                {
                    
                    existError = true;
                }
                             
                //Backpropagation
                
                double delta = finalResult - trainOutput[i];
                
                double delta1[] = new double[weights_L2.length];
                
                for(int j=0 ; j<weights_L2.length ; j++)
                {
                     delta1[j]   =  (delta * weights_L2[j]);
                     
                }
                
                for(int k = 0 ; k<2 ;k++)
                {                  
                    weights1[k] = weights1[k] + (learnigRate*delta1[0]*derivative(weightedSum_0)*trainingData[i][k]);
                }
                
                for(int k = 0 ; k<2 ;k++)
                {                  
                    weights2[k] = weights2[k] + (learnigRate*delta1[1]*derivative(weightedSum_1)*trainingData[i][k]);
                }
                
                for(int k=0 ; k<L2_input.length ; k++)
                {
                    weights_L2[k] = weights_L2[k] +(learnigRate*delta*derivative(weightedSum_L2)*L2_input[k]);
                }
                
                // Update Weights
                
                for (int k =0 ; k<weights.length ; k++)
                {
                    if( k<weights1.length)
                    {
                        weights[k] = weights1[k];
                    }
                    else
                    {
                        weights[k] = weights2[k-(weights2.length)];
                    }
                     
                }
                               
                System.out.println( "-");
                System.out.println( "New Weights :");
                for( int k=0 ; k<weights.length ; k++)
                {
                   // weights1[k] = weights[k];
                    System.out.print( "Weight ["+k+"]: "+ weights[k]+" ");
                    System.out.print( " | ");
                }
                
          
                System.out.println();
                
            }
            
            if(!existError)
            {
                System.out.println( "--------------------------------------------------------------");
                System.out.println( "Final Weights :");
                for( int k=0 ; k<weights.length ; k++)
                {
                    System.out.print( "Weight ["+k+"]: "+ weights[k]+" ");
                    System.out.print( " - * - ");
                }
                System.out.println();
                
                break;
            }
            
            System.out.println();
            System.out.println("Iteration : "+iteration );
            System.out.println();
        }
     
     }
    
}
