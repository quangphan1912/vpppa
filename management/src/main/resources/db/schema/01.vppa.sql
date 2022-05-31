DROP VIEW  IF EXISTS "vw_parameter" cascade;
DROP VIEW  IF EXISTS "vw_scheme" cascade;
DROP TABLE IF EXISTS "tbl_scheme_status" cascade;
DROP TABLE IF EXISTS "tbl_scheme_parameter" cascade;
DROP TABLE IF EXISTS "tbl_scheme_product" cascade;
DROP TABLE IF EXISTS "tbl_scheme_subproduct" cascade;
DROP TABLE IF EXISTS "tbl_scheme_position";
DROP TABLE IF EXISTS "tbl_parameter" cascade;
DROP TABLE IF EXISTS "tbl_data_source" cascade;
DROP TABLE IF EXISTS "tbl_scheme" cascade;
DROP TABLE IF EXISTS "tbl_scheme_type" cascade;
DROP TABLE IF EXISTS "tbl_product" cascade;
DROP TABLE IF EXISTS "tbl_process" cascade;
DROP TABLE IF EXISTS "tbl_channel" cascade;
DROP TABLE IF EXISTS "tbl_division" cascade;
DROP TABLE IF EXISTS "tbl_center" cascade;
DROP TABLE IF EXISTS "tbl_department" cascade;
DROP TABLE IF EXISTS "tbl_position" cascade;
DROP TABLE IF EXISTS "tbl_campaign" cascade;
DROP TABLE IF EXISTS "tbl_process_status" cascade;
DROP TABLE IF EXISTS "tbl_process_status" cascade;
DROP TABLE IF EXISTS "tbl_process_type" cascade;
DROP TABLE IF EXISTS "tbl_process_report" cascade;
DROP TABLE IF EXISTS "tbl_scheme_executor" cascade;
DROP TABLE IF EXISTS "tbl_data_source_input" cascade;
DROP TABLE IF EXISTS "tbl_parameter_condition" cascade;
DROP TABLE IF EXISTS "tbl_user_group" cascade;
DROP TABLE IF EXISTS "tbl_user_role" cascade;
DROP TABLE IF EXISTS "tbl_ldap_user" cascade;
DROP TABLE IF EXISTS "tbl_user" cascade;
DROP TABLE IF EXISTS "user_status" cascade;
DROP TABLE IF EXISTS "tbl_user_loggined" cascade;

--ALTER TABLE "parameter_type" ALTER COLUMN "code" SET DEFAULT NULL;
DROP TABLE IF EXISTS "tbl_data_source";
CREATE TABLE IF NOT EXISTS "tbl_data_source" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"source" VARCHAR(128) UNIQUE NOT NULL,
	"content" TEXT NOT NULL,
	
	"created_date" TIMESTAMP NULL DEFAULT NULL,
	"created_by" VARCHAR(128) NULL DEFAULT NULL,
	"updated_date" TIMESTAMP NULL DEFAULT NULL,
	"updated_by" VARCHAR(128) NULL DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS "tbl_parameter" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"name" VARCHAR(200) UNIQUE NOT NULL,
	"source" VARCHAR(128) NULL DEFAULT NULL,
	"description" VARCHAR(4000) NULL DEFAULT NULL,
	"note" VARCHAR(4000) NULL DEFAULT NULL,	
	"status" VARCHAR(128) NOT NULL DEFAULT 'DRAFT', --DRAFT, ACTIVE, INACTIVE
	
	"created_date" TIMESTAMP NULL DEFAULT NULL,
	"created_by" VARCHAR(128) NULL DEFAULT NULL,
	"updated_date" TIMESTAMP NULL DEFAULT NULL,
	"updated_by" VARCHAR(128) NULL DEFAULT NULL,
	CONSTRAINT fk_parameter_data_source FOREIGN KEY (source) REFERENCES tbl_data_source (source)
);

CREATE TABLE IF NOT EXISTS "tbl_scheme_type" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"code" VARCHAR(128) NULL DEFAULT NULL, --Optional
	"name" VARCHAR(200) UNIQUE NOT NULL -- Bonus, Pilot, etc
);

CREATE TABLE IF NOT EXISTS "tbl_product" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"code" VARCHAR(128) NULL DEFAULT NULL, --Optional
	"name" VARCHAR(200) NOT NULL,
	"parent_id" INTEGER NULL DEFAULT NULL,
	unique ("name", "parent_id")
);

