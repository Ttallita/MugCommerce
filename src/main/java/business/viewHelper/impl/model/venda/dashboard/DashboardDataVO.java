package business.viewHelper.impl.model.venda.dashboard;

import java.util.List;

public class DashboardDataVO {

    private String name;
    private List<Integer> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
