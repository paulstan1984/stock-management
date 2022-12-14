ALTER TABLE TCALENDAR
ADD COLUMN DATA_MONTH VARCHAR(7);

CREATE INDEX DATA_MONTH_1
ON TCALENDAR (DATA_MONTH);

UPDATE TCALENDAR
SET DATA_MONTH = SUBSTR(CAST("DATA" AS VARCHAR(10)), 1, 7);


ALTER TABLE TOPERATII
ADD COLUMN DATA_MONTH VARCHAR(7);

CREATE INDEX DATA_MONTH_2
ON TOPERATII (DATA_MONTH);

UPDATE TOPERATII
SET DATA_MONTH = SUBSTR(CAST("DATA" AS VARCHAR(10)), 1, 7);


ALTER TABLE TRAPORTTOTALDAY
ADD COLUMN DATA_MONTH VARCHAR(7);

CREATE INDEX TRT_DATA_MONTH
ON TRAPORTTOTALDAY (DATA_MONTH);

UPDATE TRAPORTTOTALDAY
SET DATA_MONTH = SUBSTR(CAST("DATA" AS VARCHAR(10)), 1, 7);



DROP VIEW VRAPORTTOTALMONTH;

CREATE VIEW VRAPORTTOTALMONTH AS
SELECT R.DATA_MONTH AS "DATA",R.IDPRODUS, P.DENUMIRE, UM, 
    SUM(PRETINTRARE) AS PRETINTRARE,SUM(PRETIESIRE) AS PRETIESIRE,
    SUM(CANTITATEINTRARE) AS CANTITATEINTRARE,SUM(CANTITATEIESIRE) AS CANTITATEIESIRE,
    S.STOC,S.DATA_MONTH
FROM TPRODUSE P
JOIN TRAPORTTOTALDAY R ON P.ID=R.IDPRODUS
JOIN VSTOCENDOFMONTH S ON R.IDPRODUS=S.IDPRODUS AND R.DATA_MONTH=S.DATA_MONTH
GROUP BY R.DATA_MONTH,S.DATA_MONTH,
         P.DENUMIRE, R.IDPRODUS,P.UM, S.STOC
ORDER BY R.DATA_MONTH;





DROP VIEW VSTOCTOTAL;
DROP VIEW VLASTSTOCMAPPINGS;
DROP VIEW VRAPORTTOTALMONTH;
DROP VIEW VSTOCENDOFMONTH;
DROP VIEW VRAPORTTOTALDAY;


CREATE VIEW VRAPORTTOTALDAY AS
SELECT R.*, P.DENUMIRE,P.UM
FROM TPRODUSE P
JOIN TRAPORTTOTALDAY R
ON P.ID=R.IDPRODUS
ORDER BY R."DATA";

CREATE VIEW VSTOCENDOFMONTH AS
SELECT R1.DATA AS DATA, R1.DATA_MONTH, R1.IDPRODUS, R1.STOC
FROM VRAPORTTOTALDAY R1
JOIN (SELECT MAX("DATA") AS "DATA", DATA_MONTH, IDPRODUS 
      FROM VRAPORTTOTALDAY
      GROUP BY IDPRODUS, DATA_MONTH
     ) R2
ON R1.IDPRODUS=R2.IDPRODUS and R1.DATA=R2.DATA;

CREATE VIEW VRAPORTTOTALMONTH AS 
SELECT R.DATA_MONTH AS "DATA",R.IDPRODUS, P.DENUMIRE, UM, 
    SUM(PRETINTRARE) AS PRETINTRARE,SUM(PRETIESIRE) AS PRETIESIRE,
    SUM(CANTITATEINTRARE) AS CANTITATEINTRARE,SUM(CANTITATEIESIRE) AS CANTITATEIESIRE,
    S.STOC
FROM TPRODUSE P
JOIN TRAPORTTOTALDAY R ON P.ID=R.IDPRODUS
JOIN VSTOCENDOFMONTH S ON R.IDPRODUS=S.IDPRODUS AND R.DATA_MONTH=S.DATA_MONTH
GROUP BY R.DATA_MONTH,
         P.DENUMIRE, R.IDPRODUS,P.UM, S.STOC
ORDER BY R.DATA_MONTH;

CREATE VIEW VLASTSTOCMAPPINGS AS
SELECT 
    IDPRODUS,
    C.DATA_MONTH AS DATA, 
    MAX(S."DATA") AS PREVDATE
FROM (SELECT DISTINCT DATA_MONTH FROM TCALENDAR) C
JOIN VSTOCENDOFMONTH S
ON C.DATA_MONTH >= S.DATA_MONTH
GROUP BY C.DATA_MONTH, IDPRODUS;

CREATE VIEW VSTOCTOTAL AS 
SELECT 
    C.DATA_MONTH AS DATA, 
    P.DENUMIRE,P.UM,PV.IDPRODUS,P.CATEGORIE,
    PV.STOC    
FROM (SELECT DISTINCT DATA_MONTH FROM TCALENDAR) C
JOIN VLASTSTOCMAPPINGS SM 
    ON C.DATA_MONTH=SM."DATA"
JOIN VSTOCENDOFMONTH PV 
    ON SM.IDPRODUS=PV.IDPRODUS AND SM.PREVDATE=PV."DATA"
JOIN TPRODUSE P 
    ON SM.IDPRODUS=P.ID;
