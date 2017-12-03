package oxymat.demo;

public class Display {
    private int display = 0;

    public Display(){

    }

    public Display(int display){

        this.display = display;
    }

    public int getDisplay(){ return display; }

    public void setDisplay(int value){
        this.display = value;
    }
}
