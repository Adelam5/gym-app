package gymapp.gymapp.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "package")
public class Package {

    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "gymaccess")
    private boolean gymaccess;
    @Column(name = "groupactivities")
    private boolean groupactivities;
    @Column(name = "personaltrainer")
    private boolean personaltrainer;
    @Column(name = "nutritionist")
    private boolean nutritionist;
    @OneToMany(mappedBy="user", cascade={CascadeType.ALL})
    private List<User> users;

    public Package() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isGymaccess() {
        return gymaccess;
    }

    public void setGymaccess(boolean gymaccess) {
        this.gymaccess = gymaccess;
    }

    public boolean isGroupactivities() {
        return groupactivities;
    }

    public void setGroupactivities(boolean groupactivities) {
        this.groupactivities = groupactivities;
    }

    public boolean isPersonaltrainer() {
        return personaltrainer;
    }

    public void setPersonaltrainer(boolean personaltrainer) {
        this.personaltrainer = personaltrainer;
    }

    public boolean isNutritionist() {
        return nutritionist;
    }

    public void setNutritionist(boolean nutritionist) {
        this.nutritionist = nutritionist;
    }

    @Override
    public String toString(){
        return name;
    }

}
