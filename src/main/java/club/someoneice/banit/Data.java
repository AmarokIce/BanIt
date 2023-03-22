package club.someoneice.banit;

import java.util.List;

public class Data {
    private List<String> defaultlist;
    private List<String> whitelist;

    public Data(List<String> defaultlist, List<String> whitelist) {
        this.defaultlist = defaultlist;
        this.whitelist = whitelist;
    }

    public List<String> getDefaultList() {
        return this.defaultlist;
    }

    public List<String> getwhitelist() {
        return this.whitelist;
    }
}
