package zd.fancky.loganalysis.model;

import java.time.LocalDateTime;

public class StatisticsAnalysis {
    private LocalDateTime logTime;
    private Integer count;

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
