rm -rf out/

javac -cp src/ src/*.java -d out/
java -cp out/ Main