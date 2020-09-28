/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5.model;

/**
 *
 * @author seanmapanao
 */
public class Stock {
    
//data members and their default values.
    private String productID = "111_AAA";
    private String productName = "Unknown Product";
    private int qoh = 0;
    private int rsp = 25;
    private double sellPrice = 0;
    private double buyPrice = 0;


    public Stock() {
        
    }

    public Stock(String productID, String productName, double sellPrice) {
        this.productID = productID;
        this.productName = productName;
        this.sellPrice = sellPrice;

    }

    public Stock(String productID, String productName, int qoh, int rsp, double 
            sellPrice, double buyPrice) {
        this.productID = productID;
        this.productName = productName;
        this.qoh = qoh;
        this.rsp = rsp;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
    }

    public String getProductId() {
        return productID;
    }
    
    // calls isValidProductID method for name validation. 
    public void setProductId(String id) {
        if (id.length() != 7 || isValidProductID(id) == false) {
            IllegalArgumentException validity = new IllegalArgumentException(
                    "Product ID  must consists of 7 characters with 3 digits, "
                    + "followed by an underscore, followed by 3 uppercase"
                    + " letters ");
            throw validity;
        } else {
            productID = id;
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String name) {
        productName = name;
    }

    public int getQoh() {
        return qoh;
    }
    
    //QOH validation - can't be a negative number.
    public void setQoh(int qoh) {
        if (qoh < 0) {
            IllegalArgumentException quantity = new IllegalArgumentException("QOH"
                    + " can not be a negative number.");
            throw quantity;
        } else {
            this.qoh = qoh;
        }
    }

    public int getRsp() {
        return rsp;
    }

    public void setRsp(int rsp) {
        this.rsp = rsp;
    }

    public double getSellPrice() {
        return sellPrice;
    }
    
    //SellPrice validation - can't be a negative number.
    public void setSellPrice(double sPrice) {
        if (sPrice < 0) {
            IllegalArgumentException sellingPrice = new IllegalArgumentException(
                    "Sell price can not be a negative number.");
            throw sellingPrice;
        } else {
            sellPrice = sPrice;
        }
    }

    public double getBuyPrice() {
        return buyPrice;
    }
    
    //BuyPrice validation - can't be a negative number.
    public void setBuyPrice(double bPrice) {
        if (bPrice < 0) {
            IllegalArgumentException buyingPrice = new IllegalArgumentException(
                    "Buy price can not be a negative number.");
            throw buyingPrice;
        } else {
            buyPrice = bPrice;
        }
    }
    
    //Multiplies number of units wanted and buy price. Rounded to nearest decimal.
    public double reStockFee(int bQuantity) {
        
        return bQuantity * buyPrice;
    }
    
    //Validates Product ID name - must consist of 7 characters, first three 
    //numbers, followed by an underscore (_), followed by three uppercase letters.
    public static boolean isValidProductID(String productID) {
        boolean isValid = true;

        char[] ch = new char[productID.length()];

        for (int i = 0; i < productID.length(); i++) {
            ch[i] = productID.charAt(i);
        }

        for (int i = 0; i <= 2; i++) {
            if (ch[i] < '0' || ch[i] > '9') {
                isValid = false;
            }
        }

        if (ch[3] != '_') {
            isValid = false;
        }

        for (int i = 4; i <= 6; i++) {
            if (ch[i] < 'A' || ch[i] > 'Z') {
                isValid = false;
            }

        }

        return isValid;

    }
    
    //String representation of stock object.
    @Override
    public String toString() {
        
        String roundedTotal = String.format("%.2f", getBuyPrice());
 
            return productID + " (" + productName + "), " + "QOH:" + qoh
                    + " Buying Price: $" + roundedTotal;
        }

    }


    
