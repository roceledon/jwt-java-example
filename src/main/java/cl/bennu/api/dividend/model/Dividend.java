package cl.bennu.api.dividend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Dividend implements Serializable {
    private Long code;
    private String message;
    private String mutualCode;
    private Long quota;
    private Date dayToPay;
    private String pdf;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMutualCode() {
        return mutualCode;
    }

    public void setMutualCode(String mutualCode) {
        this.mutualCode = mutualCode;
    }

    public Long getQuota() {
        return quota;
    }

    public void setQuota(Long quota) {
        this.quota = quota;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    public Date getDayToPay() {
        return dayToPay;
    }

    public void setDayToPay(Date dayToPay) {
        this.dayToPay = dayToPay;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
