## 프로그래머스 - LV4 자동차 대여 기록 별 대여 금액 구하기
SELECT H.HISTORY_ID,
ROUND(C.DAILY_FEE * (DATEDIFF(H.END_DATE, H.START_DATE) + 1) *
(1 - COALESCE(P.DISCOUNT_RATE / 100, 0))) AS FEE
FROM CAR_RENTAL_COMPANY_CAR C
JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY H
ON C.CAR_ID = H.CAR_ID
LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN P
ON C.CAR_TYPE = P.CAR_TYPE
AND (P.DURATION_TYPE =
CASE
WHEN DATEDIFF(H.END_DATE, H.START_DATE) + 1 >= 90 THEN '90일 이상'
WHEN DATEDIFF(H.END_DATE, H.START_DATE) + 1 >= 30 THEN '30일 이상'
WHEN DATEDIFF(H.END_DATE, H.START_DATE) + 1 >= 7 THEN '7일 이상'
END)
WHERE C.CAR_TYPE = '트럭'
ORDER BY FEE DESC, H.HISTORY_ID DESC;

## 프로그래머스 - LV4 취소되지 않은 진료 예약 조회하기
SELECT A.APNT_NO, P.PT_NAME, P.PT_NO, A.MCDP_CD, D.DR_NAME, A.APNT_YMD
FROM PATIENT P
JOIN APPOINTMENT A
ON P.PT_NO = A.PT_NO
AND DATE_FORMAT(A.APNT_YMD, '%Y-%m-%d') = '2022-04-13'
AND A.APNT_CNCL_YN ='N'
AND A.MCDP_CD = 'CS'
JOIN DOCTOR D
ON A.MDDR_ID = D.DR_ID
ORDER BY A.APNT_YMD ASC