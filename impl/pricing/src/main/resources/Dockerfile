FROM sdorra/oracle-java-8:latest
VOLUME /tmp
ADD pricing.jar pricing.jar
RUN bash -c 'touch /pricing.jar'
ENTRYPOINT ["java","-jar","/pricing.jar"]