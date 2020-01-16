package gymapp.gymapp.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "position")
public class Position {

    @Column(name = "position_id")
    private int position_id;
    @Column(name = "name")
    private String name;
    @Column(name = "wage")
    private double wage;
    @OneToMany(mappedBy="employee", cascade={CascadeType.ALL})
    private List<Employee> employees;


    public Position() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    @Override
    public String toString(){
        return name;
    }

}
