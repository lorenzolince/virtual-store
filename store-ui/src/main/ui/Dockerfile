FROM node:lts-alpine

# install simple http server for serving static content
RUN npm install -g http-server

# make the 'app' folder the current working directory
WORKDIR /app

RUN mkdir -p dist/store/_nuxt

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY dist/static/* /app/dist/store/_nuxt/
RUN mv dist/store/_nuxt/index.html dist/store/
RUN ls /app/dist/store/_nuxt/
RUN ls /app/dist/store/

EXPOSE 8080
CMD [ "http-server", "dist" ]