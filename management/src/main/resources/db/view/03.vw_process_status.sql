DROP VIEW IF EXISTS public.vw_process_status;
CREATE OR REPLACE VIEW vw_process_status AS
SELECT ps.id AS id,
	   ps.process_id AS process_id,
	   pr.name AS process_name,
	   ps.scheme_id AS scheme_id,
	   sc.name AS scheme_name,
	   ps.status AS status,
	   ps.execute_by AS execute_by,
	   ps.execute_date AS execute_date
	FROM tbl_process_status ps
		LEFT JOIN tbl_scheme sc ON sc.id = ps.scheme_id
		LEFT JOIN tbl_process pr ON pr.id = ps.process_id
ORDER BY id;