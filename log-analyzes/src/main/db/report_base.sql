DROP TABLE IF EXISTS report_base;

CREATE TABLE report_base(
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  account_id BIGINT(20) NOT NULL ,
  campaign_id BIGINT(20) NOT NULL ,
  channel_id BIGINT(20) NOT NULL ,
  creative_id BIGINT(20) NOT NULL ,
  media_id BIGINT(20) NOT NULL ,
  imp BIGINT(20) NOT NULL DEFAULT 0,
  click BIGINT(20) NOT NULL DEFAULT 0,
  creation DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (account_id,campaign_id,channel_id,creative_id,media_id),
   KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;