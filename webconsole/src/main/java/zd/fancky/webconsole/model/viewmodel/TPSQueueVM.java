package zd.fancky.loganalysis.model.viewmodel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TPSQueueVM {
    private int id;
    private LocalDateTime logTime;
    private String enqueueOrDequeue;
    private int lineCount;

    private NetInfo netInfo;

    public NetInfo getNetInfo() {
        return netInfo;
    }

    public void setNetInfo(NetInfo netInfo) {
        this.netInfo = netInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public String getEnqueueOrDequeue() {
        return enqueueOrDequeue;
    }

    public void setEnqueueOrDequeue(String enqueueOrDequeue) {
        this.enqueueOrDequeue = enqueueOrDequeue;
    }


    public String getLogTimeSecondStr() {
        return logTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getLogTimeMinuteStr() {
        return logTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getLogTimeHourStr() {
        return logTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH"));
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }


}
