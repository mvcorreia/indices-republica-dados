@echo off
cd /d "%~dp0"

if not exist out mkdir out

echo Compilando o Sistema Indices 2.0...
javac -encoding UTF-8 -cp "lib/*" -d out src\*.java
if errorlevel 1 (
    echo.
    echo Falha na compilacao. Confira se o JDK 11 ou superior esta instalado.
    pause
    exit /b 1
)

echo Iniciando o sistema...
java -cp "out;lib/*" SistemaIndices
pause
