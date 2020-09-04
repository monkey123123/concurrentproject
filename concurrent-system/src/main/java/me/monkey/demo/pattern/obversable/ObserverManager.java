package me.monkey.demo.pattern.obversable;

/**
 * comes from website below
 * https://www.cnblogs.com/wolf-bin/p/11604227.html
 * @author Greiz
 */
public class ObserverManager {
    public static void main(String[] args) {
        TeacherObservable observable = new TeacherObservable();
        // 给被观察者对象添加观察者
        observable.addObserver(new PoorStudentObserver());
        observable.addObserver(new ExcellentStudentObserver());
        // 修改被观察者
        observable.setExamKeyPoints("这是考试重点！！！");
    }
}