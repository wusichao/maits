package com.wusc.entranceclick.writers;

import com.wusc.entrancebase.model.inputers.Action;
import com.wusc.entrancebase.utils.StringUtils;
import com.wusc.entrancebase.writers.CommonWriter;
import com.wusc.entranceclick.model.inputers.ClickAction;
import com.wusc.entranceclick.model.outers.ClickLogField;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.applet.Main;

@Component
public class ClickWriter extends CommonWriter{
    @Autowired
    private Logger logger;
    @Override
    public void write(Action action, StringBuilder buf){
        action.setActionType("CLICK");
        action.setActionPreId("preID");
        super.write(action,buf);
        Object[] fields = render((ClickAction)action);
        if (fields != null && fields.length > 0) {
            for (Object field : fields) {
                buf.append(StringUtils.toLogSafeString(field)).append('\t');
            }
        }

        if (buf.length() > 0) {
            buf.deleteCharAt(buf.length() - 1);
        }
        String log = buf.toString();
        logger.info(log);
    }

    private Object[] render(ClickAction action) {
        Object[] ret = new Object[ClickLogField.values().length];
        if(action != null){
            ret[ClickLogField.PREACTIONID.ordinal()] = action.getPreActionId();
        }
        return ret;
    }

    public static void main(String[] args) {
        ClickAction action  = new ClickAction();
        action.setPreActionId("pre");
        Action action1 =action;
        System.out.println(action1.toString());
        ClickAction action2=(ClickAction)action1;
        System.out.println(action2.toString());
    }
}
