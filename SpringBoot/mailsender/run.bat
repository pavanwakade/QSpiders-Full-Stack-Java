@echo off
rem Build and run script for the mailsender project
rem Usage: double-click or run from PowerShell/CMD

setlocal

cd /d "%~dp0"

echo ===== Building mailsender =====
if exist mvnw.cmd (
  call .\mvnw.cmd clean package
) else (
  echo Maven wrapper not found. Trying system mvn...
  mvn clean package
)

if ERRORLEVEL 1 (
  echo.
  echo Build failed. See output above.
  pause
  endlocal
  exit /b 1
)

echo.
echo ===== Running mailsender.jar =====
set JAR_PATH=%~dp0target\mailsender.jar
if exist "%JAR_PATH%" (
  java -jar "%JAR_PATH%" %*
) else (
  echo Jar not found at "%JAR_PATH%"
  pause
  endlocal
  exit /b 1
)

endlocal
