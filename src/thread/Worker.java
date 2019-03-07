package thread;

/**
 * @author furui
 * @date 2019/3/5 0005
 */
public abstract class Worker {
    String name;
    Boolean gender;
    void say(String voice){

    }
    void work(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }
}
