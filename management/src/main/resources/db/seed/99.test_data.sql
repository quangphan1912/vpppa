TRUNCATE tbl_ldap_user RESTART IDENTITY CASCADE;
TRUNCATE tbl_user RESTART IDENTITY CASCADE;
TRUNCATE tbl_user_loggined RESTART IDENTITY CASCADE;
TRUNCATE tbl_user_role RESTART IDENTITY CASCADE;
TRUNCATE tbl_user_group RESTART IDENTITY CASCADE;

-- user ldap.
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (1, 'administrator@vppa.com', 'vppa123@', 'Adminsitrator', 'Administrator');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (2, 'user-director-01@vppa.com', 'vppa123@', 'User director 01', 'Director');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (3, 'user-manager-02@vppa.com', 'vppa123@', 'User manager 02', 'Manager');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (4, 'user-sale-03@vppa.com', 'vppa123@', 'User sale 03', 'Sale');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (5, 'user-it-04@vppa.com', 'vppa123@', 'User IT 04', 'IT Specialis');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (6, 'user01@vppa.com', 'vppa123@', 'user01', 'user01');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (7, 'user02@vppa.com', 'vppa123@', 'user02', 'user02');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (8, 'user03@vppa.com', 'vppa123@', 'user03', 'user03');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (9, 'user04@vppa.com', 'vppa123@', 'user04', 'user04');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (10, 'user05@vppa.com', 'vppa123@', 'user05', 'user05');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (11, 'user06@vppa.com', 'vppa123@', 'user06', 'user06');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (12, 'user07@vppa.com', 'vppa123@', 'user07', 'user07');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (13, 'user08@vppa.com', 'vppa123@', 'user08', 'user08');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (14, 'user09@vppa.com', 'vppa123@', 'user09', 'user09');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (15, 'user10@vppa.com', 'vppa123@', 'user10', 'user10');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (16, 'user11@vppa.com', 'vppa123@', 'user11', 'user11');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (17, 'user12@vppa.com', 'vppa123@', 'user12', 'user12');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (18, 'user13@vppa.com', 'vppa123@', 'user13', 'user13');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (19, 'user14@vppa.com', 'vppa123@', 'user14', 'user14');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (20, 'user15@vppa.com', 'vppa123@', 'user15', 'user15');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (21, 'user16@vppa.com', 'vppa123@', 'user16', 'user16');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (22, 'user17@vppa.com', 'vppa123@', 'user17', 'user17');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (23, 'user18@vppa.com', 'vppa123@', 'user18', 'user18');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (24, 'user19@vppa.com', 'vppa123@', 'user19', 'user19');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (25, 'user20@vppa.com', 'vppa123@', 'user20', 'user20');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (26, 'user21@vppa.com', 'vppa123@', 'user21', 'user21');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (27, 'user22@vppa.com', 'vppa123@', 'user22', 'user22');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (28, 'user23@vppa.com', 'vppa123@', 'user23', 'user23');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (29, 'user24@vppa.com', 'vppa123@', 'user24', 'user24');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (30, 'user25@vppa.com', 'vppa123@', 'user25', 'user25');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (31, 'user26@vppa.com', 'vppa123@', 'user26', 'user26');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (32, 'user27@vppa.com', 'vppa123@', 'user27', 'user27');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (33, 'user28@vppa.com', 'vppa123@', 'user28', 'user28');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (34, 'user29@vppa.com', 'vppa123@', 'user29', 'user29');
INSERT INTO public.tbl_ldap_user(id, email, password, fullname, title) VALUES (35, 'user30@vppa.com', 'vppa123@', 'user30', 'user30');
SELECT setval('public.tbl_ldap_user_id_seq', 35, true);

-- scheme tbl_user_group.
INSERT INTO public.tbl_user_group(id, code, name, description) VALUES (1, 'GP-Admin', 'Administrator', 'Group admin description');
SELECT setval('public.tbl_user_group_id_seq', 1, true);

-- role.
INSERT INTO public.tbl_user_role(id, name, description, user_group_id, permission, created_date, created_by, updated_date, updated_by )
VALUES (1, 'Admin', 'System Administrator', 1, null, '2020-08-26', 1, '2020-08-26', 1);
SELECT setval('public.tbl_user_role_id_seq', 1, true);

-- user.
INSERT INTO public.tbl_user(id, email, fullname, title, user_role_id, status, remark, user_type, created_date, created_by, updated_date, updated_by)
VALUES (1, 'administrator@vppa.com', 'Adminsitrator', 'Administrator', 1, 'Active', 'Administrator Remark', 1,'2020-09-05', 1, '2020-09-05', 1);
SELECT setval('public.tbl_user_id_seq', 1, true);