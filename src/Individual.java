
import java.util.*;

//여기서 itemlayout는 매대를 뜻하는 것이다.
public class Individual implements java.io.Serializable {
	private int nrow;
	private int ncol;
	private int[][] itemlayout;
	private Set<Integer> itemUse= new HashSet<Integer>();


	// Cache
	public Individual(int nrow, int ncol) {
		this.nrow = nrow;
		this.ncol = ncol;
		itemlayout = new int[nrow][ncol];
	}


	/* Getters and setters */
	// Use this if you want to create individuals with different gene lengths

	//row번째, col번째 매대에 있는 item 번호를 return해준다.
	public int getValue(int row, int col) {
		return itemlayout[row][col];
	}

	//그래서 이 getGeneArray()함수의 의의는 매대를 ArrayList로 return 받는 것이 목적이다.
	public ArrayList<Integer> getGeneArray() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for(int i=0; i<this.rsize(); i++) { //this.rsize()란 nrow를 뜻하고
			for(int j=0; j<this.csize(); j++) {//this.csize()란 ncol을 뜻한다.
				array.add(this.getValue(i, j));	//그래서 getValue란 (i,j)매대의 아이템 번호를 return 해주는 것이다.
			}
		}
		return array;
	}


	/* Public methods */
	public int rsize() {
		return nrow;
	}

	public int csize() {
		return ncol;
	}
	
	public int psize() {
		return nrow*ncol;
	}
	
	public void setSize(int nrow, int ncol) {
		this.nrow = nrow;
		this.ncol = ncol;
	}

	@Override
	public String toString() {
		String itemlayouttring = "";
		for (int i = 0; i < rsize(); i++) {
			for (int j = 0; j < csize(); j++) {
				itemlayouttring += getValue(i,j);
				itemlayouttring += " ";
			}
			itemlayouttring += "\n";
		}
		return itemlayouttring;
	}
	//매대의 전체 profit 구하는 함수!!!!!중요중요
	public float getObjectiveValue(ValueTable table) {
		float fitness = 0;
		int nrow = this.rsize();
		int ncol = this.csize();
		System.out.println("nrow:" + nrow + ", ncol:" + ncol);

		for (int i=1; i<nrow; i++) {
			for (int j=0; j<ncol; j++) {
				fitness+=table.getValue(this.getValue(i-1, j)-1, this.getValue(i, j)-1);
			}
		}
		
		for (int i=0; i<nrow; i++) {
			for (int j=1; j<ncol; j++) {
				System.out.println("nrow:" + nrow + ", ncol:" + ncol +", i:" + i + ",j:" + j);
				fitness+=table.getValue(this.getValue(i, j-1)-1, this.getValue(i, j)-1);
			}
		}
		
		return fitness;
	}

	public void setValue(int row, int col, int value) {
		itemlayout[row][col] = value;
		itemUse.add(value);
	}
	public boolean isInUse(int value){
		return itemUse.contains(value);
	}
}
