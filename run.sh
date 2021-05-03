# run.sh

repository="https://github.com/csathenaryu/CSE364_Group10.git"
folder="CSE364_Group10"

git clone $repository

cd $folder
git checkout main

mvn install
mvn assembly:assembly

java -cp target/CSE364-project-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.Milestone2 "" "" ""
java -cp target/CSE364-project-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.Milestone2 "" "" "" "Adventure"
