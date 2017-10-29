import javafx.geometry.Pos;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dhtpr on 2017-10-20.
 */
public class Shelf {
    private int nRow, nCol;
    private int[] itemlayout;
    private Set<Integer> itemUse;

    public Shelf(int nRow,int nCol){
        this.nCol= nCol;
        this.nRow = nRow;
        itemlayout = new int[nRow*nCol];
        Arrays.fill(itemlayout,-1);
        itemUse= new HashSet<Integer>();
    }
    public int getnRow(){
        return nRow;
    }
    public int getnCol(){
        return nCol;
    }
    public float getProfitFromPoint(int row,int col){

        return 0;
    }
    public float getProfitFromPoint(int row,int col,int item){
        int up=0, right=0, down=0, left=0;
        float profit=0;
        if(row-1>-1){ up = getItem(row-1,col);}
        if(row+1<nRow-1){ down= getItem(row+1, col);}
        if(col-1>-1 ){ left = getItem(row, col-1);}
        if(col+1<nCol){ right=getItem(row, col+1);}
        if(up!=-1) profit+=ItemProfits.getItemProfit(item,up);
        if(down!=-1) profit+=ItemProfits.getItemProfit(item,down);
        if(right!=-1) profit+=ItemProfits.getItemProfit(item,right);
        if(left!=-1) profit+=ItemProfits.getItemProfit(item,left);
        return profit;
    }
    public float getProfitFromPoint(Position pos, int item){
        return this.getProfitFromPoint(pos.row,pos.col,item);
    }
    public float getTotalProfit(){
        float fitness = 0;
        //System.out.println("nrow:" + nRow + ", ncol:" + nCol);

        for (int i=1; i<nRow; i++) {
            for (int j=0; j<nCol; j++) {
                fitness+=ItemProfits.getItemProfit(this.getItem(i-1, j), this.getItem(i, j));
            }
        }

        for (int i=0; i<nRow; i++) {
            for (int j=1; j<nCol; j++) {
                //System.out.println("nrow:" + nRow + ", ncol:" + nCol +", i:" + i + ",j:" + j);
                fitness+=ItemProfits.getItemProfit(this.getItem(i, j-1), this.getItem(i, j));
            }
        }
        return fitness;
    }

    public int getItem(int row, int col) {
        return itemlayout[row*nRow+col];
    }
    public int getItem(Position pos) {
        return getItem(pos.row,pos.col);
    }
    public String toString() {
        String itemlayouttring = "";
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                itemlayouttring += getItem(i,j);
                itemlayouttring += " ";
            }
            itemlayouttring += "\n";
        }
        return itemlayouttring;
    }
    public void setItem(int row, int col, int item){
        itemlayout[row*nRow+col] = item;
        itemUse.add(item);
    }
    public void setItem(Position pos, int item){
        setItem(pos.row,pos.col,item);
    }
    public boolean isInUse(int value){
        return itemUse.contains(value);
    }
}
