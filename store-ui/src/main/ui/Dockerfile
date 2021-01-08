FROM node:12.19.0-alpine3.9
WORKDIR /app
COPY node_modules ./node_modules
COPY .nuxt ./.nuxt
COPY package.json ./
COPY dist ./dist
COPY nuxt.config.js ./
RUN ls 
EXPOSE 3000
CMD ["npm", "start"]