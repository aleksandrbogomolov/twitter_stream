FROM java:8-jre

ENV SPARK_FILE produser.jar

# Set vertx option
ENV VERTX_OPTIONS "-cluster"

# Set the location of the spark
ENV SPARK_HOME /

# Copy fat jar to the container
COPY ./target/$SPARK_FILE $SPARK_HOME

# Launch the spark
WORKDIR $SPARK_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar $SPARK_FILE $VERTX_OPTIONS"]