package pl.project.gpmw.tinyjobs;

/**
 * Created by GrzegorzLap on 2016-04-26.
 */
public class Task {
    private String taskDescr;
    private int taskMoney;
    private String image;

    private Task(){}

    public Task(String imageName, String taskDescr, int taskMoney){
        this.image = imageName;
        this.taskMoney= taskMoney;
        this.taskDescr = taskDescr;
    }

    public String getTaskDescr() {
        return taskDescr;
    }

    public int getMoney(){
        return this.taskMoney;
    }

    public String getTaskMoney() {
        return getMoney() + "$";
    }

    public String getImage() {
        return image;
    }
}
