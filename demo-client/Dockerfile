FROM node:8.12.0-alpine

RUN mkdir -p /app

COPY build/ /app

RUN npm install -g http-server

EXPOSE 3000

CMD [ "http-server", "-p 3000", "/app" ]
