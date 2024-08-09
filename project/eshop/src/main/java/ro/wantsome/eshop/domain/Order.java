package ro.wantsome.eshop.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "price_value")
    private Double value;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    public Order(Long id, String orderDate, Double value, String customerName, Boolean isConfirmed) {
        this.id = id;
        this.orderDate = orderDate;
        this.value = value;
        this.customerName = customerName;
        this.isConfirmed = isConfirmed;
    }

    public Order() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
