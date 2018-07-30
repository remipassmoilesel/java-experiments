package org.remipassmoilesel.plainexamples;

import org.remipassmoilesel.Mappings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by remipassmoilesel on 13/06/17.
 */
@Controller
public class LayoutExample {

    private static final Logger logger = LoggerFactory.getLogger(org.remipassmoilesel.notes.NotesController.class);

    @RequestMapping(value = Mappings.LAYOUT_EXAMPLE, method = RequestMethod.GET)
    public String displayLayoutExample() {
        return "layout/content1";
    }

}
