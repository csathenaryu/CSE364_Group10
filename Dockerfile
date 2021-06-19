FROM ubuntu:20.04
ARG DEBIAN_FRONTEND=noninteractive

RUN mkdir /root/project
WORKDIR /root/project

# add run.sh to /root/project (WORKDIR)
ADD run.sh .

RUN apt-get update
RUN apt-get upgrade -y

# install git
RUN apt-get install -y git

# install vim
RUN apt-get install -y vim

# install curl
RUN apt-get install -y curl

# install java11
RUN apt-get install -y software-properties-common
RUN add-apt-repository ppa:openjdk-r/ppa
RUN apt-get install -y openjdk-11-jdk

# install maven
RUN apt install -y maven

RUN apt-get install wget
RUN wget -qO - https://www.mongodb.org/static/pgp/server-4.4.asc | apt-key add -
RUN echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/4.4 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-4.4.list
RUN apt-get update
RUN apt-get install -y mongodb-org

#RUN mkdir /usr/local/tomcat
#RUN wget  http://apache.tt.co.kr/tomcat/tomcat-9/v9.0.46/bin/apache-tomcat-9.0.46.tar.gz  -O /tmp/tomcat.tar.gz
#RUN cd /tmp && tar xvfz tomcat.tar.gz
#RUN cp -Rv /tmp/apache-tomcat-9.0.46/* /usr/local/tomcat/
#RUN rm -rf /tmp/* && rm -rf /usr/local/tomcat/webapps/*
#COPY CSE364-project-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps


#EXPOSE 8080

#CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]

# install expect for run.sh (may not necessary)
# RUN apt-get install -y expect
