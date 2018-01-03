drop table if exists account;
CREATE TABLE account (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(50) NOT NULL UNIQUE,
  password varchar(150) NOT NULL,
  type SMALLINT(3) NOT NULL DEFAULT 0,
  company_name varchar(60) NOT NULL UNIQUE,
  vertical varchar(255) NOT NULL,
  web_site varchar(2048) NOT NULL,
  contact varchar(50) NOT NULL,
  cellphone varchar(32) NOT NULL,
  license_path varchar(255) NOT NULL,
  status varchar(20) NOT NULL DEFAULT '未审核',
  creation DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_modified datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  removed bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (id),
  KEY U_AC_EM_PA (email,password),
  KEY N_AC_LASTM (last_modified)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8;



