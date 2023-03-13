@echo off
setlocal enabledelayedexpansion

set /p dir_path="Enter directory path: "

echo Directory tree for %dir_path%:
echo.

tree /f "%dir_path%" > tree.txt

echo.

for /r "%dir_path%" %%f in (*.class) do (
    echo Contents of %%f:
    type "%%f" >> tree.txt
    echo. >> tree.txt
)

echo Output written to tree.txt.