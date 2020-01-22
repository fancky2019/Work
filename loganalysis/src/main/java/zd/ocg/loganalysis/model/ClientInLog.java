package zd.ocg.loganalysis.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientInLog {
    private LocalDateTime logTime;
    private String content;

    private String sysNo;
    private String customerNo;

    public LocalDateTime getLogTime() {
        return logTime;
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

    public ClientInLog setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ClientInLog setContent(String content) {
        this.content = content;
        return this;
    }

    public String getSysNo() {
        return sysNo;
    }

    public ClientInLog setSysNo(String sysNo) {
        this.sysNo = sysNo;
        return this;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public ClientInLog setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
        return this;
    }
}
