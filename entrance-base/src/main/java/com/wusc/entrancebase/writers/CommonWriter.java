package com.wusc.entrancebase.writers;

import com.wusc.entrancebase.model.inputers.Action;
import com.wusc.entrancebase.model.outers.CommLogField;
import com.wusc.entrancebase.utils.StringUtils;
import com.wusc.entrancebase.utils.TimeUtils;

public abstract class CommonWriter implements writerI{
    @Override
    public void write(Action action,StringBuilder buf){
            Object[] fields = render(action);
            if (fields != null && fields.length > 0) {
                for (Object field : fields) {
                    buf.append(StringUtils.toLogSafeString(field)).append('\t');
                }
            }

        if (buf.length() > 0) {
            buf.deleteCharAt(buf.length() - 1);
        }
    }

    private Object[] render(Action action) {
        Object[] ret = new Object[CommLogField.values().length];
        if(action != null){
            ret[CommLogField.ACTIONID.ordinal()] = action.getActionId();
            ret[CommLogField.ACCOUNTID.ordinal()] = action.getAccountId();
            ret[CommLogField.CAMPAIGNID.ordinal()]=action.getCampaignId();
            ret[CommLogField.CHANNELID.ordinal()] = action.getChannelId();
            ret[CommLogField.CREATIVEID.ordinal()] = action.getCreativeId();
            ret[CommLogField.MEDIAID.ordinal()] = action.getMediaId();
            ret[CommLogField.IP.ordinal()] = action.getIp();
            ret[CommLogField.SESSIONID.ordinal()] = action.getSessionId();
            ret[CommLogField.REQUESTTIME.ordinal()] = TimeUtils.formatMills(action.getRequestTime());
            ret[CommLogField.ACTIONTYPE.ordinal()] = action.getActionType();
            ret[CommLogField.ACTIONPREID.ordinal()] = action.getActionPreId();
        }
        return ret;
    }
}
