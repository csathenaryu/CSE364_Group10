rm -r testDir
mkdir testDir
cd testDir
git init
git remote add origin https://github.com/csathenaryu/CSE364_Group10.git
git clone origin
git pull origin develop
mvn install
mvn assembly:assembly
java -cp target/CSE364-project-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.Main "adventure|romance" educator