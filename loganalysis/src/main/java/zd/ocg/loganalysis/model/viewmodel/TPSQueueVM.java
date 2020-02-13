package zd.ocg.loganalysis.model.viewmodel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TPSQueueVM {
    private int id;
    private LocalDateTime logTime;
    private String EnqueueOrDequeue;
    private int lineCout;

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



    public int getLineCout() {
        return lineCout;
    }

    public void setLineCout(int lineCout) {
        this.lineCout = lineCout;
    }

    public String getEnqueueOrDequeue() {
        return EnqueueOrDequeue;
    }

    public void setEnqueueOrDequeue(String enqueueOrDequeue) {
        EnqueueOrDequeue = enqueueOrDequeue;
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
