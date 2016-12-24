FROM java:8-jre

ENV VERTICLE_FILE web.jar

# Set vertx option
ENV VERTX_OPTIONS "-cluster"

# Set the location of the verticles
ENV VERTICLE_HOME /

EXPOSE 8080

# Copy your fat jar to the container
COPY ./target/web.jar $VERTICLE_HOME

# Launch the verticle
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar $VERTICLE_FILE $VERTX_OPTIONS"]