::[Bat To Exe Converter]
::
::fBE1pAF6MU+EWHreyEY1Ph5YQxSPOWWuOqEJ7dSpxtre8g0tRu04ao7W172AJ9ww4kDnfqU5z3ZfpMgECRhTbQayYgp6o29Q1g==
::fBE1pAF6MU+EWHreyEY1Ph5YQxSPOWWuOqEJ7dSpxtre8g0tRu04ao7W172AJ9ww4kDnfqU5z3ZfpMgECRhTbQayYgoLu3hR+HyIO6c=
::YAwzoRdxOk+EWAjk
::fBw5plQjdG8=
::YAwzuBVtJxjWCl3EqQJgSA==
::ZR4luwNxJguZRRnk
::Yhs/ulQjdF+5
::cxAkpRVqdFKZSDk=
::cBs/ulQjdF+5
::ZR41oxFsdFKZSDk=
::eBoioBt6dFKZSDk=
::cRo6pxp7LAbNWATEpSI=
::egkzugNsPRvcWATEpSI=
::dAsiuh18IRvcCxnZtBJQ
::cRYluBh/LU+EWAnk
::YxY4rhs+aU+IeA==
::cxY6rQJ7JhzQF1fEqQJQ
::ZQ05rAF9IBncCkqN+0xwdVs0
::ZQ05rAF9IAHYFVzEqQJQ
::eg0/rx1wNQPfEVWB+kM9LVsJDCeKOWixNaEF5O3J+dnn
::fBEirQZwNQPfEVWB+kM9LVsJDGQ=
::cRolqwZ3JBvQF1fEqQJQ
::dhA7uBVwLU+EWGuSxyI=
::YQ03rBFzNR3SWATElA==
::dhAmsQZ3MwfNWATElA==
::ZQ0/vhVqMQ3MEVWAtB9wSA==
::Zg8zqx1/OA3MEVWAtB9wSA==
::dhA7pRFwIByZRRnk
::Zh4grVQjdCyDJF2B4kc8JwtZSQqWAH+vA4lOwdiiv6q3sEIaRuMydYbV3YitKOUQ4XbwbJoo6llSncgHPw9BcheZeDx6rHZH1g==
::YB416Ek+ZG8=
::
::
::978f952a14a936cc963da21a135fa983
@echo off
set /p TESTPATH="Enter Path to your .java to check: "
start /wait /b "" "java" -jar checkstyle.jar -c checkstyle_rvs.xml "%TESTPATH%"
pause