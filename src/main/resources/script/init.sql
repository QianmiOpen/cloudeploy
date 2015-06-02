
-- aliyun
INSERT INTO system_os(name, bit, version, platform, image) VALUES ('centos','64','7.0','aliyun','centos7u0_64_20G_aliaegis_20150130.vhd');
INSERT INTO system_os(name, bit, version, platform, image) VALUES ('centos','64','6.5','aliyun','centos6u5_64_20G_aliaegis_20150130.vhd');
INSERT INTO system_os(name, bit, version, platform, image) VALUES ('centos','32','6.5','aliyun','centos6u5_32_20G_aliaegis_20150130.vhd');
INSERT INTO system_os(name, bit, version, platform, image) VALUES ('centos','64','5.8','aliyun','centos5u8_64_20G_aliaegis_20150130.vhd');
INSERT INTO system_os(name, bit, version, platform, image) VALUES ('centos','64','5.10','aliyun','centos5u10_64_20G_aliaegis_20150130.vhd');
INSERT INTO system_os(name, bit, version, platform, image) VALUES ('centos','32','5.10','aliyun','centos5u10_32_20G_aliaegis_20150130.vhd');

INSERT INTO system_type(cpu, memory, platform, code) VALUES (1,1,'aliyun','ecs.t1.small');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (1,2,'aliyun','ecs.s1.small');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (1,4,'aliyun','ecs.s1.medium');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (1,8,'aliyun','ecs.s1.large');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (2,2,'aliyun','ecs.s2.small');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (2,4,'aliyun','ecs.s2.large');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (2,8,'aliyun','ecs.s2.xlarge');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (2,16,'aliyun','ecs.s2.2xlarge');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (4,4,'aliyun','ecs.s3.medium');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (4,8,'aliyun','ecs.s3.large');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (4,16,'aliyun','ecs.m1.medium');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (4,32,'aliyun','ecs.m2.medium');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (8,32,'aliyun','ecs.m1.xlarge');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (8,8,'aliyun','ecs.c1.small');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (8,16,'aliyun','ecs.c1.large');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (16,16,'aliyun','ecs.c2.medium');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (16,32,'aliyun','ecs.c2.large');
INSERT INTO system_type(cpu, memory, platform, code) VALUES (16,64,'aliyun','ecs.c2.xlarge');

INSERT INTO region(id, name, platform) VALUES ('cn-shenzhen','深圳', 'aliyun');
INSERT INTO region(id, name, platform) VALUES ('cn-qingdao','青岛', 'aliyun');
INSERT INTO region(id, name, platform) VALUES ('cn-beijing','北京', 'aliyun');
INSERT INTO region(id, name, platform) VALUES ('cn-hongkong','香港', 'aliyun');
INSERT INTO region(id, name, platform) VALUES ('cn-hangzhou','杭州', 'aliyun');
INSERT INTO region(id, name, platform) VALUES ('us-west-1','美国硅谷', 'aliyun');
