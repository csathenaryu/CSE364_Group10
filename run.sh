# run.sh

repository="https://github.com/csathenaryu/CSE364_Group10.git"
folder="CSE364_Group10"

git clone $repository

cd $folder
# git checkout main

mvn package

java -jar target/CSE364-project-0.0.1-SNAPSHOT.jar
