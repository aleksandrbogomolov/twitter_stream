FROM java:8-jre

ENV SPARK_FILE consumer.jar

# Set vertx option
ENV VERTX_OPTIONS "-cluster"

# Set the location of the spark
ENV SPARK_HOME /

# Set hazelcast options
ENV HAZELCAST_OPTIONS -Dhazelcast.max.no.heartbeat.seconds=30 -Dhazelcast.merge.first.run.delay.seconds=3 -Dhazelcast.merge.next.run.delay.seconds=3 -Dhazelcast.max.join.merge.target.seconds=5

# Copy fat jar to the container
COPY ./target/$SPARK_FILE $SPARK_HOME

# Launch the spark
WORKDIR $SPARK_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java $HAZELCAST_OPTIONS -jar $SPARK_FILE $VERTX_OPTIONS"]