CREATE TABLE IF NOT EXISTS "tbl_channel" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"code" VARCHAR(128) NULL DEFAULT NULL, --Optional
	"name" VARCHAR(200) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS "tbl_division" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"code" VARCHAR(128) NULL DEFAULT NULL, --Optional
	"name" VARCHAR(200) NOT NULL,
	"channel_id" INTEGER NULL DEFAULT NULL,
	CONSTRAINT fk_division_channel FOREIGN KEY (channel_id) REFERENCES tbl_channel (id),
	unique ("name", "channel_id")
);

CREATE TABLE IF NOT EXISTS "tbl_center" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"code" VARCHAR(128) NULL DEFAULT NULL, --Optional
	"name" VARCHAR(200) UNIQUE NOT NULL,
	"division_id" INTEGER NULL DEFAULT NULL,
	
	CONSTRAINT fk_center_division FOREIGN KEY (division_id) REFERENCES tbl_division (id)
);

CREATE TABLE IF NOT EXISTS "tbl_department" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"code" VARCHAR(128) NULL DEFAULT NULL, --Optional
	"name" VARCHAR(200) UNIQUE NOT NULL,
	"center_id" INTEGER NULL DEFAULT NULL,
	CONSTRAINT fk_department_center FOREIGN KEY (center_id) REFERENCES tbl_center (id)
);

CREATE TABLE IF NOT EXISTS "tbl_position" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"code" VARCHAR(128) NULL DEFAULT NULL, --Optional
	"name" VARCHAR(200) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS "tbl_campaign" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"code" VARCHAR(128) NULL DEFAULT NULL, --Optional
	"name" VARCHAR(200) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS "tbl_process_type" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"code" VARCHAR(128) NULL DEFAULT NULL, --Optional
	"name" VARCHAR(200) UNIQUE NOT NULL -- Bonus, Pilot, etc
);

CREATE TABLE IF NOT EXISTS "tbl_process" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"code" VARCHAR(128) NULL DEFAULT NULL, --Optional
	"name" VARCHAR(200) UNIQUE NOT NULL,
	"description" VARCHAR(4000) NULL DEFAULT NULL,
	"expression" VARCHAR(128) NOT NULL DEFAULT '* * * * *',
	"division_id" INTEGER NULL DEFAULT NULL,
	"center_id" INTEGER NULL DEFAULT NULL,
	"process_type_id" INTEGER NULL DEFAULT NULL,
	"source" VARCHAR(128) NULL DEFAULT NULL,
	"key" VARCHAR(128) NULL DEFAULT '',
	
	CONSTRAINT fk_process_process_type FOREIGN KEY (process_type_id) REFERENCES tbl_process_type (id),
	CONSTRAINT fk_process_data_source FOREIGN KEY (source) REFERENCES tbl_data_source (source),
	CONSTRAINT fk_process_division FOREIGN KEY (division_id) REFERENCES tbl_division (id),
	CONSTRAINT fk_process_center FOREIGN KEY (center_id) REFERENCES tbl_center (id)
);

