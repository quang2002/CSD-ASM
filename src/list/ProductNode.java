/*
 * Copyright 2022 QuangTDHE16060
 * https://github.com/quang2002
 */
package list;

import model.Product;

public class ProductNode {

    // [data : next]
    public Product data;
    public ProductNode next;

    public ProductNode() {
    }

    public ProductNode(Product data) {
        this.data = data;
    }

    public ProductNode(Product data, ProductNode next) {
        this.data = data;
        this.next = next;
    }
}
