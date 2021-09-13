package com.company;

import java.util.Arrays;
import java.util.List;


public class Output {

    public byte chunks;
    public List<String> items;
    public String message;

    public Output() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte getChunks() {
        return chunks;
    }

    public void setChunks(byte chunks) {
        this.chunks = chunks;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public void printItems() {
        System.out.println("Org. message:\n"+this.message);
        System.out.println("Number of chunks:\n"+this.chunks);
        int i=1;
        for(String element:this.items)  {
            System.out.println(""+ i +" of "+this.chunks+": "+element);
            i++;
        }
    }
}
