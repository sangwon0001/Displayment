



import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TestDriverReadTable2 
{
	public static float[][] tableinfo;
	public static ValueTable table;
	public static void main(String[] args) 
	{
		tableinfo = MyTextDataReader2.readFloatValues("table3by3.txt");
		for (int i =0 ; i<tableinfo.length; i++)
		{
			float[] subRow = tableinfo[i];
			String testString = Arrays.toString(subRow);
			System.out.println(testString);
		}

		/*
			알고리즘 시작.
		 */
		table = new ValueTable(tableinfo);
		//1. item개수만큼 individual 클래스를 만든다. 그리고 각각의 individual만큼 bfs를 돌아서 채워준다.
		Individual[] individual = new Individual[table.getNcol()*table.getNrow()+1];
		Individual maxIndividual=null;
		float maxIndividualProfit=-1;
		for(int i=1; i<=table.getNcol()*table.getNrow(); i++) {
			System.out.println(i+"번째 testing!!");
			individual[i] = new Individual(table.getNrow(), table.getNcol());
			bfs(individual[i], i);
			System.out.println(individual[i].toString());
			System.out.println("그래서 전체 profit은 : "+individual[i].getObjectiveValue(table) + "!!!");
			if(individual[i].getObjectiveValue(table)>maxIndividualProfit){
				maxIndividualProfit = individual[i].getObjectiveValue(table);
				maxIndividual = individual[i];
			}
		}
/*
	TODO
		이제 요따가 myTeam0Sol.dat에다가 집어넣으면 되용!!(OutputStream)
		이게 지금 item number를 1~255번으로 생각해서
		집어넣을때는 1씩 빼믄 되영
 */


		/*
Fittest
1 2 3 
0 4 5 
8 6 7 

Fittest value
134.27852
		*/


		//Read saved myIndividualSol in a file
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Individual myIndividualSol = maxIndividual;
		try{
			
			// create streams
			fis = new FileInputStream("myTeam0Sol.dat");
			ois = new ObjectInputStream(fis);
			
			// read the object
			myIndividualSol = (Individual)ois.readObject();

			System.out.println("my solution is restored");
		
		}catch(Exception e){			
			e.printStackTrace();
		
		}finally{
			// close the streams
			if(fis != null) try{fis.close();}catch(IOException e){}
			if(ois != null) try{ois.close();}catch(IOException e){}	
		}

		System.out.println("myIndividualSol: ");
		System.out.println("" + myIndividualSol);

		float myScore = myIndividualSol.getObjectiveValue(table);
		System.out.println("myScore: " + myScore);


	}
	public static void bfs(Individual a, int center_item)
	{
		Position tmp;
		int center_location = a.rsize()*a.rsize()/2+1;
		//int center_row = (a.rsize()-1)/2;
		//int center_col = (a.csize()-1)/2;

		Queue<Position> space = new LinkedList<Position>();

		space.add(new Position((a.rsize()-1)/2, (a.csize()-1)/2));
		tmp = space.poll();
		a.setValue(tmp.row,tmp.col,center_item);

		space.add(tmp.up());
		space.add(tmp.right());
		space.add(tmp.down());
		space.add(tmp.left());

		while(!space.isEmpty())
		{
			tmp = space.poll();
			//System.out.println("col :"+tmp.col+ "row : "  +tmp.row);
			if (	tmp.col<0 ||tmp.col>a.csize()-1 ||
					tmp.row<0 || tmp.row>a.rsize()-1 ||
					a.getValue(tmp.row,tmp.col)!=0
			)continue;
			a.setValue(tmp.row,tmp.col,findMaxProfitItem(tmp,a));

			space.add(tmp.up());
			space.add(tmp.right());
			space.add(tmp.down());
			space.add(tmp.left());
		}
	}
	private static int findMaxProfitItem(Position position, Individual a){
		int row = position.row;
		int col = position.col;
		int up=0, right=0, down=0, left=0;
		float maxProfit=-1;
		int maxItem=0;

		if(row-1>-1 && a.getValue(row-1, col)!=0){ up = a.getValue(row-1,col);}
		if(row+1<a.rsize()-1 && a.getValue(row+1, col)!=0){ down= a.getValue(row+1, col);}
		if(col-1>-1 && a.getValue(row, col-1)!=0){ left = a.getValue(row, col-1);}
		if(col+1<a.csize() && a.getValue(row, col+1)!=0){ right=a.getValue(row, col+1);}


		for(int i=1; i<=a.csize()*a.rsize(); i++)
		{
			//if문으로 사용중인지 보기
			if(a.isInUse(i)==true) continue;

			float tempProfit = 0;
			if(up!=0) tempProfit+=table.getValue(i-1,up-1);
			if(down!=0) tempProfit+=table.getValue(i-1,down-1);
			if(right!=0) tempProfit+=table.getValue(i-1,right-1);
			if(left!=0) tempProfit+=table.getValue(i-1,left-1);

			if(tempProfit>maxProfit){
				maxItem = i;
				maxProfit = tempProfit;
			}
		}
		return maxItem;
	}


}