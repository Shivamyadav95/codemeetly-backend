FROM_node:20

WORKDIR /app
COPY_js-run.sh /app/js-run.sh
RUN_chmod +x /app/js-run.sh

ENTRYPOINT ["/app/js-run.sh"]