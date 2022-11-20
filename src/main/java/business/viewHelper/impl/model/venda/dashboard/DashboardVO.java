package business.viewHelper.impl.model.venda.dashboard;

import java.time.LocalDate;
import java.util.List;

public class DashboardVO {
    private List<LocalDate> categories;
    private List<DashboardDataVO> options;

    public DashboardVO(List<LocalDate> categories, List<DashboardDataVO> options) {
        this.categories = categories;
        this.options = options;
    }

    public List<LocalDate> getCategories() {
        return categories;
    }

    public void setCategories(List<LocalDate> categories) {
        this.categories = categories;
    }

    public List<DashboardDataVO> getOptions() {
        return options;
    }

    public void setOptions(List<DashboardDataVO> options) {
        this.options = options;
    }
}
