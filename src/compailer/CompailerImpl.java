package compailer;

import gui.MainFrame;

public class CompailerImpl implements Compailer{

    public void compaile(String s){
        MainFrame mf=MainFrame.getInstance();
        mf.getAppCore().readDataFromTable("JOBS");

    }
}
