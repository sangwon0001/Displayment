import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by dhtpr on 2017-10-20.
 */
public class ItemProfits {
    static int[] MyData;
    static float[][] itemProfitTable;
    public static float getItemProfit(int itemA,int itemB){
        return itemProfitTable[itemA][itemB];
    }
    public static int makeItemProfitTable(String filename,int itemCount){
        MyData = new int[itemCount];
        itemProfitTable = readFloatValues(filename);
        if(itemProfitTable == null)return -1;
        else{
            return 0;
        }
    }
    private static float[][] readFloatValues(String fileName) {
        ArrayList<String> mylines = new ArrayList<String>();
        int HowManyLines = 0;
        float[][] inputData = null;
        try{
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                mylines.add(strLine);
                HowManyLines++;
            }
            int HowManyVars = getFloatFromOneLine(mylines.get(0)).length;
            inputData = new float[HowManyLines][HowManyVars];

            for (int k=0; k < HowManyLines ; k++ )
            {
                inputData[k] = getFloatFromOneLine(mylines.get(k));
            }

            //Close the input stream
            fstream.close();
        } catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        //return Arrays.copyOf(MyData, HowMany);
        return inputData;
    }


    private static float[] getFloatFromOneLine(String myLine) {
        //String myLine = "3 8 10   60  12 18";
        String myDelimiter = "[\\s]+";
        String[] myTokens = myLine.split(myDelimiter);
        float[] curValues = new float[myTokens.length];
        for (int k = 0; k < myTokens.length; k++) {
            curValues[k] = Float.parseFloat(myTokens[k]);
        }
        return curValues;
    }

}
