import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by dhtpr on 2017-10-20.
 */
public class GreedyShelf {
    ShelfFrame frame;
    public  GreedyShelf(ShelfFrame frame){
        this.frame = frame;
    }
    public Shelf GreedyFromPoint(int row,int col,int item){
        Shelf tempShelf = frame.makeShelf();
        Position tmpPos = new Position(row,col);
        Queue<Position> space = new LinkedList<Position>();
        tempShelf.setItem(tmpPos,item);
        space.add(tmpPos.up());
        space.add(tmpPos.right());
        space.add(tmpPos.down());
        space.add(tmpPos.left());
        while(!space.isEmpty())
        {
            tmpPos = space.poll();
            //System.out.println("col :"+tmp.col+ "row : "  +tmp.row);
            if (	tmpPos.col<0 ||tmpPos.col>tempShelf.getnCol()-1 ||
                    tmpPos.row<0 || tmpPos.row>tempShelf.getnRow()-1 ||
                    tempShelf.getItem(tmpPos)!=-1
                    )continue;
            tempShelf.setItem(tmpPos,findMaxProfitItem(tmpPos,tempShelf));
            space.add(tmpPos.up());
            space.add(tmpPos.right());
            space.add(tmpPos.down());
            space.add(tmpPos.left());
        }
        return tempShelf;
    }
    private int findMaxProfitItem(Position position,Shelf shelf){
        float maxProfit=-1;
        int maxItem=-1;
        for(int item=0; item<shelf.getnRow()*shelf.getnCol(); item++)
        {
            //if문으로 사용중인지 보기
            if(shelf.isInUse(item)) continue;

            float tempProfit = shelf.getProfitFromPoint(position,item);
            if(tempProfit>maxProfit){
                maxItem = item;
                maxProfit = tempProfit;
            }
        }
        return maxItem;
    }
}
