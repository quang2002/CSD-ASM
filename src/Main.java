
import list.ProductList;

public class Main {

    public static void main(String[] args) {
        ProductList list = new ProductList();

        list.loadDataFromFile("abc.txt");


        //list.saveDataToFile("abc.txt");
        
        list.display();
        
        list.sortByProductCode();
        
        list.display();
    }

}
