package application.supermarket.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "sellers")
public class Seller extends BaseEntity{
    private String firstName;
    private String lastName;
    private Integer age;
    private BigDecimal salary;
    private Shop shop;
    private Seller manager;


    public Seller() {
    }

    @Column(nullable = false)
    @Size(min = 2, message = "First name must be at least 2 symbols long. ")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    @Size(min = 2, message = "Last name must be at least 2 symbols long.")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false)
    @Min(value = 18, message = "Age must be greater than or equal to 18.")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(nullable = false)
    @Positive(message = "Salary can't be negative number.")
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @ManyToOne
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @ManyToOne
    public Seller getManager() {
        return manager;
    }

    public void setManager(Seller manager) {
        this.manager = manager;
    }
}
