DROP VIEW IF EXISTS public.vw_process_report;
CREATE OR REPLACE VIEW vw_process_report AS
SELECT pr.id AS id,
	   pr.name AS name,
	   pr.description AS description,
	   pr.source AS source,
	   pr.template AS template,
	   sc.id AS scheme_id,
	   sc.identifier AS scheme_id_name,
	   sc.name AS scheme_name,
	   sc.status,
	   pr.process_id AS process_id
	FROM tbl_process_report pr
		LEFT JOIN tbl_scheme sc ON sc.id = pr.scheme_id
ORDER BY id;