import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSV_Reading {


    /*public static void main(String[] args){
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("IMDB_Top_1000.csv"))) {
            String line,Title,Certificate,Duration,Genre,Rate,Metascore,Description,Cast,Info;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String[] real_values=new String[10];
                String temp=null;
                records.add(Arrays.asList(values));
                int real_counter=0;
                for(int i=0;i<values.length;i++){
                    temp=values[i];
                    if(temp.contains("\"")){
                        while(true){
                            i++;
                            if(!values[i].contains("\"")){
                                temp=temp+", "+values[i];
                            }
                            else{
                                temp=temp+", "+values[i];
                                real_values[real_counter]=temp;
                                real_counter++;
                                break;
                            }
                        }

                    }
                    else{
                        real_values[real_counter]=temp;
                        real_counter++;
                    }
                }
                //System.out.println(real_values[9]);
                for(String tokens: real_values) {
                    System.out.print(tokens + " -- ");
                }
                System.out.println();


            }
        }catch(IOException e){
            System.out.println("Dosya Okunamadi");
        }
    }*/
}
