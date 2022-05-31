DROP VIEW IF EXISTS public.vw_process;
CREATE OR REPLACE VIEW vw_process AS
SELECT pr.id AS id,
	   pr.name AS name,
	   pr.key AS key,
	   pr.description AS description,
	   pr.expression AS expression,
	   pt.id::varchar AS process_type_id,
	   pt.name AS process_type_name,
	   ct.id::varchar AS center_id,
	   ct.name AS center_name,
	   di.id::varchar AS division_id,
	   di.name AS division_name
	FROM tbl_process pr
		LEFT JOIN tbl_process_type pt ON pt.id = pr.process_type_id
		LEFT JOIN tbl_center ct ON ct.id = pr.center_id
		LEFT JOIN tbl_division di ON di.id = pr.division_id
ORDER BY id;