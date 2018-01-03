package com.wusc.loganalyzes.dao;

import com.wusc.loganalyzes.analyzes.BaseReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * create by wusc on 2017/12/27
 */
@Component
public class ReportDao {
    private static final Logger logger= LoggerFactory.getLogger(ReportDao.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String BASE_SQL="insert into report_base(account_id,campaign_id,channel_id,creative_id,media_id,imp,click) values(?,?,?,?,?,?,?)"
            + " on DUPLICATE key update imp=imp+VALUES(imp),click=click+VALUES(click)";

    public void save(List<BaseReport> baseReportList){
        jdbcTemplate.batchUpdate(BASE_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                BaseReport re = baseReportList.get(i);
                int index=1;
                ps.setLong(index++,re.getAccountId());
                ps.setLong(index++,re.getCampaignId());
                ps.setLong(index++,re.getChannelId());
                ps.setLong(index++,re.getCreativeId());
                ps.setLong(index++,re.getMediaId());
                ps.setLong(index++,re.getImp());
                ps.setLong(index++,re.getClick());
            }

            @Override
            public int getBatchSize() {
                return baseReportList.size();
            }
        });
    }

}
