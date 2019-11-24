package me.monkey.pojo;

import java.util.Date;

public class PhoneMessage {

    private Integer phoneMessageId;
    private String phoneId;
    private Integer messageId;
    private Integer status;
    private Date createTime;
    private String operId;
    private Date operTime;

    public Integer getPhoneMessageId() {
        return phoneMessageId;
    }
    public void setPhoneMessageId(Integer phoneMessageId) {
        this.phoneMessageId = phoneMessageId;
    }
    public String getPhoneId() {
        return phoneId;
    }
    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }
    public Integer getMessageId() {
        return messageId;
    }
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getOperId() {
        return operId;
    }
    public void setOperId(String operId) {
        this.operId = operId;
    }
    public Date getOperTime() {
        return operTime;
    }
    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
}
