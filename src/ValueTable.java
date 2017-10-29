

import java.util.Random;
//여기서의 ValueTable이라 함은 item 사이의 profit 관계 테이블을 말한다. 여기서 nrow, ncol의 뜻은 각각 매대의 가로, 세로 크기를 말한다.
public class ValueTable {
	private float[][] table;
	private int nrow, ncol;
	private Random rand = new Random();
	
	public ValueTable(int nrow, int ncol) {
		this.nrow = nrow;
		this.ncol = ncol;
		table = new float[nrow*ncol][nrow*ncol];
	}
	
	public ValueTable(float[][] table) {		
		this.nrow = (int)Math.sqrt(table.length);
		this.ncol = (int)Math.sqrt(table[0].length);
		this.table = table;
		System.out.println("[ValueTable] nrow: " + nrow +", ncol:" + ncol);
	}

	//이건 전체 table을 return해준다.
	public float[][] getTable() {
		return table;
	}

	//item 번호 두 개를 주면 그 두 개 사의의 profit value를 return해준다.
	public float getValue(int index1, int index2) {
		return table[index1][index2];
	}

	public int getNrow(){ return nrow; }
	public int getNcol(){ return ncol; }
	
	@Override
	public String toString() {
		String geneString = "";
		for (int i = 0; i < nrow*ncol; i++) {
			for (int j = 0; j < nrow*ncol; j++) {
				geneString += getValue(i,j);
				geneString += " ";
			}
			geneString += "\n";
		}
		return geneString;
	}
}
