@echo off
setlocal enableextensions enabledelayedexpansion
cd bases
echo ------------------------------rotate all--------------------------------------
cd RotateThese
for /r  %%i in (*.*) do (
	echo %%i
	copy "%%i" "%~dp0\readyToMakeSheet\%%~nxi"
	convert "%%i" -rotate -90 "%~dp0\readyToMakeSheet\%%~ni-90%%~xi"
	convert "%%i" -rotate -180 "%~dp0\readyToMakeSheet\%%~ni-180%%~xi"
	convert "%%i" -rotate -270 "%~dp0\readyToMakeSheet\%%~ni-270%%~xi"
)
cd ..\
echo -------------------------------flip------------------------------------------
cd FlipThese
for /r %%i in (*.*) do (
	echo %%i
	copy "%%i" "%~dp0\readyToMakeSheet\%%~nxi"
	convert "%%i" -flip "%~dp0\readyToMakeSheet\%%~ni-flipped%%~xi"
)
cd ..\
echo -----------------------------rotate once-------------------------------------
cd RotateOnce
for /r %%i in (*.*) do (
	echo %%i
	copy "%%i" "%~dp0\readyToMakeSheet\%%~nxi"
	convert "%%i" -rotate -90 "%~dp0\readyToMakeSheet\%%~ni-90%%~xi"
)
cd ..\
echo -----------------------------copy---------------------------------------------
cd CopyThese
for /r %%i in (*.*) do (
	echo %%i
	copy "%%i" "%~dp0\readyToMakeSheet\%%~nxi"
)
cd ..\