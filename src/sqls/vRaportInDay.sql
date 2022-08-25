
SELECT I.Data, I.IDProdus, P.Denumire, P.UM, 
    SUM(I.Pret) AS PretIntrare,
    SUM(I.Cantitate) AS CantitateIntrare
FROM VINTRARI I
JOIN TPRODUSE P ON I.IDprodus = P.ID
GROUP BY I.Data, I.IDProdus, P.Denumire, P.UM
ORDER BY I.Data DESC, P.Denumire