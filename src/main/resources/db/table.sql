CREATE TABLE customer(
    id int auto_increment primary key,
    customer_title varchar(100) not null,
    customer_content longtext not null,
    employee_id int not null,
    created_at timestamp not null
);

CREATE TABLE employee (
	id int primary key auto_increment,
    employee_name varchar(255) not null unique,
    employee_password varchar(255) not null,
    employee_fullname char(10) not null,
    employee_email varchar(100) not null,
    employee_tel char(12) not null,
    employee_gender char(1) not null,
    employee_birth date not null,
    employee_address varchar(255) not null,
    employee_thumbnail longtext,
    created_at timestamp not null
);

-- 학교명 테이블
CREATE TABLE school_master (
	id int primary key auto_increment,
    school_name varchar(20) not null,
    created_at timestamp not null
);

-- 자격증 테이블
CREATE TABLE license_master (
	id int primary key auto_increment,
    license_name varchar(20) not null,
    created_at timestamp not null
);

-- 기술 테이블
CREATE TABLE stack_master (
	id int primary key auto_increment,
    stack_name varchar(20) not null,
    created_at timestamp not null
);

-- ----------------------------------------------- employee 최종학력, 경력사항, 자격증, 기술 테이블 ----------------------
-- employee의 최종학력 테이블
CREATE TABLE employee_graduate (
	id int primary key auto_increment,
    employee_id int not null,
    school_id int not null,
    school_graduate varchar(20) not null,
    created_at timestamp not null
);

-- 경력사항 테이블
CREATE TABLE employee_career (
	id int primary key auto_increment,
    employee_id int not null,
    career_company varchar(50) not null,
    career_start date not null,
    career_end date not null,
    created_at timestamp not null
);

-- employee의 자격증 테이블
CREATE TABLE employee_license (
	id int primary key auto_increment,
    employee_id int not null,
    license_id int not null,
    license_issuer varchar(20), -- 발행처
    created_at timestamp not null
);

CREATE TABLE employee_stack (
	id int primary key auto_increment,
    employee_id int not null,
    stack_id int not null,
    stack_acquisition varchar(100) not null,
    created_at timestamp not null
);
-- ---------------------------------------------------------------------------------------------------

CREATE TABLE company(
	id int primary key auto_increment,
    company_username varchar(255) not null,
    company_password varchar(255) not null,
	company_fullname varchar(100) not null,
    company_ceo_name char(10) not null,
    company_license_number char(14) not null,
    company_tel char(14) not null,
    company_address varchar(255) not null,
    company_email varchar(100) not null,
    
    company_thumbnail longtext,
    company_establish date,
    company_sales bigint,
    company_employees_number int,
    company_introduction longtext,
	company_history longtext,
	company_vision longtext,
    
    created_at timestamp not null
);

CREATE TABLE resume(
	id int primary key auto_increment,
    employee_id int not null,
    resume_title varchar(100) not null,
    resume_salary varchar(10) not null,
    CV longtext not null,
    created_at timestamp
);

--------------------------------이력서의 최종학력, 경력사항, 자격증, 기술스택 ---------------

CREATE TABLE resume_graduate (
	id int primary key auto_increment,
    resume_id int not null,
    school_id int not null,
    school_graduate varchar(20) not null,
    created_at timestamp not null
);

-- 경력사항 테이블
CREATE TABLE resume_career (
	id int primary key auto_increment,
    resume_id int not null,
    career_company varchar(50) not null,
    career_start date not null,
    career_end date not null,
    created_at timestamp not null
);

-- employee의 자격증 테이블
CREATE TABLE resume_license (
	id int primary key auto_increment,
    resume_id int not null,
    license_id int not null,
    license_issuer varchar(20), -- 발행처
    created_at timestamp not null
);

CREATE TABLE resume_stack (
	id int primary key auto_increment,
    resume_id int not null,
    stack_id int not null,
    stack_acquisition varchar(100) not null,
    created_at timestamp not null
);
-------------------------------------------------------------------------

-- master 테이블을 바라봐야함.
CREATE TABLE announcement (
	id int primary key auto_increment,
    company_id int not null,
    stack_id int not null, -- stack_master id
    announcement_title varchar(100) not null,
    announcement_content longtext not null,
    announcement_carrer varchar(10) not null,
    announcement_hire_type varchar(10) not null,
    announcement_rec_num int not null,
    announcement_salary varchar(10) not null,
    announcement_area varchar(100) not null,
    created_at timestamp
);

CREATE TABLE apply (
	id int primary key auto_increment,
    announcement_id int not null,
    resume_id int not null,
    created_at timestamp not null
);

CREATE TABLE subscribe (
    id int primary key auto_increment,
    employee_id int not null,
    announcement_id int not null,
    created_at timestamp not null
)