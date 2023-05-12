package org.joew502;

/**
 * This is the main class for the program. Here the main model and main controller are initialised and made available
 * to all other classes.
 */
public class Main{
    public static DataMain dataMain;
    public static GUIMain guiMain;
    public static void main(String[] args) {
        dataMain = new DataMain();
        guiMain = new GUIMain();
    }
}