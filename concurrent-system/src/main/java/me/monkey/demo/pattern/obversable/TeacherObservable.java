package me.monkey.demo.pattern.obversable;

/**
 * 被观察对象
 * @author Greiz
 */
public class TeacherObservable extends Observable {
    private String examKeyPoints;
    public String getExamKeyPoints() {
        return examKeyPoints;
    }
    public void setExamKeyPoints(String examKeyPoints) {
        this.examKeyPoints = examKeyPoints;
        // 修改状态
        super.setChanged();
        // 通知所有观察者
        super.notifyObservers(examKeyPoints);
    }
}