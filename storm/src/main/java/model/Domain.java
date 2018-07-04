package model;

import java.io.Serializable;

/**
 * @author whp 18-7-4
 */
public class Domain implements Serializable {
    private String domain;
    private Double price;
    private String applyTime;
    private Integer usefulTime;
    private String owner;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getUsefulTime() {
        return usefulTime;
    }

    public void setUsefulTime(Integer usefulTime) {
        this.usefulTime = usefulTime;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
