package com.parneetkaur;
import javax.swing.*;
public class assignment5 {

        private final static int NUMBER_OF_ATTEMPT_EXCEEDED = 0;
        private final static int SUCCESS = 1;
        private final static int CANCELED = 2;
        public static void main(String [] args){

            // creating object
            weekseven weekseven = new weekseven();
            Object [] inputOptions = weekseven.getInputOptions(); // Input options for JOptionPane Dialog

            // showing correct credential
            JOptionPane.showMessageDialog(null, "Credential\n\nFirst Name = parneet\n\n Last Name = kaur\n\n");
            int flag = validateCredential();

            // checking if credential valid
            if(flag == NUMBER_OF_ATTEMPT_EXCEEDED){
                JOptionPane.showMessageDialog(null, "Tries are Exceeded!");
            }else if(flag == SUCCESS){

                int input = JOptionPane.showConfirmDialog(null, inputOptions, "weekseven", JOptionPane.OK_CANCEL_OPTION);
                if(input== JOptionPane.OK_OPTION) {
                    JOptionPane.showMessageDialog(null, weekseven.getOutputString());
                }
            }

        }

        public static int validateCredential(){
            // ask for credential 3 times until its valid
            for(int i=0;i<3;i++){
                JTextField textFirstName = new JTextField();
                JTextField textLastName = new JTextField();
                Object[] fields = {"Enter Credentials: Attempt "+(i+1)+" of 3", "\nEnter First Name", textFirstName, "Enter Last Name", textLastName};

                int input = JOptionPane.showConfirmDialog(null, fields, "weekseven", JOptionPane.OK_CANCEL_OPTION);
                if(input == JOptionPane.CANCEL_OPTION){
                    return CANCELED;
                }
                if(textFirstName.getText().toLowerCase().equals("parneet") && textLastName.getText().toLowerCase().equals("kaur")){
                    return SUCCESS;
                }else{
                    if(i<2)
                        JOptionPane.showMessageDialog(null, "Wrong Credential! Try Again");
                }
            }
            return NUMBER_OF_ATTEMPT_EXCEEDED;
        }

    }


