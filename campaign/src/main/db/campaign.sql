DROP TABLE IF EXISTS campaign;
CREATE TABLE campaign (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  account_id bigint(20) NOT NULL,
  name varchar(60) NOT NULL,
  begin_date date NOT NULL,
  end_date date NOT NULL,
  cost BIGINT(20) NOT NULL DEFAULT '0',
  cost_type varchar(50) NOT NULL,
  unit_price BIGINT(10) NOT NULL,
  target_url varchar(2048) NOT NULL,
  channel_ids VARCHAR(225) NOT NULL,
  media_ids VARCHAR(255) NOT NULL,
  creative_ids VARCHAR(255) NOT NULL,
  creation datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  removed bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (id),
  KEY N_CA_ACCOUNT (account_id),
  KEY N_CA_NAME (name),
  KEY N_CA_LASTM (last_modified),
  KEY U_CA_BE_EN (begin_date,end_date)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;