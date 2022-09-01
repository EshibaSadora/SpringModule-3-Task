package ru.shornikov.screen.sheetassignetinfo;

import io.jmix.ui.component.BrowserFrame;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shornikov.entity.SheetAsign;
import ru.shornikov.entity.SheetAssignetInfo;
import ru.shornikov.repository.SheetAsignDAO;

import java.util.List;

@UiController("SheetAssignetInfo.browse")
@UiDescriptor("sheet-assignet-info-browse.xml")


public class SheetAssignetInfoBrowse extends StandardLookup<SheetAssignetInfo> {

    @Autowired
    SheetAsignDAO sheetAsignDAO;

    @Autowired
    private BrowserFrame browserFrame1;

    private void createCustomerLoader(CollectionContainer<SheetAssignetInfo> container) {



    }



}