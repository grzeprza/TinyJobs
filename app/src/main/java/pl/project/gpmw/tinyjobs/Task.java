package pl.project.gpmw.tinyjobs;

/**
 * Created by GrzegorzLap on 2016-04-26.
 */
public class Task {
    private String taskDescr;
    private int taskMoney;
    private String image;
    //for detailed view
    private String address;
    private String date;
    private String phone;
    private String id;
    private String taskDescr_fullDescription;
    private String time;

    private Task(){}

    public Task(String id, String imageName, String taskDescr,String taskDescr_fullDescription, String address, String date, String time, String phone, int taskMoney){
        this.image = imageName;
        this.taskMoney= taskMoney;
        this.taskDescr = taskDescr;

        this.taskDescr_fullDescription = taskDescr_fullDescription;
        this.id = id;
        this.address = address;
        this.date = date;
        this.time = time;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getPhone() {
        return phone;
    }

    public String getId() {
        return id;
    }

    public String getTaskDescr_fullDescription() {
        return taskDescr_fullDescription;
    }

    public String getTime() {
        return time;
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

    @Override
    public String toString() {
        return this.taskDescr + "@#" + this.taskDescr_fullDescription + "@#" + getTaskMoney() + "@#" + getTime() + "@#" + getDate() + "@#" + getImage() + "@#" + getAddress() + "@#" + getPhone();
    }
}
