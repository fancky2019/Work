package zd.ocg.loganalysis.model.querymodel;

public class TPSVMQueryModel extends QueryModelBase {
    private String logTime;
    private String enqueueTime;
    private String dequeueTime;
    private Integer tPSQueueCount;
    private String customerNo;
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
