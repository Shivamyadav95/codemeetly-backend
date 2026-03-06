FROM gcc:latest

WORKDIR /app
COPY cpp-run.sh /app/cpp-run.sh
RUN chmod +x /app/cpp-run.sh

ENTRYPOINT ["/app/cpp-run.sh"]