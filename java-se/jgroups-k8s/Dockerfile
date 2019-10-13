FROM azul/zulu-openjdk-alpine:12

RUN mkdir /app
WORKDIR /app

COPY build/libs/jgroups-k8s.jar /app/jgroups-k8s.jar
RUN chmod +x /app/jgroups-k8s.jar

EXPOSE 9000
CMD java -jar /app/jgroups-k8s.jar
