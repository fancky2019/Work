package zd.ocg.loganalysis.model;

import java.time.LocalDateTime;

public class ClientInTPS {
    private String logTime;
    private Integer clientCount;
    private Integer tPSQueueCount;

    public Integer getClientCount() {
        return clientCount;
    }

    public void setClientCount(Integer clientCount) {
        this.clientCount = clientCount;
    }

    public Integer gettPSQueueCount() {
        return tPSQueueCount;
    }

    public void settPSQueueCount(Integer tPSQueueCount) {
        this.tPSQueueCount = tPSQueueCount;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }
}
