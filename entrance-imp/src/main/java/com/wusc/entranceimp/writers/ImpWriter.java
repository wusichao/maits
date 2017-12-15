package com.wusc.entranceimp.writers;

import com.wusc.entrancebase.model.inputers.Action;
import com.wusc.entrancebase.utils.StringUtils;
import com.wusc.entrancebase.writers.CommonWriter;
import com.wusc.entranceimp.model.outers.ImpLogField;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImpWriter extends CommonWriter{
    @Autowired
    private Logger logger;
    @Override
    public void write(Action action, StringBuilder buf){
        super.write(action,buf);
        Object[] fields = render(action);
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

    private Object[] render(Action action) {
        Object[] ret = new Object[ImpLogField.values().length];
        if(action != null){
        }
        return ret;
    }
}
