FROM gcc:latest

WORKDIR /app
COPY c-run.sh /app/c-run.sh
RUN chmod +x /app/c-run.sh

ENTRYPOINT ["/app/c-run.sh"]