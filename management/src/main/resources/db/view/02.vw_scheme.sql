-- View: public.vw_scheme
DROP VIEW IF EXISTS vw_scheme;
CREATE OR REPLACE VIEW vw_scheme AS
SELECT 	spr.id AS id,
		spr.identifier AS scheme_id,
		spr.name AS scheme_name,
		spr.source AS source,
		spr.status AS status,
		spr.status_date AS status_date,
		spr.effectived_date AS effectived_date,
		spr.expired_date AS expired_date,
		spr.actual_effectived_date AS actual_effectived_date,
		spr.actual_expired_date AS actual_expired_date,
		CONCAT('[', ARRAY_TO_STRING(ARRAY(SELECT DISTINCT unnest (STRING_TO_ARRAY(spr.product_id, ', '))), ', '), ']') AS product_id,
		spr.product_name AS product_name,
		CONCAT('[', ARRAY_TO_STRING(ARRAY(SELECT DISTINCT unnest (STRING_TO_ARRAY(spr.subproduct_id, ', '))), ', '), ']') AS subproduct_id,
		spr.subproduct_name AS subproduct_name,
		di.id AS division_id,
		di.name AS division_name,
		dp.id AS division_proposal_id,
		dp.name AS division_proposal_name,
		ce.id AS center_id,
		ce.name AS center_name,
		pc.id AS process_id,
		pc.name AS process_name,
		cn.id AS channel_id,
		cn.name AS channel_name,
		de.id AS department_id,
		de.name AS department_name,
		cp.id AS campaign_id,
		cp.name AS campaign_name,
		CONCAT('[', ARRAY_TO_STRING(ARRAY(SELECT DISTINCT unnest (STRING_TO_ARRAY(spr.position_id, ', '))), ', '), ']') AS position_id,
		spr.position_name AS position_name,
		st.id AS scheme_type_id,
		st.name AS scheme_type_name,
		se.execute_date as last_run_time,
		se.result_file,
		spr.created_date AS created_date,
		spr.updated_date AS updated_date
FROM
   (SELECT sc.id AS id,
		   sc.identifier AS identifier,
		   sc.name AS name,
		   sc.source AS source,
		   sc.status AS status,
		   sc.status_date AS status_date,
		   sc.effectived_date AS effectived_date,
		   sc.expired_date AS expired_date,
		   sc.actual_effectived_date AS actual_effectived_date,
		   sc.actual_expired_date AS actual_expired_date,
		   string_agg(DISTINCT pr.id::varchar, ', ') AS product_id,
		   string_agg(DISTINCT pr.name, ', ') AS product_name,
		   string_agg(DISTINCT subpr.id::varchar, ', ') AS subproduct_id,
		   string_agg(DISTINCT subpr.name, ', ') AS subproduct_name,
		   sc.division_id AS division_id,
		   sc.division_proposal_id AS division_proposal_id,
		   sc.center_id AS center_id,
		   sc.process_id AS process_id,
		   sc.channel_id AS channel_id,
		   sc.department_id AS department_id,
		   sc.campaign_id AS campaign_id,
		   string_agg(DISTINCT po.id::varchar, ', ') AS position_id,
		   string_agg(DISTINCT po.name, ', ') AS position_name,
		   sc.scheme_type_id AS scheme_type_id,
		   sc.created_date AS created_date,
		   sc.updated_date AS updated_date
	FROM tbl_scheme sc
		LEFT JOIN tbl_scheme_product spr ON sc.id = spr.scheme_id
		LEFT JOIN tbl_product pr ON spr.product_id = pr.id
		LEFT JOIN tbl_scheme_subproduct scspr ON sc.id = scspr.scheme_id
		LEFT JOIN tbl_product subpr ON scspr.product_id = subpr.id
		LEFT JOIN tbl_scheme_position spo ON sc.id = spo.scheme_id
		LEFT JOIN tbl_position po ON spo.position_id = po.id
		GROUP BY sc.id) AS spr		
	LEFT JOIN tbl_division di ON spr.division_id = di.id
	LEFT JOIN tbl_division dp ON spr.division_proposal_id = dp.id
	LEFT JOIN tbl_center ce ON spr.center_id = ce.id
	LEFT JOIN tbl_process pc ON spr.process_id = pc.id
	LEFT JOIN tbl_channel cn ON spr.channel_id = cn.id
	LEFT JOIN tbl_department de ON spr.department_id = de.id
	LEFT JOIN tbl_campaign cp ON spr.campaign_id = cp.id
	LEFT JOIN tbl_scheme_type st ON spr.scheme_type_id = st.id
	LEFT JOIN 
	(
		SELECT se1.id, se1.scheme_id, se1.process_id, se1.execute_date, se1.result_file
		FROM tbl_scheme_executor se1
		INNER JOIN 
		(
			SELECT scheme_id, process_id, max(execute_date) as execute_date
			FROM tbl_scheme_executor
			GROUP BY scheme_id, process_id
		) AS se2 ON se1.scheme_id = se2.scheme_id
			  	AND se1.process_id = se2.process_id
				AND se1.execute_date = se2.execute_date
	) AS se ON spr.id = se.scheme_id AND spr.process_id = se.process_id;
	