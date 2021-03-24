# run.sh

repository="https://github.com/csathenaryu/CSE364_Group10.git";
folder="CSE364_Group10"

# NAME=USER_NAME
# PASS=PASSWORD


# using expect, enter the username and password automatically

# expect<<EOF
# spawn git clone $repository
# expect "Username"
# send $NAME\r
# expect "Password"
# send $PASS\r
# expect eof
# EOF

git clone $repository

cd $folder

# mvn install
# JAVA -cp ~ (will be added later)
