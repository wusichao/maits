DROP TABLE IF EXISTS channel;
CREATE TABLE channel (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  account_id BIGINT(20) NOT NULL,
  name varchar(50) NOT NULL,
  creation datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  removed bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (id),
  KEY N_CH_AC (account_id)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8;