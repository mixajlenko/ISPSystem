package com.mixajlenko.finaltask.ispsystem.controller.command.utils;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CustomTag extends TagSupport {

    private String field;

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public int doStartTag() {
        JspWriter out = pageContext.getOut();
        try {
            out.print("Personal account №: " + field);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
