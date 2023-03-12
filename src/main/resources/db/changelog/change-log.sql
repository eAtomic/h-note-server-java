-- liquibase formatted sql

-- changeset Administrator:1678594558359-1
CREATE TABLE "users" ("id" VARCHAR(50) NOT NULL, "username" VARCHAR(20) NOT NULL, "nickname" VARCHAR(20) NOT NULL, "pass" VARCHAR(30) NOT NULL, "salt" VARCHAR(20) NOT NULL, "avatar" VARCHAR(50) NOT NULL, "avatar_wrapper" VARCHAR(20) NOT NULL, "phone" VARCHAR(20), "country_cd" VARCHAR(10), "mail" VARCHAR(50), "verify_cd" INTEGER DEFAULT 0 NOT NULL, "secret" VARCHAR(200), "vip_type" VARCHAR(10) NOT NULL, "vip_expire" TIMESTAMP WITHOUT TIME ZONE, "reg_source" VARCHAR(20), "reg_inviter" VARCHAR(20), "last_ip" VARCHAR(20), "last_login_time" TIMESTAMP WITHOUT TIME ZONE, "commonly_ip" VARCHAR(20), "wechat_id" VARCHAR(20), "qq_id" VARCHAR(20), "weibo_id" VARCHAR(20), "microsoft_id" VARCHAR(20), "google_id" VARCHAR(20), "apple_id" VARCHAR(20), "status" VARCHAR(10) NOT NULL, "create_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "create_by" VARCHAR(50) NOT NULL, "update_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "update_by" VARCHAR(50) NOT NULL, "deleted" VARCHAR DEFAULT '0', CONSTRAINT "users_pkey" PRIMARY KEY ("id"));

-- changeset Administrator:1678594558359-2
CREATE TABLE "permission" ("id" VARCHAR(50) NOT NULL, "permission_name" VARCHAR(20) NOT NULL, "description" VARCHAR(100), "status" VARCHAR(10) NOT NULL, "create_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "create_by" VARCHAR(50) NOT NULL, "update_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "update_by" VARCHAR(50) NOT NULL, CONSTRAINT "permission_pkey" PRIMARY KEY ("id"));

-- changeset Administrator:1678594558359-3
CREATE TABLE "role" ("id" VARCHAR(50) NOT NULL, "role_name" VARCHAR(20) NOT NULL, "description" VARCHAR(100), "status" VARCHAR(10) NOT NULL, "create_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "create_by" VARCHAR(50) NOT NULL, "update_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "update_by" VARCHAR(50) NOT NULL, CONSTRAINT "role_pkey" PRIMARY KEY ("id"));

-- changeset Administrator:1678594558359-4
ALTER TABLE "users" ADD CONSTRAINT "users_apple_id_key" UNIQUE ("apple_id");

-- changeset Administrator:1678594558359-5
ALTER TABLE "users" ADD CONSTRAINT "users_google_id_key" UNIQUE ("google_id");

-- changeset Administrator:1678594558359-6
ALTER TABLE "users" ADD CONSTRAINT "users_microsoft_id_key" UNIQUE ("microsoft_id");

-- changeset Administrator:1678594558359-7
ALTER TABLE "users" ADD CONSTRAINT "users_qq_id_key" UNIQUE ("qq_id");

-- changeset Administrator:1678594558359-8
ALTER TABLE "users" ADD CONSTRAINT "users_username_key" UNIQUE ("username");

-- changeset Administrator:1678594558359-9
ALTER TABLE "users" ADD CONSTRAINT "users_wechat_id_key" UNIQUE ("wechat_id");

-- changeset Administrator:1678594558359-10
ALTER TABLE "users" ADD CONSTRAINT "users_weibo_id_key" UNIQUE ("weibo_id");

-- changeset Administrator:1678594558359-11
ALTER TABLE "permission" ADD CONSTRAINT "permission_permission_name_key" UNIQUE ("permission_name");

-- changeset Administrator:1678594558359-12
ALTER TABLE "role" ADD CONSTRAINT "role_role_name_key" UNIQUE ("role_name");

-- changeset Administrator:1678594558359-13
CREATE TABLE "dict" ("id" VARCHAR(50) NOT NULL, "dict_name" VARCHAR(50) NOT NULL, "dict_value" JSON NOT NULL, "status" VARCHAR(10) NOT NULL, "create_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "create_by" VARCHAR(50) NOT NULL, "update_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "update_by" VARCHAR(50) NOT NULL, CONSTRAINT "dict_pkey" PRIMARY KEY ("id", "dict_name"));

-- changeset Administrator:1678594558359-14
CREATE TABLE "notification" ("id" VARCHAR(50) NOT NULL, "title" VARCHAR(50) NOT NULL, "description" VARCHAR(500) NOT NULL, "uri" VARCHAR(300), "target_group" VARCHAR(50), "status" VARCHAR(10) NOT NULL, "create_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "create_by" VARCHAR(50) NOT NULL, "update_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "update_by" VARCHAR(50) NOT NULL, CONSTRAINT "notification_pkey" PRIMARY KEY ("id"));

-- changeset Administrator:1678594558359-15
CREATE TABLE "role_permission" ("role_id" VARCHAR(50) NOT NULL, "permission_id" VARCHAR(50) NOT NULL, CONSTRAINT "role_permission_pkey" PRIMARY KEY ("role_id", "permission_id"));

-- changeset Administrator:1678594558359-16
CREATE TABLE "sysconfig" ("id" VARCHAR(50) NOT NULL, "config_name" VARCHAR(20) NOT NULL, "config_value" VARCHAR(500) NOT NULL, "description" VARCHAR(500), "uri" VARCHAR(300), "data_type" VARCHAR(10) NOT NULL, "data_source" VARCHAR(50), "status" VARCHAR(10) NOT NULL, "create_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "create_by" VARCHAR(50) NOT NULL, "update_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "update_by" VARCHAR(50) NOT NULL, CONSTRAINT "sysconfig_pkey" PRIMARY KEY ("id", "config_name"));

-- changeset Administrator:1678594558359-17
CREATE TABLE "user_role" ("user_id" VARCHAR(50) NOT NULL, "role_id" VARCHAR(50) NOT NULL, "expire" TIMESTAMP WITHOUT TIME ZONE, "status" VARCHAR(10) NOT NULL, "create_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "create_by" VARCHAR(50) NOT NULL, "update_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "update_by" VARCHAR(50) NOT NULL, CONSTRAINT "user_role_pkey" PRIMARY KEY ("user_id", "role_id"));

