FROM docker.io/java

MAINTAINER root

COPY user.jar /app.jar

ENV JAVA_OPTS="\
-server \
-Xmx300m \
-Xms300m "

EXPOSE 10002
ENTRYPOINT ["java","-jar","/app.jar"]