package org.remipassmoilesel.sel;

import org.remipassmoilesel.Mappings;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

/**
 * Created by remipassmoilesel on 18/02/17.
 *
 * Source: https://docs.spring.io/spring/docs/current/spring-framework-reference/html/expressions.html
 */
@Controller
public class SpringExpressionLanguage {

    @RequestMapping(value = Mappings.SPRING_SPEL, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String spelExperiments() {

        ArrayList<Object> response = new ArrayList<>();

        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        response.add(exp.getValue());

        //
        // invokes 'getBytes()'
        //
        exp = parser.parseExpression("'Hello World'.bytes");
        byte[] bytes = (byte[]) exp.getValue();
        response.add(Arrays.asList(bytes));

        //
        // access properties
        //
        exp = parser.parseExpression("'Hello World'.bytes.length");
        int length = (Integer) exp.getValue();
        response.add(length);

        //
        // use of constructor
        //
        exp = parser.parseExpression("new String('hello world').toUpperCase()");
        String resp = exp.getValue(String.class);
        response.add(resp);

        // Specify class prevent use of cast
        // String resp = exp.getValue(String.class);

        //
        // Create and set a calendar
        //
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);

        //
        // use of context
        //
        Inventor tesla = new Inventor("Nikola Tesla", "Serbian", c.getTime());
        Expression exp2 = parser.parseExpression("name");
        EvaluationContext context = new StandardEvaluationContext(tesla);

        // boths expressions are equals
        response.add((String) exp2.getValue(context));
        response.add((String) exp2.getValue(tesla));



        //
        //
        //
        // construct body and return
        //
        StringBuilder message = new StringBuilder();
        int i = 0;
        for (Object r : response) {
            message.append("<p>");
            message.append(i + ": " + r);
            message.append("<p/>");
            i++;
        }

        return message.toString();

    }

}
