package com.parneetkaur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class weekseven{
        public final float PRICE_PACKAGE_A = 100;
        public final float PRICE_PACKAGE_B = 150;
        public final float PRICE_STORAGE_SMALL = 8;
        public final float PRICE_Storage_LARGE = 20.11f;
        public final float PRICE_BOX_SMALL = 2.50f;
        public final float PRICE_BOX_LARGE = 4.50f;

        private final MovingElement packageA, packageB, boxSmall, boxLarge, storageSmall, storageLarge;

        public weekseven(){
            packageA = new MovingElement("Package A", PRICE_PACKAGE_A, "hour", "Enter hours");
            packageB = new MovingElement("Package B", PRICE_PACKAGE_B, "hour", "Enter hours");
            storageLarge = new MovingElement("Large Storage", PRICE_Storage_LARGE, "day", "Enter days");
            storageSmall = new MovingElement("Small Storage", PRICE_STORAGE_SMALL, "day", "Enter days");
            boxLarge = new MovingElement("Large Box", PRICE_BOX_LARGE, "box", "Enter Boxes");
            boxSmall = new MovingElement("Small Box", PRICE_BOX_SMALL, "box", "Enter boxes");
        }
        public Object[] getInputOptions(){
            java.util.List<Object> inputOptions = new ArrayList<>();
            inputOptions.add("Choose all the following options  and enter quantity\n"); // main title
            inputOptions.add("\nSelect Packages:-"); // title for input packages
            inputOptions.addAll(packageA.getInputOptinos());
            inputOptions.addAll(packageB.getInputOptinos());
            inputOptions.add("\nSelect Storage Options:-"); // title for Storage options
            inputOptions.addAll(storageSmall.getInputOptinos());
            inputOptions.addAll(storageLarge.getInputOptinos());
            inputOptions.add("\nSelect Boxes:-"); // title for Boxes
            inputOptions.addAll(boxSmall.getInputOptinos());
            inputOptions.addAll(boxLarge.getInputOptinos());

            return inputOptions.toArray();
        }
        public String getOutputString(){
            MovingElement[] elements = {packageA, packageB, storageSmall, storageLarge, boxSmall, boxLarge};
            double service = 0, options = 0, items = 0;
            String outputString = "";
            for(MovingElement element : elements){

                if(element.checkBox.isSelected()){
                    try{
                        element.setQuantity((int)Double.parseDouble(element.getInput().trim()));
                        double elementTotal = element.getPrice()*element.getQuantity();
                        outputString += "Total price in "+element.getName()+ " = $"+elementTotal+"\n\n";
                        if(element == packageA || element == packageB)
                            service += elementTotal;
                        if(element == storageSmall || element == storageLarge)
                            options += elementTotal;
                        if(element == boxSmall || element == boxLarge){
                            items += elementTotal;
                        }
                    }catch (NumberFormatException e){
                        return "Try again!Values are not Correct";
                    }
                }
            }

            outputString += "Service Total = $"+service+"\n\n";
            outputString += "Options Total = $"+options+"\n\n";
            outputString += "Items Total = $"+items+"\n\n";
            outputString += "Total = $"+(service+options+items)+"\n\n";

            return outputString;
        }

        // inner class
        private static class MovingElement implements ActionListener{
            private final String name;
            private final JTextField textField;
            private final JCheckBox checkBox;
            private final JLabel label, lablePlaceholder, textPlaceholder;
            private int quantity;
            private final float price;

            MovingElement(String name, float price, String per, String promptString){
                this.name = name;
                this.textField = new JTextField();
                textField.setEnabled(false);
                textField.setVisible(false);
                this.label = new JLabel(promptString);
                this.label.setVisible(false);
                this.lablePlaceholder = new JLabel(".");
                this.textPlaceholder = new JLabel(".");
                this.price = price;
                this.checkBox = new JCheckBox(this.name+" | $"+this.price+"/"+per);
                this.checkBox.addActionListener(this);
                this.quantity = 0;
            }

            private void setQuantity(int quantity){
                this.quantity = quantity;
            }
            private float getTotal(){
                return this.price * this.quantity;
            }
            private java.util.List<Object> getInputOptinos(){
                java.util.List<Object> options = new ArrayList<>();
                options.add(this.checkBox);
                options.add(this.lablePlaceholder);
                options.add(this.textPlaceholder);
                options.add(this.label);
                options.add(this.textField);
                return options;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==this.checkBox){
                    this.textField.setVisible(this.checkBox.isSelected());
                    this.textField.setEnabled(this.textField.isVisible());
                    this.label.setVisible(this.checkBox.isSelected());
                    this.textPlaceholder.setVisible(!this.checkBox.isSelected());
                    this.lablePlaceholder.setVisible(!this.checkBox.isSelected());
                }
            }

            public String getInput() {
                return textField.getText();
            }

            public int getQuantity() {
                return quantity;
            }

            public String getName() {
                return name;
            }

            public float getPrice() {
                return price;
            }
        }
    }


