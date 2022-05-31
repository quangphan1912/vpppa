DROP VIEW IF EXISTS vw_scheme_executor;	
CREATE OR REPLACE VIEW vw_scheme_executor AS
SELECT se.id,
    se.executor_id,
    se.result_file,
    se.status,
    se.type,
    sc.id AS scheme_id,
    sc.identifier AS scheme_id_name,
    sc.name AS scheme_name,
    pc.id AS process_id,
    pc.name AS process_name,
    se.execute_month,
    se.execute_date,
    se.execute_by,
    sc.status AS status_scheme
FROM
(
   SELECT se1.id,
    se1.scheme_id,
    se1.process_id,
    se1.execute_date,
    se1.result_file,
    se1.execute_month,
    se1.executor_id,
    se1.status,
    se1.type,
    se1.execute_by
   FROM tbl_scheme_executor se1
   JOIN
   (
        SELECT tbl_scheme_executor.scheme_id,
            tbl_scheme_executor.process_id,
            max(tbl_scheme_executor.execute_date) AS execute_date
        FROM tbl_scheme_executor
        GROUP BY tbl_scheme_executor.scheme_id,
        	tbl_scheme_executor.type,
          tbl_scheme_executor.process_id
   ) se2 ON se1.scheme_id = se2.scheme_id
        AND se1.process_id = se2.process_id
        AND se1.execute_date = se2.execute_date
) as se
LEFT JOIN tbl_scheme sc ON sc.id = se.scheme_id
LEFT JOIN tbl_process pc ON se.process_id = pc.id
ORDER BY se.id;