package me.monkey.demo.pattern.obversable;

/**
 * @author Greiz
 */
public class ExcellentStudentObserver implements Observer {
    public static final String STUDENT = "我学霸";
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(STUDENT + "用各种颜色的笔划重点:" + arg.toString());
    }
}