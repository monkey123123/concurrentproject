package me.monkey.demo.pattern.obversable;

/**
 * @author Greiz
 */
public class PoorStudentObserver implements Observer {
    public static final String STUDENT = "我学渣一枚";
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(STUDENT + "用2B铅笔划重点:" + arg.toString());
    }
}