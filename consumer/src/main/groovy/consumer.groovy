def eb = vertx.eventBus()

eb.consumer("tweet_feed", { msg -> println(msg.body()) })