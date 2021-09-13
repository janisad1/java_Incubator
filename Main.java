
package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {


    static List<String> artSplit(String element, int limit) {
        int nrOfChunks = element.length()/limit+1;

        List<String> tmp = new ArrayList<String>();
        int index = 0;
        for(int i = 0; i< nrOfChunks; i++){
            int begining = i+index -1;
            if(begining<0){
                begining=0;
            }
            int end = limit + index;
            if(end > element.length()){
                end = element.length();
            }
            index = index + limit;
            tmp.add(element.substring(begining,end));
        }
        return tmp;
    }

    static Output returnSplit (String message, int limit) {
        //object for datastorage
        Output o = new Output();
        o.setMessage(message);
        //additional variables:
        byte counter = 0; //holds number of chunks
        int tmp = 0; //holds temporary local length value

        //split org. message into words based on spacebars

        List<String> items = Arrays.asList(message.split(" "));
        List<String> elements = new ArrayList<String>();
        for(String element:items){
            //if word longer than limit, apply artificial split
            if(element.length()>limit) {
                List<String> subitems = artSplit(element, limit);

                for(String subelement:subitems){
                    elements.add(subelement);
                }

            }else{

                elements.add(element);
            }

        }


        List<String> out = new ArrayList<String>();;

        String tmpString = "";
        int index = 0;
        int end = elements.size();
        String[] splitted = new String[end];
        int i = 0;
        for(String element:elements) {
            splitted[i] = element;
            i++;
        }

        // char limit
        int charLim = limit;


        // stworzenie nowego Stringa tmp żeby zachować podzielony teks
        String tmpText = "";

        // przejście przez tablicę

        for(int j = 0 ; j < splitted.length-1; j++)
        {

            tmpText = tmpText + splitted[j]+ " ";

            if(tmpText.length() + splitted[j+1].length() > charLim && j+1 < splitted.length-1){
                counter++;
                out.add(tmpText);
                tmpText = "";

            }else if(j+1>splitted.length-2){
                counter++;
                for(int z = j+1; z<splitted.length; z++){
                    tmpText = tmpText + splitted[z];
                }
                out.add(tmpText);
            }



        }


        //

        o.setChunks(counter);
        o.setItems(out);
        return o;
    }

    public static void main(String[] args) throws IOException {


        int limit = 25;


        Scanner in = new Scanner(Path.of("C:\\Users\\KOWALMA3\\Downloads\\sms\\src\\examples.txt"), StandardCharsets.UTF_8);
        in.useDelimiter("\n");
        while(in.hasNext()) {
            String message = in.next();
            Output o = returnSplit(message,limit);
            o.printItems();
        }

    }
}
