@echo off
cd /d "%~dp0"
if not exist out mkdir out
dir /s /b src\\main\\java\\*.java > sources.txt
javac -encoding UTF-8 -d out @sources.txt
if errorlevel 1 (pause & exit /b 1)
del sources.txt
java -cp out kz.studysprint.App
