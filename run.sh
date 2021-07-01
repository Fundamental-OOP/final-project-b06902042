cd src 
rm -rf *.class
cd controller
rm -rf *.class
cd ..
cd model
rm -rf *.class
cd ..
cd view
rm -rf *.class
cd ../..
javac -cp src/ src/*.java
java -cp src/ Main