CREATE TABLE IF NOT EXISTS "tbl_scheme" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"identifier" VARCHAR(200) UNIQUE NOT NULL,
	"name" VARCHAR(200) NOT NULL,
	"description" VARCHAR(4000) NULL DEFAULT NULL,
	"kpi" VARCHAR(4000) NULL DEFAULT NULL,
	"kpi_description" VARCHAR(4000) NULL DEFAULT NULL,
	"effectived_date" TIMESTAMP NOT NULL,
	"expired_date" TIMESTAMP NULL DEFAULT NULL,
	"actual_effectived_date" TIMESTAMP NULL DEFAULT NULL,
	"actual_expired_date" TIMESTAMP NULL DEFAULT NULL,
	"status" VARCHAR(128) NOT NULL DEFAULT 'DRAFT', --DRAFT, ACTIVE, INACTIVE
	"status_date" TIMESTAMP NULL DEFAULT NULL,
	
	"source" VARCHAR(128) NULL DEFAULT NULL,
	
	"scheme_reference_id" INTEGER NULL DEFAULT NULL,
	"scheme_type_id" INTEGER NULL DEFAULT NULL,
	"process_id" INTEGER NULL DEFAULT NULL,
	"channel_id" INTEGER NULL DEFAULT NULL,
	"division_id" INTEGER NULL DEFAULT NULL,
	"center_id" INTEGER NULL DEFAULT NULL,
	"division_proposal_id" INTEGER NULL DEFAULT NULL,
	"department_id" INTEGER NULL DEFAULT NULL,
	"campaign_id" INTEGER NULL DEFAULT NULL,
	
	"created_date" TIMESTAMP NULL DEFAULT NULL,
	"created_by" VARCHAR(128) NULL DEFAULT NULL,
	"updated_date" TIMESTAMP NULL DEFAULT NULL,
	"updated_by" VARCHAR(128) NULL DEFAULT NULL,
	
	CONSTRAINT fk_scheme_scheme_type FOREIGN KEY (scheme_type_id) REFERENCES tbl_scheme_type (id),
	CONSTRAINT fk_scheme_process FOREIGN KEY (process_id) REFERENCES tbl_process (id),
	CONSTRAINT fk_scheme_channel FOREIGN KEY (channel_id) REFERENCES tbl_channel (id),
	CONSTRAINT fk_scheme_center FOREIGN KEY (center_id) REFERENCES tbl_center (id),
	CONSTRAINT fk_scheme_division FOREIGN KEY (division_id) REFERENCES tbl_division (id),
	CONSTRAINT fk_scheme_division_proposal FOREIGN KEY (division_proposal_id) REFERENCES tbl_division (id),
	CONSTRAINT fk_scheme_department FOREIGN KEY (department_id) REFERENCES tbl_department (id),
	CONSTRAINT fk_scheme_campaign FOREIGN KEY (campaign_id) REFERENCES tbl_campaign (id),
	CONSTRAINT fk_scheme_data_source FOREIGN KEY (source) REFERENCES tbl_data_source (source)
);

CREATE TABLE IF NOT EXISTS "tbl_scheme_status" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"scheme_id" INTEGER NOT NULL,
	"status" VARCHAR(128) NOT NULL, --DRAFT, ACTIVE, INACTIVE
	"updated_date" TIMESTAMP NULL DEFAULT NULL,
	"updated_by" VARCHAR(128) NULL DEFAULT NULL,
	CONSTRAINT fk_scheme_status FOREIGN KEY (scheme_id) REFERENCES tbl_scheme (id)
);

CREATE TABLE IF NOT EXISTS "tbl_scheme_parameter" (
	"scheme_id" INTEGER NOT NULL,
	"parameter_id" INTEGER NOT NULL,
	PRIMARY KEY (scheme_id, parameter_id),
	CONSTRAINT fk_scheme_parameter_scheme FOREIGN KEY (scheme_id) REFERENCES tbl_scheme (id),
	CONSTRAINT fk_scheme_parameter_parameter FOREIGN KEY (parameter_id) REFERENCES tbl_parameter (id)
);

CREATE TABLE IF NOT EXISTS "tbl_scheme_product" (
	"scheme_id" INTEGER NOT NULL,
	"product_id" INTEGER NOT NULL,
	PRIMARY KEY (scheme_id, product_id),
	CONSTRAINT fk_scheme_product_scheme FOREIGN KEY (scheme_id) REFERENCES tbl_scheme (id),
	CONSTRAINT fk_scheme_product_product FOREIGN KEY (product_id) REFERENCES tbl_product (id)
);

CREATE TABLE IF NOT EXISTS "tbl_scheme_subproduct" (
	"scheme_id" INTEGER NOT NULL,
	"product_id" INTEGER NOT NULL,
	PRIMARY KEY (scheme_id, product_id),
	CONSTRAINT fk_scheme_subproduct_scheme FOREIGN KEY (scheme_id) REFERENCES tbl_scheme (id),
	CONSTRAINT fk_scheme_subproduct_product FOREIGN KEY (product_id) REFERENCES tbl_product (id)
);

