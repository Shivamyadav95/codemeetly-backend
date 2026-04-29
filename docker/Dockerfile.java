FROM openjdk:21-slim

WORKDIR /app
COPY java-run.sh /app/java-run.sh
RUN chmod +x /app/java-run.sh

ENTRYPOINT ["/app/java-run.sh"]