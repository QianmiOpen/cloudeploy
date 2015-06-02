-- os
CREATE TABLE system_os(
  name VARCHAR(50),
  `bit` VARCHAR(10),
  version VARCHAR(50),
  platform VARCHAR(50),
  image VARCHAR(255)
);

CREATE TABLE system_type(
  cpu INT(10),
  memory INT(10),
  platform VARCHAR(50),
  code VARCHAR(255)
);

CREATE TABLE user_instance (
  user VARCHAR(100),
  instanceId VARCHAR(255),
  instanceIp VARCHAR(255),
  platform VARCHAR(50)
);

CREATE TABLE region (
  id VARCHAR(100),
  name VARCHAR(100),
  platform VARCHAR(50)
);

