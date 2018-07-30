package org.remipassmoilesel.errors;

import org.remipassmoilesel.Mappings;
import org.remipassmoilesel.utils.Utils;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.rmi.CORBA.Util;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by remipassmoilesel on 11/02/17.
 */
@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value = Mappings.ERROR, method = RequestMethod.GET)
    public String renderErrorPage(Model model, HttpServletRequest httpRequest) {

        Utils.printAttributes(httpRequest);

        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
        }

        model.addAttribute("date", new Date());
        model.addAttribute("errorMsg", errorMsg);
        return "errorPage";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }

    @Override
    public String getErrorPath() {
        return Mappings.ERROR;
    }
}
