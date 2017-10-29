import java.util.Arrays;

/**
 * Created by dhtpr on 2017-10-20.
 */
public class Main {
    public static void main(String[] args) {
//        int row_col = 3;
        int row_col = 15;
//        ItemProfits.makeItemProfitTable("table3by3.txt",row_col);
        ItemProfits.makeItemProfitTable("table15by15.txt",row_col);
        ShelfFrame frame = new ShelfFrame(row_col,row_col);
        GreedyShelf greedyShelf = new GreedyShelf(frame);
        float maxProfit=-1;
        Shelf maxShelf = null;
        for(int item= 0;item<row_col*row_col;item++) {
            Shelf shelf = greedyShelf.GreedyFromPoint(0, 0, item);
            System.out.println(shelf.toString() + " total profit is " + shelf.getTotalProfit());
            if (shelf.getTotalProfit() > maxProfit) {
                maxProfit = shelf.getTotalProfit();
                maxShelf = shelf;
            }
        }
        System.out.println(
                "final is \n"+maxShelf.toString() + " total profit is " + maxProfit);
    }
}
