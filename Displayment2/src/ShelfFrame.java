/**
 * Created by dhtpr on 2017-10-20.
 */
public class ShelfFrame {
    int nrow, ncol;
    public ShelfFrame(int nrow,int ncol) {
        this.nrow = nrow;
        this.ncol = ncol;
    }
    public Shelf makeShelf(){
        return new Shelf(nrow,ncol);
    }
}