CREATE TABLE IF NOT EXISTS "tbl_process_status" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"process_id" INTEGER NOT NULL,
	"scheme_id" INTEGER NOT NULL,
	"status" VARCHAR(128) NOT NULL, --SUCCESS, FAIL
	"execute_date" TIMESTAMP NULL DEFAULT NULL,
	"execute_by" VARCHAR(128) NULL DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS "tbl_scheme_position" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"position_id" INTEGER NOT NULL,
	"scheme_id" INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS "tbl_process_report" (
    "id" SERIAL PRIMARY KEY NOT NULL,
    "name" VARCHAR(200) NOT NULL,
    "source" VARCHAR(128) NULL DEFAULT NULL,
    "template" VARCHAR(128) NULL DEFAULT NULL,
    "description" VARCHAR(4000) NULL DEFAULT NULL,
    "scheme_id" INTEGER NOT NULL,
    "process_id" INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS "tbl_scheme_executor" (
    "id" SERIAL PRIMARY KEY NOT NULL,
    "executor_id" VARCHAR(128) NOT NULL, --YYMMDDhhmmss
    "process_id" INTEGER NOT NULL,
    "scheme_id" INTEGER NOT NULL,
    "type" VARCHAR(128) NOT NULL, --REVISE, RERUN, RUN
    "result_file" VARCHAR(128) NULL DEFAULT NULL, --result
    "status" VARCHAR(128) NOT NULL, --INPROGRESS, COMPLETE, FAILED
    "execute_month" VARCHAR(128) NULL DEFAULT NULL,
    "execute_date" TIMESTAMP NULL DEFAULT NULL,
    "execute_by" VARCHAR(128) NULL DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS "tbl_data_source_input" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"type" VARCHAR(128) NOT NULL, --file or db
	"name" VARCHAR(128) NOT NULL,
	"value" VARCHAR(128) NOT NULL, -- if type is "file" value is file name. If type is "db", value is connection string
	"data_source_id" INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS "tbl_parameter_condition" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"parameter_id" INTEGER NOT NULL,
	"name" VARCHAR(200) NOT NULL,
	"value" VARCHAR(200) NOT NULL,
	"updated_date" TIMESTAMP NULL DEFAULT NULL,
	"updated_by" VARCHAR(128) NULL DEFAULT NULL,
	CONSTRAINT fk_parameter_condition FOREIGN KEY (parameter_id) REFERENCES tbl_parameter (id)
);

CREATE TABLE IF NOT EXISTS "tbl_user_group" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"code" VARCHAR(128) UNIQUE NOT NULL,
	"name" VARCHAR(128) UNIQUE NOT NULL,
	"description" VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS "tbl_user_role" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"name" VARCHAR(200) UNIQUE NOT NULL,
	"description" VARCHAR(4000),
	"user_group_id" INTEGER NOT NULL,
	"permission" integer[],
	"created_date"  TIMESTAMP,
	"created_by" INTEGER,
	"updated_date" TIMESTAMP,
	"updated_by" INTEGER,
	CONSTRAINT fk_user_role_user_group_user_group_id FOREIGN KEY (user_group_id) REFERENCES tbl_user_group (id)
);

CREATE TABLE IF NOT EXISTS "tbl_ldap_user" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"email" VARCHAR(200) UNIQUE NOT NULL,
	"password" varchar(200),
	"fullname" VARCHAR(200),
	"title" VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS "tbl_user" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"email" VARCHAR(200) UNIQUE NOT NULL,
	"fullname" VARCHAR(200),
	"title" VARCHAR(200),
	"user_role_id" INTEGER NOT NULL,
	"status" VARCHAR(50) NOT NULL,
	"remark" VARCHAR(4000) NOT NULL,
	"user_type" INTEGER NOT NULL DEFAULT 2, -- 1: admin, 2 normal.
	"created_date" TIMESTAMP,
	"created_by" INTEGER,
	"updated_date" TIMESTAMP,
	"updated_by" INTEGER,
	CONSTRAINT fk_user_role_user_group_user_group_id FOREIGN KEY (user_role_id) REFERENCES tbl_user_role (id)
);
COMMENT ON COLUMN tbl_user.user_type is '1: admin, 2: normal user';

CREATE TABLE IF NOT EXISTS "tbl_user_loggined" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"user_id" INTEGER NOT NULL,
	"token" varchar(200) UNIQUE NOT NULL,
	"last_accessed_time" TIMESTAMP,
	"is_expired" BOOLEAN NOT NULL DEFAULT FALSE,
	"created_date"  TIMESTAMP,
	"created_by" INTEGER,
	"updated_date" TIMESTAMP,
	"updated_by" INTEGER,
	CONSTRAINT fk_user_loggined_user_user_id FOREIGN KEY (user_id) REFERENCES tbl_user (id)
);

ALTER TABLE tbl_scheme
  ALTER COLUMN scheme_reference_id TYPE VARCHAR(4000);
 ALTER TABLE tbl_scheme DROP CONSTRAINT tbl_scheme_identifier_key;