# run.sh

repository="https://github.com/csathenaryu/CSE364_Group10.git"
folder="CSE364_Group10"

git clone $repository

cd $folder
git checkout develop_milestone3

mvn package

java -jar target/CSE364-project-0.0.1-SNAPSHOT.jar