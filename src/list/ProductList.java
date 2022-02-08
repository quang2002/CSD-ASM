/*
 * Copyright 2022 QuangTDHE16060
 * https://github.com/quang2002
 */
package list;

import java.io.File;
import java.io.FileWriter;
import java.util.Locale;
import java.util.Scanner;
import model.Product;

public class ProductList {

    private ProductNode head, tail;

    public ProductList() {
    }

    public ProductNode findProductByCode(String pcode) {
        ProductNode it = head;

        while (it != null) {
            if (it.data.getPcode().equals(pcode)) {
                return it;
            }
            it = it.next;
        }

        return null;
    }

    public void addTail(Product data) {
        ProductNode node = new ProductNode();
        node.data = data;

        if (head == null && tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    public void remove(ProductNode node) {
        if (node == null) {
            return;
        }

        if (node == tail) {
            ProductNode it = head;
            ProductNode previousTail = null;
            while (it.next != null) {
                previousTail = it;
                it = it.next;
            }

            tail = previousTail;
            tail.next = null;
        } else {
            node.data = node.next.data;
            node.next = node.next.next;
        }
    }

    public void addProductToTheEnd() {
        Scanner sc = new Scanner(System.in);

        System.out.print("pcode: ");
        String pcode = sc.nextLine();

        if (findProductByCode(pcode) != null) {
            System.out.println("Da ton tai!");
            return;
        }

        System.out.print("pro_name: ");
        String pro_name = sc.nextLine();

        System.out.print("quantity: ");
        int quantity = sc.nextInt();

        System.out.print("saled: ");
        int saled = sc.nextInt();

        System.out.print("price: ");
        double price = sc.nextDouble();

        addTail(new Product(pcode, pro_name, quantity, saled, price));

        System.out.println("Them thanh cong!");
    }

    public void display() {
        ProductNode it = head;
        System.out.printf("%6s | %20s | %10s | %10s | %6s | %s\n",
                "code", "pro_name", "quantity", "saled", "price", "value");

        System.out.println("-------------------------------------------------------------------------------------");
        while (it != null) {
            System.out.printf("%6s | %20s | %10d | %10d | %6.1f | %.1f\n",
                    it.data.getPcode(),
                    it.data.getPro_name(),
                    it.data.getQuantity(),
                    it.data.getSaled(),
                    it.data.getPrice(),
                    it.data.getPrice() * it.data.getSaled()
            );
            it = it.next;
        }
    }

    public void saveDataToFile(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);

            ProductNode it = head;
            while (it != null) {
                String line = String.format(Locale.US, "%6s | %20s | %10d | %10d | %6.1f\n",
                        it.data.getPcode(),
                        it.data.getPro_name(),
                        it.data.getQuantity(),
                        it.data.getSaled(),
                        it.data.getPrice()
                );

                fw.append(line);

                it = it.next;
            }

            fw.close();
        } catch (Exception e) {
            System.out.println("Loi khi save file!");
        }
    }

    public void loadDataFromFile(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));

            sc.useLocale(Locale.US);

            while (sc.hasNext()) {
                String line = sc.nextLine();

                String[] tokens = line.split("\\|");

                if (tokens.length == 5) {
                    addTail(new Product(
                            tokens[0].trim(), tokens[1].trim(),
                            Integer.parseInt(tokens[2].trim()),
                            Integer.parseInt(tokens[3].trim()),
                            Double.parseDouble(tokens[4].trim()))
                    );
                }
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("Loi khi load data!");
        }
    }

    public void searchByPCode() {
        Scanner sc = new Scanner(System.in);

        String code = sc.nextLine();

        ProductNode productNode = findProductByCode(code);

        if (productNode != null) {
            System.out.printf("Found: %6s | %20s | %10d | %10d | %6.1f | %.1f\n",
                    productNode.data.getPcode(),
                    productNode.data.getPro_name(),
                    productNode.data.getQuantity(),
                    productNode.data.getSaled(),
                    productNode.data.getPrice(),
                    productNode.data.getPrice() * productNode.data.getSaled()
            );
        } else {
            System.out.println("Not found!");
        }
    }

    public void deleteByPCode() {
        Scanner sc = new Scanner(System.in);

        System.out.print("pcode: ");
        String code = sc.nextLine();

        ProductNode productNode = findProductByCode(code);

        remove(productNode);
        System.out.println("xoa thanh cong!");
    }

    private int compare(ProductNode a1, ProductNode a2) {
        if (a1.data.getPrice() == a2.data.getPrice()) {
            return a1.data.getPcode().compareTo(a2.data.getPcode());
        }

        return a1.data.getPrice() - a2.data.getPrice() > 0 ? 1 : 0;
    }

    public void sortByProductCode() {
        while (true) {
            boolean isSorted = true;

            ProductNode it = head;

            while (it.next != null) {
                if (compare(it, it.next) > 0) {
                    // swap 
                    Product tmp = it.data;
                    it.data = it.next.data;
                    it.next.data = tmp;

                    isSorted = false;
                }
                it = it.next;
            }

            if (isSorted) {
                break;
            }
        }
    }

    public void addAfterPositionK() {
        Scanner sc = new Scanner(System.in);

        System.out.print("pcode: ");
        String pcode = sc.nextLine();

        if (findProductByCode(pcode) != null) {
            System.out.println("Da ton tai!");
            return;
        }

        System.out.print("pro_name: ");
        String pro_name = sc.nextLine();

        System.out.print("quantity: ");
        int quantity = sc.nextInt();

        System.out.print("saled: ");
        int saled = sc.nextInt();

        System.out.print("price: ");
        double price = sc.nextDouble();

        System.out.print("position: ");
        int k = sc.nextInt();

        if (k < 0) {
            System.out.println("error!");
            return;
        }

        ProductNode it = head;

        while (it != null) {
            if (k == 0) {
                break;
            }
            k--;
            it = it.next;
        }

        Product data = new Product(pcode, pro_name, quantity, saled, price);

        if (it == null) {
            System.out.println("Nhap ngoai khoang!");
        } else {
            if (it == tail) {
                addTail(data);
            } else {
                ProductNode node = new ProductNode(data);
                node.next = it.next;
                it.next = node;
            }

            System.out.println("Them thanh cong!");
        }
    }

    public void removeAfterXCode() {
        Scanner sc = new Scanner(System.in);

        System.out.print("pcode: ");
        String code = sc.nextLine();

        ProductNode productNode = findProductByCode(code);

        remove(productNode.next);
        System.out.println("xoa thanh cong!");
    }
}
