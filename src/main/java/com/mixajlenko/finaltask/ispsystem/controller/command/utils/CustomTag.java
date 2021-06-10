package com.mixajlenko.finaltask.ispsystem.controller.command.utils;

import com.mixajlenko.finaltask.ispsystem.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CustomTag extends TagSupport {

    private String field;

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print("Personal account №: " + field);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
//    @Override
//    public void doTag() throws JspException, IOException {
//        JspWriter out = getJspContext().getOut();
//
//        User user = (User) getJspContext().getAttribute("u");
//        out.print("Personal account №: "+ user.getId());
//    }
//
//    @Override
//    protected JspContext getJspContext() {
//        return super.getJspContext();
//    }
}
