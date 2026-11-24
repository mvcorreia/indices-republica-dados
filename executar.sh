#!/usr/bin/env sh
set -eu

cd "$(dirname "$0")"
mkdir -p out

echo "Compilando o Sistema Índices 2.0..."
javac -encoding UTF-8 -cp "lib/*" -d out src/*.java

echo "Iniciando o sistema..."
java -cp "out:lib/*" SistemaIndices
