package solutions.qualliant.clarity.views.outagemapview;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import solutions.qualliant.clarity.data.SamplePerson;
import solutions.qualliant.clarity.services.SamplePersonService;
import solutions.qualliant.clarity.views.MainLayout;

@PageTitle("Outage Map View")
@Route(value = "outage-map-view", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class OutageMapViewView extends Composite<VerticalLayout> {

    public OutageMapViewView() {
        Grid basicGrid = new Grid(SamplePerson.class);
        VerticalLayout layoutColumn2 = new VerticalLayout();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        basicGrid.setWidth("100%");
        basicGrid.setHeight("200px");
        basicGrid.getStyle().set("flex-grow", "0");
        setGridSampleData(basicGrid);
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        getContent().add(basicGrid);
        getContent().add(layoutColumn2);
    }

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
}
