package org.remipassmoilesel.plainexamples;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by remipassmoilesel on 13/06/17.
 */
public class TestIfAjax {

    private static final String SIGNUP_VIEW_NAME = "viewName";

    @RequestMapping(value = "signup")
    public String signup(Model model, WebRequest webRequest) {

        if (isAjaxRequest(webRequest)) {
            return SIGNUP_VIEW_NAME.concat(" :: signupForm");
        }
        return SIGNUP_VIEW_NAME;


    }

    public static boolean isAjaxRequest(WebRequest webRequest) {
        String requestedWith = webRequest.getHeader("X-Requested-With");
        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }

}
