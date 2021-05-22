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

# install expect for run.sh (may not necessary)
# RUN apt-get install -y expect
