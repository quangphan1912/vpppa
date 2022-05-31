-- product/sub-product.
INSERT INTO public.tbl_product(id, code, name, parent_id) VALUES (1, 'PL', 'PL', null);
INSERT INTO public.tbl_product(id, code, name, parent_id) VALUES (2, 'CRC', 'CRC', null);
INSERT INTO public.tbl_product(id, code, name, parent_id) VALUES (3, 'CDL', 'CDL', null);
INSERT INTO public.tbl_product(id, code, name, parent_id) VALUES (4, 'TW', 'TW', null);
INSERT INTO public.tbl_product(id, code, name, parent_id) VALUES (5, 'XSTU', 'XSTU', 1);
INSERT INTO public.tbl_product(id, code, name, parent_id) VALUES (6, 'NTB', 'NTB', 1);
INSERT INTO public.tbl_product(id, code, name, parent_id) VALUES (7, 'DCARD', 'DCARD', 2);
INSERT INTO public.tbl_product(id, code, name, parent_id) VALUES (8, 'CARD_BUNDLE', 'CARD_BUNDLE', 2);
INSERT INTO public.tbl_product(id, code, name, parent_id) VALUES (9, 'CRC_XSTU', 'CRC_XSTU', 2);
INSERT INTO public.tbl_product(id, code, name, parent_id) VALUES (10, 'CRC_NTB', 'CRC_NTB', 2);
INSERT INTO public.tbl_product(id, code, name, parent_id) VALUES (11, 'CDL', 'CDL', 3);
INSERT INTO public.tbl_product(id, code, name, parent_id) VALUES (12, 'TW', 'TW', 4);