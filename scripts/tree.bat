@echo off

setlocal enableextensions

set "dir=%cd%"

if "%1" neq "" (
  set "dir=%1"
)

for /f "delims=" %%a in ('dir /b /s /a-d "%dir%"') do (
  set "path=%%~dpa"
  set "path=!path:%cd%\=!"
  set "path=!path:~0,-1!"
  call :printPath !path!
  echo %%~nxa
)

exit /b

:printPath
set "path=%*"
set "prefix="
:loop
echo %prefix%%path:~0,2%
if "%path:~2%" neq "" (
  set "prefix=%prefix%|  "
  set "path=%path:~2%"
  goto :loop
)
exit /b