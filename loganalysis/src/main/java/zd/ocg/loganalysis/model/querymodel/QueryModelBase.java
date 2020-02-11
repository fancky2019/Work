package zd.ocg.loganalysis.model.querymodel;

public class QueryModelBase {
    private  Integer skip;
    private  Integer take;


    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getTake() {
        return take;
    }

    public void setTake(Integer take) {
        this.take = take;
    }
}
