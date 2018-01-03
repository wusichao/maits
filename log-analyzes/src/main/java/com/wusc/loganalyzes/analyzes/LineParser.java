package com.wusc.loganalyzes.analyzes;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
@Component
public class LineParser{

	public LogField parseLine(String line) throws ParseException {
		LogField ret = new LogField();

		String[] columns = line.split("\t");
		
		if (columns.length < CommLogField.values().length) {
			return ret;
		}
		ret.setAccountId(Long.valueOf(columns[CommLogField.ACCOUNTID.ordinal()]));
		if(ret.getAccountId()==0)
			{
			return ret;}

		ret.setActionId(columns[CommLogField.ACTIONID.ordinal()]);
		ret.setActionPreId(columns[CommLogField.ACTIONPREID.ordinal()]);
		ret.setRequestTime(new SimpleDateFormat("yyyyMMddHHmmssSSS").parse(columns[CommLogField.REQUESTTIME.ordinal()]));
		ret.setActionType(ActionType.valueOf(columns[CommLogField.ACTIONTYPE.ordinal()]));
		ret.setIp(columns[CommLogField.IP.ordinal()]);
		ret.setSessionId(columns[CommLogField.SESSIONID.ordinal()]);
		ret.setCampaignId(Long.valueOf(columns[CommLogField.CAMPAIGNID.ordinal()]));
		ret.setChannelId(Long.valueOf(columns[CommLogField.CHANNELID.ordinal()]));
		ret.setCreativeId(Long.valueOf(columns[CommLogField.CREATIVEID.ordinal()]));
		ret.setMediaId(Long.valueOf(columns[CommLogField.MEDIAID.ordinal()]));
		return ret;
	}
	
}
