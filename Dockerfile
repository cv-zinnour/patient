FROM gradle:5.6.2-jdk11 as java-build
EXPOSE 8764
WORKDIR /opt/pod-isante/patient
COPY build/libs/patient-0.0.1-SNAPSHOT.jar patient-server.jar
ENTRYPOINT ["java","-jar","/opt/pod-isante/patient/patient-server.jar"]


#docker pull lahcenezinnour/patient-docker-img:latest
#docker run -p 8764:8764 -t lahcenezinnour/patient-docker-img:latest
# docker build -t patient-docker-img .
#docker tag patient-docker-img lahcenezinnour/patient-docker-img
#
#docker push lahcenezinnour/patient-docker-img