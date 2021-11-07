/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Ducky
 */
public class Int2Char extends SimpleTagSupport {

    private String number;

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public void doTag() throws JspException {
        StringWriter sw = new StringWriter();
        try {
            if (number != null) {
                JspWriter out = getJspContext().getOut();
                char c = (char) (Integer.parseInt(number) + 'A' - 1);
                out.print(c);
            }else {
                getJspBody().invoke(sw);
            }
        } catch (IOException ex) {
            Logger.getLogger(Int2Char.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
