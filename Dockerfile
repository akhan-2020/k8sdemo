FROM moneygram-docker.jfrog.io/java-base-image/amazoncorretto:11
VOLUME /tmp
ARG JAR_FILE

ENV APP_JAR_PATH=/app/app.jar
COPY ${JAR_FILE} ${APP_JAR_PATH}
COPY scripts/docker_entrypoint.sh /app/entrypoint.sh
COPY apm/newrelic.jar /app/newrelic.jar
COPY apm/newrelic-api.jar /app/newrelic-api.jar

ENV NEW_RELIC_APP_NAME="k8sdemo"
ENV NEW_RELIC_LICENSE_KEY="77a8021fc5c99b0744f97b7c7d85a15efd85NRAL"
ENV NEW_RELIC_DISTRIBUTED_TRACING_ENABLED="true"
ENV NEW_RELIC_LOG_FILE_NAME="STDOUT"

CMD chmod +x /app/entrypoint.sh
ENTRYPOINT ["/app/entrypoint.sh"]
