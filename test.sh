#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
mkdir -p out
find src/main/java src/test/java -name '*.java' > sources.txt
javac -encoding UTF-8 -d out @sources.txt
rm sources.txt
java -cp out kz.studysprint.StudySprintTests
