/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : scdemo

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-10-10 15:16:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `oauth_client_details`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) COLLATE utf8_bin NOT NULL,
  `resource_ids` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `client_secret` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `scope` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `authorized_grant_types` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `authorities` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) COLLATE utf8_bin DEFAULT NULL,
  `autoapprove` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('web', null, '$2a$10$CtIWzygnj922b/rlnFJqKeJskiot7osuZysU.3.dEVpN6A5gGC37y', 'all', 'password', null, 'all', null, null, null, null);
INSERT INTO `oauth_client_details` VALUES ('app', null, '$2a$10$CtIWzygnj922b/rlnFJqKeJskiot7osuZysU.3.dEVpN6A5gGC37y', 'all', 'password,refresh_token,authorization_code,client_credentials', null, 'all', null, null, null, null);
