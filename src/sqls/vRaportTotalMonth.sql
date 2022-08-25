DROP VIEW VRAPORTTOTALMONTH;

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
