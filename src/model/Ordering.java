/*
 * Copyright 2022 QuangTDHE16060
 * https://github.com/quang2002
 */
package model;

public class Ordering {

    private String pcode;
    private String ccode;
    private int quantity;

    public Ordering() {
    }

    public Ordering(String pcode, String ccode, int quantity) {
        this.pcode = pcode;
        this.ccode = ccode;
        this.quantity = quantity;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
