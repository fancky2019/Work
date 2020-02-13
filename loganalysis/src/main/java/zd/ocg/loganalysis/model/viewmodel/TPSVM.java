package zd.ocg.loganalysis.model.viewmodel;

public class TPSVM {
    private String logTime;
    private String enqueueTime;
    private String dequeueTime;
    private Integer tPSQueueCount;

    private String orderType;
    private String customerNo;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getEnqueueTime() {
        return enqueueTime;
    }

    public void setEnqueueTime(String enqueueTime) {
        this.enqueueTime = enqueueTime;
    }

    public String getDequeueTime() {
        return dequeueTime;
    }

    public void setDequeueTime(String dequeueTime) {
        this.dequeueTime = dequeueTime;
    }

    public Integer gettPSQueueCount() {
        return tPSQueueCount;
    }

    public void settPSQueueCount(Integer tPSQueueCount) {
        this.tPSQueueCount = tPSQueueCount;
    }

}
