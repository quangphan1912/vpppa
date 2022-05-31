-- View: public.vw_parameter
DROP VIEW IF EXISTS public.vw_parameter;
CREATE OR REPLACE VIEW vw_parameter AS
SELECT pa.id AS id,
       pa.name AS name,
       pa.description AS description,
       pa.note AS note,
       pa.status AS status,
       sc.scheme_id AS scheme_id,
       sc.scheme_name AS scheme_name,
	   CONCAT('[', ARRAY_TO_STRING(ARRAY(SELECT DISTINCT unnest (STRING_TO_ARRAY(sc.product_id, ', '))), ', '), ']') AS product_id,
       ARRAY_TO_STRING(ARRAY(SELECT DISTINCT unnest (STRING_TO_ARRAY(sc.product_name, ', '))), ', ') AS product_name,
	   CONCAT('[', ARRAY_TO_STRING(ARRAY(SELECT DISTINCT unnest (STRING_TO_ARRAY(sc.division_id, ', '))), ', '), ']') AS division_id,
       ARRAY_TO_STRING(ARRAY(SELECT DISTINCT unnest (STRING_TO_ARRAY(sc.division_name, ', '))), ', ') AS division_name,
	   CONCAT('[', ARRAY_TO_STRING(ARRAY(SELECT DISTINCT unnest (STRING_TO_ARRAY(sc.center_id, ', '))), ', '), ']') AS center_id,
       ARRAY_TO_STRING(ARRAY(SELECT DISTINCT unnest (STRING_TO_ARRAY(sc.center_name, ', '))), ', ') AS center_name,
	   pa.created_date AS created_date,
	   pa.updated_date AS updated_date
FROM tbl_parameter pa
FULL JOIN
  (SELECT sc.id AS id,
  		  string_agg(DISTINCT sc.division_id::varchar, ', ')AS division_id,
          string_agg(DISTINCT sc.division_name, ', ')AS division_name,
		  string_agg(DISTINCT sc.center_id::varchar, ', ')AS center_id,
          string_agg(DISTINCT sc.center_name, ', ')AS center_name,
          string_agg(DISTINCT sc.scheme_id, ', ')AS scheme_id,
          string_agg(DISTINCT sc.scheme_name, ', ')AS scheme_name,
          string_agg(DISTINCT sc.product_id, ', ')AS product_id,
		  string_agg(DISTINCT sc.product_name, ', ')AS product_name
   FROM
     (SELECT pa.id AS id,
             sc.identifier AS scheme_id,
             sc.name AS scheme_name,
             sc.product_id AS product_id,
             sc.product_name AS product_name,
             sc.division_id AS division_id,
             sc.division_name AS division_name,
             sc.center_id AS center_id,
             sc.center_name AS center_name
      FROM tbl_parameter pa
      LEFT JOIN
        (SELECT spr.id AS id,
                spr.identifier AS identifier,
                spr.name AS name,
                spr.product_id AS product_id,
                spr.product_name AS product_name,
                sp.parameter_id AS parameter_id,
                di.id AS division_id,
                di.name AS division_name,
                ce.id AS center_id,
                ce.name AS center_name
         FROM
           (SELECT sc.id AS id,
                   sc.identifier AS identifier,
                   sc.name AS name,
                   string_agg(DISTINCT pr.id::varchar, ', ') AS product_id,
                   string_agg(DISTINCT pr.name, ', ') AS product_name,
                   sc.division_id AS division_id,
                   sc.center_id AS center_id
            FROM tbl_scheme sc
            LEFT JOIN tbl_scheme_product spr ON sc.id = spr.scheme_id
            LEFT JOIN tbl_product pr ON spr.product_id = pr.id
            GROUP BY sc.id) AS spr
         LEFT JOIN tbl_division di ON spr.division_id = di.id
         LEFT JOIN tbl_center ce ON spr.center_id = ce.id
         LEFT JOIN tbl_scheme_parameter sp ON spr.id = sp.scheme_id) sc ON pa.id = sc.parameter_id) sc
   GROUP BY sc.id) sc ON sc.id = pa.id;