package io.fourfinanceit.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "APPLICATION_REGISTRY")
public class LoanApplication {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDate applyDate;

    @Column
    private String ip;

    public LoanApplication() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanApplication)) return false;
        LoanApplication that = (LoanApplication) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(applyDate, that.applyDate) &&
                Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, applyDate, ip);
    }
}
